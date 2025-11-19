package pages.HomePage;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class DashboardPage extends BasePage
{
    // Constructor
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    By lblTestEnvironment = By.xpath("//div[text()='This is a test environment']");
    By linkAdministration = By.xpath("//a[@aria-label='Administration']");

    // Actions
    public boolean VerifyUserLandsOnDashboardPage()
    {
        boolean result = false;
        String text = driver.findElement(lblTestEnvironment).getText();
        if(Objects.equals(text, "THIS IS A TEST ENVIRONMENT"))
        {
            result = true;
        }

        return result;
    }

    public void NavigateToAdministrationModule() {
        click(linkAdministration);
        pause(3000); // optional
    }
}
