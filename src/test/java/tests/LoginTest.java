package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.initDriver();
        driver.get("https://austin-insight4.partners.org/"); // 🔁 Use actual login URL
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLogin() {
        loginPage.login("your-username", "your-password");  // 🔁 Use real credentials
        // Add assertions or validations here
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
