package com.example.mapper;

import com.example.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
      * 查询所有
    */
    List<Employee> selectAll(Employee employee);

    /**
      * 根据ID查询
    */
    Employee selectById(Integer id);

    /**
      * 删除
    */
    int deleteById(Integer id);

	@Select("select * from employee where `username` = #{name}")
	Employee selectByUsername(@Param("name") String userName);



}