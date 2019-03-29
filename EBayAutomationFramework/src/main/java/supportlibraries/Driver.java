package supportlibraries;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import logger.Log;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.appium.java_client.android.AndroidDriver;

/**
 * contains all the methods to create a new session and destroy the session
 * after the test(s) execution is over. Each test extends this class.
 */
public class Driver {

    public static AndroidDriver driver = null;
    Properties configFile;
    protected static Properties lobConfigProp = new Properties();
    protected static Properties localeConfigProp = new Properties();
    protected FileInputStream configFis, lobConfigFis, localeConfigFis;
    public Properties testDataFile;
    private final String CONFIG_FILE_PATH = "//src//main//resources//config//config.properties";
    protected File file = new File("");
    Properties configProp = new Properties();
    String OS;

    /**
     * this method starts Appium server. Calls startAppiumServer method to start
     * the session depending upon your OS.
     * 
     * @throws Exception
     *             Unable to start appium server
     */
    // @BeforeSuite
    public void invokeAppium() throws Exception {
        // To be done
    }

    /**
     * this method stops Appium server.Calls stopAppiumServer method to stop
     * session depending upon your OS.
     * 
     * @throws Exception
     *             Unable to stop appium server
     */
    // @AfterSuite
    public void stopAppium() throws Exception {
        // To be done
    }

    /**
     * this method creates the driver depending upon the passed parameter
     * (android or iOS) and loads the properties files (config and test data
     * properties files).
     * 
     * @param os
     *            android or iOS
     * @param methodName
     *            - name of the method under execution
     * @throws Exception
     *             issue while loading properties files or creation of driver.
     */
    @Parameters({"os"})
    @BeforeMethod
    public void createDriver(String os, Method methodName) throws Exception {

        // making os as default if nothing passed
        if (!os.equalsIgnoreCase("android") && (!os.equalsIgnoreCase("iOS"))) {
            os = "android";
        }

        // load property file
        propertiesFileLoad(os);

        File propertiesFile = new File(file.getAbsoluteFile() + "//src//main//resources//log4j.properties");
        PropertyConfigurator.configure(propertiesFile.toString());
        Log.info("--------------------------------------");

        if (os.equalsIgnoreCase("android")) {
            String buildPath = chooseBuild(os);
            androidDriver(buildPath, methodName);
            Log.info("Android driver created");
        }
        else if (os.equalsIgnoreCase("iOS")) {
            String buildPath = chooseBuild(os);
            iOSDriver(buildPath, methodName);
            Log.info("iOS driver created");
        }
    }

    /**
     * this method quit the driver after the execution of test(s)
     */
    @AfterMethod
    public void tearDown() {
        Log.info("Shutting down driver");
        driver.quit();
    }

    /**
     * this method creates the android driver
     * 
     * @param buildPath
     *            - path to pick the location of the app
     * @param methodName
     *            - name of the method under execution
     * @throws MalformedURLException
     *             Thrown to indicate that a malformed URL has occurred.
     */
    public synchronized void androidDriver(String buildPath, Method methodName) throws MalformedURLException {
        File app = new File(buildPath);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", lobConfigProp.getProperty("deviceName"));
        caps.setCapability("udid", lobConfigProp.getProperty("udid"));
        caps.setCapability("platformName", lobConfigProp.getProperty("platformName"));
        caps.setCapability("platformVersion", lobConfigProp.getProperty("platformVersion"));
        caps.setCapability("noReset", lobConfigProp.getProperty("noReset"));
        caps.setCapability("clearSystemFiles", lobConfigProp.getProperty("clearSystemFiles"));
        caps.setCapability("appPackage", lobConfigProp.getProperty("appPackage"));
        caps.setCapability("appActivity", lobConfigProp.getProperty("appActivity"));
        caps.setCapability("app" , app.getAbsolutePath());
        driver = new AndroidDriver(new URL(lobConfigProp.getProperty("gridURL")), caps);
        
        //By default screenOrientation is PORTRAIT
        if (lobConfigProp.getProperty("screenOrientation").equalsIgnoreCase("LANDSCAPE"))
            driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    /**
     * this method creates the iOS driver
     * 
     * @param buildPath-
     *            path to pick the location of the app
     * @param methodName-
     *            name of the method under execution
     * @throws MalformedURLException
     *             Thrown to indicate that a malformed URL has occurred.
     */
    public void iOSDriver(String buildPath, Method methodName) throws MalformedURLException {
        // To be done
    }

    /**
     * this method loads properties files config and file having test data.
     * 
     * @param platform
     *            android or ios, to load specific test data file.
     * @throws Exception
     *             property files are not loaded successfully
     */
    public void propertiesFileLoad(String platform) throws Exception {
        configFis = new FileInputStream(file.getAbsoluteFile() + CONFIG_FILE_PATH);
        configProp.load(configFis);
        File f = new File(file.getAbsoluteFile() + "//src//main//resources//config//" + platform + "_config.properties");

        if (f.exists() && !f.isDirectory()) {
            lobConfigFis = new FileInputStream(file.getAbsoluteFile() + "/src//main//resources//config//" + platform + "_config.properties");
            lobConfigProp.load(lobConfigFis);

            String locale = lobConfigProp.getProperty("LOCALE");

            localeConfigFis = new FileInputStream(file.getAbsoluteFile() + "//src//main//resources//testData//" + locale + "_" + platform + ".properties");
            System.out.println("Create Session:" + localeConfigFis);
            localeConfigProp.load(localeConfigFis);
        }
        else {
            throw new Exception("Properties files loading failed ");
        }
    }

    /**
     * this method returns the app path file location.
     * 
     * @param invokeDriver
     *            android or ios, to load specific test data file.
     */
    public String chooseBuild(String invokeDriver) {
        String appPath = null;
        if (invokeDriver.equals("android")) {
            appPath = configProp.getProperty("AndroidAppPath");
            return appPath;
        }
        else if (invokeDriver.equals("iOS")) {
            appPath = configProp.getProperty("iOSAppPath");
            return appPath;
        }
        return appPath;
    }
}
