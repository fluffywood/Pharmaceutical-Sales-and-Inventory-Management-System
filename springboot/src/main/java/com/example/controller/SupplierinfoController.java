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
*  描述：供应商信息相关接口
*/
@RestController
@RequestMapping("/supplierinfo")
public class SupplierinfoController {

    @Resource
    SupplierinfoService supplierinfoService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Supplierinfo supplierinfo) {

        supplierinfoService.add(supplierinfo);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        supplierinfoService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        supplierinfoService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Supplierinfo supplierinfo) {

        supplierinfoService.updateById(supplierinfo);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Supplierinfo supplierinfo = supplierinfoService.selectById(id);
        return Result.success(supplierinfo);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Supplierinfo supplierinfo) {
        List<Supplierinfo> list = supplierinfoService.selectAll(supplierinfo);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Supplierinfo supplierinfo,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Supplierinfo> pageInfo = supplierinfoService.selectPage(supplierinfo, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			supplierinfoService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<Supplierinfo> all = supplierinfoService.selectAll(new Supplierinfo());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("供应商名称", null);
			row.put("地址", null);
			row.put("法人代表", null);
			row.put("联系人", null);
			row.put("联系电话", null);
			row.put("电子邮件", null);
			row.put("备注", null);
			list.add(row);
		} else {
			for (Supplierinfo supplierinfo : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("供应商名称", supplierinfo.getSupplierName());
				row.put("地址", supplierinfo.getSupplierAddress());
				row.put("法人代表", supplierinfo.getSupplierLegal());
				row.put("联系人", supplierinfo.getSupplierContact());
				row.put("联系电话", supplierinfo.getSupplierPhone());
				row.put("电子邮件", supplierinfo.getSupplierEmail());
				row.put("备注", supplierinfo.getSupplierRemark());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=supplierinfoInfoExcel.xlsx");
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
		List<Supplierinfo> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Supplierinfo.class);
		if (!CollectionUtil.isEmpty(infoList)) {
			for (Supplierinfo info : infoList) {
				try {
					supplierinfoService.add(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Result.success();
	}

}
