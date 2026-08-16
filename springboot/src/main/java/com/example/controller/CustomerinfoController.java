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
*  描述：客户信息相关接口
*/
@RestController
@RequestMapping("/customerinfo")
public class CustomerinfoController {

    @Resource
    CustomerinfoService customerinfoService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Customerinfo customerinfo) {

        customerinfoService.add(customerinfo);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        customerinfoService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        customerinfoService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Customerinfo customerinfo) {

        customerinfoService.updateById(customerinfo);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Customerinfo customerinfo = customerinfoService.selectById(id);
        return Result.success(customerinfo);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Customerinfo customerinfo) {
        List<Customerinfo> list = customerinfoService.selectAll(customerinfo);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Customerinfo customerinfo,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Customerinfo> pageInfo = customerinfoService.selectPage(customerinfo, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			customerinfoService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<Customerinfo> all = customerinfoService.selectAll(new Customerinfo());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("客户姓名", null);
			row.put("客户性别", null);
			row.put("客户生日", null);
			row.put("联系方式", null);
			row.put("身份证号", null);
			list.add(row);
		} else {
			for (Customerinfo customerinfo : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("客户姓名", customerinfo.getCustomerName());
				row.put("客户性别", customerinfo.getCustomerSex());
				row.put("客户生日", customerinfo.getCustomerBirth());
				row.put("联系方式", customerinfo.getCustomerPhone());
				row.put("身份证号", customerinfo.getCustomerIdcard());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=customerinfoInfoExcel.xlsx");
		ServletOutputStream out = response.getOutputStream();
		writer.flush(out, true);
		writer.close();
		IoUtil.close(System.out);
	}

	/**
	 * 描述：通过excel批量导入
	 */
	@PostMapping("/upload")
	public Result upload(MultipartFile file) throws IOException {
		List<Customerinfo> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Customerinfo.class);
		if (!CollectionUtil.isEmpty(infoList)) {
			for (Customerinfo info : infoList) {
				try {
					customerinfoService.add(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Result.success();
	}

}
