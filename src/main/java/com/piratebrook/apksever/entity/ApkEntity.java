/**
 * FileName: ApkEntity
 * Author:   Administrator
 * Date:     2018/8/1 16:45
 */
package com.piratebrook.apksever.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Administrator
 * @create 2018/8/1
 */
@Entity
@Table(name = "apk")
@Data
public class ApkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer apkid;

    private String name;

    private String channel;

    private String path;

    @Column(name = "versioncode")
    private int versionCode;

    @Column(name = "versionname")
    private String versionName;

    @Column(name = "buildtime")
    private Date buildTime;

    private String commits;
}
