package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 供应商信息
 */
@Data
@TableName("supplierinfo")
public class Supplierinfo  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 供应商名称 */
	@Alias("供应商名称")
	private String supplierName;
	/** 地址 */
	@Alias("地址")
	private String supplierAddress;
	/** 法人代表 */
	@Alias("法人代表")
	private String supplierLegal;
	/** 联系人 */
	@TableField(exist = false)
	private String supplierContact;
	/** 联系电话 */
	@TableField(exist = false)
	private String supplierPhone;
	/** 电子邮件 */
	@Alias("电子邮件")
	private String supplierEmail;
	/** 备注 */
	@Alias("备注")
	private String supplierRemark;
	/** 客户信息Id */
	private Integer customerinfoId;



}
