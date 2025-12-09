
package pages.Adobe;

import base.BasePage;
import listeners.ExtentReportListener;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AgreementPage extends BasePage {

    public AgreementPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    By leftSidebar = By.cssSelector("div[class*='administrationSubmenu'][class*='current-module']");
    By exportControlLink = By.cssSelector("a[href='/administration/forms-management-export-control']");
    By agreementsLink = By.xpath("//a[contains(text(),'Agreements')]");
    By agreementNumberInput = By.xpath("//input[@id='agreementNumber']");
    By searchButton = By.xpath("//button[text()='Search']");
    By agreementSpan = By.xpath("//span[text()='2025A019384']");
    By deliverablesTab = By.xpath("//a[@href='/agreements/2025A019384/latest/deliverables']//span[text()='Deliverables']");
    By toggleButton = By.xpath("//button[@type='button' and @aria-label='Expand/collapse' and contains(@class, 'toggle-button')]");
    By eSignTesting03Link = By.xpath("//a[@href='/agreements/2025A019384/latest/deliverables/1113382']//span[text()='Test']");
    By adobeIcon = By.xpath("//img[@alt='adobe-icon']");
    By fileInput = By.xpath("//input[@type='file' and contains(@accept,'application/pdf')]");
    By addRecipientButton = By.xpath("//button[text()='Add Recipient']");
    By emailInput = By.xpath("//input[@placeholder='Email' and @type='email']");
    By previewButton = By.xpath("//div[contains(@class, 'add-recipients-section')]//button[normalize-space(text())='Preview']");
    By administrationLink = By.xpath("//a[contains(@href, '/administration') and contains(., 'Administration')]");
    By formsManagementLink = By.xpath("//a[contains(@href, '/administration/forms-management-agreements') and contains(., 'Forms Management')]");
    By searchByNameInput = By.xpath("//input[@type='text' and @placeholder='Search by Name' and @maxlength='300' and @value='']");
    By searchButton01 = By.xpath("//button[@type='button' and contains(@class, 'button') and contains(@class, '-primary') and text()='Search']");
    By clearSelectionsButton = By.xpath("//button[@type='button' and contains(@class, 'button') and contains(@class, '-unstyled') and text()='Clear Selections']");
    By addNewLink = By.xpath("//a[@href='/administration/forms-management-export-control/new' and text()='Add new']");
    By nameInputField = By.id("name");
    By descriptionTextArea = By.id("description");
    By typeDropdown = By.id("type");  // the input element used to type/select
    By checklistOption = By.xpath("//div[contains(@class,'option') and text()='Checklist']");
    By categoryDropdown = By.id("category");  // Input element to activate dropdown
    By generalOption = By.xpath("//div[contains(@class,'option') and text()='General']");
    By categorySequenceNoInput = By.id("categorySequenceNo");
    By createButton = By.xpath("//button[@type='button' and contains(@class, 'button') and contains(@class, '-primary') and text()='Create']");
    By version1Link = By.xpath("//a[text()='Version 1']");
    By reminderDropdown = By.xpath("//div[@id='react-select-17-placeholder' and text()='Select frequency']");
    By reminderOptionEveryDay = By.xpath("//div[contains(text(),'Every day') and contains(@class,'menu')]");
    By uploadedFileLabel = By.xpath("//div[contains(@class,'_attachedFile')]/span[contains(text(),'.pdf')]");
    By uploadedFileName = By.xpath("//span[contains(text(),'Agreement Info 2025_03.pdf')]");
    By hiddenFileInput = By.xpath("//div[contains(@class,'_fileUploader')]/input[@type='file']");
    By advancedButton = By.xpath("//button[normalize-space(text())='Advanced (0)']");
    By closeButton = By.xpath("//button[normalize-space(text())='Close']");
    By clearSelectionsButton01 = By.xpath("//button[normalize-space(text())='Clear Selections']");
    By advancedBtn = By.xpath("//form[contains(@class,'agrAgreementsLandingSearchForm')]//button[normalize-space(.)='Search']/following-sibling::button[contains(.,'Advanced')]");
    By advancedBtnAlt = By.xpath("//form[contains(@class,'agrAgreementsLandingSearchForm')]//button[contains(normalize-space(.),'Advanced (')]");
    By addNewDeliverableBtn = By.xpath("//button[contains(@class,'add-deliverable-button') and normalize-space(.)='Add New Deliverable']");
    By typeControlnew01 = By.xpath("//label[@for='type']/following::div[contains(@class,'select-control')][1]");
    By administrationHeader = By.xpath("//*[normalize-space()='ADMINISTRATION']");
    By formsManagementHeader = By.xpath("//strong[normalize-space()='Forms Management']");
    By formsManagementSearchResultRow = By.xpath("//table//tr[contains(@class,'item-grid-tr')]");
    By newFormBreadcrumbHeader = By.xpath("//span[contains(@class,'_font-bold') and normalize-space()='New form']");
    By formVersionsHeader = By.xpath("//header[contains(@class,'_font-size-medium') and contains(normalize-space(),'Form Versions')]");
    By version1BreadcrumbHeader = By.xpath("//span[contains(@class,'_font-bold') and normalize-space()='Version 1']");
    By dashboardNotificationsHeader = By.xpath("//*[normalize-space()='Dashboard Notifications - Summary']");

    // ******** Actions *********

    public boolean isDashboardNotificationsSummaryDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement header = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(dashboardNotificationsHeader));
            return header.isDisplayed();

        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isVersion1PageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(version1BreadcrumbHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isFormVersionsPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(formVersionsHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isNewFormPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(newFormBreadcrumbHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isSearchResultDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(formsManagementSearchResultRow));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isFormsManagementPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(formsManagementHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isAdministrationPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(administrationHeader));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void selectTypeAsExportControlRequestnew01() {
        WebElement control = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(typeControlnew01));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", control);

        control.click();

        WebElement option = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(@class,'menu')]//div[normalize-space()='Test1']")));
        option.click();

        pause(1000);
    }

    public void clickAddNewDeliverable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addNewDeliverableBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
        pause(1000);
    }

    public void clickAdvanced() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By target = advancedBtn;
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(target));
        } catch (TimeoutException e) {
            target = advancedBtnAlt; // fall back if text changed (e.g., Advanced (1))
            wait.until(ExpectedConditions.presenceOfElementLocated(target));
        }

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(target));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
        pause(1000);
    }

    public void clickClearSelectionsButton01() {
        WebElement button = driver.findElement(clearSelectionsButton01);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(clearSelectionsButton01));

        button.click();
        pause(1000);
    }

    public void clickCloseButton() {
        WebElement button = driver.findElement(closeButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(closeButton));

        button.click();
        pause(1000);
    }

    public void clickAdvancedButton() {
        WebElement button = driver.findElement(advancedButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(advancedButton));

        button.click();
        pause(1000);
    }

    public void uploadAgreementPdf(String filePath) {
        WebElement uploadInput = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(hiddenFileInput));
        uploadInput.sendKeys(filePath);
        ExtentReportListener.getExtentTest().pass("Uploaded file: " + filePath);
    }

    public void waitForUploadedFileToAppear() {
        By uploadedFileLocator = By.xpath("//span[contains(text(),'Agreement Info') and contains(text(),'.pdf')]");
        waitForPresence(uploadedFileLocator); // Uses your 120s wait method in BasePage
        ExtentReportListener.getExtentTest().pass("Waited for uploaded file to appear successfully");
    }

    public void uploadPDFToAgreement(String filePath) {
        WebElement input = driver.findElement(fileInput);

        // Make it visible for sendKeys to work
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", input);

        input.sendKeys(filePath);

        pause(3000); // Wait for file to render visually
        ExtentReportListener.getExtentTest().pass("Uploaded file successfully: " + filePath);
    }

    public void selectReminderAsEveryDay() {
        WebElement dropdown = driver.findElement(reminderDropdown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
        pause(1000);
        dropdown.click(); // Open dropdown

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(reminderOptionEveryDay));
        option.click(); // Select "Every day"

        pause(1000);
    }

    public void clickVersion1Link() {
        By version1Link = By.linkText("Version 1");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            // Optional: wait for loading overlays to disappear
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".toastify, .loading-spinner")));

            // Ensure element exists in DOM first
            wait.until(ExpectedConditions.presenceOfElementLocated(version1Link));

            // Wait until it is visible and clickable
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(version1Link));

            // Scroll and JS click
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);


        } catch (Exception e) {
            throw new RuntimeException(" Unable to click 'Version 1' link: " + e.getMessage(), e);
        }
    }

    public void clickCreateButton() {
        WebElement button = driver.findElement(createButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);  // Optional: ensure visibility
        pause(1000);
        button.click();
        pause(3000);  // Wait for navigation or action to complete
    }

    public void enterCategorySequenceNo(String number) {
        WebElement input = driver.findElement(categorySequenceNoInput);
        input.clear();             // Clear existing value
        input.sendKeys(number);    // Enter the desired number
        pause(1000);               // Optional wait
    }

    public void selectCategoryAsGeneral() {
        WebElement dropdown = driver.findElement(categoryDropdown);

        // Step 1: Click to expand the dropdown
        dropdown.click();
        pause(1000);  // Wait for options to load

        // Step 2: Select the "General" option
        WebElement option = driver.findElement(generalOption);
        option.click();
        pause(1000);  // Allow selection to register
    }

    public void selectTypeAsChecklist() {
        WebElement dropdown = driver.findElement(typeDropdown);

        // Click to open the dropdown
        dropdown.click();
        pause(1000);  // Wait for dropdown to expand

        // Use arrow keys or directly click the visible "Checklist" option
        WebElement option = driver.findElement(checklistOption);
        option.click();
        pause(1000);  // Allow time to register selection
    }

    public void enterDescription(String text) {
        WebElement textarea = driver.findElement(descriptionTextArea);
        textarea.clear();           // Optional: clears any existing text
        textarea.sendKeys(text);    // Sends the input text
        pause(1000);                // Optional wait
    }

    public void enterName(String name) {
        WebElement input = driver.findElement(nameInputField);
        input.clear();  // Optional: clears existing text
        input.sendKeys(name);
        pause(1000);  // Optional: wait after entering
    }

    public void clickAddNewLink() {
        WebElement link = driver.findElement(addNewLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link); // Optional scroll
        pause(1000); // Wait after scroll
        link.click();
        pause(3000); // Wait for navigation
    }

    public void scrollSidebarToExportControlOnly() {
        WebElement sidebar = driver.findElement(leftSidebar);
        WebElement target = driver.findElement(exportControlLink);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTop = arguments[1].offsetTop - arguments[0].offsetTop;",
                sidebar, target
        );

        pause(2000); // Allow time for scrolling effect
    }

    public void scrollSidebarToExportControlAndClick() {
        WebElement sidebar = driver.findElement(leftSidebar);
        WebElement target = driver.findElement(exportControlLink);

        // Scroll the left sidebar container itself
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollTop = arguments[1].offsetTop - arguments[0].offsetTop;",
                sidebar, target
        );

        pause(1500);  // Let scroll complete
        target.click();
        pause(5000);  // Wait for new content to load
    }

    public void clickClearSelectionsButton() {
        click(clearSelectionsButton);  // Uses your reusable click() method
        pause(5000); // Optional wait after click
    }

    public void clickSearchButton() {
        click(searchButton01);  // Assuming click() is your basePage reusable method
        pause(5000); // Optional wait after click
    }

    public void enterSearchText(String name) {
        type(searchByNameInput, name);  // Assuming you have a `type()` wrapper method in basePage
        pause(5000); // Optional wait after typing
    }

    public void clickFormsManagementLink() {
        click(formsManagementLink);
        pause(5000);
    }

    public void clickAdministrationLink() {
        WebElement adminElement = new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.visibilityOfElementLocated(administrationLink));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", adminElement);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(adminElement));

        adminElement.click();
        pause(5000);
    }

    public void clickAgreementsLink() {
        WebElement link = new WebDriverWait(driver, Duration.ofSeconds(240))
                .until(ExpectedConditions.visibilityOfElementLocated(agreementsLink));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(link));

        link.click();
        pause(5000);
    }

    public void enterAgreementNumber(String number) {
        WebElement element = waitForElement50(agreementNumberInput);
        element.sendKeys(number);
        pause(5000);
    }

    public void clickSearch() {
        click(searchButton);
        pause(5000);
    }

    public void clickAgreementSpan() {
        click(agreementSpan);
        pause(5000);
    }

    public void clickDeliverablesTab() {
        scrollAndJsClick(deliverablesTab, 10);
        pause(5000);
    }

    public void clickToggleButton() {
        click(toggleButton);
        pause(5000);
    }

    public void clickTestLink() {
        click(eSignTesting03Link);
        pause(5000);
    }

    public void clickAdobeIcon() {
        click(adobeIcon);
        pause(5000);
    }

    public void clickAddRecipient() {
        click(addRecipientButton);
        pause(5000);
    }

    public void enterRecipientEmail(String email) {
        type(emailInput, email);
        pause(5000);
    }

    public void clickPreviewButton() {
        click(previewButton);
        pause(10000);
    }
}