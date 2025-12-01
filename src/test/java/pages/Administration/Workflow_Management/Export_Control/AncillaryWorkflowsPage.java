package pages.Administration;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserUtility;
import utils.WaitUtility;

import java.time.Duration;

public class AncillaryWorkflowsPage extends BasePage {

    public AncillaryWorkflowsPage(WebDriver driver) {
        super(driver);
    }


// Locators


    // Page class — strict locator


    private By workflowManagementLinkanc = By.xpath("//a[.//span[text()='Workflow Management'] and contains(@href, '/workflow-management')]");
//    private By ancillaryWorkflowsScope3 = By.xpath(
//            "//a[normalize-space(.)='Ancillary Workflows' and " +
//                    "@href='/administration/workflow-management/scopeId/3/workflowType/2/workflows']");

    private By addNewButton = By.xpath("//button[@type='button' and normalize-space()='Add New']");
    private By cancelButton = By.xpath("//button[normalize-space(text())='Cancel']");
    private By saveButton = By.xpath("//button[contains(@class,'button') and contains(@class,'-primary') and normalize-space(text())='Save']");
    private By nameInputFieldanc = By.xpath("//input[@id='name' and @type='text']");
    //    private String editButtonForWorkflowXpath = "//tr[.//a[normalize-space(text())='%s']]//button[normalize-space(text())='Edit']";
    private By updateButtonanc = By.xpath("//button[normalize-space(text())='Update']");
    //    private By workflowManagementLinkancillary = By.xpath("//a[.//span[text()='Workflow Management'] and contains(@href, '/workflow-management')]");
//    private By exportControlWorkflowsLinkanc = By.xpath("//a[contains(@href, '/administration/workflow-management/scopeId/3/workflowType/2/workflows') and text()='Workflows']");
//    private By exportControlWorkflowsLink = By.xpath("//a[contains(@href, 'workflows-export-control') and text()='Workflows']");
// Locator (stable: text + href; tolerant of inner <span>)
    private By workflowManagementLink = By.xpath("//nav//a[(normalize-space(.)='Workflow Management' or .//span[normalize-space(.)='Workflow Management'])" + " and contains(@href,'/administration/workflow-management')]");
    // Locator (stable: text + href parts; scoped to nav)
    private By ancillaryWorkflowsScope3 = By.xpath("//nav//a[normalize-space(.)='Ancillary Workflows' " + "and contains(@href,'/administration/workflow-management') " + "and contains(@href,'/scopeId/3/') " + "and contains(@href,'/workflowType/2/') " + "and contains(@href,'/workflows')]");
    //    private String editBtnByDataValueTpl = "//td[@data-column='name' and @data-value='%s']" + "/ancestor::tr[1]//button[@type='button' and contains(normalize-space(.),'Edit')]";
    private String editBtnByDataValueTpl = "//td[@data-column='name' and @data-value='%s']/ancestor::tr[1]" + "//button[@type='button' and normalize-space()='Edit']";

    private String editBtnByLinkTextTpl = "//a[normalize-space(.)='%s']/ancestor::tr[1]" + "//button[@type='button' and normalize-space()='Edit']";
    //    private String editButtonForancXpath = "//tr[.//a[normalize-space(text())='%s']]//button[normalize-space(text())='Edit']";
    private String editButtonForancXpath = "//td[@data-column='name' and @data-value='%s']" + "/ancestor::tr[1]//td[@data-column='_actions']" + "//button[@type='button' and normalize-space(.)='Edit']";
    private By lastRow = By.xpath("(//table//tr[.//td[@data-column='name']])[last()]");
    private By editBtnInRow = By.xpath(".//td[@data-column='_actions']//button[@type='button' and normalize-space()='Edit']");
    private By lastEditDirect = By.xpath("(//td[@data-column='_actions']//button[@type='button' and normalize-space()='Edit'])[last()]");
    // Action





    public void clickLastEdit() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        // Prefer row-scoped
        try {
            WebElement row = wait.until(ExpectedConditions.presenceOfElementLocated(lastRow));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", row);
            WebElement edit = row.findElement(editBtnInRow);
            wait.until(ExpectedConditions.elementToBeClickable(edit));
            try { edit.click(); } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", edit);
            }
        } catch (TimeoutException te) {
            // Fallback to direct last Edit
            WebElement edit = wait.until(ExpectedConditions.elementToBeClickable(lastEditDirect));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", edit);
            try { edit.click(); } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", edit);
            }
        }

        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.id("name")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@type='button' and normalize-space()='Update']"))
        ));
        pause(500);
    }


//    public void clickEditButtonForanc(String workflowName) {
//        By editButtonLocator = By.xpath(String.format(editButtonForancXpath, workflowName));
//        WebElement editButton = driver.findElement(editButtonLocator);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", editButton);
//        editButton.click();
//        pause(1000);
//    }


    // Row-scoped Edit — stable by data-value, fallback to link text
//    private String editBtnByDataValueTpl =
//            "//td[@data-column='name' and @data-value='%s']/ancestor::tr[1]" +
//                    "//button[@type='button' and normalize-space()='Edit']";
//
//    private String editBtnByLinkTextTpl =
//            "//a[normalize-space(.)='%s']/ancestor::tr[1]" +
//                    "//button[@type='button' and normalize-space()='Edit']";

//    public void clickEditForWorkflowanc(String workflowName) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));
//
//        By byDataValue = By.xpath(String.format(editBtnByDataValueTpl, workflowName));
//        By byLinkText  = By.xpath(String.format(editBtnByLinkTextTpl,  workflowName));
//
//        WebElement btn;
//        try {
//            btn = wait.until(ExpectedConditions.elementToBeClickable(byDataValue));
//        } catch (TimeoutException e) {
//            btn = wait.until(ExpectedConditions.elementToBeClickable(byLinkText));
//        }
//
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
//        try { btn.click(); } catch (ElementClickInterceptedException ex) {
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
//        }
//
//        wait.until(ExpectedConditions.or(
//                ExpectedConditions.presenceOfElementLocated(By.id("name")),
//                ExpectedConditions.presenceOfElementLocated(
//                        By.xpath("//button[@type='button' and normalize-space()='Update']"))
//        ));
//        pause(500);
//    }



    public void clickAncillaryWorkflowsScope3() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(ancillaryWorkflowsScope3));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();
        wait.until(ExpectedConditions.urlContains("/administration/workflow-management/scopeId/3/workflowType/2/workflows"));
        pause(600);
    }



    // Action (scroll → wait → click → confirm; no logging here)
    public void clickWorkflowManagement() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(workflowManagementLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        link.click();
        wait.until(ExpectedConditions.urlContains("/administration/workflow-management"));
        pause(600);
    }






    public void clickUpdateButtonanc() {
        WebElement button = driver.findElement(updateButtonanc);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        button.click();
        pause(1000);
    }




    public void appendSanToNameanc() {
        WebElement input = driver.findElement(nameInputFieldanc);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputFieldanc));

        input.sendKeys("San"); // Append instead of replacing
        pause(1000);
    }


//    public void clickEditButtonForWorkflowanc(String workflowName) {
//        By editButtonLocator = By.xpath(String.format(editBtnByDataValueTpl, workflowName));
//        WebElement editButton = driver.findElement(editButtonLocator);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", editButton);
//        editButton.click();
//        pause(1000);
//    }


    // Row-scoped Edit — stable by data-value, fallback to link text


    public void clickEditForWorkflow(String workflowName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

        By byDataValue = By.xpath(String.format(editBtnByDataValueTpl, workflowName));
        By byLinkText  = By.xpath(String.format(editBtnByLinkTextTpl,  workflowName));

        WebElement btn;
        try {
            btn = wait.until(ExpectedConditions.elementToBeClickable(byDataValue));
        } catch (TimeoutException e) {
            btn = wait.until(ExpectedConditions.elementToBeClickable(byLinkText));
        }

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        try { btn.click(); } catch (ElementClickInterceptedException ex) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }

        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.id("name")),
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[@type='button' and normalize-space()='Update']"))
        ));
        pause(500);
    }



    public void enterNameanc(String name) {
        WebElement input = driver.findElement(nameInputFieldanc);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInputFieldanc));

        input.clear();
        input.sendKeys(name);
        pause(1000);
    }

    public void clickSaveButton() {
        WebElement button = driver.findElement(saveButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        button.click();
        pause(1000);
    }


    public void clickCancelButton() {
        WebElement button = driver.findElement(cancelButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        button.click();
        pause(1000);
    }


    public void selectOptionFromDropdownancillary(String dropDownName,String optionToSelect){
        String dropdownXpath = "//label[normalize-space(text())='"+dropDownName+"']//following-sibling::div//div[contains(@class,'_indicatorsContainer')]";
        String menuListXpath = "//div[contains(@class,'select-menu-outer')]";
        String optionXpath = "//div[contains(@class,'select-menu-outer')]//div[normalize-space(text())='"+optionToSelect+"']";
        BrowserUtility.click(driver,By.xpath(dropdownXpath),dropDownName);
        WaitUtility.waitForVisibility(driver,By.xpath(menuListXpath),20,"option list");
        BrowserUtility.click(driver,By.xpath(optionXpath),optionToSelect);

    }


    public void clickAddNew() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addNewButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();

        // Confirm something opened/changed
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@role='dialog' or @aria-modal='true' or contains(@class,'modal')]")),
                ExpectedConditions.urlMatches(".*(/new|/create|/add).*")));
        pause(800);
    }


}