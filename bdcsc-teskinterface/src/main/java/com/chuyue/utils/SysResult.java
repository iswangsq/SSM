package com.chuyue.utils;

import com.chuyue.pojo.Dept;
import lombok.*;

import java.util.Date;


/**
 * 状态信息类
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysResult {

    private Integer code;
    private String status;
    private String message;
    private String time;
    private String trace;
    private Object data;

    private static final Integer SUCCESS_CODE = 200;
    private static final Integer ERROR_CODE = 400;
    private static final String SUCCESSFUL = "SUCCESS";
    private static final String ERROR = "error";

    public  static SysResult ok(String message,String trace,Object data) {
        return new SysResult(SUCCESS_CODE, SUCCESSFUL, message, DateUtil.formatDate_HMS(new Date()), trace, data);
    }

    /**
     * @param message  返回信息
     * @param trace 预留字段
     * @param data 日期
     * @return
     */
    public  static SysResult error(String message,String trace,Object data) {
        return new SysResult(ERROR_CODE, ERROR, message, DateUtil.formatDate_HMS(new Date()), trace, data);
    }



}
