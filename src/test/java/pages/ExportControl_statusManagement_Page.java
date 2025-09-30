package pages;


import listeners.ExtentReportListener;
import org.openqa.selenium.*;
import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExportControl_statusManagement_Page extends BasePage{


    public ExportControl_statusManagement_Page(WebDriver driver) {
        super(driver);
    }



    // Locators

    private By statusManagementLink = By.xpath("//a[.//span[text()='Status Management']]");
    private By statusManagementParent = By.xpath("//a[.//span[text()='Status Management']]");
    private By exportControlSubmenu = By.xpath("//a[.//span[text()='Export Control'] and contains(@class,'_menuItem')]");
    private By addStatusButton = By.xpath("//button[text()='Add Status' and contains(@class,'-primary')]");
    private By statusNameInput = By.xpath("//input[@type='text' and contains(@class,'default-input')]");
//    private By cancelButton = By.xpath("//button[text()='Cancel' and contains(@class,'-unstyled')]");
    private By cancelButton = By.xpath(
            "//div[contains(@class,'modal-content-wrapper')]//button[normalize-space()='Cancel']"
    );

    private By addButton = By.xpath("//button[text()='Add' and contains(@class,'-primary')]");
    private By statusNameEditInput = By.xpath("//label[.//span[text()='Status Name']]//input[@type='text']");
//    private By activeDropdown = By.xpath("//label[.//span[text()='Active']]/following-sibling::div[contains(@class,'select-control')]");
//    private By optionNo = By.xpath("//div[contains(@class,'menuPortal')]//div[text()='No']");
    private By saveButton = By.xpath("//button[text()='Save' and contains(@class,'-primary')]");
    private By activeDropdownArrow = By.xpath("//div[contains(@class,'_indicatorsContainer_') and contains(@class,'css-1wy0on6')]");
    private By optionNo = By.xpath("//div[contains(@class,'menuPortal')]//div[text()='No']");





    //Actions

    public void selectActiveAsNo() {
        WebElement dropdownArrow = driver.findElement(activeDropdownArrow);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownArrow);
        dropdownArrow.click(); // Open the dropdown

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement noOption = wait.until(ExpectedConditions.elementToBeClickable(optionNo));
        noOption.click(); // Select "No"

        pause(1000);
    }


    public void enterStatusName(String value) {
        WebElement input = driver.findElement(statusNameInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(statusNameInput));

        input.clear();
        input.sendKeys(value);

        pause(1000);
    }




    public void clickSaveButton() {
        WebElement button = driver.findElement(saveButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();

        pause(1000);
    }



//    public void selectActiveAsNo() {
//        WebElement dropdown = driver.findElement(activeDropdown);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
//        dropdown.click(); // open dropdown
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionNo));
//        option.click(); // select "No"
//
//        pause(1000);
//    }



    public void appendToStatusName(String textToAppend) {
        WebElement input = driver.findElement(statusNameEditInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(statusNameEditInput));

        input.sendKeys(textToAppend); // append only — no clear

        pause(1000);
    }



    public void clickEditButtonForStatus(String statusName) {
        String dynamicXpath = String.format(
                "//td[@data-column='name' and @data-value='%s']/parent::tr//i[contains(@class,'fi-edit')]",
                statusName
        );
        By editIcon = By.xpath(dynamicXpath);

        WebElement icon = driver.findElement(editIcon);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", icon);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(editIcon)).click();

        pause(1000);
    }



    public void clickDeleteButtonForStatus(String statusName) {
        String dynamicXpath = String.format("//td[@data-column='name' and @data-value='%s']/parent::tr//i[contains(@class,'fi-remove')]", statusName);
        By deleteIcon = By.xpath(dynamicXpath);

        WebElement icon = driver.findElement(deleteIcon);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", icon);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(deleteIcon)).click();

        pause(1000);

        // Handle confirmation pop-up
        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        alertWait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        pause(1000);
    }





    public void clickAddButton() {
        WebElement button = driver.findElement(addButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

        pause(1000);
    }



    //counter used below

//    private static int statusCounter = 1; // Initial value
//
//    public String enterStatusNameWithAutoIncrement(String baseName) {
//        String paddedNumber = String.format("%02d", statusCounter);
//        String valueToEnter = baseName + paddedNumber;
//
//        WebElement input = driver.findElement(statusNameInput);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(statusNameInput));
//
//        input.clear();
//        input.sendKeys(valueToEnter);
//
//        statusCounter++; // Increment for next call
//        pause(1000);
//
//        return valueToEnter; // Return generated name
//    }




    public void clickCancelButton() {
        WebElement button = driver.findElement(cancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();

        pause(1000);
    }

//
//    public void enterStatusName(String value) {
//        WebElement input = driver.findElement(statusNameInput);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(statusNameInput));
//
//        input.clear();
//        input.sendKeys(value);
//
//        pause(1000);
//    }



    public void clickAddStatusButton() {
        WebElement button = driver.findElement(addStatusButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addStatusButton)).click();

        ExtentReportListener.getExtentTest().pass("Clicked 'Add Status' button successfully");
        pause(1000);
    }



    public void clickStatusManagementExportControl() {
        WebElement parentMenu = driver.findElement(statusManagementParent);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", parentMenu);
        parentMenu.click(); // Expand the menu

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submenu = wait.until(ExpectedConditions.elementToBeClickable(exportControlSubmenu));
        submenu.click();
        pause(1000);
    }




    public void clickStatusManagementLink() {
        WebElement link = driver.findElement(statusManagementLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(statusManagementLink)).click();

        pause(1000);
    }











}
