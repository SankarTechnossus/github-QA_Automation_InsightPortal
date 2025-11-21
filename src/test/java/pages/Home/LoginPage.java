package pages.Home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.BasePage;

public class LoginPage extends BasePage {

    // Locators
    By usernameField = By.xpath("//input[@id='input28']");
    By nextButton = By.xpath("//input[@value='Next']");
    By passwordField = By.xpath("//input[@name='credentials.passcode']");
    By verifyButton = By.xpath("//input[@value='Verify']");

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public void LoginIntoApplication(String username, String password) {
        type(usernameField, username);
        click(nextButton);
        type(passwordField, password);
        click(verifyButton);
        pause(10000);
    }
}
