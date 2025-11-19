package pages.Administration.Forms_Management;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FormsManagement_ExportControlPage extends BasePage {

    // Constructor
    public FormsManagement_ExportControlPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    By linkFormsManagement = By.xpath("//span[text()='Forms Management']/..");
    By linkExportControl = By.xpath("//a[contains(@href, 'forms-management-export-control')]");
    By linkAddNew = By.xpath("//a[text()='Add new']");

    By inputName = By.id("name");
    By textareaDescription = By.id("description");
    By inputType = By.id("type");
    By inputCategory = By.id("category");
    By inputCategorySeqNo = By.id("categorySequenceNo");
    By buttonCreate = By.xpath("//button[text()='Create']");

    By buttonChangeActiveVersion = By.xpath("//button[text()='Change active version']");
    By buttonActivate = By.xpath("//button[text()='Activate']");
    By linkActiveVersion = By.xpath("//span[text()='Active']/../../a");

    By inputInstructions = By.xpath("//div[@class='fr-wrapper show-placeholder']/div");
    By buttonSave = By.xpath("//button[text()='Save']");

    // Functions
    public void NavigateToFormsManagementExportControlPage() {
        //Click on Forms Management navigation link
        click(linkFormsManagement);
        pause(2000);

        //Click on Export Control under Forms Management
        click(linkExportControl);
        pause(2000);
    }

    public void ClickAddNewLink() {
        click(linkAddNew);
        pause(2000);
    }

    public boolean VerifyUserIsOnNewFormPage() {
        boolean result = false;
        String name = driver.findElement(By.xpath("//div[@class='simple-bread-crumbs']//span[3]")).getText();
        if(Objects.equals(name, "New form"))
        {
            result=true;
        }
        return result;
    }

    public void CreateNewForm(String formName, String desc, String formType, String category, String seqNo) {
        // Generate a new name and enter it in the name field
        type(inputName, formName);

        //Enter description
        type(textareaDescription, desc);

        //Select type
        type(inputType, formType);
        pause(1000);
        driver.findElement(By.xpath("//div[text()='" + formType + "']")).click();
        pause(2000);

        //Enter category
        type(inputCategory, category);
        pause(1000);
        driver.findElement(By.xpath("//div[text()='" + category + "']")).click();
        pause(2000);

        //Enter category seq no
        type(inputCategorySeqNo, seqNo);

        //Click on Create button
        click(buttonCreate);
        pause(2000);
    }

    public boolean VerifyFormIsCreatedSuccessfully(String formName) {
        boolean result = false;

        String name = driver.findElement(By.xpath("//div[@class='simple-bread-crumbs']//span[3]")).getText();
        String versionNo = driver.findElement(By.xpath("//header[text()='Form Versions']/span")).getText();
        String createdOn = driver.findElement(By.xpath("//dt[text()='Created on:']/following::dd/span")).getText();

        // Get current date
        LocalDate currentDate = LocalDate.now();

        // Format the date in MM/DD/YY format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String formattedDate = currentDate.format(formatter);

        if(Objects.equals(name, formName) && Objects.equals(versionNo, "1") && Objects.equals(createdOn, formattedDate))
        {
            result=true;
        }
        return result;
    }

    public boolean ChangeFormStatusToActiveAndVerifyStatus() {
        boolean result = false;

        click(buttonChangeActiveVersion);
        pause(1000);

        click(buttonActivate);
        pause(1000);

        String activeOn = driver.findElement(By.xpath("(//dt[text()='Activated on:']/following::dd/span)[1]")).getText();
        String status = driver.findElement(By.xpath("//span[text()='Active']")).getText();

        // Get current date
        LocalDate currentDate = LocalDate.now();

        // Format the date in MM/DD/YY format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String formattedDate = currentDate.format(formatter);

        if(Objects.equals(status, "Active") && Objects.equals(activeOn, formattedDate))
        {
            result=true;
        }
        return result;
    }

    public void ClickOnTheActiveVersion() {
        click(linkActiveVersion);
        pause(2000);
    }

    public boolean AddInstructionsAndVerifyItIsSavedSuccessfully(String instructions) {
        boolean result = false;
        click(inputInstructions);
        type(inputInstructions, instructions);
        click(buttonSave);
        pause(2000);

        if(driver.findElement(By.xpath("//div[text()='Form has been saved successfully.']")).isDisplayed())
        {
            result = true;
        }
        return result;
    }
}
