package pages.My_Profile.Security_Page;
import org.openqa.selenium.*;
import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;


public class Organization_Level_Access extends BasePage {

    public Organization_Level_Access(WebDriver driver) {
        super(driver);
    }

    // Locators
    By orgSearchPlaceholder = By.xpath("//div[contains(@class,'Select-placeholder') and normalize-space()='Type keywords to search...']");
    By orgSearchInput = By.xpath("//div[contains(@class,'hierarchy-select')]//div[contains(@class,'Select-input')]//input[@role='combobox']");
    By orgArrow = By.xpath("//div[contains(@class,'hierarchy-select')]//span[contains(@class,'Select-arrow-zone')]");
    By orgDropdownPanel = By.xpath("//div[contains(@class,'Select-menu-outer') or contains(@class,'Select-menu')]");
    By anesthesiaOption = By.xpath("//label[normalize-space()='10AA - Anesthesia']");
    By orgSearchArrow = By.xpath("//div[contains(@class,'Select-placeholder') and normalize-space()='Type keywords to search...']/following::span[1] | //input[@placeholder='Type keywords to search...']/following::span[contains(@class,'Select-arrow')][1]");
    By removeOrgModalWrapper = By.xpath("//div[contains(@class,'modal-content-wrapper')]");
    By removeOrgMessage = By.xpath("//div[contains(@class,'modal-content-wrapper')]//div[@class='message' and normalize-space()='Are you sure you want to remove this organization?']");
    By removeOrgCancelButton = By.xpath("//div[contains(@class,'modal-content-wrapper')]//button[normalize-space()='Cancel']");
    By removeOrgOkButton = By.xpath("//div[contains(@class,'modal-content-wrapper')]//button[normalize-space()='OK']");
    By organizationLevelAccessHeader = By.xpath("//header[contains(normalize-space(),'Organization Level Access')]");
    By removeOrgPopup = By.xpath("//div[contains(@class,'modal-content-wrapper')]");
    By removeOrgPopupMessage = By.xpath("//div[@class='message' and contains(normalize-space(),'Are you sure you want to remove this organization?')]");
    By myProfileLink = By.xpath("//a[contains(@href,'/manage-profiles-and-security/profiles') and .//span[normalize-space()='My Profile']]");
    By lblMyProfile = By.xpath("//span[normalize-space()='My Profile']");
    By lblFirstName = By.xpath("//label[normalize-space()='First Name:']");
    By securityLink = By.xpath("//a[contains(@href,'/manage-profiles-and-security/profiles') and contains(@href,'/security') and .//span[normalize-space()='Security']]");
    By lblSecurityHeader = By.xpath("//strong[contains(@class,'page-title-item') and contains(normalize-space(),'Security:')]");
    By organizationLevelAccessToggleBtn = By.xpath("//header[contains(normalize-space(),'Organization Level Access')]//button[@aria-label='Expand/collapse']");
    By lblOrganization = By.xpath("//div[normalize-space()='Organization']");
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
    By addAdditionalOrganizationButton = By.xpath("//span[normalize-space()='Add Additional Organization']/ancestor::a | //button[normalize-space()='Add Additional Organization']");
    By organizationSearchPlaceholder = By.xpath("//div[contains(@class,'Select-placeholder') and normalize-space()='Type keywords to search...']");
    By exportControlViewCheckbox = By.xpath("//td[contains(@class,'item-grid-cell') and contains(@class,'-export-control')]//span[normalize-space()='View']/preceding-sibling::input[@type='checkbox']");
    By exportControlManageCheckbox = By.xpath("//td[contains(@class,'item-grid-cell') and contains(@class,'-export-control')]//span[normalize-space()='Manage']/preceding-sibling::input[@type='checkbox']");
    By successToast = By.xpath("//div[contains(normalize-space(),'Security accesses were successfully updated')]");
    By saveButton01 = By.xpath("//button[normalize-space()='Save' and contains(@class,'-positive')]");
    By firstRecordNumberLink = By.xpath("(//td[@data-column='_exportControlNumber']//a)[1]");
    By workflowHistorySectionTitle = By.xpath("//span[normalize-space()='Workflow History']");
    By firstWorkflowHistoryUserLink = By.xpath("(//div[contains(@class,'workflow-history-item')]//div[contains(@class,'overlay-item-link') and @role='button'])[1]");
    By exportControlBreadcrumb = By.xpath("//span[contains(@class,'crumb') and normalize-space()='Export Control']");
    By searchBreadcrumb = By.xpath("//span[contains(@class,'crumb') and normalize-space()='Search']");
    By organizationRow = By.xpath("//td[@data-column='organizationName' and contains(normalize-space(),'BWH')]");
    By createExportControlLink = By.xpath("//a[contains(@href,'/export-control/actions') and normalize-space()='Create New Export Control Record']");



    //Actions
    public void clickCreateExportControl() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement createExportControl = wait.until(
                ExpectedConditions.elementToBeClickable(createExportControlLink));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", createExportControl);

        createExportControl.click();

        // wait for navigation to Create Export Control page
        wait.until(ExpectedConditions.urlContains("/export-control/actions"));

    }

    public boolean isOrganizationPresent(String organizationKeyword) {

        By orgRow = By.xpath("//td[@data-column='organizationName' and contains(normalize-space(),'" + organizationKeyword + "')]");

        List<WebElement> rows = driver.findElements(orgRow);

        return rows.size() > 0;
    }

    public boolean verifyUserLandsOnSearchPage() {

        boolean result = false;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(searchBreadcrumb));

        String actualText = driver.findElement(searchBreadcrumb).getText();
        result = Objects.equals(actualText, "Search");

        return result;
    }

    public boolean verifyUserLandsOnExportControlPage() {

        boolean result = false;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(exportControlBreadcrumb));

        String actualText = driver.findElement(exportControlBreadcrumb).getText();
        result = Objects.equals(actualText, "Export Control");

        return result;
    }

    public void clickFirstUserLinkInWorkflowHistory() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement firstUser = wait.until(
                ExpectedConditions.elementToBeClickable(firstWorkflowHistoryUserLink));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", firstUser);

        firstUser.click();

        // Wait until the overlay expands OR dialog appears
        wait.until(ExpectedConditions.or(
                ExpectedConditions.attributeToBe(firstUser, "aria-expanded", "true"),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='dialog']"))
        ));
    }

    public void clickWorkflowHistorySection() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement workflowHistory = wait.until(
                ExpectedConditions.elementToBeClickable(workflowHistorySectionTitle));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", workflowHistory);

        workflowHistory.click();

        // wait until section expands / content becomes visible (adjust if you have a specific locator)
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'workflow-history')]")));

    }

    public void clickFirstRecordFromSearchResults() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement firstRecord = wait.until(
                ExpectedConditions.elementToBeClickable(firstRecordNumberLink));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", firstRecord);

        firstRecord.click();

        // wait until navigation happens
        wait.until(ExpectedConditions.urlContains("/export-control/"));

        pause(9000);
    }

    public void clickSaveButton01() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement saveBtn = wait.until(ExpectedConditions.presenceOfElementLocated(saveButton01));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);

        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));

        try {
            saveBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        }

        pause(1000);
    }

    public void enterOrganizationSearchText(String organizationName) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(orgSearchInput));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.clear();
        input.sendKeys(organizationName);

        pause(1000);
    }

    public void clickOrganizationSearchDropdown() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement placeholder = wait.until(ExpectedConditions.visibilityOfElementLocated(orgSearchPlaceholder));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", placeholder);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeholder);

        wait.until(ExpectedConditions.visibilityOfElementLocated(orgSearchInput));

        pause(1000);
    }

    public void selectAnesthesiaOrganization() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(anesthesiaOption));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", option);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

        pause(1000);
    }


    public boolean VerifyRemoveOrganizationPopupIsClosed() {

        boolean result = false;

        if (driver.findElements(removeOrgModalWrapper).size() == 0) {
            result = true;
        }

        return result;
    }


    public void clickRemoveOrganizationOkButton() {

        WebElement okBtn = driver.findElement(removeOrgOkButton);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", okBtn);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okBtn);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(removeOrgModalWrapper));

        pause(1000);
    }

    public void clickRemoveOrganizationCancelButton() {

        WebElement cancelBtn = driver.findElement(removeOrgCancelButton);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", cancelBtn);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cancelBtn);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(removeOrgModalWrapper));

        pause(1000);
    }

    public void waitForSecurityAccessUpdatedToastToDisappear() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        if (driver.findElements(successToast).size() > 0) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(successToast));
        }

        pause(1000);
    }


    public void clickRemoveIcon() {

        WebElement removeBtn = driver.findElement(removeIcon);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", removeBtn);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", removeBtn);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeOrgPopupMessage));
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeOrgCancelButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeOrgOkButton));

        pause(1000);
    }

    public boolean isExportControlManageCheckboxSelected() {

        boolean result = false;

        waitForPresence(exportControlManageCheckbox);

        WebElement checkbox = driver.findElement(exportControlManageCheckbox);

        if (checkbox.isSelected()) {
            result = true;
        }

        return result;
    }

    public boolean isExportControlViewCheckboxSelected() {

        boolean result = false;

        waitForPresence(exportControlViewCheckbox);

        WebElement checkbox = driver.findElement(exportControlViewCheckbox);

        if (checkbox.isSelected()) {
            result = true;
        }

        return result;
    }

    public void clickAddAdditionalOrganizationButtonToCollapse() {

        WebElement addBtn = driver.findElement(addAdditionalOrganizationButton);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", addBtn);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(organizationSearchPlaceholder));

        pause(1000);
    }

    public void clickAddAdditionalOrganizationButton() {

        WebElement addBtn = driver.findElement(addAdditionalOrganizationButton);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", addBtn);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(organizationSearchPlaceholder));
        wait.until(ExpectedConditions.visibilityOfElementLocated(applyButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cancelButton));

        pause(1000);
    }

    public void clickOrganizationLevelAccessToggleButton() {

        WebElement toggleBtn = driver.findElement(organizationLevelAccessToggleBtn);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", toggleBtn);

        // Try normal click first
        toggleBtn.click();
        pause(1000);
    }

    public void clickSaveButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement saveBtn = wait.until(ExpectedConditions.presenceOfElementLocated(saveButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn));

        try {
            saveBtn.click();
        } catch (Exception e) {

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        }

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


    public boolean VerifyExportLabelIsDisplayed() {

        boolean result = false;

        waitForPresence(lblExport);

        String text = driver.findElement(lblExport).getText();

        if (Objects.equals(text, "Export")) {
            result = true;
        }

        return result;
    }

    public void selectExportControlManageAndSave() {

        WebElement manageChk = driver.findElement(exportControlManageCheckbox);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", manageChk);

        if (!manageChk.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", manageChk);
        }

        WebElement saveBtn = driver.findElement(saveButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);
        saveBtn.click();

        pause(1000);
    }



    public void selectExportControlViewAndSave() {

        WebElement viewChk = driver.findElement(exportControlViewCheckbox);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", viewChk);

        if (!viewChk.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewChk);
        }

        WebElement saveBtn = driver.findElement(saveButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);
        saveBtn.click();

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