package pages;
import listeners.ExtentReportListener;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class Exportcontrol_RecordTypes_Page extends BasePage{


    public Exportcontrol_RecordTypes_Page(WebDriver driver) {
        super(driver);
    }



    // Locators

    private By recordTypesLink = By.xpath("//a[@class='label' and @href='/administration/record-types/record-type' and span[text()='Record Types']]");
    private By exportControlLink = By.xpath("//a[span[text()='Record Types']]/ancestor::div[contains(@class,'menu-item')]/following-sibling::div//span[text()='Export Control']");
    private By addRecordTypeLink = By.xpath("//a[@class='_link_ogtko_1' and text()='Add Record Type']");
    private By recordTypeInput = By.xpath("//label[text()='Enter Record Type']/following::input[contains(@class,'text-input') and @id='refMeaning']");
    private By activeCheckbox = By.id("active");
    private By createButton = By.xpath("//button[@type='button' and contains(text(), 'Create')]");
    private By cancelButton = By.xpath("//a[contains(@class,'_cancelLink_1i3of_13') and text()='Cancel']");
    private By searchByNameInput = By.xpath("//input[@placeholder='Search by Name']");
    private By searchButton = By.xpath("//button[@type='button' and text()='Search']");
    private By clearSelectionsButton = By.xpath("//button[@type='button' and text()='Clear Selections']");
    private By addCategoryLink = By.xpath("//a[@class='_link_ogtko_1' and text()='Add Category']");
    private By categoryDropdown = By.xpath("//div[contains(@class,'select-dropdown-indicator')]");
    private final String categoryOptionXpath = "//div[text()='%s']";
    private By refMeaningInput = By.xpath("//input[contains(@class,'default-input') and @type='text']");
    private By moduleDropdownArrow = By.xpath("//div[contains(@class,'select-dropdown-indicator')]");
    private final String moduleOptionXpath = "//div[text()='%s']";



    //Actions


    public void selectModuleAsExportControl() {
        WebElement dropdownArrow = driver.findElement(moduleDropdownArrow);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownArrow);
        pause(1000); // Stabilization wait

        dropdownArrow.click(); // Open dropdown

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for dropdown options to render
        By exportControlOption = By.xpath(String.format(moduleOptionXpath, "Export Control"));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(exportControlOption));
        option.click(); // Select the option

        ExtentReportListener.getExtentTest().pass("Selected 'Export Control' from Module dropdown successfully");
        pause(1000); // Optional post-click wait
    }



    public void enterRefMeaning(String value) {
        WebElement input = driver.findElement(refMeaningInput);
        input.clear();
        input.sendKeys(value);

    }


    public void selectCategory(String categoryName) {
        WebElement dropdown = driver.findElement(categoryDropdown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
        pause(1000);
        dropdown.click(); // Open the dropdown

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By optionLocator = By.xpath(String.format(categoryOptionXpath, categoryName));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
        option.click(); // Select the category

        ExtentReportListener.getExtentTest().pass("Selected '" + categoryName + "' from Category dropdown successfully");
        pause(1000);
    }




    public void clickAddCategoryLink() {
        WebElement link = driver.findElement(addCategoryLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        pause(1000); // Scroll pause
        link.click();
        pause(3000); // Wait for navigation
        ExtentReportListener.getExtentTest().pass("Clicked 'Add Category' link successfully");
    }


    public void clickClearSelectionsButton() {
        WebElement button = driver.findElement(clearSelectionsButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        pause(1000); // Wait after scroll
        button.click();
        pause(2000); // Wait for reset action
        ExtentReportListener.getExtentTest().pass("Clicked 'Clear Selections' button successfully");
    }



    public void clickSearchButton() {
        WebElement button = driver.findElement(searchButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        pause(1000); // Wait after scroll
        button.click();
        pause(2000); // Wait for search results
        ExtentReportListener.getExtentTest().pass("Clicked 'Search' button successfully");
    }



    public void searchRecordByName(String name) {
        WebElement input = driver.findElement(searchByNameInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
        pause(1000); // Wait after scroll
        input.clear();
        input.sendKeys(name);
        pause(2000); // Optional wait to let results filter
        ExtentReportListener.getExtentTest().pass("Entered '" + name + "' in 'Search by Name' input field");
    }


    public void clickCancelButton() {
        WebElement cancel = driver.findElement(cancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cancel);
        pause(1000); // Optional scroll wait

        cancel.click();
        pause(3000); // Wait for navigation

        ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button and navigated back to Record Types list");
    }



    public void clickCreateButton() {
        WebElement button = driver.findElement(createButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        pause(1000); // Optional scroll wait

        button.click();
        pause(3000); // Wait for action or navigation

        ExtentReportListener.getExtentTest().pass("Clicked 'Create' button successfully");
    }



    public void tickActiveCheckbox() {
        WebElement checkbox = driver.findElement(activeCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkbox);
        pause(1000); // Optional scroll wait

        if (!checkbox.isSelected()) {
            checkbox.click();
            ExtentReportListener.getExtentTest().pass("Checked 'Active' checkbox");
        } else {
            ExtentReportListener.getExtentTest().pass("'Active' checkbox was already checked");
        }

        pause(1000); // Optional after click
    }



    public void enterRecordType(String recordTypeName) {
        WebElement input = driver.findElement(recordTypeInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
        pause(1000); // Wait after scroll
        input.clear(); // Clear if anything prefilled
        input.sendKeys(recordTypeName); // Enter the value
        pause(1000); // Optional wait
        ExtentReportListener.getExtentTest().pass("Entered '" + recordTypeName + "' into Record Type input field");
    }

//
//    public void selectModuleAsExportControl() {
//        WebElement dropdownArrow = driver.findElement(moduleDropdownArrow);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownArrow);
//        pause(1000); // Wait after scroll
//        dropdownArrow.click(); // Open the dropdown
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(exportControlOption));
//        option.click(); // Click 'Export Control'
//
//    }


//    public void selectModuleAsExportControl() {
//        WebElement dropdownArrow = driver.findElement(moduleDropdownArrow);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownArrow);
//        pause(1000); // Optional wait
//
//        dropdownArrow.click(); // Open the dropdown
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // Wait for the dropdown options to be visible (container)
//        wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//div[contains(@class,'menu')]")
//        ));
//
//        // Wait and click on "Export Control"
//        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//div[contains(text(),'Export Control')]")
//        ));
//        option.click();
//
//        ExtentReportListener.getExtentTest().pass("Selected 'Export Control' from Module dropdown successfully");
//    }
//


//    public void selectModuleAsExportControl() {
//        WebElement dropdownArrow = driver.findElement(moduleDropdownArrow);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdownArrow);
//        pause(1000); // Just to stabilize
//
//        dropdownArrow.click(); // Open the dropdown
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // Wait for the dropdown menu container
//        wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//div[contains(@class,'select__menu-list')]")
//        ));
//
//        // Then wait and click the option
//        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//div[contains(@class,'select__option') and text()='Export Control']")
//        ));
//        option.click();
//
//        ExtentReportListener.getExtentTest().pass("Selected 'Export Control' from Module dropdown successfully");
//    }




    public void clickAddRecordTypeLink() {
        WebElement link = driver.findElement(addRecordTypeLink);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", link); // Scroll into view
        pause(1000); // Optional wait after scroll
        link.click();
        pause(3000); // Wait for navigation
    }




    public void clickRecordTypesLink() {
        WebElement link = driver.findElement(recordTypesLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link); // Optional scroll
        pause(1000); // Wait after scroll
        link.click();
        pause(3000); // Wait for navigation
    }


    public void clickExportControlUnderRecordTypes() {
        WebElement link = driver.findElement(exportControlLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        pause(1000);
        link.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("record-types"));

        // Assertion inside method (optional)
        Assert.assertTrue(driver.getCurrentUrl().contains("record-types"), "Export Control page did not load properly");
    }


}
