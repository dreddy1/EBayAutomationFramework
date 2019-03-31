package pages.android;

import logger.Log;
import objects.android.EBayLoginScreen;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Contains all methods to login on android app
 */
public class EBayLoginCore {

    EBayLoginScreen eBayLoginScreen;

    public EBayLoginCore(WebDriver driver) {
        eBayLoginScreen = new EBayLoginScreen(driver);
    }

    /**
     * Method to login to android app
     *
     * @param userName
     *            emailId to be used for login
     * @param password
     *            - valid password
     */
    public void verifyLogin(String userName, String password) throws InterruptedException {

        try {
            WebElement signIn = eBayLoginScreen.findElement(eBayLoginScreen.signInButton);
            if (signIn != null) {
                signIn.click();
                WebElement userName_txtbox = eBayLoginScreen.findElement(eBayLoginScreen.userName);
                WebElement password_txtbox = eBayLoginScreen.findElement(eBayLoginScreen.password);
                if (userName_txtbox != null && password_txtbox != null) {
                     if (userName_txtbox.equals(userName) == false) {
                        userName_txtbox.clear();
                        userName_txtbox.sendKeys(userName);
                    }

                    password_txtbox.sendKeys(password);

                    signIn = eBayLoginScreen.findElement(eBayLoginScreen.signInButton);
                    if (signIn != null) {
                        signIn.click();
                        Log.info("Login Success");
                    }
                }
            }
        }
        catch (Exception e) {
            Log.logError(getClass().getName(), "Login", "Unable to login app");
        }

    }

}
