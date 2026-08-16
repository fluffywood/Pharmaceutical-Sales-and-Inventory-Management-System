package com.example.entity;

import cn.hutool.core.annotation.Alias;
import java.util.List;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 销售报表
 */
@Data
@TableName("sale_reporter")
public class SaleReporter  {

    /** id */
    @TableId(type = IdType.AUTO)
    private Integer id;
	/** 销售报表提交 */
	private String saleReport;
	/** 销售报表截图 */
	private String salePic;



}
