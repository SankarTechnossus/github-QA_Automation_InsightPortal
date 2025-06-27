package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    // Constructor to initialize PageFactory
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page Elements
    @FindBy(id = "input28")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@value='Next']")
    private WebElement nextButton;

    @FindBy(name = "credentials.passcode")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Verify']")
    private WebElement verifyButton;

    // Page Actions
    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void clickNext() {
        nextButton.click();
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickVerify() {
        verifyButton.click();
    }
}
