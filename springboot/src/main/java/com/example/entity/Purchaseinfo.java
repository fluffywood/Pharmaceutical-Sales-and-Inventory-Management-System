package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 进货信息
 */
@Data
@TableName("purchaseinfo")
public class Purchaseinfo  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 药品编号 */
	@TableField(exist = false)
	private String drugCode;
	/** 药品名称 */
	@TableField(exist = false)
	private String drugName;
	/** 供应价格 */
	private Double supplyPrice;
	/** 进货数量 */
	private Integer supplyNum;
	/** 供应商 */
	@TableField(exist = false)
	private String drugSupplier;
	/** 保质期 */
	private String drugDate;
	/** 进货负责人 */
	@TableField(exist = false)
	private String purchaseMan;
	/** 进货时间 */
	private String purchaseTime;
	/** 进货物流 */
	private String purchaseTransport;
	/** 供应商信息Id */
	private Integer supplierinfoId;
	/** 员工信息Id */
	private Integer employeeinfoId;
	/** 药品信息Id */
	private Integer druginfoId;



}
