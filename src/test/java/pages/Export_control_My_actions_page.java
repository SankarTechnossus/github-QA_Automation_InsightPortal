package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Export_control_My_actions_page extends BasePage {

    public Export_control_My_actions_page(WebDriver driver) {
        super(driver);
    }



    //Locators

    private By exportControlLink = By.xpath("//a[@href='/export-control' and contains(@class,'module-link')]");

    private By recordTypeControl = By.xpath("//label[normalize-space()='Record Type']/following::div[contains(@class,'select-control')][1]");
    private By recordTypeInput   = By.xpath("//label[normalize-space()='Record Type']/following::div[contains(@class,'select-control')][1]//input[@role='combobox']");

    private By recordNumberInput = By.xpath("//label[normalize-space()='Record Number']/following::input[1]");

    private By transactionTypeControl = By.xpath("//label[normalize-space()='Transaction Type']/following::div[contains(@class,'select-control')][1]");
    private By transactionTypeInput   = By.xpath("//label[normalize-space()='Transaction Type']/following::div[contains(@class,'select-control')][1]//input[@role='combobox']");

    private By agreementNumbersInput  = By.xpath("//label[normalize-space()='Agreement Numbers']/following::input[1]");

    private By searchButton = By.xpath("//button[contains(@class,'-primary') and normalize-space()='Search']");

    // React-Select option (menu) – used by both dropdowns
    private By optionBy(String text) {
        return By.xpath("//div[@role='option' and normalize-space()='" + text + "']");
    }

    private By actionRequiredLink = By.xpath("//a[@href='/export-control/action-required' and contains(@class,'label')]");




    //Actions


    public void clickActionRequiredLink() {
        WebElement link = driver.findElement(actionRequiredLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(link));
        link.click();

        pause(1000);
    }



    public void selectRecordType(String value) {
        WebElement control = driver.findElement(recordTypeControl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        control.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(recordTypeInput));
        input.sendKeys(value);
        WebElement opt = wait.until(ExpectedConditions.visibilityOfElementLocated(optionBy(value)));
        opt.click();
    }

    public void enterRecordNumber(String number) {
        WebElement el = driver.findElement(recordNumberInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        el.clear();
        el.sendKeys(number);
    }

    public void selectTransactionType(String value) {
        WebElement control = driver.findElement(transactionTypeControl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        control.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(transactionTypeInput));
        input.sendKeys(value);
        WebElement opt = wait.until(ExpectedConditions.visibilityOfElementLocated(optionBy(value)));
        opt.click();
    }

    public void enterAgreementNumbers(String value) {
        WebElement el = driver.findElement(agreementNumbersInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        el.clear();
        el.sendKeys(value);
    }

    public void clickSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
        pause(1000);
    }

    public void clickExportControlLink() {
        WebElement link = driver.findElement(exportControlLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(link));
        link.click();

        pause(1000);
    }






}
