package com.chuyue.pojo;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dept extends BaseEntity{


    //数据类型全部用包装类型
    @NotBlank(message = "部门id不能为空")
    private String deptId;  //部门id号
    private Dept parentDept;//上级部门   一对一封装
    @NotBlank(message = "部门名称不能为空")
    private String deptName;	//部门名称
    @Max(value = 0,message = "state必须为0")
    @Min(value = 0,message = "state必须为0")
    private Integer state;	//部门状态  0表示停用   1表示启用




}
