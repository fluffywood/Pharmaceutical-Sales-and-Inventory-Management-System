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
public class SaleReporterService {

    @Resource
    private SaleReporterMapper saleReporterMapper;

    /**
     * 新增
     */
    public void add(SaleReporter saleReporter) {
        saleReporterMapper.insert(saleReporter);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        saleReporterMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            saleReporterMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(SaleReporter saleReporter) {
        saleReporterMapper.updateById(saleReporter);
    }

    /**
     * 根据ID查询
     */
    public SaleReporter selectById(Integer id) {
        return saleReporterMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<SaleReporter> selectAll(SaleReporter saleReporter) {
        return saleReporterMapper.selectAll(saleReporter);
    }

    /**
     * 分页查询
     */
    public PageInfo<SaleReporter> selectPage(SaleReporter saleReporter, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<SaleReporter> list = this.selectAll(saleReporter);

        return PageInfo.of(list);
    }

}