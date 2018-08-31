/**
 * FileName: ApkServiceImpl
 * Author:   Administrator
 * Date:     2018/8/1 17:13
 */
package com.piratebrook.apksever.service.impl;

import com.piratebrook.apksever.entity.ApkEntity;
import com.piratebrook.apksever.repository.ApkRepository;
import com.piratebrook.apksever.service.ApkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Administrator
 * @create 2018/8/1
 */
@Service
@Slf4j
public class ApkServiceImpl implements ApkService {

    @Autowired
    private ApkRepository repository;

    @Override
    public Optional<ApkEntity> findOne(Integer apkid) {
        return repository.findByApkid(apkid);
    }

    @Override
    public Stream<ApkEntity> findUpAll() {
        Sort sort = new Sort(Sort.Direction.DESC, "versionCode");
        return repository.findAll(sort).stream();
    }

    @Override
    public Optional<ApkEntity> findLast(String apkName) {
        Sort sort = new Sort(Sort.Direction.DESC, "versionCode");
        List<ApkEntity> apkEntityList = repository.findAllByName(apkName, sort);
        ApkEntity apkEntity = null;
        if (apkEntityList != null) {
            apkEntity = apkEntityList.get(0);
        }
        return Optional.ofNullable(apkEntity);
    }

    @Override
    public ApkEntity save(ApkEntity entity) {
        return repository.save(entity);
    }
}
