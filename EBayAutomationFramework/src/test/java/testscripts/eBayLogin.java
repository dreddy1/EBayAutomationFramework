package testscripts;

import logger.Log;
import pages.android.*;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import supportlibraries.Driver;
import supportlibraries.ExcelUtility;

/**
 * Automated test to verify login to android/iOS app.
 */
public class eBayLogin extends Driver {

    EBayLoginCore eBayLoginCore;
    String userName;
    String password;
    List testData;

    /**
     * This method instantiate required helpers depending on the
     * platform(android or iOS)
     * 
     * @param invokeDriver
     *            android or iOS
     */
    @Parameters({"os"})
    @BeforeMethod
    public void instantiateHelpers(String invokeDriver) {

        // Getting test data required from external test data file.
        // this can be extended to repeat the test based on the number of row
        // data provided.
        ExcelUtility excelUtility = new ExcelUtility();
        testData = excelUtility.getTestData(file.getAbsoluteFile() + "\\src\\main\\resources\\testData\\eBayLogin.xlsx");
        userName = ((List) testData.get(1)).get(1).toString();
        password = ((List) testData.get(1)).get(2).toString();
    }

    /**
     * Method to verify login
     * 
     * @throws InterruptedException
     *             Thrown when a thread is waiting, sleeping, or otherwise
     *             occupied, and the thread is interrupted, either before or
     *             during the activity.
     */
    @Test
    public void LoginVerification() throws InterruptedException {

        Log.info("TC01 - Started login test");

        try {
            EBayLoginCore ebay = new EBayLoginCore(driver);

            ebay.verifyLogin(userName, password);
        }
        catch (Exception e) {
            Log.logError(getClass().getName(), "TC01", "Not able to login");
        }
        Log.info("TC01 - Ended login test");
    }

}
