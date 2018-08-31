/**
 * FileName: ApkDto
 * Author:   Administrator
 * Date:     2018/8/2 9:53
 */
package com.piratebrook.apksever.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 * @create 2018/8/2
 */
@Data
public class ApkDto {

    private String name;

    private String channel;

    private String path;

    private int versionCode;

    private String versionName;

    private Date buildTime;

    private String commits;
}
