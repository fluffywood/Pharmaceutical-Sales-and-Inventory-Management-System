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
public class DruginfoService {

    @Resource
    private DruginfoMapper druginfoMapper;

    /**
     * 新增
     */
    public void add(Druginfo druginfo) {
        druginfoMapper.insert(druginfo);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        druginfoMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            druginfoMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Druginfo druginfo) {
        druginfoMapper.updateById(druginfo);
    }

    /**
     * 根据ID查询
     */
    public Druginfo selectById(Integer id) {
        return druginfoMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Druginfo> selectAll(Druginfo druginfo) {
        return druginfoMapper.selectAll(druginfo);
    }

    /**
     * 分页查询
     */
    public PageInfo<Druginfo> selectPage(Druginfo druginfo, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Druginfo> list = this.selectAll(druginfo);

        return PageInfo.of(list);
    }

}