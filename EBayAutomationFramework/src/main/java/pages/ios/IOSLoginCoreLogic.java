package pages.ios;

import org.openqa.selenium.WebDriver;

import objects.ios.IOSLoginScreen;

/**
 * contains method to login on iOS app
 */
public class IOSLoginCoreLogic{
	IOSLoginScreen iosLoginScreen;

	public IOSLoginCoreLogic(WebDriver driver){
		iosLoginScreen = new IOSLoginScreen(driver);
	}

/**
 * method to verify login scenario on iOS app
 * @param userName emailId to be used for login
 * @param password - valid password
 */
	public void verifyLogin(String userName, String password)
			throws InterruptedException {		
		// To be done
	}
}

