package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 药品信息
 */
@Data
@TableName("druginfo")
public class Druginfo  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 药品编号 */
	@Alias("药品编号")
	private Integer drugCode;
	/** 药品名称 */
	@Alias("药品名称")
	private String drugName;
	/** 药品性质 */
	@Alias("药品性质")
	private String drugScript;
	/** 供应价格 */
	@TableField(exist = false)
	private String supplyPrice;
	/** 售价 */
	@TableField(exist = false)
	private String salePrice;
	/** 供应商 */
	@TableField(exist = false)
	private String drugSupplier;
	/** 保质期 */
	@TableField(exist = false)
	private String drugDate;
	/** 销售信息Id */
	private Integer saleinfoId;
	/** 供应商信息Id */
	private Integer supplierinfoId;
	/** 进货信息Id */
	private Integer purchaseinfoId;



}
