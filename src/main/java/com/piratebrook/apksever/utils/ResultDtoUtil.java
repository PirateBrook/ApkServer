/**
 * FileName: ResultDtoUtil
 * Author:   Administrator
 * Date:     2018/8/1 17:41
 */
package com.piratebrook.apksever.utils;

import com.piratebrook.apksever.LogicError;
import com.piratebrook.apksever.dto.ResultDto;

/**
 * @author Administrator
 * @create 2018/8/1
 */
public class ResultDtoUtil {

    public static ResultDto success(Object value) {
        ResultDto resultDto = new ResultDto();
        resultDto.setData(value);
        resultDto.setCode(0);
        resultDto.setMsg("success");
        return resultDto;
    }

    public static ResultDto success() {
        return success(null);
    }

    public static ResultDto error(LogicError error) {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(error.getCode());
        resultDto.setMsg(error.getMsg());
        return resultDto;
    }
}
