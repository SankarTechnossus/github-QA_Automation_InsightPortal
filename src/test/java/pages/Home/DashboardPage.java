package pages.Home;

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
    By dropdownLogout = By.xpath("//span[@class='fi-down-dir']");
    By linkDashboard = By.xpath("//span[text()='Dashboard']/..");
    By linkLogout = By.xpath("//span[text()='Logout']/..");
    By linkExportControl = By.xpath("//a[@aria-label='Export Control']");


    // Actions
    public boolean VerifyUserLandsOnDashboardPage() {
        boolean result = false;
        waitForPresence(lblTestEnvironment);
        String text = driver.findElement(lblTestEnvironment).getText();
        if(Objects.equals(text, "THIS IS A TEST ENVIRONMENT"))
        {
            result = true;
        }

        return result;
    }

    public void NavigateToAdministrationModule() {
        click(linkAdministration);
        pause(3000);
    }

    public void NavigateBackToDashboardPage() {
        click(dropdownLogout);
        pause(1000);

        click(linkDashboard);
        pause(3000);
    }

    public void NavigateToExportControlModule() {
        waitForPresence(linkExportControl);
        click(linkExportControl);
        pause(2000);
    }

    public void UserLogout() {
        click(dropdownLogout);
        pause(1000);

        click(linkLogout);
        pause(3000);
    }
}
