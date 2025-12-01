package pages.Export_Control.Export_Control_Details;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InitialReviewWorkflowPage extends BasePage {

    private WebDriverWait wait;

    public InitialReviewWorkflowPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Locators

    private By initialReviewLink = By.xpath("//div[@class='name' and normalize-space()='Initial Review (IR)']");
    private By expandGroupButton = By.xpath("//button[@aria-label='Expand group']");
    // First PDF (Review Letter)
    private By reviewLetterPdfButton = By.xpath("//button[@aria-label='Download review letter']");
    // Second PDF (Reviewer Checklist)
    private By reviewerChecklistPdfButton = By.xpath("//button[@aria-label='Download reviewer checklist']");
    // Textbox under a question label (e.g. "Your Name")
    private By textInputByQuestionLabel(String questionLabel) {
        return By.xpath(
                "//div[contains(@class,'dynamic-form-field')" +
                        "  and .//div[contains(@class,'_questionLabelContainer')]" +
                        "           //div[contains(@class,'fr-element') and normalize-space()='" + questionLabel + "']]" +
                        "//input[@type='text']"
        );
    }

    // Radio option under a question label (e.g. "Gender" -> "Male")
    private By radioOptionByQuestionLabel(String questionLabel, String optionText) {
        return By.xpath(
                "//div[contains(@class,'dynamic-form-field')" +
                        "  and .//div[contains(@class,'_questionLabelContainer')]" +
                        "           //div[contains(@class,'fr-element') and normalize-space()='" + questionLabel + "']]" +
                        "//div[contains(@class,'radio-group')]//label" +
                        "[.//span[normalize-space()='" + optionText + "']]//input[@type='radio']"
        );
    }





    //Actions

    // Name: "Test_Auto"
    public void enterYourName(String name) {
        By inputBy = textInputByQuestionLabel("Your Name");

        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(inputBy)
        );
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(name);

        pause(500);
    }

    // Gender: "Male"
    public void selectGender(String gender) {
        By radioBy = radioOptionByQuestionLabel("Gender", gender);

        WebElement radio = wait.until(
                ExpectedConditions.elementToBeClickable(radioBy)
        );
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", radio);

        if (!radio.isSelected()) {
            radio.click();
        }

        pause(500);
    }



    public void clickReviewLetterPDF() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement pdf1 = wait.until(
                ExpectedConditions.elementToBeClickable(reviewLetterPdfButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", pdf1);

        pdf1.click();

        pause(1500); // Let download start
    }

    public void clickReviewerChecklistPDF() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement pdf2 = wait.until(
                ExpectedConditions.elementToBeClickable(reviewerChecklistPdfButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", pdf2);

        pdf2.click();

        pause(1500); // Let download start
    }

    public void clickExpandGroup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(expandGroupButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(800);
    }

    public void clickInitialReview() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement ir = wait.until(
                ExpectedConditions.elementToBeClickable(initialReviewLink)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", ir);

        ir.click();

        pause(800);
    }





}