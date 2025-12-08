package pages.Export_Control.Export_Control_Details;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyActionsPage extends BasePage {
    private final WebDriverWait wait;

    public MyActionsPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // **************************** Locators *******************************************

    // Left nav – "Action Required" under Export Control

    // Already have:
    By openDropdownPanel = By.xpath("//div[contains(@class,'menu') and contains(@class,'select')]");
    By submitterInput = By.xpath("//label[normalize-space()='Submitter']/following::input[contains(@id,'react-select')][1]");
    String submitterOptionXpath = "//div[contains(@id,'react-select') and contains(@id,'listbox')]" + "//div[contains(@class,'option') and contains(normalize-space(),'OPTION_TEXT')]";
    By reviewerDropdown = By.xpath("//label[normalize-space()='Reviewer']/following::div[contains(@class,'select-control')][1]");
    By reviewerInput = By.xpath("//label[normalize-space()='Reviewer']/following::input[contains(@id,'react-select')][1]");
    String reviewerOptionXpath = "//div[contains(@id,'react-select') and contains(@id,'listbox')]" + "//div[contains(@class,'option') and contains(normalize-space(),'OPTION_TEXT')]";
    By actionRequiredLink = By.xpath("//div[contains(@class,'export-control-nav-block')]//a" + "[contains(@class,'label') and normalize-space()='Action Required']");
    By recordNumberInput = By.xpath("//label[normalize-space()='Record Number']/following::input[1]");
    By agreementNumbersInput = By.xpath("//label[normalize-space()='Agreement Numbers']/following::input[1]");
    // buttons
    By clearSelectionsButton = By.xpath("//button[normalize-space()='Clear Selections']");
    By searchButton = By.xpath("//button[@type='submit' and normalize-space()='Search']");
    By firstRecordNumberLink = By.xpath("//table[contains(@class,'item-grid')]//tbody/tr[1]" + "//td[@data-column='_exportControlNumber']//a");
    // Created On - From
    By createdOnFromInput = By.xpath("//label[normalize-space()='Created On']/following::input[@type='text'][1]");
    By createdOnToInput = By.xpath("//label[normalize-space()='Created On']/following::input[@type='text'][2]");
    By reviewDateToInput = By.xpath("//label[normalize-space()='Review date']/following::input[@type='text'][2]");
    // Review Date - From
    By reviewDateFromInput = By.xpath("//label[normalize-space()='Review date']/following::input[@type='text'][1]");
    // Submitter dropdown (already populated with MA1279)
    By submitterDropdown = By.xpath("//label[normalize-space()='Submitter']/following::div[contains(@class,'select-control')]");
    // Record Number input
    By recordNumberInput01 = By.xpath("//label[normalize-space()='Record Number']/following::input");
    // Clear Selection button
    By clearSelectionButton = By.xpath("//button[@type='button' and contains(.,'Clear Selections')]");
    // Search button
    By searchButton01 = By.xpath("//button[@type='submit' and normalize-space()='Search']");

    // ****************** Functions ******************************************************

    public void selectSubmitter(String searchText, String optionToSelect) {

        // We assume clickSubmitterFilter() already called from test

        // Step 1: Type submitter code
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(submitterInput));
        input.click();
        input.clear();
        input.sendKeys(searchText);
        pause(1000);

        // Step 2: Try to click the suggestion from dropdown
        By dynamicOption = By.xpath(submitterOptionXpath.replace("OPTION_TEXT", optionToSelect));

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement option = shortWait.until(
                    ExpectedConditions.visibilityOfElementLocated(dynamicOption));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", option);
            option.click();
        } catch (TimeoutException e) {
            // Fallback for "traditional" behaviour => just confirm with ENTER
            input.sendKeys(Keys.ENTER);
        }

        pause(1000);
    }

    public void clickSubmitterFilter() {

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(openDropdownPanel));
        } catch (TimeoutException e) {
            // ignore if no open dropdown
        }

        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(submitterInput));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input);

        try {
            input.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", input);
        }

        pause(500);
    }

    public void selectReviewer(String searchText, String optionToSelect) {

        // Step 1: Open the dropdown
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(reviewerDropdown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);
        dropdown.click();
        pause(500);

        // Step 2: Type reviewer code
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(reviewerInput));
        input.click();
        input.clear();
        input.sendKeys(searchText);
        pause(1000);

        // Step 3: Try to click the suggestion from the popup
        By dynamicOption = By.xpath(reviewerOptionXpath.replace("OPTION_TEXT", optionToSelect));

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement option = shortWait.until(ExpectedConditions
                    .visibilityOfElementLocated(dynamicOption));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", option);
            option.click();
        } catch (TimeoutException e) {
            // Fallback for “traditional” behaviour:
            // listbox not found / auto-select on enter -> just hit ENTER
            input.sendKeys(Keys.ENTER);
        }

        // Optional: click somewhere to blur the field if needed
        // driver.findElement(By.xpath("//label[normalize-space()='Record Number']")).click();

        pause(1000);
    }

    public void selectReviewer(String reviewerName) {
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(reviewerInput));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(reviewerName);

        // select option etc...

        // ✅ after selecting reviewer, wait for dropdown to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(openDropdownPanel));
        pause(500);
    }

    public void enterReviewDateTo(String dateValue) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(reviewDateToInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(dateValue);
        pause(500);
    }

    public void enterCreatedOnTo(String dateValue) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(createdOnToInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(dateValue);
        pause(500);
    }

    public void enterCreatedOnFrom(String dateValue) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(createdOnFromInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(dateValue);
        pause(500);
    }

    public void enterReviewDateFrom(String dateValue) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(reviewDateFromInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(dateValue);
        pause(500);
    }

    public void selectReviewer() {
        WebElement dd = wait.until(ExpectedConditions.elementToBeClickable(reviewerDropdown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", dd);
        dd.click();
        pause(500);
    }

    public void selectSubmitter() {
        WebElement dd = wait.until(ExpectedConditions.elementToBeClickable(submitterDropdown));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", dd);
        dd.click();
        pause(500);
    }

    public void enterRecordNumber(String recordNo) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(recordNumberInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(recordNo);
        pause(500);
    }

    public void clickClearSelections() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(clearSelectionButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
        pause(1000);
    }

    public void clickSearchButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
        pause(2000);
    }

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
    public void enterRecordNumber01(String recordNumber) {
        WebElement input = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(recordNumberInput01));
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
    public void clickClearSelections01() {
        WebElement btn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(clearSelectionsButton));
        btn.click();
        pause(1000);
    }

    // 7. Click Search
    public void clickSearchButton01() {
        WebElement btn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(searchButton01));
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