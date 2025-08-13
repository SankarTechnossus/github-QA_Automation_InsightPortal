package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Exportcontrol_WorkflowManagement_StepName extends BasePage {

    public  Exportcontrol_WorkflowManagement_StepName (WebDriver driver) {
        super(driver);
    }


// Locators
    private By workflowManagementLinkstepname = By.xpath("//nav//a[(normalize-space(.)='Workflow Management' or .//span[normalize-space(.)='Workflow Management'])" + " and contains(@href,'/administration/workflow-management')]");
    private By stepNameLink = By.xpath("//a[normalize-space(text())='Step name' and contains(@href,'/step-names')]");
    private By addNewButton = By.xpath("//button[@type='button' and contains(@class,'button') and contains(@class,'-primary') and normalize-space(text())='Add New']");
    private By nameInputField = By.xpath("//input[@id='name' and contains(@class,'default-input') and @type='text']");
    private By cancelButton = By.xpath("//button[@type='button' and contains(@class,'button') and contains(@class,'-unstyled') and normalize-space(text())='Cancel']");
    private By nameInputFielduni = By.xpath("//input[@id='name' and contains(@class,'default-input') and @type='text']");
    private By addButton = By.xpath("//button[@type='button' and contains(@class,'button') and contains(@class,'-primary') and normalize-space(text())='Add']");



//Actions


    public void clickAddButton() {
        WebElement button = driver.findElement(addButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addButton));

        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }

        pause(1000);
    }


    public String enterUniqueName(String baseName) {
        String uniqueName = baseName + "_" + System.currentTimeMillis();

        WebElement input = driver.findElement(nameInputFielduni);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputFielduni));

        input.clear();
        input.sendKeys(uniqueName);

        pause(1000);
        return uniqueName; // return so the test can reuse it if needed
    }





    public void clickCancelButton() {
        WebElement button = driver.findElement(cancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));

        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }

        pause(1000);
    }



    public void enterName(String name) {
        WebElement input = driver.findElement(nameInputField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputField));

        input.clear();
        input.sendKeys(name);

        pause(1000);
    }



    public void clickAddNewButton() {
        WebElement button = driver.findElement(addNewButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addNewButton));

        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }

        pause(1000);
    }


    public void clickStepNameLink() {
        WebElement link = driver.findElement(stepNameLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(stepNameLink));

        try {
            link.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }

        pause(1000);
    }


    // Action (scroll → wait → click → confirm; no logging here)
    public void clickWorkflowManagementstepname() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(workflowManagementLinkstepname));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();
        wait.until(ExpectedConditions.urlContains("/administration/workflow-management"));
        pause(600);
    }




}
