package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtility;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    // Constructor to initialize WebDriver
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    // Reusable explicit wait method 10 sec
    public WebElement waitForElement(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }


    // Reusable explicit wait method 30 sec
    public WebElement waitForElement50(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }


    // Reusable explicit wait for present
    public void waitForPresence(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    // Waits until element is visible and clickable, then returns it
    public WebElement waitForVisibleAndClickable(By locator, int timeoutSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return customWait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    // Scrolls into view, waits for clickability, and returns the WebElement
    public WebElement scrollAndWaitForClickable(By locator, int timeoutInSeconds) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }


    public void scrollAndJsClick(By locator, int timeoutInSeconds) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void scrollToBottomOfModal(By locator, int timeoutInSeconds) {
        WebElement scrollableDiv = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", scrollableDiv);
    }

    public void clickElement(By locator, int timeoutInSeconds) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Pause interrupted: " + e.getMessage());
        }
    }

//    public void switchToFrame(By locator, int timeoutInSeconds) {
//        WebElement frame = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
//                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
//    }

    public void switchToFrame(By locator, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

//    public void dragAndDrop(By sourceLocator, By targetLocator, int timeoutInSeconds) {
//        WebElement source = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
//                .until(ExpectedConditions.presenceOfElementLocated(sourceLocator));
//        WebElement target = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
//                .until(ExpectedConditions.presenceOfElementLocated(targetLocator));
//
//        Actions actions = new Actions(driver);
//        actions.dragAndDrop(source, target).build().perform();
//    }

    public void dragAndDrop(By sourceLocator, By targetLocator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        // Wait until both source and target are visible & clickable
        WebElement source = wait.until(ExpectedConditions.elementToBeClickable(sourceLocator));
        WebElement target = wait.until(ExpectedConditions.visibilityOfElementLocated(targetLocator));

        // Scroll both into view (optional but avoids "not clickable at point" issues)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", source);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", target);

        // Perform drag and drop
        Actions actions = new Actions(driver);
        actions.clickAndHold(source)
                .pause(Duration.ofMillis(300))   // small stability pause
                .moveToElement(target)
                .pause(Duration.ofMillis(300))
                .release()
                .build()
                .perform();
    }


    // Optional: Add more reusable methods
    public void click(By locator) {
        waitForElement(locator).click();
    }

    public void type(By locator, String text) {
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void waitAdobeFormToBeVisible(){
        String xpath = "//span[normalize-space(.)='Add form fields for']";
        WaitUtility.waitForVisibility(driver,By.xpath(xpath),60,"Adobe Form");
    }

    public WebElement getVisibleElement(By locator, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void switchToFrame(WebElement frameElement, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }
//
//    public void switchToDefaultContent() {
//        driver.switchTo().defaultContent();
//    }

    public void waitForStaleness(WebElement element, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.stalenessOf(element));
    }

    public void waitForVisible(By locator, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
