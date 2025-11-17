package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Export_control_My_actions_page extends BasePage {

    public Export_control_My_actions_page(WebDriver driver) {
        super(driver);
    }



    //Locators


    // Left nav - Action Required
    private By actionRequiredLink = By.xpath(
            "//div[contains(@class,'export-control-nav-block')]//a[@href='/export-control/action-required']"
    );

    // Generic React-Select controls (we'll use helper with label)
    private By recordTypeControl = By.xpath(
            "//label[normalize-space()='Record Type']/following::div[contains(@class,'select-control')][1]"
    );
    private By transactionTypeControl = By.xpath(
            "//label[normalize-space()='Transaction Type']/following::div[contains(@class,'select-control')][1]"
    );

    // Text inputs
    private By recordNumberInput = By.xpath(
            "//label[normalize-space()='Record Number']/following::input[contains(@class,'default-input')][1]"
    );
    private By agreementNumbersInput = By.xpath(
            "//label[normalize-space()='Agreement Numbers']/following::input[contains(@class,'default-input')][1]"
    );

    // Search button
    private By searchButton = By.xpath(
            "//form[contains(@class,'base-search-form')]//button[@type='submit' and normalize-space()='Search']"
    );


// helper
public void selectReactSelectByLabel(String label, String optionText) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    By controlBy = By.xpath("//label[normalize-space()='" + label + "']" +
            "/following::div[contains(@class,'select-control')][1]");
    WebElement controlEl = wait.until(ExpectedConditions.elementToBeClickable(controlBy));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", controlEl);
    controlEl.click();

    By inputBy = By.xpath("//label[normalize-space()='" + label + "']" +
            "/following::div[contains(@class,'select-control')][1]//input[@role='combobox']");
    WebElement inputEl = wait.until(ExpectedConditions.elementToBeClickable(inputBy));
    inputEl.sendKeys(optionText);

    By optionBy = By.xpath("//div[@role='listbox']//div[@role='option']" +
            "[.//div[normalize-space()='" + optionText + "']]");
    WebElement optionEl = wait.until(ExpectedConditions.elementToBeClickable(optionBy));
    optionEl.click();

    pause(1000);
}


//Method

    // 1. Click on "Action Required"
    public void clickActionRequiredLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(actionRequiredLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();
        pause(1500);
    }

    // 2. Record Type = "Export Control Request"
    public void selectRecordTypeAsExportControlRequest() {
        selectReactSelectByLabel("Record Type", "Export Control Request");
    }

    // 3. Enter Record Number "2025E006129"
    public void enterRecordNumber(String recordNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(recordNumberInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);
        input.clear();
        input.sendKeys(recordNumber);
        pause(500);
    }

    // 4. Transaction Type = "Initial Review"
    public void selectTransactionTypeAsInitialReview() {
        selectReactSelectByLabel("Transaction Type", "Initial Review");
    }

    // 5. Agreement Numbers = "932840"
    public void enterAgreementNumbers(String agreementNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(agreementNumbersInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);
        input.clear();
        input.sendKeys(agreementNumber);
        pause(500);
    }

    // Click Search
    public void clickSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
        pause(2000);
    }




}