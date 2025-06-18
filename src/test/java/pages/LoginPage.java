package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators ()
    By usernameInput = By.xpath("//input[@id='input28']");// or whatever locator strategy is appropriate
    By loginButton = By.xpath("//input[@value='Next']"); // use actual ID
    By passwordInput = By.xpath("//input[@name='credentials.passcode']");
    By verifyButton = By.xpath("//input[@value='Verify']");
    // Methods
    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void clickVerify() {
        driver.findElement(verifyButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        clickVerify();
    }
}
