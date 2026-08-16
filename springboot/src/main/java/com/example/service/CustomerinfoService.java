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
public class CustomerinfoService {

    @Resource
    private CustomerinfoMapper customerinfoMapper;

    /**
     * 新增
     */
    public void add(Customerinfo customerinfo) {
        customerinfoMapper.insert(customerinfo);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        customerinfoMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            customerinfoMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Customerinfo customerinfo) {
        customerinfoMapper.updateById(customerinfo);
    }

    /**
     * 根据ID查询
     */
    public Customerinfo selectById(Integer id) {
        return customerinfoMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Customerinfo> selectAll(Customerinfo customerinfo) {
        return customerinfoMapper.selectAll(customerinfo);
    }

    /**
     * 分页查询
     */
    public PageInfo<Customerinfo> selectPage(Customerinfo customerinfo, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Customerinfo> list = this.selectAll(customerinfo);

        return PageInfo.of(list);
    }

}