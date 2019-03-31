package supportlibraries;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import logger.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class GenericMethods extends Driver {

    WebDriver driver = null;

    // Common timeout for all tests can be set here
    // Timeout can be implemented even in scenario level - to be done
    public final int timeOut = Integer.getInteger(lobConfigProp.getProperty("timeOut"));

    public GenericMethods(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method verify whether element is present on screen
     *
     * @param targetElement
     *            element to be present
     * @return true if element is present else throws exception
     * @throws InterruptedException
     *             Thrown when a thread is waiting, sleeping, or otherwise
     *             occupied, and the thread is interrupted, either before or
     *             during the activity.
     */
    public Boolean isElementPresent(By targetElement) throws InterruptedException {
        Boolean isPresent = driver.findElements(targetElement).size() > 0;
        return isPresent;
    }

    /**
     * Method to wait for an element to be visible
     *
     * @param targetElement
     *            element to be visible
     * @return true if element is visible else throws TimeoutException
     */
    public void click(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            if (element.isDisplayed()) {
                element.click();
            }
            else {
                Log.info(locator + "Element not found on this page");
            }
        }
        catch (TimeoutException e) {
            Log.logError(this.getClass().getName(), "click", "element not clicked " + locator);
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Method to wait for an element to be visible
     *
     * @param targetElement
     *            element to be visible
     * @return true if element is visible else throws TimeoutException
     */
    public boolean waitForVisibility(By targetElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.visibilityOfElementLocated(targetElement));
            return true;
        }
        catch (TimeoutException e) {
            System.out.println("Element is not visible: " + targetElement);
            throw new TimeoutException(e.getMessage());

        }
    }

    /**
     * Method to wait for an element until it is invisible
     *
     * @param targetElement
     *            element to be invisible
     * @return true if element gets invisible else throws TimeoutException
     */
    public boolean waitForInvisibility(By targetElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(targetElement));
            return true;
        }
        catch (TimeoutException e) {
            System.out.println("Element is still visible: " + targetElement);
            System.out.println();
            System.out.println(e.getMessage());
            throw new TimeoutException();

        }
    }

    /**
     * Method to tap on the screen on provided coordinates
     *
     * @param xPosition
     *            x coordinate to be tapped
     * @param yPosition
     *            y coordinate to be tapped
     */
    public void tap(double xPosition, double yPosition) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> tapObject = new HashMap<String, Double>();
        tapObject.put("startX", xPosition);
        tapObject.put("startY", yPosition);
        js.executeScript("mobile: tap", tapObject);
    }

    /**
     * Method to find an element
     *
     * @param locator
     *            element to be found
     * @return WebElement if found else throws NoSuchElementException
     */
    public WebElement findElement(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element;
        }
        catch (NoSuchElementException e) {
            Log.logError(this.getClass().getName(), "findElement", "Element not found" + locator);
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Method to find all the elements of specific locator
     *
     * @param locator
     *            element to be found
     * @return return the list of elements if found else throws
     *         NoSuchElementException
     */
    public List<WebElement> findElements(By locator) {
        try {
            List<WebElement> element = driver.findElements(locator);
            return element;
        }
        catch (NoSuchElementException e) {
            Log.logError(this.getClass().getName(), "findElements", "element not found" + locator);
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Method to get message test of alert
     *
     * @return message text which is displayed
     */
    public String getAlertText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            return alertText;
        }
        catch (NoAlertPresentException e) {
            throw new NoAlertPresentException();
        }
    }

    /**
     * Method to verify if alert is present
     *
     * @return returns true if alert is present else false
     */
    public boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert();
            return true;
        }
        catch (NoAlertPresentException e) {
            throw new NoAlertPresentException();
        }
    }

    /**
     * Method to Accept Alert if alert is present
     */

    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    /**
     * Method to Dismiss Alert if alert is present
     */

    public void dismissAlert() {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    /**
     * Method to swipe on the screen on provided coordinates
     *
     * @param startX
     *            - start X coordinate to be tapped
     * @param endX
     *            - end X coordinate to be tapped
     * @param startY
     *            - start y coordinate to be tapped
     * @param endY
     *            - end Y coordinate to be tapped
     * @param duration
     *            duration to be tapped
     */

    public void swipe(double startX, double startY, double endX, double endY, double duration) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        // swipeObject.put("touchCount", 1.0);
        swipeObject.put("startX", startX);
        swipeObject.put("startY", startY);
        swipeObject.put("endX", endX);
        swipeObject.put("endY", endY);
        swipeObject.put("duration", duration);
        js.executeScript("mobile: swipe", swipeObject);
    }

    static String uiScrollable(String uiSelector) {
        return "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector + ".instance(0));";
    }

    /**
     * Method to scroll down on screen from java-client 6
     *
     * @param swipeTimes
     *            number of times swipe operation should work
     * @param durationForSwipe
     *            time duration of a swipe operation
     */
    public void scrollDown(int swipeTimes, int durationForSwipe) {
        Dimension dimension = driver.manage().window().getSize();

        for (int i = 0; i <= swipeTimes; i++) {
            int start = (int) (dimension.getHeight() * 0.5);
            int end = (int) (dimension.getHeight() * 0.3);
            int x = (int) (dimension.getWidth() * .5);

            new TouchAction((AppiumDriver) driver).press(PointOption.point(x, start)).moveTo(PointOption.point(x, end)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe))).release().perform();
        }
    }

    /**
     * Method to scroll up on screen from java-client 6
     *
     * @param swipeTimes
     *            number of times swipe operation should work
     * @param durationForSwipe
     *            time duration of a swipe operation
     */
    public void scrollUp(int swipeTimes, int durationForSwipe) {
        Dimension dimension = driver.manage().window().getSize();

        for (int i = 0; i <= swipeTimes; i++) {
            int start = (int) (dimension.getHeight() * 0.3);
            int end = (int) (dimension.getHeight() * 0.5);
            int x = (int) (dimension.getWidth() * .5);

            new TouchAction((AppiumDriver) driver).press(PointOption.point(x, start)).moveTo(PointOption.point(x, end)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe))).release().perform();
        }
    }

    /**
     * Method to capture the screenshot
     */
    public static void captureScreenshot(WebDriver driver, String screenName, String status) throws IOException, InterruptedException {
        // To be done
    }

}
