package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdobeE_Sign_AgreementPage extends BasePage {

    public AdobeE_Sign_AgreementPage(WebDriver driver) {
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
//    private By administrationLink = By.xpath("//a[contains(@class, 'module-link') and contains(., 'Administration')]");
    private By administrationLink = By.xpath("//a[contains(@href, '/administration') and contains(., 'Administration')]");
    private By formsManagementLink = By.xpath("//a[contains(@href, '/administration/forms-management-agreements') and contains(., 'Forms Management')]");

    // Actions

    public void clickFormsManagementLink() {
        click(formsManagementLink);
        pause(10000);
    }


    public void clickAdministrationLink() {
        WebElement adminElement = new WebDriverWait(driver, Duration.ofSeconds(60))
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
