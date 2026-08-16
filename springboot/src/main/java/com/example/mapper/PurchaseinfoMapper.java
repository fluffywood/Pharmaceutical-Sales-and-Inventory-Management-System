package com.example.mapper;

import com.example.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

public interface PurchaseinfoMapper extends BaseMapper<Purchaseinfo> {

    /**
      * 查询所有
    */
    List<Purchaseinfo> selectAll(Purchaseinfo purchaseinfo);

    /**
      * 根据ID查询
    */
    Purchaseinfo selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);



}