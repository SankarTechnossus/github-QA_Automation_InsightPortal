package pages.Export_Control;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Export_control_Display_checklist_flow_page extends BasePage {

    private WebDriverWait wait;

    public Export_control_Display_checklist_flow_page(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Locators

    By personnelExclusionRadio = By.xpath("//span[normalize-space()='Personnel Exclusion']/preceding-sibling::input[@type='radio']");
    By newTest07Link = By.xpath("//span[normalize-space()='New Test 07']/parent::a");
    By phoneNumberInput = By.xpath("//div[contains(@class,'dynamic-form-field') and @data-type='PhoneInput']" + "//input[@type='tel']");
    By saveActionButton = By.xpath("//button[normalize-space()='Save']");
    By submitActionButton = By.xpath("//button[normalize-space()='Submit']");
    By option1Radio = By.xpath("//span[normalize-space()='Option (1)']/preceding-sibling::input[@type='radio']");

    //Actions

    public void selectOption1() {
        WebElement radio = wait.until(
                ExpectedConditions.elementToBeClickable(option1Radio)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", radio);

        radio.click();

        pause(1000);
    }

    public void clickSubmitAction() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(submitActionButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(1000);
    }

    public void clickSaveAction() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(saveActionButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(1000);
    }

    public void enterPhoneNumber(String phoneNumber) {
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(phoneNumberInput)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(phoneNumber);

        pause(1000);
    }

    public void clickNewTest07() {
        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(newTest07Link)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", link);

        link.click();

        pause(1000);
    }

    public void selectPersonnelExclusion() {
        WebElement radio = wait.until(
                ExpectedConditions.elementToBeClickable(personnelExclusionRadio)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", radio);

        radio.click();

        pause(1000);
    }
}