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
public class PurchaseinfoService {

    @Resource
    private PurchaseinfoMapper purchaseinfoMapper;

    /**
     * 新增
     */
    public void add(Purchaseinfo purchaseinfo) {
        purchaseinfoMapper.insert(purchaseinfo);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        purchaseinfoMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            purchaseinfoMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Purchaseinfo purchaseinfo) {
        purchaseinfoMapper.updateById(purchaseinfo);
    }

    /**
     * 根据ID查询
     */
    public Purchaseinfo selectById(Integer id) {
        return purchaseinfoMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Purchaseinfo> selectAll(Purchaseinfo purchaseinfo) {
        return purchaseinfoMapper.selectAll(purchaseinfo);
    }

    /**
     * 分页查询
     */
    public PageInfo<Purchaseinfo> selectPage(Purchaseinfo purchaseinfo, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Purchaseinfo> list = this.selectAll(purchaseinfo);

        return PageInfo.of(list);
    }

}