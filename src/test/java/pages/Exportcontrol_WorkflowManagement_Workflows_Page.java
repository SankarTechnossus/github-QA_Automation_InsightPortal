package pages;

import base.BasePage;
import listeners.ExtentReportListener;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Exportcontrol_WorkflowManagement_Workflows_Page extends BasePage {

    public  Exportcontrol_WorkflowManagement_Workflows_Page (WebDriver driver) {
        super(driver);
    }


// Locators

    private By workflowManagementLink = By.xpath("//a[.//span[text()='Workflow Management'] and contains(@href, '/workflow-management')]");
    private By exportControlWorkflowsLink = By.xpath("//a[contains(@href, 'workflows-export-control') and text()='Workflows']");

//Actions

    public void clickExportControlWorkflows() {
        WebElement link = driver.findElement(exportControlWorkflowsLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(exportControlWorkflowsLink));

        link.click();
        pause(1000);
    }




    public void clickWorkflowManagementLink() {
        WebElement link = driver.findElement(workflowManagementLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(workflowManagementLink));

        link.click();
        pause(1000);
    }




}







