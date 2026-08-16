package com.example.mapper;

import com.example.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

public interface EmployeeinfoMapper extends BaseMapper<Employeeinfo> {

    /**
      * 查询所有
    */
    List<Employeeinfo> selectAll(Employeeinfo employeeinfo);

    /**
      * 根据ID查询
    */
    Employeeinfo selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);



}