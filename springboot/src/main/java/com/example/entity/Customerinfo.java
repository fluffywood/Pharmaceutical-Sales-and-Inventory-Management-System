package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 客户信息
 */
@Data
@TableName("customerinfo")
public class Customerinfo  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 客户姓名 */
	@Alias("客户姓名")
	private String customerName;
	/** 客户性别 */
	@Alias("客户性别")
	private String customerSex;
	/** 客户生日 */
	@Alias("客户生日")
	private String customerBirth;
	/** 联系方式 */
	@Alias("联系方式")
	private String customerPhone;
	/** 身份证号 */
	@Alias("身份证号")
	private String customerIdcard;



}
