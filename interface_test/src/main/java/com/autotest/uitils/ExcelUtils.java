package com.autotest.uitils;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.autotest.pojo.CaseInfo;
import com.autotest.pojo.WriteBack;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ExcelUtils
 * @Description TODO
 * @Author tyx
 * @Date 2022/3/2 14:07
 */
public class ExcelUtils {
    public static Logger logger=Logger.getLogger(ExcelUtils.class);

    public static List<WriteBack> writeBackList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(readCaseInfo(0, 1));
    }

    public static Object[] readCaseInfo(int setStartSheetIndex, int setSheetNum) {
        /*
         * @Description: 读取用例数据方法
         * @Date: 2020/11/4
         * @Param: [setStartSheetIndex:起始的excel索引, setSheetNum:读取长度，一般为1]
         * @Return: java.lang.Object[]
         */
        File excelFile = new File(Constants.EXCEL_PATH);
        System.out.println("Excel路径:"+excelFile);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(excelFile);
        } catch (FileNotFoundException e) {
            logger.info(String.format("Excel文件：%s,路径错误或者文件不存在.", (new File(Constants.EXCEL_PATH)).getName()));
            logger.info(e);
        }
        ImportParams importParams = new ImportParams();
        importParams.setStartSheetIndex(setStartSheetIndex);
        importParams.setSheetNum(setSheetNum);
        List<? extends Object> CaseInfoLists = null;
        try {
            System.out.println("类名:"+Constants.CASEINFO_CLASS_NAME);
            CaseInfoLists = ExcelImportUtil.importExcel(fis, CaseInfo.class, importParams);
        } catch (Exception e) {
            logger.info("ExcelImportUtil.importExcel执行错误,可能是Excelpojo文件存在问题");
            logger.info("e1:",e);
        }
        try {
            fis.close();
        } catch (IOException e) {
            logger.info(String.format("Excel文件:%s,未正常关闭", (new File(Constants.EXCEL_PATH)).getName()));
            logger.info("e2",e);
        }

        Object[] objects = CaseInfoLists.toArray();

        return objects;
    }

    public static void batchWrite() {
        File excelFile = new File(Constants.EXCEL_PATH);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(excelFile);
        } catch (FileNotFoundException e) {
            logger.info("Excel文件：" + (new File(Constants.EXCEL_PATH)).getName() + "路径错误或者文件不存在.");
            logger.info(e);
        }
        Workbook sheets = null;
        try {
            sheets = WorkbookFactory.create(fis);
        } catch (IOException e) {
            logger.info("创建WorkbookFactory.create方法错误.");
            logger.info(e);
        }
        for (WriteBack writeBack : writeBackList) {
            int sheetIndex = writeBack.getSheetIndex();
            int rowNum = writeBack.getRowNum();
            int cellNum = writeBack.getCellNum();
            String content = writeBack.getContent();

            Sheet sheet = sheets.getSheetAt(sheetIndex);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(content);
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(Constants.EXCEL_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            sheets.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                logger.info("EXCEL文件关闭失败");
                logger.info(e);
            }
        }
    }

}


