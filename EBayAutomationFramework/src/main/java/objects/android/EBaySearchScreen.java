package objects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import supportlibraries.GenericMethods;

/**
 * Contains all the locators present on the android app
 */
public class EBaySearchScreen extends GenericMethods {

    public EBaySearchScreen(WebDriver driver) {
        super(driver);
    }

    // Locators on the eBay screen
    public By homePageSearchBox = By.id("com.ebay.mobile:id/search_box");
    public By productList = By.id("com.ebay.mobile:id/cell_collection_item");

    public By productDetailsName = By.id("com.ebay.mobile:id/textview_item_name");
    public By productDetailsPrice = By.id("com.ebay.mobile:id/textview_item_price");
    public By productDetailsAddToCart = By.id("com.ebay.mobile:id/button_add_to_cart");
    public By productDetailsViewInCart = By.id("com.ebay.mobile:id/button_view_in_cart");

    // ** CART DETAILS
    public By cartProductName = By.id("com.ebay.mobile:id/title");
    public By cartProductPrice = By.id("com.ebay.mobile:id/item_price");
    public By cartRemoveProduct = By.id("com.ebay.mobile:id/remove_from_cart_button");

    // Android widget Button
    public By removeConfirmation = By.id("android:id/button1");

    public By tvImage = By.xpath("//*[@text = 'LG 65UJ6300 65-inch UHD 4K Smart LED TV']");
    public By tvText = By.xpath("//*[@text = 'LG 65UJ6300 65-inch UHD 4K Smart LED TV");
    public By tvPrice = By.id("com.ebay.mobile:id/textview_item_price");

    public By cartTvText = By.xpath("//*[@text = 'LG 65UJ6300 65-inch UHD 4K Smart LED TV']");
    public By cartTvPrice = By.id("com.ebay.mobile:id/item_price");
    
    //Payment completion
    public By continueButton = By.id("Continue");
    public By selectExistingCard = By.id("<radio_btn/>1");
    public By pay = By.id("Pay");
}
