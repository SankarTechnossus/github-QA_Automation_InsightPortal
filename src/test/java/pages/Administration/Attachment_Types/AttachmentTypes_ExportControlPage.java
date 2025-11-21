package pages.Administration.Attachment_Types;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class AttachmentTypes_ExportControlPage extends BasePage {

    // Constructor
    public AttachmentTypes_ExportControlPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    By linkAttachmentTypes = By.xpath("//span[text()='Attachment Types']/..");
    By linkExportControl = By.xpath("(//a[@href='/administration/attachment-type'])[2]");

    By buttonAddAttachmentType = By.xpath("//button[text()='Add Attachment Type']");
    By inputTypeName = By.xpath("//span[text()='Type Name']/..//input");
    By buttonAdd = By.xpath("//button[text()='Add']");

    // Functions
    public void NavigateToAttachmentTypesExportControlPage() {

        //Click on Attachment Types navigation link
        waitForPresence(linkAttachmentTypes);
        click(linkAttachmentTypes);
        pause(2000);

        //Click on Export Control under People Management
        waitForPresence(linkExportControl);
        click(linkExportControl);
        pause(2000);
    }

    public boolean AddAttachmentTypeAndVerifyInTheAttachmentTypeList(String typeName) {
        boolean result = false;

        // Click on Add Attachment Type button
        click(buttonAddAttachmentType);

        // Enter Attachment Type name
        type(inputTypeName, typeName);

        // Click Add button
        click(buttonAdd);
        pause(5000);

        int noOfRows = driver.findElements(By.xpath("//tbody/tr")).size();

        for (int i = 1; i <= noOfRows; i++)
        {
            String name = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]/div/div")).getText();

            if(Objects.equals(name, typeName))
            {
                result=true;
                break;
            }
        }

        return result;
    }
}
