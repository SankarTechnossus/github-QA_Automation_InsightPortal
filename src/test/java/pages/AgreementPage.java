package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AgreementPage extends BasePage {

    public AgreementPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By agreementsLink = By.xpath("//a[contains(text(),'Agreements')]");
    private By agreementNumberInput = By.xpath("//input[@id='agreementNumber']");
    private By searchButton = By.xpath("//button[text()='Search']");
    private By agreementSpan = By.xpath("//span[text()='2025A012368']");
    private By deliverablesTab = By.xpath("//a[@href='/agreements/2025A012368/latest/deliverables']//span[text()='Deliverables']");
    private By toggleButton = By.xpath("//button[@type='button' and @aria-label='Expand/collapse' and contains(@class, 'toggle-button')]");
    private By eSignTesting03Link = By.xpath("//a[@href='/agreements/2025A012368/latest/deliverables/1113382']//span[text()='Test']");
    private By adobeIcon = By.xpath("//img[@alt='adobe-icon']");
    private By fileInput = By.xpath("//input[@type='file' and @accept='application/pdf']");
    private By addRecipientButton = By.xpath("//button[text()='Add Recipient']");
    private By emailInput = By.xpath("//input[@placeholder='Email' and @type='email']");
    private By previewButton = By.xpath("//div[contains(@class, 'add-recipients-section')]//button[normalize-space(text())='Preview']");
    private By administrationLink = By.xpath("//a[contains(@href, '/administration') and contains(., 'Administration')]");
    private By formsManagementLink = By.xpath("//a[contains(@href, '/administration/forms-management-agreements') and contains(., 'Forms Management')]");
    private By searchByNameInput = By.xpath("//input[@type='text' and @placeholder='Search by Name' and @maxlength='300' and @value='']");
    private By searchButton01 = By.xpath("//button[@type='button' and contains(@class, 'button') and contains(@class, '-primary') and text()='Search']");
    private By clearSelectionsButton = By.xpath("//button[@type='button' and contains(@class, 'button') and contains(@class, '-unstyled') and text()='Clear Selections']");
    private By exportControlLink = By.xpath("//a[@href='/administration/forms-management-export-control' and span[text()='Export Control']]");
    private By leftSidebar = By.cssSelector("#left-sidebar");
    private By addNewLink = By.xpath("//a[@href='/administration/forms-management-export-control/new' and text()='Add new']");
    private By nameInputField = By.id("name");
    private By descriptionTextArea = By.id("description");
    private By typeDropdown = By.id("type");  // the input element used to type/select
    private By checklistOption = By.xpath("//div[contains(@class,'option') and text()='Checklist']");
    private By categoryDropdown = By.id("category");  // Input element to activate dropdown
    private By generalOption = By.xpath("//div[contains(@class,'option') and text()='General']");
    private By categorySequenceNoInput = By.id("categorySequenceNo");
    private By createButton = By.xpath("//button[@type='button' and contains(@class, 'button') and contains(@class, '-primary') and text()='Create']");




    // ******** Actions *********


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
        pause(10000);
    }

    public void clickAdministrationLink() {
        WebElement adminElement = new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(ExpectedConditions.visibilityOfElementLocated(administrationLink));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", adminElement);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(adminElement));

        adminElement.click();
        pause(10000);
    }

    public void clickAgreementsLink() {
        click(agreementsLink);
        pause(10000);
    }

    public void enterAgreementNumber(String number) {
        WebElement element = waitForElement50(agreementNumberInput);
        element.sendKeys(number);
        pause(10000);
    }

    public void clickSearch() {
        click(searchButton);
        pause(10000);
    }

    public void clickAgreementSpan() {
        click(agreementSpan);
        pause(10000);
    }

    public void clickDeliverablesTab() {
        scrollAndJsClick(deliverablesTab, 30);
        pause(10000);
    }

    public void clickToggleButton() {
        click(toggleButton);
        pause(10000);
    }

    public void clickTestLink() {
        click(eSignTesting03Link);
        pause(10000);
    }

    public void clickAdobeIcon() {
        click(adobeIcon);
        pause(10000);
    }

    public void uploadAgreementPdf(String filePath) {
        WebElement file = waitForPresence(fileInput);
        file.sendKeys(filePath);
        pause(10000);
    }

    public void clickAddRecipient() {
        click(addRecipientButton);
        pause(10000);
    }

    public void enterRecipientEmail(String email) {
        type(emailInput, email);
        pause(10000);
    }

    public void clickPreviewButton() {
        click(previewButton);
        pause(40000);
    }
}
