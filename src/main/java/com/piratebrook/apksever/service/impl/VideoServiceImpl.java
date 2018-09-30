/**
 * FileName: VideoServiceImpl
 * Author:   Administrator
 * Date:     2018/9/30 15:15
 */
package com.piratebrook.apksever.service.impl;

import com.piratebrook.apksever.entity.VideoInfoEntity;
import com.piratebrook.apksever.repository.VideoRepository;
import com.piratebrook.apksever.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 * @create 2018/9/30
 */
@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoRepository videoRepository;

    private String rootUrl = "http://www.kpd51.com/";

    @Override
    public List<VideoInfoEntity> getAll() {
        return videoRepository.findAll();
    }

    @Override
    public void prepareData() {
        try {
            Document html = Jsoup.connect(rootUrl).get();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
