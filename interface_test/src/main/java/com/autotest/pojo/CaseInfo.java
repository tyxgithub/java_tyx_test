package com.autotest.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * Author:   tyx
 * Date:     2020/11/3 14:20
 * Description: 用例文件pojo,注意private 后面的属性参数不能大写
 */
public class CaseInfo {
    //    用例编号	模块名称	用例描述	请求方式
//    请求地址	请求参数	参数类型	期望结果
//    实际结果	sql	断言结果	其他
    @Excel(name = "用例编号")
    private int caseid;
    @Excel(name = "模块名称")
    private String module;
    @Excel(name = "用例标题")
    private String description;
    @Excel(name = "请求方式")
    private String method;
    @Excel(name = "请求地址")
    private String url;
    @Excel(name = "请求参数")
    private String params;
    @Excel(name = "参数类型")
    private String contentType;
    @Excel(name = "期望结果")
    private String expectedResult;
    @Excel(name = "实际结果")
    private String actualResult;
    @Excel(name = "sql")
    private String sql;
    @Excel(name = "断言结果")
    private String assertResult;
    @Excel(name = "sql结果")
    private String sqlResult;
    @Excel(name = "其他")
    private String other;

    public int getCaseid() {
        return caseid;
    }

    public void setCaseid(int caseid) {
        this.caseid = caseid;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getAssertResult() {
        return assertResult;
    }

    public void setAssertResult(String assertResult) {
        this.assertResult = assertResult;
    }

    public String getSqlResult() {
        return sqlResult;
    }

    public void setSqlResult(String sqlResult) {
        this.sqlResult = sqlResult;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}

