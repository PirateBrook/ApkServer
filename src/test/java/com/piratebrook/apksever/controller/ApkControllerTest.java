package com.piratebrook.apksever.controller;

import com.piratebrook.apksever.dto.ApkDto;
import com.piratebrook.apksever.dto.ResultDto;
import com.piratebrook.apksever.entity.ApkEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApkControllerTest {

    @Autowired
    private ApkController apkController;

    @Test
    public void list() {
        ResultDto resultDto = apkController.list();
        Assert.assertEquals(resultDto.getCode(), Integer.valueOf(101));
    }

    @Test
    public void findLast() {
        String name = "天津e采集";
        ResultDto<ApkEntity> resultDto = apkController.findLast(name);
        Assert.assertNotNull(resultDto);
    }

    @Test
    public void uploadApk() {
        ApkDto apkDto = new ApkDto();
        apkDto.setBuildTime(new Date());
        apkDto.setChannel("official");
        apkDto.setName("天津e采集");
        apkDto.setPath("\\tj\\123455.apk");
        apkDto.setVersionCode(1);
        apkDto.setVersionName("1.0.0");
        apkDto.setCommits("init");

        ResultDto<ApkEntity> apkEntity = apkController.uploadApk(apkDto);
        Assert.assertNotNull(apkEntity.getData().getApkid());
    }

    @Test
    public void downloadApk() throws IOException {
        ResponseEntity<byte[]> responseEntity = apkController.downloadApk(1);
        byte[] max = responseEntity.getBody();
        File file = new File("apk");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(max);
    }
}