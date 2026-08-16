package com.example.mapper;

import com.example.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

public interface PurchaseReporterMapper extends BaseMapper<PurchaseReporter> {

    /**
      * 查询所有
    */
    List<PurchaseReporter> selectAll(PurchaseReporter purchaseReporter);

    /**
      * 根据ID查询
    */
    PurchaseReporter selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);



}