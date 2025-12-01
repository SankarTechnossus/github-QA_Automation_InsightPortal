package pages.Export_Control.Export_Control_Details;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyActionsPage extends BasePage {

    public MyActionsPage(WebDriver driver) {
        super(driver);
    }

    // **************************** Locators *******************************************

    // Left nav – "Action Required" under Export Control
    By actionRequiredLink = By.xpath("//div[contains(@class,'export-control-nav-block')]//a" + "[contains(@class,'label') and normalize-space()='Action Required']");

    // text inputs
    By recordNumberInput = By.xpath("//label[normalize-space()='Record Number']/following::input[1]");
    By agreementNumbersInput = By.xpath("//label[normalize-space()='Agreement Numbers']/following::input[1]");

    // buttons
    By clearSelectionsButton = By.xpath("//button[normalize-space()='Clear Selections']");
    By searchButton = By.xpath("//button[@type='submit' and normalize-space()='Search']");
    By firstRecordNumberLink = By.xpath("//table[contains(@class,'item-grid')]//tbody/tr[1]" + "//td[@data-column='_exportControlNumber']//a");

    // ****************** Functions ******************************************************

    // inside MyActionsPage
    public WebElement waitHighlightAndGetClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));

        // scroll to center
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", el);

        // highlight element for demo
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].style.outline='3px solid red';", el);

        // demo pause so you can *see* it before click
        pause(1500);

        return el;
    }

    // root div for a React-Select control for a given <label>
    public By controlRoot(String label) {
        return By.xpath("//label[normalize-space()='" + label + "']" +
                "/following::div[contains(@class,'select-control')][1]");
    }

    // ---------- Generic React-Select (single) by <label> ----------
    private void selectReactSelectSingleByLabel(String label, String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement root = wait.until(
                ExpectedConditions.elementToBeClickable(controlRoot(label)));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", root);

        // highlight & pause before opening dropdown
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].style.outline='3px solid red';", root);
        pause(1000);

        root.click();

        WebElement inputEl = root.findElement(By.xpath(".//input[@role='combobox']"));

        String listboxId = inputEl.getAttribute("aria-controls");
        if (listboxId == null || listboxId.isEmpty()) {
            String inputId = inputEl.getAttribute("id");
            if (inputId != null) {
                listboxId = inputId.replace("input", "listbox");
            }
        }
        By listboxBy = By.id(listboxId);

        inputEl.clear();
        inputEl.sendKeys(optionText);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(listboxBy));

        By optionBy = By.xpath("//*[@id='" + listboxId + "']//*[normalize-space()='" + optionText + "']");
        WebElement optionEl = wait.until(ExpectedConditions.elementToBeClickable(optionBy));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", optionEl);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].style.outline='3px solid blue';", optionEl);
        pause(1000);   // see the option before click

        optionEl.click();

        try { inputEl.sendKeys(Keys.ESCAPE); } catch (Exception ignored) {}
        try { driver.findElement(By.tagName("body")).click(); } catch (Exception ignored) {}

        By selectedValueBy = By.xpath(
                "//label[normalize-space()='" + label + "']/following::div[contains(@class,'select-control')][1]" +
                        "//*[contains(@class,'singleValue') or contains(@class,'valueContainer')]"
        );
        wait.until(d -> d.findElement(selectedValueBy).getText().trim().equals(optionText));

        pause(1000);   // after selection pause
    }

    // 1. Click "Action Required" left nav is in some LeftNav page – leaving as you already have.
    public void clickActionRequiredLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(actionRequiredLink));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        link.click();

        pause(1500);   // after-click pause
    }

    // 2. Select Record Type = "Export Control Request"
    public void selectRecordTypeExportControlRequest() {
        selectReactSelectSingleByLabel("Record Type", "Export Control Request");
        pause(1000);
    }

    // 3. Enter Record Number
    public void enterRecordNumber(String recordNumber) {
        WebElement input = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(recordNumberInput));
        input.clear();
        input.sendKeys(recordNumber);
        pause(500);
    }

    // 4. Select Transaction Type = "Initial Review"
    public void selectTransactionTypeInitialReview() {
        selectReactSelectSingleByLabel("Transaction Type", "Initial Review");
        pause(1000);
    }

    // 5. Enter Agreement Numbers
    public void enterAgreementNumbers(String agreementNumber) {
        WebElement input = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(agreementNumbersInput));
        input.clear();
        input.sendKeys(agreementNumber);
        pause(500);
    }

    // 6. Click Clear Selections
    public void clickClearSelections() {
        WebElement btn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(clearSelectionsButton));
        btn.click();
        pause(1000);
    }

    // 7. Click Search
    public void clickSearchButton() {
        WebElement btn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
        pause(2000);
    }

    // 8. Click first Record Number link (2025E006129)
    public void clickFirstRecordNumberLink() {
        WebElement link = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(firstRecordNumberLink));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();
        pause(1500);
    }
}