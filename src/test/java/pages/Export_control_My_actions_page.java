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


    // root div for a React-Select control for a given <label>
    private By controlRoot(String label) {
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

        // pre-filter text & wait for listbox
        inputEl.clear();
        inputEl.sendKeys(optionText);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(listboxBy));

        By optionBy = By.xpath("//*[@id='" + listboxId + "']//*[normalize-space()='" + optionText + "']");
        WebElement optionEl = wait.until(ExpectedConditions.elementToBeClickable(optionBy));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", optionEl);
        optionEl.click();

        // close dropdown
        try { inputEl.sendKeys(Keys.ESCAPE); } catch (Exception ignored) {}
        try { driver.findElement(By.tagName("body")).click(); } catch (Exception ignored) {}

        // assert selected value
        By selectedValueBy = By.xpath(
                "//label[normalize-space()='" + label + "']/following::div[contains(@class,'select-control')][1]" +
                        "//*[contains(@class,'singleValue') or contains(@class,'valueContainer')]"
        );
        wait.until(d -> d.findElement(selectedValueBy).getText().trim().equals(optionText));
    }


    // ActionRequiredPage.java

    // Left nav – "Action Required" under Export Control
    private By actionRequiredLink = By.xpath(
            "//div[contains(@class,'export-control-nav-block')]//a" +
                    "[contains(@class,'label') and normalize-space()='Action Required']"
    );


    // text inputs
    private By recordNumberInput = By.xpath(
            "//label[normalize-space()='Record Number']/following::input[1]"
    );

    private By agreementNumbersInput = By.xpath(
            "//label[normalize-space()='Agreement Numbers']/following::input[1]"
    );

    // buttons
    private By clearSelectionsButton = By.xpath("//button[normalize-space()='Clear Selections']");
    private By searchButton          = By.xpath("//button[@type='submit' and normalize-space()='Search']");

    // grid link (first row, record number)
    private By firstRecordNumberLink = By.xpath(
            "//table[contains(@class,'item-grid')]//tbody/tr[1]" +
                    "//td[@data-column='_exportControlNumber']//a"
    );



    // 1. Click "Action Required" left nav is in some LeftNav page – leaving as you already have.





    public void clickActionRequiredLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(actionRequiredLink));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        link.click();
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

