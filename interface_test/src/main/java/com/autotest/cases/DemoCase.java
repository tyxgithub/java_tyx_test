package com.autotest.cases;

import com.autotest.pojo.CaseInfo;
import com.autotest.uitils.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @ClassName DemoCase
 * @Description TODO
 * @Author tyx
 * @Date 2022/3/3 16:02
 */
public class DemoCase extends BaseCase {
    @Test(dataProvider = "getDatas")
    public void test_data_access(CaseInfo caseInfo) {
        System.out.println(caseInfo.getCaseid());
        addDataToWriteBack(caseInfo.getCaseid(),8,"数据回写",setStartSheetIndex);
    }
    @DataProvider
    public Object[] getDatas() {
        return ExcelUtils.readCaseInfo(0, 2);
    }
}
