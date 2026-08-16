package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 员工信息
 */
@Data
@TableName("employeeinfo")
public class Employeeinfo  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 员工编号 */
	@TableField(exist = false)
	private String employeeCode;
	/** 员工姓名 */
	@TableField(exist = false)
	private String employeeName;
	/** 电话 */
	@TableField(exist = false)
	private String employeePhone;
	/** 员工邮箱 */
	@TableField(exist = false)
	private String employeeEmail;
	/** 员工Id */
	private Integer employeeId;



}
