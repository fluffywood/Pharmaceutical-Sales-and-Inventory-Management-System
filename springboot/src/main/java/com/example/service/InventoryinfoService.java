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
public class InventoryinfoService {

    @Resource
    private InventoryinfoMapper inventoryinfoMapper;

    /**
     * 新增
     */
    public void add(Inventoryinfo inventoryinfo) {
        inventoryinfoMapper.insert(inventoryinfo);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        inventoryinfoMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            inventoryinfoMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Inventoryinfo inventoryinfo) {
        inventoryinfoMapper.updateById(inventoryinfo);
    }

    /**
     * 根据ID查询
     */
    public Inventoryinfo selectById(Integer id) {
        return inventoryinfoMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Inventoryinfo> selectAll(Inventoryinfo inventoryinfo) {
        return inventoryinfoMapper.selectAll(inventoryinfo);
    }

    /**
     * 分页查询
     */
    public PageInfo<Inventoryinfo> selectPage(Inventoryinfo inventoryinfo, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Inventoryinfo> list = this.selectAll(inventoryinfo);

        return PageInfo.of(list);
    }

}