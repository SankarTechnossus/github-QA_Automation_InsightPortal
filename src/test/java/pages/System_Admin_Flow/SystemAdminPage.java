package pages.System_Admin_Flow;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class SystemAdminPage extends BasePage {


    private WebDriverWait wait;

    public SystemAdminPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    //locator


    // First Record Number link in the Action Required grid
    private By firstRecordNumberLink = By.xpath(
            "(//table[contains(@class,'item-grid')]//tbody" +
                    "//td[@data-column='_exportControlNumber']//a)[1]");

    private By approveButton = By.xpath("//button[@type='button' and @aria-label='Approve']");
    private By commentsButton = By.xpath("//span[normalize-space()='Comments']/parent::div");
    // Comments modal – Froala editor body
    private By commentsEditor = By.xpath("//div[contains(@class,'fr-element') and contains(@class,'fr-view') and @contenteditable='true']");

    // Comments modal – "Comment" button
    private By commentModalButton = By.xpath("//button[contains(@class,'comment-btn') and normalize-space()='Comment']");
    // Left nav – "Response To Review" menu item (under current record)
    private By responseToReviewMenu = By.xpath("//div[@id='left-sidebar']//a[@class='label'][span[normalize-space()='Response To Review']]");

    private By checklistForm = By.xpath("//button[contains(@class,'content-toggler-button')]//span[span[normalize-space()='Checklist form']]");
    // User dropdown (Mohan C)
    private By userDropdown = By.xpath("//div[contains(@class,'current-user-name')]");

    // Logout link
    private By logoutLink = By.xpath("//a[span[normalize-space()='Logout']]");

    private By recordNumberValue = By.xpath("//dt[normalize-space()='Record #:']/following-sibling::dd");




    private By valueInput = By.xpath("//input[@aria-label='Value']");
    // Record Number link in results grid – dynamic by record number text
    private By recordNumberLink(String recordNumber) {
        return By.xpath(
                "//table[contains(@class,'item-grid')]//tbody//tr" +
                        "//td[@data-column='_exportControlNumber']" +
                        "//a[span[normalize-space()='" + recordNumber + "']]");
    }

    // Checklist form toggle button
    private By checklistFormToggleButton = By.xpath(
            "//div[contains(@class,'toggleable-title')]//button" +
                    "[contains(@class,'content-toggler-button')]" +
                    "[.//span[normalize-space()='Checklist form']]");

    private By downloadButton = By.xpath("//button[contains(@class,'button') and .//span[normalize-space()='Download']]");
    private By optionOneRadio = By.xpath("//input[@name='RadioButtonList1' and @value='Option1']");






    //method

    public void selectOptionOne() {

        WebElement radio = wait.until(ExpectedConditions.elementToBeClickable(optionOneRadio));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                radio
        );

        radio.click();

        pause(500);  // follow your standard pause
    }


    public void clickDownloadButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(downloadButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(1000);
    }


    public void clickChecklistFormToggle() {
        WebElement toggleBtn = wait.until(
                ExpectedConditions.elementToBeClickable(checklistFormToggleButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", toggleBtn);

        toggleBtn.click();
        pause(1000);
    }


    public void clickRecordNumber(String recordNumber) {
        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(recordNumberLink(recordNumber))
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        link.click();
        pause(1000);
    }


    public void enterValueField(String value) {
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(valueInput)
        );
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", input);
        input.click();
        input.clear();
        input.sendKeys(value);
        pause(1000);
    }

    public String getRecordNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int i = 0; i < 3; i++) {   // small retry for stale element
            try {
                WebElement record = wait.until(
                        ExpectedConditions.refreshed(
                                ExpectedConditions.visibilityOfElementLocated(recordNumberValue)
                        )
                );

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block:'center'});", record);

                pause(500);

                String text = record.getText().trim();
                if (!text.isEmpty()) {
                    return text;
                }
            } catch (StaleElementReferenceException e) {
                // retry with a fresh element
                if (i == 2) {
                    throw e;   // after 3 tries, let it fail
                }
            }
        }

        throw new RuntimeException("Record # not found or empty");
    }



    public void clickLogout() {
        // Step 1: Click dropdown
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(userDropdown)
        );
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);
        dropdown.click();
        pause(500);

        // Step 2: Click Logout
        WebElement logout = wait.until(
                ExpectedConditions.elementToBeClickable(logoutLink)
        );
        logout.click();
        pause(1000);
    }

    public void clickChecklistForm() {
        WebElement ele = wait.until(
                ExpectedConditions.elementToBeClickable(checklistForm)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", ele);

        ele.click();

        pause(1000);
    }


    public void clickResponseToReviewMenu() {
        WebElement menuItem = wait.until(
                ExpectedConditions.elementToBeClickable(responseToReviewMenu));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", menuItem);

        menuItem.click();

        pause(1000);
    }


    public void enterCommentInModal(String commentText) {
        WebElement editor = wait.until(
                ExpectedConditions.visibilityOfElementLocated(commentsEditor));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", editor);

        editor.click();
        // Clear any existing text
        editor.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        editor.sendKeys(Keys.DELETE);

        // Type new comment
        editor.sendKeys(commentText);

        pause(1000);
    }

    public void clickCommentButtonOnModal() {
        WebElement commentBtn = wait.until(
                ExpectedConditions.elementToBeClickable(commentModalButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", commentBtn);

        commentBtn.click();

        pause(1000);
    }

    public void clickComments() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement comments = wait.until(
                ExpectedConditions.elementToBeClickable(commentsButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", comments);

        comments.click();

        pause(1000);
    }


    public void clickApproveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement approveBtn = wait.until(
                ExpectedConditions.elementToBeClickable(approveButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", approveBtn);

        approveBtn.click();

        pause(1000);
    }

    public void clickFirstRecordNumber() {
        WebElement firstRecord = wait.until(
                ExpectedConditions.elementToBeClickable(firstRecordNumberLink));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", firstRecord);

        firstRecord.click();

        pause(1000);
    }


















}