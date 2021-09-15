package com.iris.excel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author iris
 * @date 2021/8/24
 */
@RestController
@Slf4j
@Api(tags = "导入导出工具类")
public class ExcelController {

    @ApiOperation("导出excel")
    @PostMapping("export")
    public void export(HttpServletResponse response) {
        // 拿到头信息
        List<String> heard = new ArrayList<>();
        heard.add("序号");
        heard.add("字段1名称");
        heard.add("字段2名称");
        List<List<Object>> content = new ArrayList<>();
        List<Object> first = new ArrayList<>();
        first.add("字段1值");
        first.add(2.21);
        content.add(first);
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(heard, content,"f");
        //输出Excel文件
        try (OutputStream output = response.getOutputStream()) {
            response.setHeader("Content-Disposition", "attachment;Filename=" + System.currentTimeMillis() + ".xls");
            wb.write(output);
            output.flush();
        } catch (IOException e) {
            log.error("{}", e.getMessage());
        }

    }

    @ApiOperation("导入excel")
    @PostMapping("importExcel")
    public Object importExcel(@RequestPart("file") MultipartFile multipartFile, @RequestParam String type) {
        if (multipartFile == null) {
            return "文件为空";
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
             ExcelUtil.readExcel(inputStream, multipartFile.getOriginalFilename());
        } catch (IOException e) {
            log.error("{}", e.getMessage());
        }
        return "导入成功";
    }
}
