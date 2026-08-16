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
public class SupplierinfoService {

    @Resource
    private SupplierinfoMapper supplierinfoMapper;

    /**
     * 新增
     */
    public void add(Supplierinfo supplierinfo) {
        supplierinfoMapper.insert(supplierinfo);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        supplierinfoMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            supplierinfoMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Supplierinfo supplierinfo) {
        supplierinfoMapper.updateById(supplierinfo);
    }

    /**
     * 根据ID查询
     */
    public Supplierinfo selectById(Integer id) {
        return supplierinfoMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Supplierinfo> selectAll(Supplierinfo supplierinfo) {
        return supplierinfoMapper.selectAll(supplierinfo);
    }

    /**
     * 分页查询
     */
    public PageInfo<Supplierinfo> selectPage(Supplierinfo supplierinfo, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Supplierinfo> list = this.selectAll(supplierinfo);

        return PageInfo.of(list);
    }

}