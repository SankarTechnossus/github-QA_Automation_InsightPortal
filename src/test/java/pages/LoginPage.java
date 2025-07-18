package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import base.BasePage;

public class LoginPage extends BasePage {

    // Locators
    private By usernameField = By.xpath("//input[@id='input28']");
    private By nextButton = By.xpath("//input[@value='Next']");
    private By passwordField = By.xpath("//input[@name='credentials.passcode']");
    private By verifyButton = By.xpath("//input[@value='Verify']");

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public void enterUsername(String username) {
        type(usernameField, username);
        pause(10000); // optional
    }

    public void clickNext() {
        click(nextButton);
        pause(10000);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
        pause(10000);
    }

    public void clickVerify() {
        click(verifyButton);
        pause(10000);
    }

    public void loginToInsightPortal(String username, String password) {
        enterUsername(username);
        clickNext();
        enterPassword(password);
        clickVerify();
    }
}
