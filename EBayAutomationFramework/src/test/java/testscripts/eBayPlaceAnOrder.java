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
 * automated test to verify login to android/iOS app.
 */
public class eBayPlaceAnOrder extends Driver {

    EBaySearch eBaySearch;
    String productDetails;
    List testData;

    /**
     * this method instantiate required helpers depending on the
     * platform(android or iOS)
     * 
     * @param invokeDriver
     *            android or iOS
     */
    @Parameters({"os"})
    @BeforeMethod
    public void instantiateHelpers(String invokeDriver) {

         // Getting test data required from external test data file.
        // this can be extended to repeat the test based on the number of row data provided.
        ExcelUtility excelUtility = new ExcelUtility();
        testData = excelUtility.getTestData(file.getAbsoluteFile() + "\\src\\main\\resources\\testData\\eBayPlaceAnOrder.xlsx");
        productDetails = ((List) testData.get(1)).get(1).toString();
    }

    /**
     * method to place an order
     * 
     * @throws InterruptedException
     *             Thrown when a thread is waiting, sleeping, or otherwise
     *             occupied, and the thread is interrupted, either before or
     *             during the activity.
     */
    @Test
    public void placeAnOrder() throws InterruptedException {
        Log.info("TC02 -Started place an order test");
        try {
            eBaySearch = new EBaySearch(driver);
            
            eBaySearch.searchForItem(productDetails);
            eBaySearch.addItemToCart();
            eBaySearch.verifyProductDetailsInCart(productDetails);
            eBaySearch.placeAnOrder();
            Log.info("TC02 - Ended Place an order test");
        }
        catch (Exception e) {
            Log.logError(getClass().getName(), "TC02", "Unable to pay");
        }
    }

}
