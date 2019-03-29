package objects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import supportlibraries.GenericMethods;

/**
 * contains all the locators present on the eBay Login Screen
 */
public class EBayLoginScreen extends GenericMethods {

    public EBayLoginScreen(WebDriver driver) {
        super(driver);
    }

    // Locators on the login screen
    public By userName = By.id("com.ebay.mobile:id/edit_text_username");
    public By password = By.id("com.ebay.mobile:id/et_temp");
    public By signInButton = By.id("com.ebay.mobile:id/button_sign_in");

}
