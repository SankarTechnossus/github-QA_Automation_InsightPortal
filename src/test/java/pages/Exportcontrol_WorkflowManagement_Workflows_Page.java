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
//    private By dropdownPlaceholder = By.xpath("//div[contains(@class,'select-placeholder') and text()='Select...']");
//    private String dropdownOptionXpath = "//div[contains(@class,'select__option') and normalize-space(text())='%s']";
//    private By transactionTypeDropdownArrow = By.xpath("//label[@for='additionalProps.transactionTypeId']/parent::div/following-sibling::div//div[contains(@class,'select-dropdown-indicator')]");
    private By recordTypeDropdownArrow = By.xpath("//label[@for='additionalProps.exportControlOfficeId']/ancestor::div[contains(@class,'form-row')]/following-sibling::div//div[@aria-hidden='true' and contains(@class,'select-dropdown-indicator')]");
    private String dropdownOptionXpath = "//div[contains(@class,'select__option') and normalize-space(text())='%s']";




//Actions

    public void selectRecordType(String recordTypeValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Step 1: Click the dropdown arrow
        WebElement dropdownArrow = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[@for='additionalProps.exportControlOfficeId']/ancestor::div[contains(@class,'form-row')]/following-sibling::div//div[contains(@class,'select-dropdown-indicator')]")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownArrow);
        dropdownArrow.click();

        // Step 2: Wait until dropdown container appears (menu)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("select__menu")));

        pause(500); // Let options settle

        // Step 3: Now locate and click the option (using contains instead of exact match)
        By optionLocator = By.xpath(String.format("//div[contains(@class,'select__option') and contains(text(),'%s')]", recordTypeValue));
        WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(optionLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

        pause(500);
    }





//    public void selectRecordType(String recordTypeValue) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // Step 1: Click dropdown arrow
//        WebElement dropdownArrow = wait.until(ExpectedConditions.elementToBeClickable(recordTypeDropdownArrow));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownArrow);
//        dropdownArrow.click();
//
//        pause(1000); // let dropdown populate
//
//        // Step 2: Click the dynamic option
//        By optionLocator = By.xpath(String.format(dropdownOptionXpath, recordTypeValue));
//        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
//
//        pause(1000);
//    }


//
//    public void selectRecordTypeAsTest() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // Step 1: Click dropdown arrow
//        WebElement dropdownArrow = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//label[@for='additionalProps.exportControlOfficeId']/parent::div/following-sibling::div//div[contains(@class,'select-dropdown-indicator')]")
//        ));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownArrow);
//        dropdownArrow.click();
//
//        pause(1000); // Let dropdown render
//
//        // Step 2: Click option "Test" via JS
//        By optionLocator = By.xpath("//div[contains(@class,'select__option') and normalize-space(text())='Test']");
//        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", option);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
//
//        pause(1000); // Post click pause
//    }
//


//    public void selectTransactionTypeAsTest() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // Step 1: Scroll to and click the dropdown arrow
//        WebElement dropdownArrow = wait.until(ExpectedConditions.elementToBeClickable(transactionTypeDropdownArrow));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownArrow);
//        dropdownArrow.click();
//
//        pause(1000); // Optional: allow dropdown options to load
//
//        // Step 2: Locate and click the "Test" option
//        By testOption = By.xpath("//div[contains(@class,'select__option') and normalize-space(text())='Test']");
//        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(testOption));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
//
//        pause(1000); // Optional pause after selection
//    }

//
//    public void selectTransactionTypeAsTest() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // Scroll and click dropdown arrow
//        WebElement dropdownArrow = wait.until(ExpectedConditions.elementToBeClickable(transactionTypeDropdownArrow));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownArrow);
//        dropdownArrow.click();
//
//        pause(1000); // Let dropdown options render
//
//        // Use JS click for "Test" option
//        By testOption = By.xpath("//div[contains(@class,'select__option') and normalize-space(text())='Test']");
//        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(testOption)); // still needed for visibility
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", option);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
//
//        pause(1000);
//    }
//
//
//
//
//
//    public void selectDropdownOption(String optionText) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownPlaceholder));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
//        dropdown.click();
//
//        pause(1000); // Allow options to load
//
//        By optionLocator = By.xpath(String.format(dropdownOptionXpath, optionText));
//        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
//
//        pause(1000);
//    }
//
//
//
//
//
//    public void selectRecordTypeAsExportControlRequest() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // Step 1: Click dropdown arrow to show options
//        By dropdownArrow = By.xpath("//div[contains(@class,'dropdown-indicator')]");
//        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownArrow));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
//        dropdown.click();
//        pause(1000); // Optional post-select pause
//    }
//



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







