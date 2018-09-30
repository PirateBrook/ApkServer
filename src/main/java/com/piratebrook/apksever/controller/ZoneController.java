/**
 * FileName: ZoneController
 * Author:   Administrator
 * Date:     2018/9/3 15:25
 */
package com.piratebrook.apksever.controller;

import com.piratebrook.apksever.LogicError;
import com.piratebrook.apksever.dto.ResultDto;
import com.piratebrook.apksever.entity.Zoning;
import com.piratebrook.apksever.service.ZoneService;
import com.piratebrook.apksever.utils.ResultDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 * @create 2018/9/3
 */
@RestController
@RequestMapping("/zone")
public class ZoneController {

    @Autowired
    ZoneService zoneService;

    @GetMapping("/collect")
    public ResultDto collect() throws IOException {
        List<Zoning> zoningList = zoneService.findAll();
        if (CollectionUtils.isEmpty(zoningList)) {
            return ResultDtoUtil.error(LogicError.NoData);
        }
        return ResultDtoUtil.success(zoningList);
    }
}
