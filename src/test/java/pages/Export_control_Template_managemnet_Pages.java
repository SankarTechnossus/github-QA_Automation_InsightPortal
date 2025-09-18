package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Export_control_Template_managemnet_Pages extends BasePage {

    public Export_control_Template_managemnet_Pages(WebDriver driver) {
        super(driver);
    }



    //Locators

    // Parent: Template Management
    private By templateManagementMenu = By.xpath("//a[@class='label' and normalize-space()='Template Management']");

    // Child: Export Control under Template Management
    private By exportControlLink = By.xpath("//a[@class='label' and normalize-space()='Export Control' and contains(@href,'template-management/export-control')]");



    //Action

    public void clickTemplateManagementExportControl() {
        // Scroll Template Management into view
        WebElement templateMenu = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(templateManagementMenu));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", templateMenu);

        // Click Template Management to expand (if not already expanded)
        templateMenu.click();
        pause(1000);

        // Wait for Export Control link to appear and click
        WebElement exportCtrl = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(exportControlLink));
        exportCtrl.click();

        pause(1000);
    }








}
