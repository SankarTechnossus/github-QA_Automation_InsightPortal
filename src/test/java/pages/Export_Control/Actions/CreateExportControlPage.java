package pages.Export_Control.Actions;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class CreateExportControlPage extends BasePage {

    // Constructor
    public CreateExportControlPage(WebDriver driver) {
        super(driver);
    }

    // General Locators
    By buttonActions = By.xpath("//button[text()='Actions']");
    By linkCreateExportControl = By.xpath("//span[text()='Create Export Control']/..");
    By linkInitialReview = By.xpath("//div[text()='Initial Review (IR)']/..");

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

    //Attachment Locators
    By linkAttachments = By.xpath("//span[text()='Attachments']/..");
    By inputSearchAttachment = By.xpath("//input[@placeholder='Search by attachments...']");
    By buttonSearch = By.xpath("//button[text()='Search']");
    By linkSelectFilesFromComputer = By.xpath("//span[text()='select files from computer']");
    By fileInput = By.xpath("//input[@type='file']");
    By buttonClearSelections = By.xpath("//button[text()='Clear Selections']");

    By inputAttachmentType = By.xpath("//td[@data-column='exportControlAttachmentCategoryId']/div//input");
    By textareaAttachmentDescription = By.xpath("//td[@data-column='description']/textarea");
    By buttonDeleteAttachment = By.xpath("//button[@aria-label='Delete attachment']");
    By buttonOK = By.xpath("//button[text()='OK']");

    //People Locators
    By linkPeople = By.xpath("//span[text()='People']/..");

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

    public void NavigateToPeople() {
        click(linkPeople);
        pause(2000);
    }

    public void UploadAnAttachment(String filePath) {
        pause(2000);

        WebElement uploadElement = driver.findElement(fileInput);

        // Directly send file path (bypasses drag/drop UI)
        uploadElement.sendKeys(filePath);
        pause(3000);
    }

    public boolean VerifyIfFileIsUploadedSuccessfully(String name) {
        pause(3000);

        return driver.findElement(By.xpath("//div[text()='" + name + "']")).isDisplayed();
    }

    public boolean EnterAttachmentTypeAndDescriptionAndVerifyTheGrouping(String fileName, String typeName, String desc) {
        boolean result = false;

        // Select Attachment type
        click(inputAttachmentType);
        pause(1000);

        driver.findElement(By.xpath("//div[text()='" + typeName + "']")).click();
        pause(1000);

        // Enter Attachment Description
        type(textareaAttachmentDescription, desc);
        pause(3000);

        // Search for the attachment
        driver.findElement(inputSearchAttachment).clear();
        type(inputSearchAttachment, fileName);
        click(buttonSearch);
        pause(3000);

        // Verify of attachment group is visible
        if(driver.findElement(By.xpath("//span[text()='" + typeName + "']")).isDisplayed() && driver.findElement(By.xpath("//span[text()='1']")).isDisplayed())
        {
            result = true;
        }
        return result;
    }

    public boolean DeleteAttachmentAndVerifyAttachmentDeletedSuccessfully(String typeName) {
        boolean result = false;

        // Expand the group
        driver.findElement(By.xpath("//span[text()='" + typeName + "']")).click();
        pause(2000);

        click(buttonDeleteAttachment);
        if(driver.findElement(By.xpath("//div[text()='Are you sure you want to delete this attachment?']")).isDisplayed())
        {
            click(buttonOK);
            pause(2000);

            if(driver.findElement(By.xpath("//div[text()='Document was successfully deleted.']")).isDisplayed())
            {
                result = true;
                pause(5000);

                driver.findElement(inputSearchAttachment).clear();
                click(buttonSearch);
                pause(3000);

                //click(buttonClearSelections);
                driver.navigate().refresh();
                pause(2000);
            }
        }
        return result;
    }

    public boolean VerifyInstructionsForPeople(String formInst) {
        boolean result = false;

        // Click on Instructions link
        driver.findElement(By.xpath("//span[text()='Instructions']")).click();
        pause(2000);

        // Verify Instructions
        String name = driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).getText();
        if(Objects.equals(name, formInst))
        {
            result=true;
        }

        return result;
    }

    public boolean VerifyInstructionsForAttachments(String formInst) {
        boolean result = false;

        // Click on Instructions link
        driver.findElement(By.xpath("//span[text()='Instructions']")).click();
        pause(2000);

        // Verify Instructions
        String name = driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).getText();
        if(Objects.equals(name, formInst))
        {
            result=true;
        }

        return result;
    }

    public boolean VerifyInstructionsForReviewLetter(String formInst) {
        boolean result = false;

        // Click on Initial Review link
        click(linkInitialReview);
        pause(5000);

        // Verify Instructions
        if(driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).isDisplayed())
        {
            String name = driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).getText();
            if(Objects.equals(name, formInst))
            {
                result=true;
            }
        }
        else {
            // Click on Instructions link
            driver.findElement(By.xpath("//span[text()='Instructions']")).click();
            pause(3000);

            String name = driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).getText();
            if(Objects.equals(name, formInst))
            {
                result=true;
            }
        }




        return result;
    }
}
