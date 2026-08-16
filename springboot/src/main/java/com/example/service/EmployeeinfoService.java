package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.config.TokenUtils;
import com.example.mapper.*;
import com.example.entity.*;
import com.example.exception.CustomException;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeinfoService {

    @Resource
    private EmployeeinfoMapper employeeinfoMapper;

    /**
     * 新增
     */
    public void add(Employeeinfo employeeinfo) {
        employeeinfoMapper.insert(employeeinfo);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        employeeinfoMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            employeeinfoMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Employeeinfo employeeinfo) {
        employeeinfoMapper.updateById(employeeinfo);
    }

    /**
     * 根据ID查询
     */
    public Employeeinfo selectById(Integer id) {
        return employeeinfoMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Employeeinfo> selectAll(Employeeinfo employeeinfo) {
        return employeeinfoMapper.selectAll(employeeinfo);
    }

    /**
     * 分页查询
     */
    public PageInfo<Employeeinfo> selectPage(Employeeinfo employeeinfo, Integer pageNum, Integer pageSize) {
		Account currentUser = TokenUtils.getCurrentUser();
		if ("employee".equals(currentUser.getRole())) {
			employeeinfo.setEmployeeId(currentUser.getId());
		}

        PageHelper.startPage(pageNum, pageSize);
        List<Employeeinfo> list = this.selectAll(employeeinfo);

        return PageInfo.of(list);
    }

}