package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Export_control_My_actions_page extends BasePage {

    public Export_control_My_actions_page(WebDriver driver) {
        super(driver);
    }



    //Locators

    private By exportControlLink = By.xpath("//a[@href='/export-control' and contains(@class,'module-link')]");


    private By actionRequiredLink = By.xpath("//a[@href='/export-control/action-required' and contains(@class,'label')]");

    //Actions


    // ---------- Generic helpers anchored by <label> ----------
    private By controlByLabel(String label) {
        return By.xpath("//label[normalize-space()='" + label + "']/following::div[contains(@class,'select-control')][1]");
    }
    private By inputByLabel(String label) {
        return By.xpath("//label[normalize-space()='" + label + "']/following::div[contains(@class,'select-control')][1]//input[@role='combobox']");
    }
    // React-Select menu + options live at top level; do NOT scope under the field
    private By rsMenu() {
        return By.xpath("//div[contains(@class,'-menu') or contains(@class,'rs__menu') or contains(@class,'css-')][div[@role='listbox']]");
    }
    private By rsOption(String text) {
        return By.xpath("//div[@role='option' and normalize-space()='" + text + "']");
    }
    private By singleValueByLabel(String label) {
        // after selection, React-Select renders a singleValue div inside the control
        return By.xpath("//label[normalize-space()='" + label + "']/following::div[contains(@class,'select-control')][1]//*[contains(@class,'-singleValue') or contains(@class,'select-placeholder')]");
    }

    // Generic select for React-Select fields
    public void selectReactSelectByLabel(String label, String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1) Click control (re-find each time to avoid stale)
        WebElement control = wait.until(ExpectedConditions.elementToBeClickable(controlByLabel(label)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        control.click();

        // 2) Ensure menu is visible; then type to filter
        wait.until(ExpectedConditions.visibilityOfElementLocated(rsMenu()));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputByLabel(label)));
        input.sendKeys(optionText);

        // 3) Click the exact option (fallback to ENTER if click intercepted)
        try {
            WebElement opt = wait.until(ExpectedConditions.elementToBeClickable(rsOption(optionText)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", opt);
            opt.click();
        } catch (Exception e) {
            // Sometimes the menu consumes the click; ENTER selects first highlighted item
            input.sendKeys(Keys.ENTER);
        }

        // 4) Verify selected value appears in the control
        wait.until(ExpectedConditions.textToBePresentInElementLocated(singleValueByLabel(label), optionText));
        pause(300);
    }

    // ---------- Page-specific text inputs & button ----------
    private By recordNumberInput = By.xpath("//label[normalize-space()='Record Number']/following::input[1]");
    private By agreementNumbersInput = By.xpath("//label[normalize-space()='Agreement Numbers']/following::input[1]");
    private By searchButton = By.xpath("//button[contains(@class,'-primary') and normalize-space()='Search']");

    // ---------- Page-specific wrappers ----------
    public void setRecordType(String value) { selectReactSelectByLabel("Record Type", value); }
    public void setTransactionType(String value) { selectReactSelectByLabel("Transaction Type", value); }

    public void setRecordNumber(String value) {
        WebElement el = driver.findElement(recordNumberInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        el.clear(); el.sendKeys(value);
    }

    public void setAgreementNumbers(String value) {
        WebElement el = driver.findElement(agreementNumbersInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        el.clear(); el.sendKeys(value);
    }

    public void clickSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
        pause(800);
    }



    public void clickActionRequiredLink() {
        WebElement link = driver.findElement(actionRequiredLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(link));
        link.click();

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
