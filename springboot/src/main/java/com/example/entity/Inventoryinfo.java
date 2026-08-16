package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 库房管理
 */
@Data
@TableName("inventoryinfo")
public class Inventoryinfo  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 药品编号 */
	@TableField(exist = false)
	private String drugCode;
	/** 药品名称 */
	@TableField(exist = false)
	private String drugName;
	/** 库存信息 */
	private Integer drugNum;
	/** 进货数量 */
	@TableField(exist = false)
	private String supplyNum;
	/** 进货物流 */
	@TableField(exist = false)
	private String purchaseTransport;
	/** 销售数量 */
	@TableField(exist = false)
	private String saleNum;
	/** 退货操作状态 */
	@TableField(exist = false)
	private String returnPos;
	/** 退货物流 */
	@TableField(exist = false)
	private String returnTransport;
	/** 销售信息Id */
	private Integer saleinfoId;
	/** 进货信息Id */
	private Integer purchaseinfoId;
	/** 药品信息Id */
	private Integer druginfoId;



}
