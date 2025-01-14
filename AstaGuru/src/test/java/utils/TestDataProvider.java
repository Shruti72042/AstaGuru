/*
package utils;
	
	

	import java.io.IOException;
	import java.util.List;
	import java.util.Map;

import org.testng.annotations.DataProvider;

	public class TestDataProvider {
	    private static final String LOGIN_TEST_DATA_PATH = "src/test/resources/TestData/LoginTestData.xlsx";

	    @DataProvider(name = "loginData")
	    public Object[][] loginData() throws IOException {
	        ExcelUtils excelUtils = new ExcelUtils(LOGIN_TEST_DATA_PATH, "LoginData");
	        List<Map<String, String>> data = excelUtils.getAllData();
	        Object[][] testData = new Object[data.size()][1];
	        for (int i = 0; i < data.size(); i++) {
	            testData[i][0] = data.get(i);
	        }
	        return testData;
	    }
	}
*/
/*

package utils;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestDataProvider {
    private static final String LOGIN_TEST_DATA_PATH = "src/test/resources/TestData/LoginTestData.xlsx";

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        ExcelUtils excelUtils = new ExcelUtils(LOGIN_TEST_DATA_PATH, "LoginData");
        List<Map<String, String>> data = excelUtils.getAllData();
        Object[][] testData = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            testData[i][0] = data.get(i);
        }
        return testData;
    }
}


*/