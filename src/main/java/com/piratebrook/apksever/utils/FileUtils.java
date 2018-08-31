/**
 * FileName: FileUtils
 * Author:   Administrator
 * Date:     2018/8/2 10:57
 */
package com.piratebrook.apksever.utils;

import com.piratebrook.apksever.config.ApkPathConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Administrator
 * @create 2018/8/2
 */
@Component
public class FileUtils {

    @Autowired
    private ApkPathConfig pathConfig;

    public byte[] load(String fileName) {
        String fp = getFileUrl() + File.separator + fileName;
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(fp);
            byte[] content = new byte[fi.available()];
            fi.read(content, 0, fi.available());
            return content;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fi != null)
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    private String getFileUrl() {
        return pathConfig.getApkRootPath();
    }
}
