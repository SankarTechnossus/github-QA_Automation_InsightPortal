package pages.My_Profile.Security_Page;
import org.openqa.selenium.*;
import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;


public class Organization_Level_Access extends BasePage {

    public Organization_Level_Access(WebDriver driver) {
        super(driver);
    }

    // Locators
    By myProfileLink = By.xpath("//a[contains(@href,'/manage-profiles-and-security/profiles') and .//span[normalize-space()='My Profile']]");
    By lblMyProfile = By.xpath("//span[normalize-space()='My Profile']");
    By lblFirstName = By.xpath("//label[normalize-space()='First Name:']");
    By securityLink = By.xpath("//a[contains(@href,'/manage-profiles-and-security/profiles') and contains(@href,'/security') and .//span[normalize-space()='Security']]");
    By lblSecurityHeader = By.xpath("//strong[contains(@class,'page-title-item') and contains(normalize-space(),'Security:')]");
    By organizationLevelAccessToggleBtn = By.xpath("//header[contains(normalize-space(),'Organization Level Access')]//button[@aria-label='Expand/collapse']");
    By lblOrganization = By.xpath("//div[normalize-space()='Organization']");
    By addAdditionalOrganizationButton = By.xpath("//button[normalize-space()='Add Additional Organization']");
    By exportControlViewCheckbox = By.xpath("//div[contains(@class,'_word-break') and normalize-space()='Export']//following::span[normalize-space()='View'][1]/preceding-sibling::input");
    By exportControlManageCheckbox = By.xpath("//div[contains(@class,'_word-break') and normalize-space()='Export']//following::span[normalize-space()='Manage'][1]/preceding-sibling::input");
    By saveButton = By.xpath("//button[normalize-space()='Save']");
    By lblExport = By.xpath("//div[normalize-space()='Export']");
    By removeIcon = By.xpath("//i[contains(@class,'fi-remove')]");
    By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    By okButton = By.xpath("//button[normalize-space()='OK']");
    By lblRemoveOrganizationMessage = By.xpath("//div[@class='message' and normalize-space()='Are you sure you want to remove this organization?']");
    By lblNoResultsMessage = By.xpath("//td[normalize-space()='The search criteria yielded no results.']");
    By organizationSearchDropdown = By.xpath("//div[contains(@class,'Select-placeholder') and normalize-space()='Type keywords to search...']");
    By organizationSearchInput = By.xpath("//input[contains(@class,'Select-input')]");
    By anesthesiaCheckbox = By.xpath("//label[normalize-space()='10AA - Anesthesia']/preceding-sibling::input");
    By applyButton = By.xpath("//button[normalize-space()='Apply']");
    By cancelButtonOnSearch = By.xpath("//button[normalize-space()='Cancel']");
    By saveButton1 = By.xpath("//button[normalize-space()='Save']");






    //Actions
    public void clickSaveButton() {

        WebElement saveBtn = driver.findElement(saveButton1);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);

        saveBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(saveBtn));

        pause(1000);
    }

    public void clickCancelButton() {

        WebElement cancelBtn = driver.findElement(cancelButtonOnSearch);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", cancelBtn);

        cancelBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(cancelBtn));

        pause(1000);
    }

    public void clickApplyButton() {

        WebElement applyBtn = driver.findElement(applyButton);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", applyBtn);

        applyBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(anesthesiaCheckbox));

        pause(1000);
    }

    public void selectAnesthesiaOrganization() {

        WebElement checkbox = driver.findElement(anesthesiaCheckbox);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", checkbox);

        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeSelected(anesthesiaCheckbox));

        pause(1000);
    }

    public void enterOrganizationSearchText(String orgName) {

        WebElement searchInput = driver.findElement(organizationSearchInput);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", searchInput);

        searchInput.clear();
        searchInput.sendKeys(orgName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(anesthesiaCheckbox));

        pause(1000);
    }

    public void clickOrganizationSearchDropdown() {

        WebElement dropdown = driver.findElement(organizationSearchDropdown);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);

        dropdown.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(organizationSearchInput));

        pause(1000);
    }

    public boolean VerifyNoResultsMessageIsDisplayed() {

        boolean result = false;

        waitForPresence(lblNoResultsMessage);

        String text = driver.findElement(lblNoResultsMessage).getText();

        if (Objects.equals(text, "The search criteria yielded no results.")) {
            result = true;
        }

        return result;
    }


    public boolean VerifyRemoveOrganizationConfirmationMessageIsDisplayed() {

        boolean result = false;

        waitForPresence(lblRemoveOrganizationMessage);

        String text = driver.findElement(lblRemoveOrganizationMessage).getText();

        if (Objects.equals(text, "Are you sure you want to remove this organization?")) {
            result = true;
        }

        return result;
    }

    public void clickRemoveOrganizationOkButton() {

        WebElement okBtn = driver.findElement(okButton);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", okBtn);

        okBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(okBtn));

        pause(1000);
    }

    public void clickRemoveOrganizationCancelButton() {

        WebElement cancelBtn = driver.findElement(cancelButton);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", cancelBtn);

        cancelBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(cancelBtn));

        pause(1000);
    }

    public void clickRemoveIcon() {

        WebElement removeBtn = driver.findElement(removeIcon);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", removeBtn);

        removeBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(removeBtn));

        pause(1000);
    }

    public boolean VerifyExportLabelIsDisplayed() {

        boolean result = false;

        waitForPresence(lblExport);

        String text = driver.findElement(lblExport).getText();

        if (Objects.equals(text, "Export")) {
            result = true;
        }

        return result;
    }


    public void clickExportControlManageAndSave() {

        WebElement manageCheckbox = driver.findElement(exportControlManageCheckbox);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", manageCheckbox);

        manageCheckbox.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));

        driver.findElement(saveButton).click();

        pause(1000);
    }


    public void clickExportControlViewAndSave() {

        WebElement viewCheckbox = driver.findElement(exportControlViewCheckbox);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", viewCheckbox);

        viewCheckbox.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));

        driver.findElement(saveButton).click();

        pause(1000);
    }


    public void clickAddAdditionalOrganizationButton() {

        WebElement addBtn = driver.findElement(addAdditionalOrganizationButton);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", addBtn);

        addBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'modal') or contains(@class,'dialog')]")
        ));

        pause(1000);
    }


    public boolean VerifyOrganizationLabelIsDisplayed() {

        boolean result = false;

        waitForPresence(lblOrganization);

        String text = driver.findElement(lblOrganization).getText();

        if (Objects.equals(text, "Organization")) {
            result = true;
        }

        return result;
    }

    public void clickOrganizationLevelAccessToggleButton() {

        WebElement toggleBtn = driver.findElement(organizationLevelAccessToggleBtn);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", toggleBtn);

        toggleBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeToBe(toggleBtn, "aria-expanded", "true"));

        pause(1000);
    }

    public boolean VerifyUserLandsOnSecurityPage() {

        boolean result = false;

        waitForPresence(lblSecurityHeader);

        String text = driver.findElement(lblSecurityHeader).getText();

        if (Objects.equals(text, "Security: Venkatesan, Sankar (SV1179)")) {
            result = true;
        }

        return result;
    }

    public void clickSecurityLink() {

        WebElement secLink = driver.findElement(securityLink);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", secLink);

        secLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/security"));

        pause(1000);
    }

    public boolean VerifyFirstNameLabelIsDisplayed() {

        boolean result = false;

        waitForPresence(lblFirstName);

        String text = driver.findElement(lblFirstName).getText();

        if (Objects.equals(text, "First Name:")) {
            result = true;
        }

        return result;
    }

    public boolean VerifyUserLandsOnMyProfilePage() {

        boolean result = false;

        waitForPresence(lblMyProfile);

        String text = driver.findElement(lblMyProfile).getText();

        if (Objects.equals(text, "My Profile")) {
            result = true;
        }

        return result;
    }

    public void clickMyProfileLink() {

        WebElement profileLink = driver.findElement(myProfileLink);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", profileLink);

        profileLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/manage-profiles-and-security/profiles"));

        pause(1000);
    }



}