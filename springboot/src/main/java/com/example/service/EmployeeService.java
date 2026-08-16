package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.*;
import com.example.exception.CustomException;
import com.example.mapper.*;
import com.example.common.config.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 新增
     */
    public void add(Employee employee) {
        // 唯一校验
        Employee dbEmployee = employeeMapper.selectByUsername(employee.getUsername());
        if (ObjectUtil.isNotNull(dbEmployee)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(employee.getPassword())) {
            employee.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(employee.getName())) {
            employee.setName(employee.getUsername());
        }
        employee.setRole("employee");
        employeeMapper.insert(employee);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        employeeMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            employeeMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Employee employee) {
        employeeMapper.updateById(employee);
    }

    /**
     * 根据ID查询
     */
    public Employee selectById(Integer id) {
        return employeeMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Employee> selectAll(Employee employee) {
        return employeeMapper.selectAll(employee);
    }

    /**
     * 分页查询
     */
    public PageInfo<Employee> selectPage(Employee employee, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Employee> list = employeeMapper.selectAll(employee);

        return PageInfo.of(list);
    }

    /**
     * 注册
     */
    public void register(Account account) {
        Employee employee = new Employee();
        employee.setUsername(account.getUsername());
        employee.setPassword(account.getPassword());
        this.add(employee);
    }

    public Employee login(Account account) {
        Employee dbEmployee = employeeMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbEmployee)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbEmployee.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token
        String tokenData = dbEmployee.getId() + "-employee";
        String token = TokenUtils.genToken(tokenData, dbEmployee.getPassword());
        dbEmployee.setToken(token);


        return dbEmployee;
    }

    /**
     * 修改密码
     */
    public void updatePassword(Account account) {
        Employee dbEmployee = employeeMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbEmployee)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbEmployee.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbEmployee.setPassword(account.getNewPassword());
        employeeMapper.updateById(dbEmployee);
    }

}