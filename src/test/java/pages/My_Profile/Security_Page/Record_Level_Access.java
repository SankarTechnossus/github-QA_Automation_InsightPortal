package pages.My_Profile.Security_Page;
import org.openqa.selenium.*;
import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;


public class Record_Level_Access extends BasePage {

    public Record_Level_Access(WebDriver driver) {
        super(driver);
    }

    // Locators

    By noResultsMessage = By.xpath("//td[normalize-space()='The search criteria yielded no results.']");
    By searchForUserSpan = By.xpath("//span[normalize-space()='Search For User']");
    By userSearchInput = By.xpath("//input[@id='admPersonId']");
    By firstUserOption = By.xpath("//div[contains(@class,'select__menu')]//div[contains(@class,'option')][1]");
    By searchButton = By.xpath("//button[@type='submit' and normalize-space()='Search']");
    By userNameLink = By.xpath("//td[@data-column='userFullName']//span[normalize-space()='%s']");
    By recordLevelAccessToggleButton = By.xpath("//header[contains(normalize-space(),'Record Level Access')]//button[@aria-label='Expand/collapse']");
    By addAdditionalExportControlButton = By.xpath("//button[normalize-space()='Add Additional Export Control']");
    By exportControlNumberInput = By.xpath("//input[@id='exportControlNumber' and @type='text']");
    By clearSelectionsButton = By.xpath("//button[@type='button' and normalize-space()='Clear Selections']");
    By modalSearchButton = By.xpath("//div[contains(@class,'modal-content-wrapper')]//button[@type='submit' and normalize-space()='Search']");
    By noDataFoundMessage = By.xpath("//div[contains(@class,'modal-content-wrapper')]//td[normalize-space()='No data found.']");
    By securityPageTitle = By.xpath("//strong[contains(@class,'page-title-item') and contains(normalize-space(),'Security:')]");
    By exportControlNumberLabel = By.xpath("//label[@for='exportControlNumber' and normalize-space()='Export Control #']");
    By firstResultCheckbox = By.xpath("//div[contains(@class,'modal-content-wrapper')]//tbody/tr[1]//td[@data-column='_checkbox']//input[@type='checkbox']");
    By applyButton = By.xpath("//div[contains(@class,'modal-content-wrapper')]//button[normalize-space()='Apply']");
    By applyButtonEnabled = By.xpath("//div[contains(@class,'modal-content-wrapper')]//button[normalize-space()='Apply' and not(@disabled)]");
    By validationsCompletedMessage = By.xpath("//span[normalize-space()='All validations in this area have been completed']");




    //Actions

    public boolean verifyValidationsCompletedMessageDisplayed() {
        boolean result = false;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement message = wait.until(
                ExpectedConditions.presenceOfElementLocated(validationsCompletedMessage));

        String actualText = message.getText().trim();

        result = actualText.equals("All validations in this area have been completed");

        return result;
    }


    public void clickApplyButtonWhenEnabled() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement enabledApply = wait.until(
                ExpectedConditions.elementToBeClickable(applyButtonEnabled));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", enabledApply);

        enabledApply.click();

        pause(1000);
    }

    public void clickFirstExportControlCheckbox() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(firstResultCheckbox));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", checkbox);

        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        pause(1000);
    }


    public boolean verifyExportControlNumberLabelDisplayed() {
        boolean result = false;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement label = wait.until(ExpectedConditions.presenceOfElementLocated(exportControlNumberLabel));

        String actualText = label.getText().trim();

        result = actualText.equals("Export Control #");

        return result;
    }

    public boolean verifySecurityPageTitle(String expectedUserFullName, String expectedUserId) {
        boolean result = false;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(securityPageTitle));

        String actualTitle = title.getText().trim();
        String expectedTitle = "Security: " + expectedUserFullName + " (" + expectedUserId + ")";

        result = actualTitle.equals(expectedTitle);

        return result;
    }

    public void clickModalSearchButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement searchBtn = wait.until(
                ExpectedConditions.elementToBeClickable(modalSearchButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", searchBtn);

        searchBtn.click();

        // wait for grid to refresh (No data found OR results)
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(noDataFoundMessage),
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[contains(@class,'modal-content-wrapper')]//tbody//tr[not(contains(@class,'empty-grid-row'))]"))
        ));

        pause(1000);
    }


    public void clickClearSelectionsButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement clearBtn = wait.until(
                ExpectedConditions.elementToBeClickable(clearSelectionsButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", clearBtn);

        clearBtn.click();

        wait.until(ExpectedConditions.attributeToBe(exportControlNumberInput, "value", ""));

        pause(1000);
    }


    public void enterExportControlRecordNumber(String recordNum) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(exportControlNumberInput));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(recordNum);

        pause(1000);
    }

    public void clickAddAdditionalExportControlButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement addBtn = wait.until(
                ExpectedConditions.elementToBeClickable(addAdditionalExportControlButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", addBtn);

        addBtn.click();

        wait.until(ExpectedConditions.urlContains("export-control"));

        pause(1000);
    }


    public void clickRecordLevelAccessExpandButton() {

        WebElement toggleBtn = driver.findElement(recordLevelAccessToggleButton);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", toggleBtn);

        toggleBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(
                recordLevelAccessToggleButton, "aria-expanded", "true"));

        pause(1000);
    }


    public void clickOnUserName(String userFullName) {

        By dynamicUserNameLink = By.xpath(
                String.format("//td[@data-column='userFullName']//span[normalize-space()='%s']",
                        userFullName));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement userLink = wait.until(
                ExpectedConditions.elementToBeClickable(dynamicUserNameLink));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", userLink);

        userLink.click();

        wait.until(ExpectedConditions.urlContains("demographics"));

        pause(1000);
    }


    public void clickSearchButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(searchButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", button);

        button.click();

        pause(1000);
    }

    public boolean verifySearchButtonDisplayed() {
        boolean result = false;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement button = wait.until(
                ExpectedConditions.presenceOfElementLocated(searchButton));

        String actualText = button.getText().trim();

        result = actualText.equals("Search");

        return result;
    }


    public void selectFirstUserFromDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(firstUserOption));
        option.click();
        pause(1000);
    }

    public void enterUserSearchValue(String organizationName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement userInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(userSearchInput));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", userInput);

        userInput.click();
        userInput.clear();
        userInput.sendKeys(organizationName);

        pause(1000);
    }


    public void clickSearchForUser() {
        WebElement searchForUser = driver.findElement(searchForUserSpan);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", searchForUser);

        searchForUser.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("search"));

        pause(1000);
    }


    public boolean verifySearchForUserLabelDisplayed() {
        boolean result = false;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement label = wait.until(ExpectedConditions.presenceOfElementLocated(searchForUserSpan));

        String actualText = label.getText().trim();

        result = actualText.equals("Search For User");

        return result;
    }


    public boolean verifyNoSearchResultsMessageDisplayed() {
        boolean result = false;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement message = wait.until(
                ExpectedConditions.presenceOfElementLocated(noResultsMessage));

        String actualText = message.getText().trim();

        result = actualText.equals("The search criteria yielded no results.");

        return result;
    }


}