package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Exportcontrol_WorkflowManagement_ActionName extends BasePage {

    public  Exportcontrol_WorkflowManagement_ActionName (WebDriver driver) {
        super(driver);
    }


// Locators
private By workflowManagementLink = By.xpath("//nav//a[(normalize-space(.)='Workflow Management' or .//span[normalize-space(.)='Workflow Management'])" + " and contains(@href,'/administration/workflow-management')]");
    // Locator (stable: text + href parts; scoped to nav)
    private By actionNameScope3 = By.xpath("//nav//a[normalize-space(.)='Action name' " + "and contains(@href,'/administration/workflow-management') " + "and contains(@href,'/scopeId/3/') " + "and contains(@href,'/action-names')]");

//Actions


    public void clickActionNameScope3() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(actionNameScope3));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();
        wait.until(ExpectedConditions.urlContains("/administration/workflow-management/scopeId/3/action-names"));
        pause(600);
    }

    // Action (scroll → wait → click → confirm; no logging here)
    public void clickWorkflowManagementaction() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(workflowManagementLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();
        wait.until(ExpectedConditions.urlContains("/administration/workflow-management"));
        pause(600);
    }



}