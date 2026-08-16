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
public class SaleinfoService {

    @Resource
    private SaleinfoMapper saleinfoMapper;

    /**
     * 新增
     */
    public void add(Saleinfo saleinfo) {
        saleinfoMapper.insert(saleinfo);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        saleinfoMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            saleinfoMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Saleinfo saleinfo) {
        saleinfoMapper.updateById(saleinfo);
    }

    /**
     * 根据ID查询
     */
    public Saleinfo selectById(Integer id) {
        return saleinfoMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Saleinfo> selectAll(Saleinfo saleinfo) {
        return saleinfoMapper.selectAll(saleinfo);
    }

    /**
     * 分页查询
     */
    public PageInfo<Saleinfo> selectPage(Saleinfo saleinfo, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Saleinfo> list = this.selectAll(saleinfo);

        return PageInfo.of(list);
    }

}