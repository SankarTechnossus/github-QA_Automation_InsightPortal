package pages.My_Profile.Security_Page;
import org.openqa.selenium.*;
import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;


public class Profile_Level_Access extends BasePage {

    public Profile_Level_Access(WebDriver driver) {
        super(driver);
    }

    // Locators
    By profileDelegateToggleButton = By.xpath("//header[contains(normalize-space(),'Profile/Delegate Level Access')]//button[contains(@class,'toggle-button')]");
    By addAdditionalProfileButton = By.xpath("//button[normalize-space()='Add Additional Profile']");
    By profileSearchInput = By.xpath("//input[contains(@id,'react-select') and @role='combobox']");
    By applyButton = By.xpath("//button[normalize-space()='Apply']");
    By exportControlManageCheckbox = By.xpath("//td[contains(@class,'-export-control')]//span[normalize-space()='Manage']/preceding-sibling::input");
    By saveButton = By.xpath("//button[normalize-space()='Save']");
    By removeProfileModal = By.xpath("//div[contains(@class,'modal-content-wrapper')][.//div[contains(@class,'message') and contains(normalize-space(),'remove this profile')]]");
    By cancelButtonInModal = By.xpath("//div[contains(@class,'modal-content-wrapper')][.//div[contains(@class,'message') and contains(normalize-space(),'remove this profile')]]//button[contains(@class,'cancel-button') and normalize-space()='Cancel']");
    By okButtonInModal = By.xpath("//div[contains(@class,'modal-content-wrapper')][.//div[contains(@class,'message') and contains(normalize-space(),'remove this profile')]]//button[contains(@class,'ok-button') and normalize-space()='OK']");
    By profileHeaderLabel = By.xpath("//div[contains(@class,'_word-break') and normalize-space()='Profile']");
    By removeProfileConfirmationMessage = By.xpath("//div[contains(@class,'message') and normalize-space()='Are you sure you want to remove this profile?']");
    By specifyAccessLevelMessage = By.xpath("//div[contains(@class,'submission-checklist-list-inner') and normalize-space()='Please specify access level']");
    By validationsCompletedMessage = By.xpath("//span[normalize-space()='All validations in this area have been completed']");
    By piColumnValue = By.xpath("//td[@data-column='_pIAdmPersonId']");



    //Actions

    public boolean verifyPINameIsNotPresent(String expectedPIName) {
        boolean result = true;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(piColumnValue));

        List<WebElement> piList = driver.findElements(piColumnValue);

        for (WebElement pi : piList) {
            String actualPIName = pi.getText().trim();

            if (actualPIName.contains(expectedPIName)) {
                result = false;
                break;
            }
        }

        return result;
    }

    public boolean verifyPIName(String expectedPIName) {
        boolean result = false;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement piValue = wait.until(ExpectedConditions.presenceOfElementLocated(piColumnValue));

        String actualPIName = piValue.getText().trim();

        result = actualPIName.contains(expectedPIName);

        return result;
    }


    public boolean verifyAllValidationsCompletedMessageIsDisplayed() {

        boolean result = false;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(validationsCompletedMessage));

        String actualText = driver.findElement(validationsCompletedMessage).getText();
        result = Objects.equals(actualText, "All validations in this area have been completed");

        pause(3000);
        return result;

    }

    public boolean verifySpecifyAccessLevelMessageIsDisplayed() {

        boolean result = false;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(specifyAccessLevelMessage));

        String actualText = driver.findElement(specifyAccessLevelMessage).getText();
        result = Objects.equals(actualText, "Please specify access level");

        pause(3000);
        return result;
    }

    public boolean verifyRemoveProfileConfirmationMessageIsDisplayed() {

        boolean result = false;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeProfileConfirmationMessage));

        String actualText = driver.findElement(removeProfileConfirmationMessage).getText();
        result = Objects.equals(actualText, "Are you sure you want to remove this profile?");

        pause(3000);
        return result;
    }

    public boolean verifyProfileLabelIsDisplayed() {

        boolean result = false;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(profileHeaderLabel));

        String actualText = driver.findElement(profileHeaderLabel).getText();
        result = Objects.equals(actualText, "Profile");

        pause(3000);
        return result;
    }

    public void clickOkOnRemoveProfileModal() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOfElementLocated(removeProfileModal));

        WebElement okBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(okButtonInModal));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", okBtn);

        wait.until(ExpectedConditions.elementToBeClickable(okBtn)).click();
        pause(3000);
    }

    public void clickCancelOnRemoveProfileModal() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOfElementLocated(removeProfileModal));

        WebElement cancelBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(cancelButtonInModal));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", cancelBtn);

        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
        pause(3000);
    }


    public void clickSaveButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(saveButton))
                .click();
        pause(3000);
    }

    public void selectExportControlManage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement manage = wait.until(ExpectedConditions.elementToBeClickable(exportControlManageCheckbox));
        if (!manage.isSelected()) {
            manage.click();
            pause(3000);
        }
    }

    public void clickApplyButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(applyButton))
                .click();
        pause(3000);
    }

    public void searchAndSelectProfile(String code) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(profileSearchInput));
        searchBox.sendKeys(code);

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'" + code + "')]")));
        option.click();
        pause(3000);
    }

    public void clickAddAdditionalProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addAdditionalProfileButton)).click();
        pause(3000);
    }

    public void clickRemoveProfile(String profileName) {

        By removeBtn = By.xpath("//td[@data-column='admPersonName' and contains(normalize-space(),'" + profileName + "')]" +
                "//following-sibling::td//i[contains(@class,'fi-remove')]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement remove = wait.until(ExpectedConditions.elementToBeClickable(removeBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", remove);
        remove.click();
        pause(3000);
    }

    public boolean isProfilePresent(String profileName) {

        boolean result = false;

        By profileRow = By.xpath("//td[@data-column='admPersonName' and contains(normalize-space(),'" + profileName + "')]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        List<WebElement> rows = driver.findElements(profileRow);

        if (rows.size() > 0) {
            result = true;
        }
        pause(3000);
        return result;

    }

    public void toggleProfileDelegateSectionTwice() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement toggleBtn = wait.until(
                ExpectedConditions.elementToBeClickable(profileDelegateToggleButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", toggleBtn);

        // First click (+ expand)
        toggleBtn.click();

        // Wait for section body to be visible
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'search-form-row') and .//header[contains(text(),'Profile/Delegate Level Access')]]")));

        // Second click (- collapse)
        toggleBtn = wait.until(ExpectedConditions.elementToBeClickable(profileDelegateToggleButton));
        toggleBtn.click();
        pause(3000);
    }

    public void clickAddAdditionalProfileTwice() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        for (int i = 1; i <= 2; i++) {

            WebElement addBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(addAdditionalProfileButton));

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", addBtn);

            addBtn.click();
            pause(3000);

            // Wait for profile modal / row to appear (adjust locator if you have modal header)
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(@class,'modal') or contains(text(),'Profile')]")));
        }
    }


}