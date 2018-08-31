/**
 * FileName: ApkController
 * Author:   Administrator
 * Date:     2018/8/1 17:30
 */
package com.piratebrook.apksever.controller;

import com.piratebrook.apksever.LogicError;
import com.piratebrook.apksever.dto.ApkDto;
import com.piratebrook.apksever.dto.ResultDto;
import com.piratebrook.apksever.entity.ApkEntity;
import com.piratebrook.apksever.service.ApkService;
import com.piratebrook.apksever.utils.FileUtils;
import com.piratebrook.apksever.utils.ResultDtoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @create 2018/8/1
 */
@RestController
@RequestMapping("/apk")
public class ApkController {

    @Autowired
    private ApkService apkService;

    @Autowired
    private FileUtils fileUtils;

    @GetMapping("/list")
    public ResultDto list() {
        List<ApkEntity> apkEntityList = apkService.findUpAll().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(apkEntityList)) {
            return ResultDtoUtil.error(LogicError.NoData);
        }
        return ResultDtoUtil.success(apkEntityList);
    }

    @GetMapping("/findLast")
    public ResultDto findLast(@RequestParam("apkName") String apkName) {
        Optional<ApkEntity> apkEntity = apkService.findLast(apkName);
        if (apkEntity.isPresent()) {
            return ResultDtoUtil.success(apkEntity.get());
        }
        return ResultDtoUtil.error(LogicError.NoData);
    }

    @PostMapping("/uploadApk")
    public ResultDto uploadApk(@RequestBody ApkDto apkDto) {
        ApkEntity apkEntity = new ApkEntity();
        BeanUtils.copyProperties(apkDto, apkEntity);
        ApkEntity result = apkService.save(apkEntity);
        return ResultDtoUtil.success(result);
    }

    @GetMapping("/downloadApk")
    public ResponseEntity<byte[]> downloadApk(@RequestParam int apkId) {
        Optional<ApkEntity> apkEntity = apkService.findOne(apkId);
        if (apkEntity.isPresent()) {
            ApkEntity entity = apkEntity.get();
            String path = entity.getPath();
            byte[] content = fileUtils.load(path);
            HttpHeaders headers = new HttpHeaders();
            headers.setAcceptCharset(Arrays.asList(Charset.forName("utf-8")));
            headers.add("Content-Length", String.valueOf(content.length));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", apkId + ".apk");
            return new ResponseEntity<byte[]>(content, headers, HttpStatus.CREATED);
        }
        return null;
    }
}
