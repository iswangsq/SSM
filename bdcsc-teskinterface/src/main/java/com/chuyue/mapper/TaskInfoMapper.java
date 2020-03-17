package com.chuyue.mapper;

import com.chuyue.pojo.TaskInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskInfoMapper  {

    @Insert("insert into task_infos values(#{taskId},#{taskType},#{taskInfo},#{priority},#{userId},#{status},#{takes},#{createDate},#{updateDate})")
    public int setTaskinfo(TaskInfo taskInfo);

    @Select("select * from task_infos where userId = #{userId}")
    List<TaskInfo> finadAllTaskInfoByUserId(String userId);

    //获取任务
    @Select("select * from task_infos where taskType = #{modelTag} and takes = 0 and status = 0 ")
    List<TaskInfo> getTaskInfoByModelTag4Java(String modelTag);

    //更新任务takes信息为1，被模型层取到了
    @Update("update task_infos set takes = 1 where taskId = #{taskId}")
    public void updataTaskInfoOfTakeByTId(String taskId);

    //做二次排序
    //第一次排序根据优先级，由大到小排序 5优先级最高 1最低
    //第二次排序，如果优先级一样 创建时间越早越优先
    //+++++++++++++++++++++++++++++++++
    //只返回take=0字段  还没有被模型层拉取
    //只返回status为完成的任务
    //+++++++++++++++++++++++++++++++++
    //为什么不给模型层返回失败的任务重写执行呢？
    //失败的任务需要用户手动确认，不然每次失败后的任务都被模型层拉取的话，这个任务一直在占用资源
    //而且新的下发任务永远跑不了
    @Select("select * from task_infos where taskType = #{modelTag} and takes = 0 and status = 0 order by priority desc,createDate limit 1" )
    TaskInfo getTaskInfoByModelTag(String modelTag);

    //更新任务状态
    @Update("update task_infos set status = 1 where taskId = #{taskId}")
    int updateTaskStatusByTaskId(String taskId);
}
