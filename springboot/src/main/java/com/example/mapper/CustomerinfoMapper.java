package com.example.mapper;

import com.example.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

public interface CustomerinfoMapper extends BaseMapper<Customerinfo> {

    /**
      * 查询所有
    */
    List<Customerinfo> selectAll(Customerinfo customerinfo);

    /**
      * 根据ID查询
    */
    Customerinfo selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);



}