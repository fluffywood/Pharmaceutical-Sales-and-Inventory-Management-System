package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 销售信息
 */
@Data
@TableName("saleinfo")
public class Saleinfo  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 药品编号 */
	@TableField(exist = false)
	private String drugCode;
	/** 药品名称 */
	@TableField(exist = false)
	private String drugName;
	/** 售价 */
	private Double salePrice;
	/** 销售数量 */
	private Integer saleNum;
	/** 退货申请 */
	private String returnApply;
	/** 操作状态 */
	private String returnPos;
	/** 退货物流 */
	private String returnTransport;
	/** 药品信息Id */
	private Integer druginfoId;



}
