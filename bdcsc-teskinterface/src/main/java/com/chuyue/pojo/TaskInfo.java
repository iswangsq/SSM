package com.chuyue.pojo;

import com.chuyue.utils.DateUtil;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 属性类
 */
@Getter
@Setter
@NoArgsConstructor
public class TaskInfo implements Comparable<TaskInfo>{

    private String taskId;
    private String taskType;
    private String taskInfo;
    private Integer priority;
    private String userId;
    private Integer status;
    private Integer takes;
    private Date createDate;
    private Date updateDate;


    @Override
    public int compareTo(TaskInfo taskInfo2) {
        if (this.getPriority() > taskInfo2.getPriority()) {
            //taskInfo1 优先级高
            return -1;
        } else if (this.getPriority() < taskInfo2.getPriority()) {
            //taskInfo2优先级高
            return 1;
        } else {
            //优先级相同 则判断时间
            if (DateUtil.compareDate(this.getCreateDate(), taskInfo2.getCreateDate())) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
