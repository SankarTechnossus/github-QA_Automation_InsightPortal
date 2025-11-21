package pages.Export_Control.Actions;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Objects;

public class CreateExportControlPage extends BasePage {

    // Constructor
    public CreateExportControlPage(WebDriver driver) {
        super(driver);
    }

    // General Locators
    By buttonActions = By.xpath("//button[text()='Actions']");
    By linkCreateExportControl = By.xpath("//span[text()='Create Export Control']/..");

    // Create Export Control page locators
    By inputPersonnelExclusion = By.xpath("//input[@value='PersonnelExclusion']");
    By inputExportControlRequest = By.xpath("//input[@value='ExportControlRequest']");
    By inputSelectPI = By.xpath("//input[@id='dynamic-form-field-input-74472-PiName']");
    By piSelection = By.xpath("//div[text()='Mass General Brigham']/..");

    By buttonCancel = By.xpath("//button[text()='Cancel']");
    By buttonSave = By.xpath("//button[text()='Save']");
    By buttonCreate = By.xpath("//button[text()='Create']");

    // Export Control Details locators
    By exportControlRecordNo = By.xpath("//dt[text()='Record #']/../dd");
    By lblExportControlSuccessfulCreation = By.xpath("//div[text()='Application has been submitted successfully']");
    By lblExportControlRecordNum = By.xpath("//dt[text()='Record #']");

    By linkAttachments = By.xpath("//span[text()='Attachments']/..");

    // Functions
    public void NavigateToCreateExportControlPage() {
        waitForPresence(buttonActions);
        click(buttonActions);
        pause(1000);

        waitForPresence(linkCreateExportControl);
        click(linkCreateExportControl);
        pause(3000);
    }

    public void CreateExportControl(String pi) {
        // Select Export Control Type
        waitForPresence(inputExportControlRequest);
        click(inputExportControlRequest);
        pause(1000);

        // Select PI Name
        type(inputSelectPI, pi);
        pause(1000);
        click(piSelection);
        pause(2000);

        // Click Create button
        click(buttonCreate);
        pause(2000);

        waitForPresence(lblExportControlSuccessfulCreation);
    }

    public boolean VerifyExportControlIsCreatedSuccessfully() {
        pause(5000);
        waitForPresence(lblExportControlRecordNum);
        return driver.findElement(lblExportControlRecordNum).isDisplayed();
    }

    public String GetExportControlRecordNumber() {
        pause(2000);
        return driver.findElement(exportControlRecordNo).getText();
    }

    public boolean VerifyCreatedFormIsVisible(String formName) {
        return driver.findElement(By.xpath("//span[text()='" + formName + "']/..")).isDisplayed();
    }

    public boolean VerifyInstructionsAssociatedWithFormIsVisibleUnderExportControl(String formName, String formInst) {
        boolean result = false;

        // Select the form
        driver.findElement(By.xpath("//span[text()='" + formName + "']/..")).click();
        pause(1000);

        // Click on Instructions link
        driver.findElement(By.xpath("//span[text()='Instructions']")).click();
        pause(1000);

        // Verify Instructions
        String name = driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).getText();
        if(Objects.equals(name, formInst))
        {
            result=true;
        }

        return result;
    }

    public void NavigateToAttachments() {
        click(linkAttachments);
        pause(2000);
    }
}
