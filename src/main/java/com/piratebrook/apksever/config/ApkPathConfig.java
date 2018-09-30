/**
 * FileName: ProjectPathConfig
 * Author:   Administrator
 * Date:     2018/8/2 11:00
 */
package com.piratebrook.apksever.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @create 2018/8/2
 */
@Data
@ConfigurationProperties(prefix = "apkpath", ignoreUnknownFields = true)
@Component
public class ApkPathConfig {

    private String apkRootPath;
}
