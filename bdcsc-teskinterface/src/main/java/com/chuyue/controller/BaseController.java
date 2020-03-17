package com.chuyue.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseController {
    @InitBinder
    //此方法用于日期的转换，如果未加，当页面日期格式转换错误，将报400错误，实际是因为此方法
    //如果添加该日期转化格式，则时间必须为yyyy-MM-dd否则会报400错误
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
