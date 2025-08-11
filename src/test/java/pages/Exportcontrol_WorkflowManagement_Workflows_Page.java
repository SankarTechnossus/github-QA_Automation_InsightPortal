package pages;

import base.BasePage;
import listeners.ExtentReportListener;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.NoSuchElementException;
import utils.BrowserUtility;
import utils.WaitUtility;

import java.time.Duration;
import java.util.List;

public class Exportcontrol_WorkflowManagement_Workflows_Page extends BasePage {

    public  Exportcontrol_WorkflowManagement_Workflows_Page (WebDriver driver) {
        super(driver);
    }


// Locators

    private By workflowManagementLink = By.xpath("//a[.//span[text()='Workflow Management'] and contains(@href, '/workflow-management')]");
    private By exportControlWorkflowsLink = By.xpath("//a[contains(@href, 'workflows-export-control') and text()='Workflows']");
    private By addNewButton = By.xpath("//button[@type='button' and contains(@class,'-primary') and text()='Add New']");
    private By nameInputField = By.xpath("//input[@id='name' and @type='text']");
    private By cancelButton = By.xpath("//button[normalize-space(text())='Cancel']");
    private By saveButton = By.xpath("//button[contains(@class,'button') and contains(@class,'-primary') and normalize-space(text())='Save']");
    private String editButtonForWorkflowXpath = "//tr[.//a[normalize-space(text())='%s']]//button[normalize-space(text())='Edit']";
    private By updateButton = By.xpath("//button[normalize-space(text())='Update']");


//Actions

    public void clickUpdateButton() {
        WebElement button = driver.findElement(updateButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        button.click();
        pause(1000);
    }



    public void appendSanToName() {
        WebElement input = driver.findElement(nameInputField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputField));

        input.sendKeys("San"); // Append instead of replacing
        pause(1000);
    }




    public void clickEditButtonForWorkflow(String workflowName) {
        By editButtonLocator = By.xpath(String.format(editButtonForWorkflowXpath, workflowName));
        WebElement editButton = driver.findElement(editButtonLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", editButton);
        editButton.click();
        pause(1000);
    }


    public void clickSaveButton() {
        WebElement button = driver.findElement(saveButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        button.click();
        pause(1000);
    }


    public void clickCancelButton() {
        WebElement button = driver.findElement(cancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        button.click();
        pause(1000);
    }


    public void selectOptionFromDropdown(String dropDownName,String optionToSelect){
        String dropdownXpath = "//label[normalize-space(text())='"+dropDownName+"']//following-sibling::div//div[contains(@class,'_indicatorsContainer')]";
        String menuListXpath = "//div[contains(@class,'select-menu-outer')]";
        String optionXpath = "//div[contains(@class,'select-menu-outer')]//div[normalize-space(text())='"+optionToSelect+"']";
        BrowserUtility.click(driver,By.xpath(dropdownXpath),dropDownName);
        WaitUtility.waitForVisibility(driver,By.xpath(menuListXpath),20,"option list");
        BrowserUtility.click(driver,By.xpath(optionXpath),optionToSelect);

    }


    public void selectOptionFromDropdownExportcontrolstatus(String dropDownName,String optionToSelect){
        String dropdownXpath = "//label[normalize-space(text())='"+dropDownName+"']//following-sibling::div//div[contains(@class,'_indicatorsContainer')]";
        String menuListXpath = "//div[contains(@class,'select-menu-outer')]";
        String optionXpath = "//div[contains(@class,'select-menu-outer')]//div[normalize-space(text())='"+optionToSelect+"']";
        BrowserUtility.click(driver,By.xpath(dropdownXpath),dropDownName);
        WaitUtility.waitForVisibility(driver,By.xpath(menuListXpath),20,"option list");
        BrowserUtility.click(driver,By.xpath(optionXpath),optionToSelect);
        BrowserUtility.click(driver, By.xpath(dropdownXpath), dropDownName); // collapse

    }



    public void enterName(String name) {
        WebElement input = driver.findElement(nameInputField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputField));

        input.clear();
        input.sendKeys(name);
        pause(1000);
    }





    public void clickAddNewButton() {
        WebElement button = driver.findElement(addNewButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addNewButton));

        button.click();
        pause(1000);
    }




    public void clickExportControlWorkflows() {
        WebElement link = driver.findElement(exportControlWorkflowsLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(exportControlWorkflowsLink));

        link.click();
        pause(1000);
    }




    public void clickWorkflowManagementLink() {
        WebElement link = driver.findElement(workflowManagementLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(workflowManagementLink));

        link.click();
        pause(1000);
    }




}







