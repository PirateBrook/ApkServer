/**
 * FileName: VideoInfoEntity
 * Author:   Administrator
 * Date:     2018/9/30 15:12
 */
package com.piratebrook.apksever.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Administrator
 * @create 2018/9/30
 */
@Entity
@Table(name = "videoinfo")
@Data
public class VideoInfoEntity {

    @Id
    String videoId;

    @Column(name = "videoname")
    String videoName;

    String path;
}
