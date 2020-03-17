package com.chuyue.service;

import com.chuyue.pojo.TaskInfo;

import java.util.List;

public interface TaskInfoService {


    /**
     * 任务下发
     * @param taskInfo
     * @return
     */
    public int setTask(TaskInfo taskInfo);


    /**
     * 根据用户id查询其任务信息
     * @param userId
     * @return
     */
    List<TaskInfo> finadAllTaskInfoByUserId(String userId);


    /**
     * 根据modeltag获取任务信息 总是获取优先级最高的，优先级相同则创建时间越早越优先
     * @param modelTag
     * @return
     */
    TaskInfo getTaskInfoByModelTag(String modelTag);


    /**
     * 模型层完成任务后更新状态
     * @param taskId
     * @return
     */
    int updateTaskStatusByTaskId(String taskId);
}
