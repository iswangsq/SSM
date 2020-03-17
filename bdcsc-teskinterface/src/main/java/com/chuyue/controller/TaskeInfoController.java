package com.chuyue.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chuyue.config.ModelTagConfig;
import com.chuyue.pojo.TaskInfo;
import com.chuyue.service.TaskInfoService;
import com.chuyue.utils.Base64Utils;
import com.chuyue.utils.DateUtil;
import com.chuyue.utils.SysResult;
import org.aspectj.weaver.tools.Trace;
import org.hibernate.validator.constraints.NotEmpty;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 与BDCSC和模型层交互的接口
 */

@RestController
public class TaskeInfoController {

    private static final Logger logger = LoggerFactory.getLogger(TaskeInfoController.class);

    @Autowired
    private TaskInfoService taskInfoService;

    @Autowired
    private ModelTagConfig modelTagConfig;


    /**
     * 注入spring环境
     */
//    @Autowired
//    private Environment environment;

    /**
     * @param jsonObject  bdcsc传递过来的参数
     * @return SysResult
     * 下发任务接口
     */
    @PostMapping(value = "/restful/task/setTaskInterface", produces = "application/json;charset=UTF-8")
    public SysResult setTakeInterface(@RequestBody @NotEmpty(message = "下发参数不能为空") JSONObject jsonObject) {
        if (jsonObject == null) {
            logger.error("用户信息为null");
            return SysResult.error("下发参数为null", "", jsonObject);
        }
        TaskInfo taskInfo = null;
        try {

            // key 不对 所以取到了null 这样就知道问题在哪里了
            byte[] bytes = Base64Utils.decodeBase64(jsonObject.getString("taskDtails"));//key 为前台传过来的参数名称 要一致 否则取不到
            taskInfo = JSON.parseObject(bytes, TaskInfo.class);
            logger.info("下发任务参数为" + taskInfo);

            int result = taskInfoService.setTask(taskInfo);
            if (result > 0) {
                return SysResult.ok("下发参数成功", "", jsonObject);
            } else {
                throw new RuntimeException("插入数据库失败");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("下发任务失败");
            return SysResult.error("下发任务失败", "", jsonObject);
        }
    }


    /**
     * @param userId 用户id
     * @return Reqesult
     * 根据用户Id查询其下发的任务信息
     */
    @GetMapping("/restful/test/{userId}")
    public SysResult findAllTaskInfo(@PathVariable(value = "userId", required = true) String userId) {

        List<TaskInfo> taskInfoList = null;
        //查询userid的任务信息
        try {
            taskInfoList = taskInfoService.finadAllTaskInfoByUserId(userId);
            return SysResult.ok("任务信息", "", taskInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询信息出现异常，异常用户ID：" + userId);
            return SysResult.error("查询信息失败", "", taskInfoList);
        }


    }

    /**
     * @param modelTag
     * @return
     * 模型层主动获取任务
     */
    @GetMapping("/restful/task/{modelTag}")//pop-analysis city-analysis
    public SysResult getGetTaskInfoByModelTag(@PathVariable(value = "modelTag", required = true) String modelTag) {

        String modelTags = modelTagConfig.getTag();
        List<String> modelTagList = Arrays.asList(modelTags.split(","));
        logger.debug("modelTag config" + modelTag);

        if (!modelTagList.contains(modelTag)) {

            logger.info("出现未知modeTag:" + modelTag);
            return SysResult.error("模型不存在", "", modelTag);
        }
        TaskInfo taskInfo = null;
        try {
            taskInfo = taskInfoService.getTaskInfoByModelTag(modelTag);
            return SysResult.ok("模型信息", "", taskInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("modeltag获取信息出现异常，异常tag：" + modelTag);
            return SysResult.error("获取信息失败", "", taskInfo);
        }
    }

    /**
     * @param taskId
     * @return
     * 更新任务状态
     */
    @PutMapping("/restful/task/{taskId}")
    public SysResult updateTaskStatusByTaskId(@PathVariable(value = "taskId",required = true) String taskId) {

        int result = -1;
        Map<String, Integer> data = new HashMap<>();

        try {
            result = taskInfoService.updateTaskStatusByTaskId(taskId);
            if(result > 0) {
                logger.info("状态更新成功，taskId" + taskId);
                data.put("value", 1);
                return SysResult.ok("任务状态更新成功", "", taskId);
            } else {
                throw new RuntimeException();
            }

        } catch (Exception e) {
            logger.error("任务更新失败" + e.getMessage());
            e.printStackTrace();
            data.put("value", 0);
            return SysResult.error("任务更新失败", "", data);
        }
    }
}
