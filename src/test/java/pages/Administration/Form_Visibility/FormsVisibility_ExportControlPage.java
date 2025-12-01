package pages.Administration.Form_Visibility;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FormsVisibility_ExportControlPage extends BasePage {

    private WebDriverWait wait;

    public FormsVisibility_ExportControlPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }



    //Locators
    // Left menu – Form Visibility toggle arrow

    // Active rules modal (after-open)
    private By activeRulesModal = By.xpath(
            "//div[contains(@class,'ReactModal__Content--after-open') and contains(@class,'_rulesModal')]");

    // Step 1: Form input inside active modal
    private By formInput = By.xpath(
            "//div[contains(@class,'ReactModal__Content--after-open') and contains(@class,'_rulesModal')]//input[@id='dataSourceKey']");

    // Step 2: Access input inside active modal
    private By accessInput = By.xpath(
            "//div[contains(@class,'ReactModal__Content--after-open') and contains(@class,'_rulesModal')]//input[@id='access']");

    // Step 3: Query Builder input inside active modal (relative to 'Query Builder' label)
    private By queryBuilderInput = By.xpath(
            "//div[contains(@class,'ReactModal__Content--after-open') and contains(@class,'_rulesModal')]//div[contains(text(),'Query Builder')]/following::input[@role='combobox'][1]");


    // Form dropdown inside rules modal
    private By formDropdown = By.xpath(
            "//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]//label[normalize-space()='Form']/following::div[contains(@class,'select-control')][1]");


    private By activeModal = By.xpath(
            "//div[contains(@class,'ReactModal__Content--after-open') and contains(@class,'_rulesModal')]"
    );




    private By formControl = By.xpath(
            "//div[contains(@class,'ReactModal__Content--after-open') and contains(@class,'_rulesModal')]//div[@id='react-select-2-placeholder']/ancestor::div[contains(@class,'select-control')]"
    );


    private By accessControl = By.xpath(
            "//div[contains(@class,'ReactModal__Content--after-open') and contains(@class,'_rulesModal')]//div[@id='react-select-3-placeholder']/ancestor::div[contains(@class,'select-control')]"
    );

    private By queryBuilderControl = By.xpath(
            "//div[contains(@class,'ReactModal__Content--after-open') and contains(@class,'_rulesModal')]//div[@id='react-select-4-placeholder']/ancestor::div[contains(@class,'select-control')]"
    );
    private By selectOption(String text) {
        return By.xpath("//div[@role='option' and normalize-space()='" + text + "']");
    }



    // Toggle button for Form Visibility
    private By formVisibilityToggleButton = By.xpath(
            "//button[contains(@class,'toggle-menu-icon-button') and contains(@aria-label,'Form Visibility')]");

    // Clickable link for Export Control under Form Visibility
    private By exportControlLink = By.xpath(
            "//a[contains(@href,'/administration/form-visibility/export-control') and contains(@class,'label')]");


//    private By formVisibilityToggle = By.xpath(
//            "//span[normalize-space()='Form Visibility']/ancestor::div[contains(@class,'menu-item')]//button[contains(@aria-label,'Collapse') or contains(@aria-label,'Expand')]");

    // Under Form Visibility – Export Control link
//    private By exportControlLink = By.xpath(
//            "//span[normalize-space()='Form Visibility']/ancestor::li[contains(@class,'menu-item')]//span[normalize-space()='Export Control']");
    private By addRuleButton = By.xpath("//button[normalize-space()='Add rule']");

//    private By formDropdown = By.xpath("//label[normalize-space()='Form']/following::div[contains(@class,'select-control')][1]");
//
//    private By accessDropdown = By.xpath("//label[normalize-space()='Access']/following::div[contains(@class,'select-control')][1]");
//
//    private By queryBuilderDropdown = By.xpath("//div[contains(text(),'Query Builder')]/following::div[contains(@class,'select-control')][1]");
//    private By reactSelectOption(String text) {
//        return By.xpath("//div[contains(@class,'select__option') and normalize-space()='" + text + "']");
//    }

    // React modal wrapper
    private By rulesModal = By.xpath(
            "//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]");


    private By modalWrapper = By.xpath("//div[@class='modal-content-wrapper']");

    private By addRuleInsideModalButton = By.xpath(
            "//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]//button[normalize-space()='Rule']"
    );

    private By removeRuleButton = By.xpath(
            "//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]//button[@aria-label='Remove rule']"
    );
    private By addGroupButton = By.xpath(
            "//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]//button[contains(@class,'-primary')][div/i[contains(@class,'fi-add')]][contains(.,'Group')]"
    );

    private By removeGroupButton = By.xpath(
            "//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]//button[@aria-label='Remove group']"
    );

    private By migrationButton = By.xpath(
            "//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]//button[normalize-space()='Migration']"
    );

    private By saveButton = By.xpath(
            "//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]//button[normalize-space()='Save']"
    );

    private By cancelButton = By.xpath(
            "//div[contains(@class,'ReactModal__Content') and contains(@class,'_rulesModal')]//button[normalize-space()='Cancel']"
    );


    // Step 1 – First row Edit button
    private By firstEditButton = By.xpath("(//table//button[normalize-space()='Edit'])[1]");

    // Step 1 – Cancel button inside Form Visibility modal
    private By cancelButton01 = By.xpath("//button[@type='button' and normalize-space()='Cancel']");


    // Step 1 – Save button inside Form Visibility modal
    private By saveButton01 = By.xpath("//button[@type='button' and normalize-space()='Save']");



    //Actions

    public void clickSaveButton() {

        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton01));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", save);

        save.click();
        pause(1000);
    }


    public void clickCancelButton() {

        WebElement cancel = wait.until(ExpectedConditions.elementToBeClickable(cancelButton01));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", cancel);

        cancel.click();
        pause(1000);
    }



    public void clickFirstEditButton() {

        WebElement editBtn = wait.until(
                ExpectedConditions.elementToBeClickable(firstEditButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", editBtn);

        editBtn.click();
        pause(1000);
    }

    public void clickCancel() {

        WebElement cancel = wait.until(
                ExpectedConditions.elementToBeClickable(cancelButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", cancel);

        cancel.click();

        pause(800);
    }



    public void clickSave() {

        WebElement save = wait.until(
                ExpectedConditions.elementToBeClickable(saveButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", save);

        save.click();

        pause(800);
    }


    public void clickMigration() {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(migrationButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(800);
    }


    public void clickRemoveGroup() {

        WebElement removeBtn = wait.until(
                ExpectedConditions.elementToBeClickable(removeGroupButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", removeBtn);

        removeBtn.click();

        pause(800);
    }



    public void clickAddGroup() {

        WebElement groupBtn = wait.until(
                ExpectedConditions.elementToBeClickable(addGroupButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", groupBtn);

        groupBtn.click();

        pause(800);
    }


    public void clickRemoveRule() {

        WebElement deleteBtn = wait.until(
                ExpectedConditions.elementToBeClickable(removeRuleButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", deleteBtn);

        deleteBtn.click();

        pause(800);
    }


    public void clickAddRuleInsideModal() {

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addRuleInsideModalButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", button);

        button.click();

        pause(800);
    }


    // Step 1: Select Form by typing and pressing ENTER
    public void selectForm(String formName) {

        // Ensure modal is visible
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(activeRulesModal));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", modal);

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(formInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);
        input.click();
        pause(300);

        // Type and select via ENTER
        input.clear();
        input.sendKeys(formName);
        pause(300);
        input.sendKeys(Keys.ENTER);

        pause(800);
    }

    // Step 2: Select Access by typing and pressing ENTER
    public void selectAccess(String accessType) {

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(accessInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);
        input.click();
        pause(300);

        input.clear();
        input.sendKeys(accessType);
        pause(300);
        input.sendKeys(Keys.ENTER);

        pause(800);
    }

    // Step 3: Select Query Builder by typing and pressing ENTER
    public void selectQueryBuilder(String builderOption) {

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(queryBuilderInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);
        input.click();
        pause(300);

        input.clear();
        input.sendKeys(builderOption);
        pause(300);
        input.sendKeys(Keys.ENTER);

        pause(800);
    }




    // Wait for modal (you already have something like this, keep it)
    public void waitForFormVisibilityModal() {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(rulesModal));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", modal);
        pause(800);
    }



    public void openExportControlUnderFormVisibility() {

        // 1) Make sure Form Visibility section is expanded
        WebElement toggleBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(formVisibilityToggleButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", toggleBtn);

        String expanded = toggleBtn.getAttribute("aria-expanded");
        // Only click if it's NOT already expanded
        if (expanded == null || expanded.equalsIgnoreCase("false")) {
            toggleBtn.click();
            pause(500);
        }

        // 2) Click Export Control link
        WebElement exportControl = wait.until(
                ExpectedConditions.elementToBeClickable(exportControlLink));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", exportControl);

        exportControl.click();
        pause(1000);
    }



    public void clickAddRuleButton() {

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addRuleButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", button);
        button.click();

        pause(1000);
    }





}