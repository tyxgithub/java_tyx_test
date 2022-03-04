package com.autotest.cases;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.autotest.pojo.CaseInfo;
import com.autotest.pojo.WriteBack;
import com.autotest.uitils.ExcelUtils;
import com.autotest.uitils.HttpUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.util.Map;
import java.util.Set;

/**
 * @ClassName BaseCase
 * @Description TODO
 * @Author tyx
 * @Date 2022/3/3 16:03
 */
public class BaseCase {
    public static Logger logger = Logger.getLogger(BaseCase.class);

    public int setStartSheetIndex;
    public int setSheetNum;

    /**
     * create by: tyx
     * description: 套件执行之前，用于获取带有Token,session的请求头
     * create time: 2022/3/3 17:05
     * @return void
     */
    @BeforeSuite
    public void beforeSuit() {
        Map<String, Object> headers = HttpUtils.getDefaultHeaders();
    }
    /**
     * create by: tyx
     * description: 每个测试Case执行之前执行的方法，用来获取当前执行类，读取Excel文件
     * 中第几个索引薄
     * create time: 2022/3/3 16:32
     * @Param: setStartSheetIndex 读取excel索引起始位置，从0开始计数
     * @Param: setSheetNum   读取长度，一般默认是1
     * @return void
     */
    @BeforeClass
    @Parameters({"setStartSheetIndex", "setSheetNum"})
    public void beforeClass(int setStartSheetIndex, int setSheetNum) {
        this.setStartSheetIndex = setStartSheetIndex;
        this.setSheetNum = setSheetNum;
    }
    /**
     * create by: tyx
     * description: 测试结果回写方法
     * create time: 2022/3/3 16:42
     * @Param: rowNum  回写行，第几行
       @Param: cellNum 回写列，一般是固定值，实际结果是第9列，那就是9
       @Param: content 回写的内容
       @Param: sheetIndex 回写的excel是第几个薄
     * @return void
     */
    public void addDataToWriteBack(int rowNum, int cellNum, String content, int sheetIndex) {
        WriteBack wb = new WriteBack(rowNum, cellNum, content, sheetIndex);
        ExcelUtils.writeBackList.add(wb);
        System.out.println("数据回写成功");
    }
    /**
     * create by: tyx
     * description: 数据的批量回,回写数据装载到writeBackList 这个列表中
     * create time: 2022/3/3 17:00
     * @return void
     */
    @AfterSuite
    public void afterSuite() {
        ExcelUtils.batchWrite();
        logger.info("Excel数据回写成功.");
    }
    /**
     * create by: tyx
     * description: 参数替换方法,主要针对某些接口的请求参数依赖别的接口，
     * 但是请求数据写死在了excel里面，所以需要请求前替换，当然了依赖接口的
     * 返回数据，需要写在对应测试类下面
     * create time: 2022/3/3 17:14
     * @Param: params 替换的参数，这是一个占位符，对应excel中请求参数是${param}
     * @Param: targetMethod
     * @return java.lang.String
     */
    public static String replaceData(String params,String targetMethod){
        params=params.replace("${param}",targetMethod);
        return params;
    }

    /**
     * create by: tyx
     * description: case断言方法
     * create time: 2022/3/4 10:36
     * @Param: caseInfo  每个测试用例对象
     * @Param: responseBody  接口得返回体
     * @return java.lang.Boolean
     */
    public Boolean responseAssert(CaseInfo caseInfo, String responseBody) {
        String expectedResult = caseInfo.getExpectedResult();
        Map<String, Object> map = null;
        try {
            map = JSONObject.parseObject(expectedResult, Map.class);
        } catch (Exception e) {
            logger.info(String.format("用例编号:%s ,用例名称:%s ,期望结果fastjson解析失败", caseInfo.getCaseid(), caseInfo.getDescription()));
            logger.info(e);
        }
        Set<String> keySets = map.keySet();
        Boolean assertResult = true;
        int count = 0;
        logger.info(String.format("-----执行用例,用例编号: %s,用例标题: %s-----",caseInfo.getCaseid(),caseInfo.getDescription()));
        for (String actualJsonPath : keySets) {
            count++;
            Object actualValue = JSONPath.read(responseBody, actualJsonPath);
            Object expectValue = map.get(actualJsonPath);
            if (!expectValue.equals(actualValue)) {
                logger.info("字段" + count + ": 断言失败！正则表达式字段：" + actualJsonPath + ",实际值：" + actualValue + "，期望值：" + expectValue);
                assertResult = false;
            }
        }
        if (assertResult) {
            logger.info("-----响应断言成功------");
        }else{
            logger.info("-----响应断言失败!!------");
        }
        return assertResult;
    }
}
