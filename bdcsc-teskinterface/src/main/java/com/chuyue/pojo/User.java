package com.chuyue.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name ="user")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User extends  BaseEntity {

    @Id
    private String userId;

    @Column
    @NotBlank(message = "usrename不能为空")
    private String username;

    @Column
    @NotBlank(message = "password不能为空")
    private String password;

    @Column
    @Min(value = 0,message = "只能传0")
    @Max(value = 0,message = "只能传0")
    private  Integer state;

    @Column(name = "dept_id")
    @NotBlank(message = "deptId不能为空")
    @JsonProperty("dept_id")
    private  String deptId;

}
