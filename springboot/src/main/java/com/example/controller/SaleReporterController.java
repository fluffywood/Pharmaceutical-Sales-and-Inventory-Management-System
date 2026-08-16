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
*  描述：销售报表相关接口
*/
@RestController
@RequestMapping("/saleReporter")
public class SaleReporterController {

    @Resource
    SaleReporterService saleReporterService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody SaleReporter saleReporter) {

        saleReporterService.add(saleReporter);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        saleReporterService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        saleReporterService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody SaleReporter saleReporter) {

        saleReporterService.updateById(saleReporter);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        SaleReporter saleReporter = saleReporterService.selectById(id);
        return Result.success(saleReporter);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(SaleReporter saleReporter) {
        List<SaleReporter> list = saleReporterService.selectAll(saleReporter);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            SaleReporter saleReporter,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<SaleReporter> pageInfo = saleReporterService.selectPage(saleReporter, pageNum, pageSize);
        return Result.success(pageInfo);
    }




}
