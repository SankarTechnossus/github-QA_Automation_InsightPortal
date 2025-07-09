package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By usernameField = By.id("input28");
    By nextButton = By.xpath("//input[@value='Next']");
    By passwordField = By.name("credentials.passcode");
    By verifyButton = By.xpath("//input[@value='Verify']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Actions
    public void navigateToLoginPage(String url) {
        driver.get(url);
    }

    public void enterUsername(String username) {
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameInput.sendKeys(username);
    }

    public void clickNext() {
        WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextBtn.click();
    }

    public void enterPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordInput.sendKeys(password);
    }

    public void clickVerify() {
        WebElement verifyBtn = wait.until(ExpectedConditions.elementToBeClickable(verifyButton));
        verifyBtn.click();
    }

    // Combined login method
    public void login(String url, String username, String password) throws InterruptedException {
        navigateToLoginPage(url);
        Thread.sleep(5000);
        enterUsername(username);
        clickNext();
        Thread.sleep(3000);
        enterPassword(password);
        Thread.sleep(2000);
        clickVerify();
        Thread.sleep(30000); // wait for dashboard to load
    }
}
