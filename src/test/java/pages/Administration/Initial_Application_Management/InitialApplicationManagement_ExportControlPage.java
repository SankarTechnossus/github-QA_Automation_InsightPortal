
package pages.Administration.Initial_Application_Management;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InitialApplicationManagement_ExportControlPage extends BasePage {
    private WebDriverWait wait;
    public InitialApplicationManagement_ExportControlPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    //Locators

    By inputBy = By.xpath("(//input[@role='combobox' and @id='entityTypeId'])[last()]");

    private By actionNameInput = By.id("name");   // from your HTML: id="name"

    // Method
    public void enterRandomActionName(String actionName) {
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(actionNameInput)
        );
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input
        );
        input.clear();
        input.sendKeys(actionName);
    }


    // Step 1: Main "Initial Application Management" entry (works for <a> or <button>)
    private By initialAppMgmtMain = By.xpath(
            "//div[@id='left-sidebar']" +
                    "//span[normalize-space()='Initial Application Management']" +
                    "/ancestor::*[self::a or self::button][1]"
    );

    // Step 2: Arrow icon for Initial Application Management (if present)
    private By initialAppMgmtArrow = By.xpath(
            "//div[@id='left-sidebar']" +
                    "//span[normalize-space()='Initial Application Management']" +
                    "/ancestor::div[contains(@class,'-level-1') and contains(@class,'menu-item')]" +
                    "//button[contains(@class,'toggle-menu-icon-button')]"
    );
    // Step 3: "Export Control" under Initial Application Management
    private By initialAppMgmtExportControl = By.xpath(
            "//div[contains(@class,'_applicationManagementMenu')]//a[normalize-space()='Export Control']"
    );
    // Step 1: Add Initial Application button
    private By addInitialApplicationButton = By.xpath(
            "//button[normalize-space()='Add Initial Application']"
    );
    private By addButton = By.xpath("//button[contains(@class,'button') and contains(@class,'-primary') and normalize-space()='Add']");


    // --- Modal buttons ---
    private By modalAddButton = By.xpath(
            "//div[contains(@class,'ReactModal__Content')]//button[normalize-space()='Add']");

    private By modalCancelButton = By.xpath(
            "//div[contains(@class,'ReactModal__Content')]//button[normalize-space()='Cancel']");

    // put in class-level locators section
    private By entityTypeCombobox = By.xpath(
            "//div[contains(@class,'ReactModal__Content--after-open')]"
                    + "//input[@id='entityTypeId' and @role='combobox']"
    );

    // First row "Edit" button in Initial Application Management > Export Control
    private By firstEditButton = By.xpath(
            "(//table[contains(@class,'item-grid')]//tbody//button[normalize-space()='Edit'])[1]"
    );

    private By inlineCancelButton = By.xpath(
            "(//tr[contains(@class,'item-grid-tr')]//button[@aria-label='Undo item'])[1]"
    );

    private By inlineSaveButton = By.xpath(
            "(//tr[contains(@class,'item-grid-tr')]//button[@aria-label='Save item'])[1]"
    );



    //Actions

    public void clickInlineCancel() {
        WebElement cancel = wait.until(ExpectedConditions.elementToBeClickable(inlineCancelButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", cancel);
        cancel.click();
        pause(500);
    }

    public void clickInlineSave() {
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(inlineSaveButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", save);
        save.click();
        pause(800);
    }


    public void clickFirstEditButton() {
        WebElement editBtn = wait.until(
                ExpectedConditions.elementToBeClickable(firstEditButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", editBtn
        );

        editBtn.click();
    }


    public void clickAddButton() {

        // Step 1: Wait & scroll into view
        WebElement add = wait.until(
                ExpectedConditions.elementToBeClickable(addButton)
        );
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", add);

        // Step 2: Click Add
        add.click();

        // Step 3: Pause (your standard)
        pause(1000);
    }


    public void selectEntityType() {

        // Wait until the combobox is visible in the active modal
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(entityTypeCombobox)
        );

        // Scroll into view
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        // Use JS click to bypass any overlay / animation issues
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", input);

        // Type and select "Export Control"
        input.clear();
        input.sendKeys("Export Control");
        pause(500);
        input.sendKeys(Keys.ENTER);
    }



    public String enterRandomActionAndSelectEntity() {

        // Step 1: Generate random name using your BasePage method
        String randomName = GenerateRandomName(6);

        // Step 2: Enter Action Name
        WebElement actionNameInput =
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", actionNameInput);

        actionNameInput.click();
        actionNameInput.clear();
        actionNameInput.sendKeys(randomName);

        // Step 3: Select Entity Type = Export Control (React-Select)
        selectEntityType();

        return randomName;
    }





    public void clickCancel() {
        driver.findElement(modalCancelButton).click();
    }

    // Click Add on modal
    public void clickAddOnModal() {
        WebElement add = wait.until(ExpectedConditions.elementToBeClickable(modalAddButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", add);
        add.click();
    }

    public void clickAddInitialApplication() {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(addInitialApplicationButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn
        );

        btn.click();
        pause(800);
    }

    public void openInitialApplicationManagementExportControl() {

        // STEP 1 – Click "Initial Application Management" (link or button)
        WebElement main = wait.until(
                ExpectedConditions.elementToBeClickable(initialAppMgmtMain));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", main);
        main.click();
        pause(800); // allow page / submenu to render

        // STEP 2 – If Export Control not visible yet, try expanding via arrow
        if (driver.findElements(initialAppMgmtExportControl).isEmpty()) {
            java.util.List<WebElement> arrows = driver.findElements(initialAppMgmtArrow);
            if (!arrows.isEmpty()) {
                WebElement arrow = wait.until(
                        ExpectedConditions.elementToBeClickable(initialAppMgmtArrow));
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center'});", arrow);
                arrow.click();
                pause(800);
            }
        }

        // STEP 3 – Click "Export Control"
        WebElement exportControl = wait.until(
                ExpectedConditions.elementToBeClickable(initialAppMgmtExportControl));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", exportControl);
        exportControl.click();
        pause(1000);
    }






}
