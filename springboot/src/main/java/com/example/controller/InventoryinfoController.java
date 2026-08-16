package com.example.controller;

import com.example.common.config.TokenUtils;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageInfo;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.*;
import com.example.service.*;
import com.example.exception.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.*;

import java.util.List;

/**
*  描述：库房管理相关接口
*/
@RestController
@RequestMapping("/inventoryinfo")
public class InventoryinfoController {

    @Resource
    InventoryinfoService inventoryinfoService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Inventoryinfo inventoryinfo) {

        inventoryinfoService.add(inventoryinfo);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        inventoryinfoService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        inventoryinfoService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Inventoryinfo inventoryinfo) {

        inventoryinfoService.updateById(inventoryinfo);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Inventoryinfo inventoryinfo = inventoryinfoService.selectById(id);
        return Result.success(inventoryinfo);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Inventoryinfo inventoryinfo) {
        List<Inventoryinfo> list = inventoryinfoService.selectAll(inventoryinfo);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Inventoryinfo inventoryinfo,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Inventoryinfo> pageInfo = inventoryinfoService.selectPage(inventoryinfo, pageNum, pageSize);
        return Result.success(pageInfo);
    }




}
