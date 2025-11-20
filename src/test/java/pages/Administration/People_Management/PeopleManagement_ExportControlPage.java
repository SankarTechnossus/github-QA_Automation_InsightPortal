package pages.Administration.People_Management;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PeopleManagement_ExportControlPage extends BasePage {

    // Constructor
    public PeopleManagement_ExportControlPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    By linkPeopleManagement = By.xpath("//span[text()='People Management']/..");
    By linkExportControl = By.xpath("(//a[@href='/administration/people-management'])[2]");

    // Functions
    public void NavigateToPeopleManagementExportControlPage() {

        //Click on People Management navigation link
        waitForPresence(linkPeopleManagement);
        click(linkPeopleManagement);
        pause(2000);

        //Click on Export Control under People Management
        waitForPresence(linkExportControl);
        click(linkExportControl);
        pause(2000);
    }
}
