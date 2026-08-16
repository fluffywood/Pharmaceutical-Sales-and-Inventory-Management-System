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
public class PurchaseReporterService {

    @Resource
    private PurchaseReporterMapper purchaseReporterMapper;

    /**
     * 新增
     */
    public void add(PurchaseReporter purchaseReporter) {
        purchaseReporterMapper.insert(purchaseReporter);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        purchaseReporterMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            purchaseReporterMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(PurchaseReporter purchaseReporter) {
        purchaseReporterMapper.updateById(purchaseReporter);
    }

    /**
     * 根据ID查询
     */
    public PurchaseReporter selectById(Integer id) {
        return purchaseReporterMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<PurchaseReporter> selectAll(PurchaseReporter purchaseReporter) {
        return purchaseReporterMapper.selectAll(purchaseReporter);
    }

    /**
     * 分页查询
     */
    public PageInfo<PurchaseReporter> selectPage(PurchaseReporter purchaseReporter, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<PurchaseReporter> list = this.selectAll(purchaseReporter);

        return PageInfo.of(list);
    }

}