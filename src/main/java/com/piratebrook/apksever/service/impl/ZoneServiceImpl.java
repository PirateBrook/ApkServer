/**
 * FileName: ZoneServiceImpl
 * Author:   Administrator
 * Date:     2018/9/3 15:19
 */
package com.piratebrook.apksever.service.impl;

import com.piratebrook.apksever.entity.Zoning;
import com.piratebrook.apksever.repository.ZoneRepository;
import com.piratebrook.apksever.service.ZoneService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;

/**
 * @author Administrator
 * @create 2018/9/3
 */
@Service
@Slf4j
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    ZoneRepository repository;

    static final String baseUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/14.html";
    static final String rootUrl = baseUrl;
    int success;
    int failure;
    HashSet<String> failureUrls = new HashSet<>();
    enum Type {
        City,
        C,
        T,
        V,
    }

    @Override
    public Zoning findOne(Integer id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<Zoning> findAll() throws IOException {
        success = 0;
        failure = 0;
        failureUrls.clear();
        Document html = Jsoup.connect(rootUrl).get();
        saveAllFromRoot(html);
        System.out.println("成功：" + success + "   失败：" + failure);
        return repository.findAll();
    }

    private void saveAllFromRoot(Document content) {
        if (content == null) {
            return;
        }
        success++;
        Elements elements = content.getElementsByClass("citytr");
        String parentUrl = content.baseUri();
        HashSet<String> childUrl = new HashSet<>();
        Type type = Type.City;
        if (CollectionUtils.isEmpty(elements)) {
            elements = content.getElementsByClass("countytr");
            type = Type.C;
        }
        if (CollectionUtils.isEmpty(elements)) {
            elements = content.getElementsByClass("towntr");
            type = Type.T;
        }

        if (CollectionUtils.isEmpty(elements)) {
            elements = content.getElementsByClass("villagetr");
            type = Type.V;
        }

        if (CollectionUtils.isEmpty(elements)) {
            return;
        }

        for (Element e : elements) {
            String code = e.child(0).text();
            String value = e.child(1).text();
            String townCode = "";
            String parent = "";
            if (type == Type.V) {
                value = e.child(2).text();
                townCode = e.child(1).text();
            }

            Zoning zoning = new Zoning();
            zoning.setCode(code);
            zoning.setValue(value);
            zoning.setTownCode(townCode);
            insertOne(zoning);
        }

        Elements links = content.getElementsByTag("a");

        if (CollectionUtils.isEmpty(links)) {
            return;
        }

        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkText = link.text();
            if (linkHref.contains("www.miibeian.gov.cn")) {
                break;
            }
            if (linkHref != null) {
                int index = parentUrl.lastIndexOf("/");
                String realUrl = parentUrl.substring(0, index + 1);
                childUrl.add(realUrl + linkHref);
            }
        }

//linkHref        childUrl.parallelStream().forEach(new Consumer<String>() {
//            @Override
//            public void accept(String child) {
//                Document html = null;
//                try {
//                    html = Jsoup.connect(child).get();
//                } catch (SocketTimeoutException e) {
//
//                } catch (IOException e) {
//                    System.out.println(child);
//                    e.printStackTrace();
//                }
//                saveAllFromRoot(html);
//            }
//        });

        for (String child : childUrl) {
            Document html = null;
            try {
//                html = Jsoup.connect(child)
//                        .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
//                        .header("Connection", "close")//如果是这种方式，这里务必带上
//                        .timeout(8000)//超时时间
//                        .get();
                html = Jsoup.parse(new URL(child).openStream(), "GBK", child);
//                html = Jsoup.connect(child).timeout(60 * 1000).get();
            } catch (SocketTimeoutException e) {
                failure++;
                failureUrls.add(child);
                log.info("超时地址：" + child);
            } catch (IOException e) {
                failure++;
                failureUrls.add(child);
                log.info("错误地址：" + child);
                e.printStackTrace();
            }
            saveAllFromRoot(html);
        }
    }

    @Override
    public void insertOne(Zoning zoning) {
        repository.save(zoning);
    }
}
