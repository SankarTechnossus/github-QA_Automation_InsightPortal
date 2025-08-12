package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserUtility;
import utils.WaitUtility;
import org.openqa.selenium.TimeoutException;


import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;


public class Exportcontrol_WorkflowManagement_Rules_Page extends BasePage {


    public  Exportcontrol_WorkflowManagement_Rules_Page (WebDriver driver) {
        super(driver);
    }


// Locators

    private By workflowManagementLinkrules = By.xpath("//a[.//span[text()='Workflow Management'] and contains(@href, '/workflow-management')]");
//    private By rulesLink = By.xpath("//a[normalize-space(text())='Rules']");
//    private By rulesLink = By.xpath("//nav//a[normalize-space(.)='Rules' and contains(@href,'/workflow-management') and contains(@href,'/rules')]");

    private By addRuleButton = By.xpath("//button[normalize-space(text())='Add rule']");
    private By nameInputField = By.xpath("//input[@id='name' and @type='text']");
    private By addRuleButtonrule = By.xpath("//button[normalize-space()='Rule' or normalize-space(text())='Add Rule']");
    private By removeRuleButton = By.xpath("//button[@aria-label='Remove rule']");
    private By addGroupButton = By.xpath("//button[normalize-space()='Group' or normalize-space(text())='Add Group']");
    private By removeGroupButton = By.xpath("//button[@aria-label='Remove group']");
    private By migrationButton = By.xpath("//button[normalize-space()='Migration']");
    private By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    private By saveButton = By.xpath("//button[normalize-space()='Save']");
    private By nameInputFieldappend = By.xpath("//input[@id='name' and @type='text']");
    private static final String ARROW_BY_LABEL =
        "(" +
                " (//*[self::label or self::div or self::span][normalize-space()='%s']" +
                "   /ancestor::div[contains(@class,'form') or contains(@class,'row') or contains(@class,'column')][1]" +
                "   //div[contains(@class,'select-dropdown-indicator') or contains(@class,'_indicatorsContainer')]" +
                " )" +
                " | " +
                " (//*[self::label or self::div or self::span][normalize-space()='%s']" +
                "   /following::div[contains(@class,'select-dropdown-indicator') or contains(@class,'_indicatorsContainer')][1]" +
                " )" +
                ")";

    private static final String MENU_XPATH =
            "//div[contains(@class,'select__menu') or contains(@class,'select-menu-outer')]";

    private static final String OPTION_BY_TEXT =
            "(//div[(contains(@class,'select__option') or contains(@class,'select-option') or @role='option') " +
                    " and normalize-space()='%s'])[1]";

    // Page class — locator (Workflow Management → Rules, scopeId=3 only)
    private By rulesLink = By.xpath(
            "//nav//a[normalize-space(.)='Rules' " +
                    "and contains(@href,'/administration/workflow-management/')" +
                    "and contains(@href,'/scopeId/3/rules')]"
    );




//Actions



    public void selectQueryBuilderOperator(String labelText, String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        By arrow = By.xpath(String.format(ARROW_BY_LABEL, labelText, labelText));
        List<WebElement> arrows = driver.findElements(arrow);
        if (arrows.isEmpty()) {
            throw new NoSuchElementException("Could not find dropdown indicator near label: " + labelText);
        }

        WebElement arrowEl = arrows.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", arrowEl);

        // Open menu (retry if needed)
        arrowEl.click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MENU_XPATH)));
        } catch (org.openqa.selenium.TimeoutException e) {
            arrowEl.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MENU_XPATH)));
        }

        // Pick the option (e.g., AND)
        By option = By.xpath(String.format(OPTION_BY_TEXT, optionText));
        WebElement opt = wait.until(ExpectedConditions.visibilityOfElementLocated(option));
        wait.until(ExpectedConditions.elementToBeClickable(opt));
        try {
            new Actions(driver).moveToElement(opt).pause(Duration.ofMillis(80)).click().perform();
        } catch (Exception ignored) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opt);
        }

        // Let the menu close
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(MENU_XPATH)));
        pause(300);
    }



    public void appendToNameField(String textToAppend) {
        WebElement input = driver.findElement(nameInputFieldappend);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputFieldappend));

        // Append without clearing
        input.sendKeys(textToAppend);

        pause(1000);
    }



    public void clickEditButtonForRule(String ruleName) {
        String editButtonXpath = "//td[@data-column='name' and normalize-space(text())='" + ruleName + "']" +
                "/following-sibling::td//button[normalize-space()='Edit']";

        By editButton = By.xpath(editButtonXpath);

        WebElement button = driver.findElement(editButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(editButton));

        pause(1000);
        button.click();
    }



    public void clickSaveButton() {
        WebElement button = driver.findElement(saveButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));

        pause(1000);
        button.click();
    }



    public void clickCancelButton() {
        WebElement button = driver.findElement(cancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton));

        pause(1000);
        button.click();
    }


    public void clickMigrationButton() {
        WebElement button = driver.findElement(migrationButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(migrationButton));

        pause(1000);
        button.click();
    }



    public void clickRemoveGroupButton() {
        WebElement button = driver.findElement(removeGroupButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(removeGroupButton));

        pause(1000);
        button.click();
    }



    public void clickAddGroupButton() {
        WebElement button = driver.findElement(addGroupButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addGroupButton));

        pause(1000);
        button.click();
    }



    public void clickRemoveRuleButton() {
        WebElement button = driver.findElement(removeRuleButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(removeRuleButton));

        pause(1000);
        button.click();
    }


    public void clickAddRuleButtonrule() {
        WebElement button = driver.findElement(addRuleButtonrule);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addRuleButtonrule));

        pause(1000);
        button.click();
    }


//    public void selectOptionFromDropdownrules(String dropDownName,String optionToSelect){
//        String dropdownXpath = "//label[normalize-space(text())='"+dropDownName+"']//following-sibling::div//div[contains(@class,'_indicatorsContainer')]";
//        String menuListXpath = "//div[contains(@class,'select-menu-outer')]";
//        String optionXpath = "//div[contains(@class,'select-menu-outer')]//div[normalize-space(text())='"+optionToSelect+"']";
//        BrowserUtility.click(driver,By.xpath(dropdownXpath),dropDownName);
//        WaitUtility.waitForVisibility(driver,By.xpath(menuListXpath),20,"option list");
//        BrowserUtility.click(driver,By.xpath(optionXpath),optionToSelect);
//
//    }



    public String enterUniqueNameWithTestPrefix() {
        String uniqueName = "Test_" + System.currentTimeMillis(); // e.g., Test_1723465923456

        WebElement input = driver.findElement(nameInputField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputField));

        input.clear();
        input.sendKeys(uniqueName);

        pause(1000);

        return uniqueName; // return so test can log or use later
    }



    public void clickAddRuleButton() {
        WebElement button = driver.findElement(addRuleButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addRuleButton));

        pause(1000);
        button.click();
    }



    public void clickRulesLink() {
    WebElement link = driver.findElement(rulesLink);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
    pause(1000);
    link.click();
    }



    public void clickWorkflowManagementLinkrules() {
        WebElement link = driver.findElement(workflowManagementLinkrules);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(workflowManagementLinkrules));

        link.click();
        pause(1000);
    }











}
