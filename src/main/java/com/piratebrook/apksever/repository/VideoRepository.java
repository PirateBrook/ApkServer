package com.piratebrook.apksever.repository;

import com.piratebrook.apksever.entity.VideoInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<VideoInfoEntity, Integer> {

}
