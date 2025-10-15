package pages;

import base.BasePage;
import listeners.ExtentReportListener;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
    private By saveButton = By.xpath("//div[contains(@class,'buttons-cell')]//button[normalize-space()='Save']");
//    private By saveButton = By.xpath("//button[contains(@class,'button') and contains(@class,'-primary') and normalize-space(text())='Save']");
    private String editButtonForWorkflowXpath = "//tr[.//a[normalize-space(text())='%s']]//button[normalize-space(text())='Edit']";
    private By updateButton = By.xpath("//button[normalize-space(text())='Update']");
    // Scope: the workflows grid/table
    private By workflowGrid = By.cssSelector("div.panel.-table, div.list, table"); // adjust if you have a stable class

    // Any Edit control in the grid (link or button, text or title)
    private By editControls = By.xpath(
            ".//a[normalize-space()='Edit' or @title='Edit']" +
                    " | .//button[normalize-space()='Edit' or @title='Edit' or .//i[contains(@class,'edit')]]"
    );


//Actions

    public void clickFirstEdit() {
        WebElement grid = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(workflowGrid));

        // Wait until at least one Edit is present
        List<WebElement> edits = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver1 -> {
                    List<WebElement> els = grid.findElements(editControls);
                    return (els != null && !els.isEmpty()) ? els : null;
                });

        // Pick the first *displayed* one
        WebElement first = edits.stream().filter(WebElement::isDisplayed).findFirst().orElse(edits.get(0));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", first);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(first));

        try {
            first.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", first);
        }

        pause(2000);
    }


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







