package pages.Administration.Instructions_Management;

import base.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class InstructionsManagement_ExportControlPage extends BasePage {

    // Constructor
    public InstructionsManagement_ExportControlPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    By linkInstructionsManagement = By.xpath("//span[text()='Instructions Management']/..");
    By linkExportControl = By.xpath("//span[text()='Export Control']/../../a[@href='/administration/instructions-management']");
    By buttonAddNew = By.xpath("//button[text()='Add new']");

    By inputPage = By.xpath("//input[@id='page']");
    By inputOffice = By.xpath("//input[@id='office']");
    By inputContent = By.xpath("//div[@class='fr-element fr-view']");
    By buttonCreate = By.xpath("//button[text()='Create']");

    // Functions
    public void NavigateToInstructionsManagementExportControlPage() {

        //Click on Instructions Management navigation link
        waitForPresence(linkInstructionsManagement);
        click(linkInstructionsManagement);
        pause(2000);

        //Click on Export Control under Forms Management
        waitForPresence(linkExportControl);
        click(linkExportControl);
        pause(2000);
    }

    public void AddNewInstructions(String page, String officeName, String contentName) {
        click(buttonAddNew);
        pause(2000);

        type(inputPage, page);
        type(inputOffice, officeName);

        click(inputContent);
        pause(2000);

        type(inputContent, contentName);

        click(buttonCreate);
        pause(2000);
    }

    public boolean VerifyInstructionsAreAddedSuccessfully(String pageName, String officeName, String contentName) {
        boolean result = false;

        // Get row count
        int noOfRows = driver.findElements(By.xpath("//tbody/tr")).size();

        for (int i = 1; i<=noOfRows; i++)
        {
            // Get page name from rows
            String page = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]/a")).getText();
            String office = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[2]/div")).getText();
            String content = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[3]")).getText();

            if(Objects.equals(page, pageName) && Objects.equals(office, officeName) && Objects.equals(content, contentName))
            {
                result = true;
                break;
            }
        }
        return result;
    }

    public void DeleteInstructionsIfAlreadyExist(String page) {
        //Verify if there is an existing page & office instruction and if yes, delete that
        int noOfRows = driver.findElements(By.xpath("//tbody/tr")).size();

        for (int i = 1; i<=noOfRows; i++)
        {
            // Get page name from rows
            String pageName = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]/a")).getText();
            String officeName = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[2]/div")).getText();

            if(Objects.equals("Personnel Exclusion", officeName) || Objects.equals("Export Control Request", officeName))
            {
                if (Objects.equals(page, pageName))
                {
                    driver.findElement(By.xpath("(//button[@aria-label='Delete instruction'])[" + i + "]")).click();
                    pause(2000);

                    // Switch to alert
                    Alert alert = driver.switchTo().alert();

                    // Accept the alert (click 'OK')
                    alert.accept();
                    pause(2000);
                    if(driver.findElement(By.xpath("//div[text()='Instruction Deleted successfully.']")).isDisplayed())
                    {
                        pause(5000);

                        break;
                    }
                }
            }
        }
    }
}
