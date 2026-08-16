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
*  描述：员工信息相关接口
*/
@RestController
@RequestMapping("/employeeinfo")
public class EmployeeinfoController {

    @Resource
    EmployeeinfoService employeeinfoService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Employeeinfo employeeinfo) {
		Account currentUser = TokenUtils.getCurrentUser();
		if (ObjectUtil.isEmpty(currentUser)) {
			throw new CustomException(ResultCodeEnum.USER_NOT_LOGIN);
		}
		if ("employee".equals(currentUser.getRole())) {
			employeeinfo.setEmployeeId(currentUser.getId());
		}

        employeeinfoService.add(employeeinfo);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        employeeinfoService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        employeeinfoService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Employeeinfo employeeinfo) {

        employeeinfoService.updateById(employeeinfo);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Employeeinfo employeeinfo = employeeinfoService.selectById(id);
        return Result.success(employeeinfo);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Employeeinfo employeeinfo) {
        List<Employeeinfo> list = employeeinfoService.selectAll(employeeinfo);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Employeeinfo employeeinfo,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Employeeinfo> pageInfo = employeeinfoService.selectPage(employeeinfo, pageNum, pageSize);
        return Result.success(pageInfo);
    }




}
