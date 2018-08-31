package com.piratebrook.apksever.repository;

import com.piratebrook.apksever.entity.ApkEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApkRepository extends JpaRepository<ApkEntity, Integer> {

    Optional<ApkEntity> findByApkid(Integer integer);

    @Override
    List<ApkEntity> findAll();

    @Override
    List<ApkEntity> findAll(Sort sort);

    List<ApkEntity> findAllByName(String name, Sort sort);
}

