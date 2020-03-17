package com.chuyue.service;

import com.chuyue.mapper.TaskInfoMapper;
import com.chuyue.pojo.TaskInfo;
import com.chuyue.utils.DateUtil;
import com.chuyue.utils.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TaskInfoServiceImpl implements TaskInfoService {

    @Autowired
    public TaskInfoMapper taskInfoMapper;


    //事务标签 在RuntiomException 或者Error 错误下回滚
    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public int setTask(TaskInfo taskInfo) {

        taskInfo.setTakes(0);                                    //代表任务还未被模型层取走
        taskInfo.setStatus(0);                                  //代表任务还未完成
        taskInfo.setCreateDate(new Date());                    //任务的创建时间
        taskInfo.setUpdateDate(taskInfo.getCreateDate());     //任务的更新时间
        return taskInfoMapper.setTaskinfo(taskInfo);
    }

    @Override
    public List<TaskInfo> finadAllTaskInfoByUserId(String userId) {
        return taskInfoMapper.finadAllTaskInfoByUserId(userId);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
//        @Override
//        public TaskInfo getTaskInfoByModelTag(String modelTag) {
//
//            TaskInfo taskinfo = taskInfoMapper.getTaskInfoByModelTag(modelTag);
//            taskInfoMapper.updataTaskInfoOfTakeByTId(taskinfo.getTaskId());
//            return taskinfo;
//        }

//    @Override
//    public TaskInfo getTaskInfoByModelTag(String modelTag) {
//        long start_sql = System.currentTimeMillis();
//        //利用java实现
//        List<TaskInfo> taskList = taskInfoMapper.getTaskInfoByModelTag4Java(modelTag);
//
//        long end_sql = System.currentTimeMillis();
//        System.out.println("sql执行的时间" + (end_sql - start_sql) / 1000);
//        List<TaskInfo> tmpList1 = new ArrayList<>();
//        for (TaskInfo taskInfo : taskList) {
//            if (taskInfo.getStatus() == 0 && taskInfo.getTakes() == 0) {
//                tmpList1.add(taskInfo);
//
//            }
//        }
//        tmpList1.sort(new Comparator<TaskInfo>() {
//            @Override
//            public int compare(TaskInfo taskInfo1, TaskInfo taskInfo2) {
//
//                //二次排序
//                //判断优先级，优先级越高越优先
//                //如果优先级相同，判断创建时间，创建时间越早越优先
//                if (taskInfo1.getPriority() > taskInfo2.getPriority()) {
//                    //taskInfo1 优先级高
//                    return -1;
//                } else if (taskInfo1.getPriority() < taskInfo2.getPriority()) {
//                    //taskInfo2优先级高
//                    return 1;
//                } else {
//                    //优先级相同 则判断时间
//                    if (DateUtil.compareDate(taskInfo1.getCreateDate(), taskInfo2.getCreateDate())) {
//                        return -1;
//                    } else {
//                        return 1;
//                    }
//                }
//
//            }
//        });
//        TaskInfo taskInfo = null;
//        if (!tmpList1.isEmpty()) {
//            taskInfo = tmpList1.get(0);
//            taskInfoMapper.updataTaskInfoOfTakeByTId(taskInfo.getTaskId());
//        }
//        long end_java = System.currentTimeMillis();
//
//        System.out.println("java执行的时间" + (end_java - end_sql) / 1000);
//
//        return taskInfo;//返回模型层的任务
//
//    }


//    @Override
    public TaskInfo getTaskInfoByModelTag(String modelTag) {

        long start_sql = System.currentTimeMillis();
        //获得任务
        List<TaskInfo> taskList = taskInfoMapper.getTaskInfoByModelTag4Java(modelTag);

        long end_sql = System.currentTimeMillis();

        Optional<TaskInfo> taskInfoOp = taskList.stream()
//                .filter(taskInfo -> taskInfo.getStatus() == 0)
//                .filter(taskInfo -> taskInfo.getTakes() == 0)
                .sorted()
                .findFirst();



        TaskInfo taskInfo = taskInfoOp.get();
        //取到任务后 更新任务takes信息为1
        taskInfoMapper.updataTaskInfoOfTakeByTId(taskInfo.getTaskId());

        long end_java = System.currentTimeMillis();
        System.out.println("sql执行的时间" + (end_sql - start_sql) / 1000);
        System.out.println("java执行的时间" + (end_java - end_sql) / 1000);

        return taskInfo;
    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    @Override
    public int updateTaskStatusByTaskId(String taskId) {
        return taskInfoMapper.updateTaskStatusByTaskId(taskId);
    }


}
