/**
 * FileName: ApkService
 * Author:   Administrator
 * Date:     2018/8/1 17:10
 */
package com.piratebrook.apksever.service;

import com.piratebrook.apksever.entity.ApkEntity;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Administrator
 * @create 2018/8/1
 */
public interface ApkService {

    Optional<ApkEntity> findOne(Integer apkid);

    Stream<ApkEntity> findUpAll();

    Optional<ApkEntity> findLast(String apkName);

    ApkEntity save(ApkEntity entity);
}
