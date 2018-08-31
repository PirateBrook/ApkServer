/**
 * FileName: ResultDto
 * Author:   Administrator
 * Date:     2018/8/1 17:37
 */
package com.piratebrook.apksever.dto;

import lombok.Data;

/**
 * @author Administrator
 * @create 2018/8/1
 */
@Data
public class ResultDto<T> {

    private Integer code;

    private String msg;

    private T data;
}
