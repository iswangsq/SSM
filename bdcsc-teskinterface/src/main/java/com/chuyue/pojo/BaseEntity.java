package com.chuyue.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

//抽象类
@Getter
@Setter
//JPA中如果如父类需要在父类加@MappedSuperclass注解 不然父类属性注入不进去
@MappedSuperclass
public abstract class BaseEntity implements Serializable{

	@Column(name = "create_by")
	private String createBy;
	@Column(name = "create_dept")
	private String createDept;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "update_by")
	private String updateBy;
	@Column(name = "update_time")
	private Date updateTime;

	
}
