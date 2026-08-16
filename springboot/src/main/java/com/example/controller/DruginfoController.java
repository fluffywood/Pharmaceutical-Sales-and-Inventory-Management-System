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
*  描述：药品信息相关接口
*/
@RestController
@RequestMapping("/druginfo")
public class DruginfoController {

    @Resource
    DruginfoService druginfoService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Druginfo druginfo) {

        druginfoService.add(druginfo);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        druginfoService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        druginfoService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新
     */
    @PutMapping("/update")
    public Result update(@RequestBody Druginfo druginfo) {

        druginfoService.updateById(druginfo);
        return Result.success();
    }

    /**
     * 查询单个
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Druginfo druginfo = druginfoService.selectById(id);
        return Result.success(druginfo);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Druginfo druginfo) {
        List<Druginfo> list = druginfoService.selectAll(druginfo);
        return Result.success(list);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Druginfo druginfo,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Druginfo> pageInfo = druginfoService.selectPage(druginfo, pageNum, pageSize);
        return Result.success(pageInfo);
    }

	/**
	 * 描述：批量删除
	 */
	@PutMapping("/batchDel")
	public Result deleteBatch(@RequestBody List<Integer> list) {
		for (Integer id : list) {
			druginfoService.deleteById(id);
		}
		return Result.success();
	}

	/**
	 * 描述：批量导出到excel
	 */
	@GetMapping("/export")
	public void export(HttpServletResponse response) throws IOException {
		List<Druginfo> all = druginfoService.selectAll(new Druginfo());
		List<Map<String, Object>> list = new ArrayList<>(all.size());
		if (CollectionUtil.isEmpty(all)) {
			Map<String, Object> row = new LinkedHashMap<>();
			row.put("药品编号", null);
			row.put("药品名称", null);
			row.put("药品性质", null);
			row.put("供应价格", null);
			row.put("售价", null);
			row.put("供应商", null);
			row.put("保质期", null);
			list.add(row);
		} else {
			for (Druginfo druginfo : all) {
				Map<String, Object> row = new LinkedHashMap<>();
				row.put("药品编号", druginfo.getDrugCode());
				row.put("药品名称", druginfo.getDrugName());
				row.put("药品性质", druginfo.getDrugScript());
				row.put("供应价格", druginfo.getSupplyPrice());
				row.put("售价", druginfo.getSalePrice());
				row.put("供应商", druginfo.getDrugSupplier());
				row.put("保质期", druginfo.getDrugDate());
				list.add(row);
			}
		}
		ExcelWriter writer = ExcelUtil.getWriter(true);
		writer.write(list, true);
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
		response.setHeader("Content-Disposition","attachment;filename=druginfoInfoExcel.xlsx");
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
		List<Druginfo> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(Druginfo.class);
		if (!CollectionUtil.isEmpty(infoList)) {
			for (Druginfo info : infoList) {
				try {
					druginfoService.add(info);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return Result.success();
	}

}
