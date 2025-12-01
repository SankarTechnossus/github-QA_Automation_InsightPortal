package pages.Administration;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StepNamePage extends BasePage {

    public StepNamePage(WebDriver driver) {
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
    private String editButtonForStepNameXpath = "//tr[.//td[@data-column='name' and @data-value='%s']]//button[normalize-space(text())='Edit']";
    private String cancelBtnForStepNameXpath =
            "//tr[.//td[@data-column='name' and (@data-value='%s' or .//input[@value='%s'])]]" +
                    "//td[@data-column='_actions']//button[normalize-space(text())='Cancel']";


    private String saveBtnForStepNameXpath =
            "//tr[.//td[@data-column='name' and (@data-value='%s' or .//input[@value='%s'])]]" +
                    "//td[@data-column='_actions']//button[(normalize-space(text())='Save' or @aria-label='Save item')]";
//
//
//    private By exportControlToggle =
//            By.xpath("//div[contains(@class,'menu-item')][.//button[contains(@class,'label') and normalize-space()='Export Control']]//button[contains(@class,'label')]");
//
//    // The 'Step name' under *Export Control* only (uses the sliding-container right after the group)
//    private By exportControlStepNameLink =
//            By.xpath("//div[contains(@class,'menu-item')][.//button[contains(@class,'label') and normalize-space()='Export Control']]" +
//                    "/following-sibling::div[contains(@class,'sliding-container')][1]" +
//                    "//a[normalize-space()='Step name' and contains(@href,'/step-names')]");


    // The "Export Control" header button in the left nav
    private By exportControlHeaderBtn = By.xpath(
            "//button[contains(@class,'label') and normalize-space(.)='Export Control']"
    );

    // The "Step name" link under Export Control (scopeId=3 only)
    private By exportControlStepNameLink = By.xpath(
            "//button[contains(@class,'label') and normalize-space(.)='Export Control']" +
                    "/ancestor::div[contains(@class,'-level-1')][1]" +
                    "/following-sibling::div[contains(@class,'toggleable-menu-children')][1]" +
                    "//a[normalize-space(.)='Step name' and contains(@href,'/scopeId/3/step-names')]"
    );




//Actions


    public void clickExportControlStepName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1) Bring the Export Control header into view
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(exportControlHeaderBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", header);

        // 2) Ensure the section is expanded
        String expanded = header.getAttribute("aria-expanded");
        if (expanded == null || !expanded.equalsIgnoreCase("true")) {
            header.click();
            pause(400);
        }

        // 3) Click the scoped Step name link (scopeId=3)
        WebElement stepLink = wait.until(ExpectedConditions.presenceOfElementLocated(exportControlStepNameLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", stepLink);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(stepLink)).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", stepLink);
        }

        pause(800);
    }



//
//    public void clickExportControlStepName() {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // 1) Ensure the Export Control group is visible
//        WebElement toggle = wait.until(ExpectedConditions.visibilityOfElementLocated(exportControlToggle));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", toggle);
//
//        // 2) Expand if collapsed (aria-expanded != true)
//        String expanded = toggle.getAttribute("aria-expanded");
//        if (expanded == null || !expanded.equalsIgnoreCase("true")) {
//            toggle.click();
//            pause(500);
//        }
//
//        // 3) Click the scoped 'Step name' inside Export Control
//        WebElement stepName = wait.until(ExpectedConditions.elementToBeClickable(exportControlStepNameLink));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", stepName);
//
//        try {
//            stepName.click();
//        } catch (ElementClickInterceptedException e) {
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", stepName);
//        }
//
//        pause(1000);
//    }


    public void clickSaveForStepName(String stepName) {
        By saveLocator = By.xpath(String.format(saveBtnForStepNameXpath, stepName, stepName));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);

        try {
            saveBtn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        }

        // Post-save sanity: row returns to view mode with 'Edit' button visible
        By editAfterSave = By.xpath(String.format(
                "//tr[.//td[@data-column='name' and @data-value='%s']]//button[normalize-space(text())='Edit']",
                stepName
        ));
        wait.until(ExpectedConditions.presenceOfElementLocated(editAfterSave));

        pause(1000);
    }


    public void clickCancelForStepName(String stepName) {
        By cancelLocator = By.xpath(String.format(cancelBtnForStepNameXpath, stepName, stepName));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(cancelLocator));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }

        pause(1000);
    }


    public void clickEditButtonForStepName(String stepName) {
        By editButtonLocator = By.xpath(String.format(editButtonForStepNameXpath, stepName));

        WebElement button = driver.findElement(editButtonLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(editButtonLocator));

        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }

        pause(1000);
    }



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