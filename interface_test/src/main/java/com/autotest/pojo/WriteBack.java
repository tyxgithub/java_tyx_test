package com.autotest.pojo;

public class WriteBack {
    public int rowNum;
    public int cellNum;
    public String content;
    public int sheetIndex;

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getCellNum() {
        return cellNum;
    }

    public void setCellNum(int cellNum) {
        this.cellNum = cellNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public WriteBack(int rowNum, int cellNum, String content, int sheetIndex) {
        this.rowNum = rowNum;
        this.cellNum = cellNum;
        this.content = content;
        this.sheetIndex = sheetIndex;
    }
}
