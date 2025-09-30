package pages;

import listeners.ExtentReportListener;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import base.BasePage;
import org.openqa.selenium.WebElement;

public class ExportControl_TransactionTypes_Page extends BasePage {

    public ExportControl_TransactionTypes_Page(WebDriver driver) {
        super(driver);
    }


    // Locators
//    private By transactionTypesLink = By.xpath("//a[@href='/administration/transaction-types/transaction-type']/span[text()='Transaction Types']");
    private By exportControlTransactionTypeLink = By.xpath("//span[text()='Export Control']/parent::a");
//    private By exportControlTransactionTypeLink = By.xpath("//a[@href='/administration/transaction-types/transaction-type']/span[text()='Export Control']");
    private By addNewTransactionTypeLink = By.xpath("//a[@href='/administration/transaction-types/transaction-new' and normalize-space(text())='Add new']");
    private By transactionTypeInput = By.xpath("//input[@id='name' and contains(@class,'text-input') and contains(@class,'default-input')]");
    private By searchByNameInput = By.xpath("//input[@placeholder='Search by Name' and contains(@class,'text-input') and contains(@class,'default-input')]");
    private By activeCheckbox = By.id("isActive");
    private By transactionTypesLink = By.xpath("//a[@href='/administration/transaction-types']//span[normalize-space()='Transaction Types']");



    //Actions


    public void checkActiveCheckbox() {
        WebElement checkbox = driver.findElement(activeCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkbox);
        pause(1000);

        if (!checkbox.isSelected()) {
            checkbox.click();
            ExtentReportListener.getExtentTest().pass("Checked 'Active' checkbox");
        } else {
            ExtentReportListener.getExtentTest().info("'Active' checkbox was already checked");
        }

        pause(1000);
    }


    public void enterSearchByName(String name) {
        WebElement input = driver.findElement(searchByNameInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
        pause(1000);
        input.clear();
        input.sendKeys(name);
        pause(1000);
        ExtentReportListener.getExtentTest().pass("Entered '" + name + "' into Search by Name input field");
    }




    public String generateUniqueTransactionTypeName() {
        return "Test " + new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
    }





    public void enterTransactionType(String transactionName) {
        WebElement input = driver.findElement(transactionTypeInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
        pause(1000);
        input.clear();
        input.sendKeys(transactionName);
        pause(1000);
        ExtentReportListener.getExtentTest().pass("Entered '" + transactionName + "' into Transaction Type input field");
    }





    public void clickAddNewTransactionType() {
        WebElement addNewLink = driver.findElement(addNewTransactionTypeLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", addNewLink);
        pause(1000);
        addNewLink.click();
        pause(1000);
    }


    public void clickExportControlTransactionType() {
        WebElement link = driver.findElement(exportControlTransactionTypeLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        pause(1000); // Wait after scroll
        link.click(); // Click the link
        pause(1000); // Optional wait
    }


    public void clickTransactionTypesLink() {
        WebElement link = driver.findElement(transactionTypesLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        pause(1000);
        link.click();
        pause(1000);
    }





}
