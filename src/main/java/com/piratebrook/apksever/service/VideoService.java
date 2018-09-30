package com.piratebrook.apksever.service;

import com.piratebrook.apksever.entity.VideoInfoEntity;

import java.util.List;

public interface VideoService {

    List<VideoInfoEntity> getAll();

    void prepareData();
}
