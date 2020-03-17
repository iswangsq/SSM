package com.chuyue.controller;

import com.alibaba.fastjson.JSONObject;
import com.chuyue.pojo.Dept;
import com.chuyue.service.DeptService;
import com.chuyue.utils.SysResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restful")
public class DeptController {

    private static final Logger logger = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;


    /**
     * @param page
     * @param rows
     * @return
     * 分页查询部门信息
     */
    @GetMapping("/deptsByPage/{page}/{rows}")
    public SysResult findAllDeptByPage(@PathVariable("page") int page,@PathVariable("rows") int rows) {
        List<Dept> deptList = null;

        try {
            deptList = deptService.findAllDeptByPage(page,rows);
            logger.debug("查询出的部门信息"+ deptList);
            return SysResult.ok("部门信息", "",deptList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询出的部门出现异常" + e.getMessage());
            return SysResult.error("查询错误", "", deptList);
        }
    }

    @GetMapping("/depts/{page}/{rows}")
    public SysResult findAllDept(@PathVariable("page") int page,@PathVariable("rows") int rows) {
        List<Dept> deptList = null;

        try {
            deptList = deptService.findAllDept(page,rows);
            logger.debug("查询出的部门信息"+ deptList);
            return SysResult.ok("部门信息", "",deptList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询出的部门出现异常" + e.getMessage());
            return SysResult.error("查询错误", "", deptList);
        }
    }

    @PutMapping("/dept")
    public SysResult updateDeptByIds(@RequestBody JSONObject jsonObject) {
        //1.判断是否为空
        if (jsonObject == null) {
            logger.error("参数不正确");
            return SysResult.error("参数不正确", "", jsonObject);
        }
        //2.更新状态
        int result = 0;

        try {
            result = deptService.updateDeptByIds(jsonObject.getString("ids"), jsonObject.getString("status"));

            if (result > 0 ){
                return SysResult.ok("更新状态成功","",result);
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更新状态失败","",result);
            return SysResult.error("更新状态失败","",result);
        }
    }



//    {
//        "dept_id":"100600",
//        "dept_name":"后勤部",
//        "state":0,
//        "parentDept":{
//                "dept_id":"100"
//
//                }
//    }
//参数校验
    @PostMapping("/dept")
    public SysResult inertDept(@RequestBody @Valid Dept dept, BindingResult result) {
        StringBuilder stringBuilder = new StringBuilder();
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                stringBuilder.append(error.getDefaultMessage()).append(";");
            }
            logger.error("参数校验错误:" + stringBuilder.toString());
            return SysResult.error("参数校验错误","",stringBuilder.toString());
        }
        //TODO
        //插入错误
        System.out.println(dept);
        return null;

    }

}
