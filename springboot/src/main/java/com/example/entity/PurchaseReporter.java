package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 进货报表
 */
@Data
@TableName("purchase_reporter")
public class PurchaseReporter  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 进货报表提交 */
	private String purchaseReport;
	/** 进货报表截图 */
	private String purchasePic;



}
