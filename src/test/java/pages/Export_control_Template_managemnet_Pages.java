package pages;

import base.BasePage;
import org.openqa.selenium.*;
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

    private By addNewTemplateLink = By.xpath("//a[@href='/administration/template-management/new' and normalize-space()='Add new']");

    private By titleInput = By.id("title");

    private By templateManagementMenu01 = By.xpath("//div[@id='left-sidebar']//a[@class='label' and normalize-space()='Template Management']");
    private By exportControlLink01 = By.xpath("//div[@id='left-sidebar']//a[@class='label' and normalize-space()='Export Control' and contains(@href,'template-management/export-control')]");




    //Action

    public void clickTemplateManagementExportControl01() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement templateMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(templateManagementMenu01));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", templateMenu);

        try {
            templateMenu.click(); // parent often navigates directly
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", templateMenu);
        }

        // EITHER: we already navigated to the page, OR we need to click the child link
        boolean onTarget =
                wait.until(d -> d.getCurrentUrl().contains("/administration/template-management/export-control")
                        || !driver.findElements(exportControlLink01).isEmpty());

        if (!driver.getCurrentUrl().contains("/administration/template-management/export-control")) {
            // Sidebar path: click the child
            WebElement child = wait.until(ExpectedConditions.elementToBeClickable(exportControlLink01));
            try {
                child.click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", child);
            }
        }

        pause(1000);
    }


    public String enterUniqueTitle() {
        // Generate unique name starting with "Test"
        String uniqueTitle = "Test" + System.currentTimeMillis();

        WebElement input = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(titleInput));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);
        input.clear();
        input.sendKeys(uniqueTitle);

        pause(500);
        return uniqueTitle; // return value if you want to use it in test for validation
    }


    public void clickAddNewTemplate() {
        WebElement addNew = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(addNewTemplateLink));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", addNew);
        addNew.click();

        pause(1000);
    }


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
