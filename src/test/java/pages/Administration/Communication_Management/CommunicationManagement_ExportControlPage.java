package pages.Administration.Communication_Management;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import java.time.Duration;

public class CommunicationManagement_ExportControlPage extends BasePage {
    private WebDriverWait wait;
    public CommunicationManagement_ExportControlPage(WebDriver driver) {

        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Locator

    // Notice Group control box inside the modal
    private By noticeGroupControl = By.xpath(
            "//div[contains(@class,'form-cell') and contains(@class,'-group')]" +
                    "//div[contains(@class,'select-control')]"
    );

    // Notice Group input inside that control
    private By noticeGroupInput = By.xpath(
            "//div[contains(@class,'form-cell') and contains(@class,'-group')]" +
                    "//input[@id='group']"
    );


    // "Add New Template" modal wrapper – you already have this
    private By addNewTemplateModal = By.xpath(
            "//div[contains(@class,'modal-content-wrapper')]" +
                    "[.//strong[normalize-space()='Add New Template']]"
    );



    private By communicationManagementToggle = By.xpath(
            "//button[@aria-label='Expand Communication Management' or @aria-label='Collapse Communication Management']"
    );


    // Export Control under Communication Management (level-2 item)
    private By communicationExportControlLink = By.xpath(
            "//div[contains(@class,'-level-2') and .//span[normalize-space()='Export Control']]" +
                    "//a[contains(@href,'/administration/communication-management/export-control')]"
    );

    private By addNewTemplateButton = By.xpath("//button[@class='button -primary' and normalize-space()='Add New Template']");


    // Add New Template modal – fields & buttons

    // Step 1 – Template Name
    private By templateNameInput = By.id("name");



    // Reminder Frequency input inside "Add New Template" modal
    private By reminderFrequencyInput = By.xpath(
            "//div[contains(@class,'modal-content-wrapper')]" +
                    "[.//strong[normalize-space()='Add New Template']]" +
                    "//input[@id='reminderSettings.intervalDays']"
    );

    // Step 5 – Create Template button
    private By createTemplateButton = By.xpath(
            "//button[@type='submit' and contains(@class,'-primary') and normalize-space()='Create Template']"
    );

    // Step 6 – Cancel button (bottom-right of modal)
    private By cancelTemplateButton = By.xpath(
            "//div[contains(@class,'_flexJustifyEnd')]/button[normalize-space()='Cancel']"
    );



    // Layout input inside that control
    private By layoutInput = By.xpath(
            "//div[contains(@class,'form-cell') and contains(@class,'-layout-id')]" +
                    "//input[@id='layoutId']"
    );

    // Layout control box inside the Layout form cell
    private By layoutControl = By.xpath(
            "//div[contains(@class,'form-cell') and contains(@class,'-layout-id')]" +
                    "//div[contains(@class,'select-control')]"
    );



    private By notificationTypeInput = By.id("id");


    private By cancelButton = By.xpath(
            "//div[contains(@class,'_bottom-action-panel')]//button[normalize-space()='Cancel']");

    private By saveButton = By.xpath(
            "//div[contains(@class,'_bottom-action-panel')]//button[normalize-space()='Save']");

    private By addNotificationTemplateButton = By.xpath(
            "//button[normalize-space()='Add Notification Template']"
    );

    // Notifications button (tab)
    private By notificationsButton = By.xpath(
            "//button[contains(@class,'button') and normalize-space()='Notifications']"
    );


    // Left navigation – Notifications menu link
    private By notificationsMenuLink = By.xpath(
            "//a[contains(@class,'label') and @href='/export-control/notifications']//span[normalize-space()='Notifications']"
    );

    // + button (first row expand icon)
    private By firstPlusButton = By.xpath("(//button[contains(@class,'plus-button')])[1]");


    private By collapseButton = By.xpath("(//button[@aria-label='Collapse details'])[1]");







    //Action

    public void clickCollapseButton() {

        WebElement minusBtn = wait.until(
                ExpectedConditions.elementToBeClickable(collapseButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", minusBtn
        );

        minusBtn.click();

        pause(800);
    }


    public void clickFirstPlusButton() {
        WebElement plusBtn = wait.until(
                ExpectedConditions.elementToBeClickable(firstPlusButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", plusBtn);

        plusBtn.click();

        pause(1000);
    }


    public void clickNotificationsMenu() {
        WebElement notifMenu = wait.until(
                ExpectedConditions.elementToBeClickable(notificationsMenuLink)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", notifMenu
        );

        notifMenu.click();

        pause(1000); // small wait for Notifications page to load
    }


    public void clickNotificationsButton() {
        WebElement notifBtn = wait.until(
                ExpectedConditions.elementToBeClickable(notificationsButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", notifBtn
        );

        notifBtn.click();

        // Optional small pause so tab content loads properly
        pause(1000);
    }


    // In CommunicationManagementPage (or NotificationTemplatePage)


    public void openNotificationTemplateForm() {
        WebElement addBtn = wait.until(
                ExpectedConditions.elementToBeClickable(addNotificationTemplateButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", addBtn);

        addBtn.click();

        // Wait for Notification Type input to appear
        wait.until(ExpectedConditions.elementToBeClickable(By.id("id")));
        pause(1000);
    }



    // Step 1 – Select Notification Type: "Export Control Approved"
    public void selectNotificationType(String notificationTypeText) {

        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(notificationTypeInput));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(notificationTypeText);

        // Option from react-select dropdown
        By optionLocator = By.xpath(
                "//div[contains(@class,'menu')]//div[normalize-space()='" + notificationTypeText + "']");

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(optionLocator));
        option.click();

        pause(1000);
    }

    // Step 2 – Click Cancel button
    public void clickCancelButton() {
        WebElement cancel = wait.until(
                ExpectedConditions.elementToBeClickable(cancelButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", cancel);

        cancel.click();
        pause(1000);
    }

    // Step 3 – Click Save button
    public void clickSaveButton() {
        WebElement save = wait.until(
                ExpectedConditions.elementToBeClickable(saveButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", save);

        save.click();
        pause(1000);
    }


    public void selectLayout(String layoutName) {

        WebElement control = wait.until(
                ExpectedConditions.visibilityOfElementLocated(layoutControl));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", control);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);

        WebElement input = control.findElement(By.xpath(".//input[@id='layoutId']"));
        input.clear();
        input.sendKeys(layoutName);   // "Green"

        pause(600);

        input.sendKeys(Keys.ENTER);
        pause(800);
    }

//    // Step 3: Select Layout (e.g., "Green")
//    public void selectLayout(String layoutName) {
//
//        // 1) Get the Layout control (visible), then JS-click it to open dropdown
//        WebElement control = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(layoutControl));
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].scrollIntoView({block:'center'});", control);
//
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);
//
//        // 2) Find the input inside this control and type layout name
//        WebElement input = control.findElement(By.xpath(".//input[@id='layoutId']"));
//        input.clear();
//        input.sendKeys(layoutName);   // e.g., "Green"
//
//        // Small wait to let React-Select match the option
//        pause(600);
//
//        // 3) Press ENTER to select the highlighted option ("Green")
//        input.sendKeys(Keys.ENTER);
//
//        // Optional: tiny pause for the value to appear in the control
//        pause(800);
//    }




    // Step 2: Select Notice Group (e.g. "ExportControl Workflow")
// This method stays fully inside the modal and does NOT click outside
    public void selectNoticeGroup(String searchText) {

        // 1) Click the Notice Group control box to open the dropdown
        WebElement control = wait.until(
                ExpectedConditions.visibilityOfElementLocated(noticeGroupControl));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", control);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);

        // 2) Type into the Notice Group input
        WebElement input = control.findElement(By.xpath(".//input[@id='group']"));
        input.clear();
        input.sendKeys(searchText);   // e.g., "ExportControl Workflow"

        // Small wait to let React-Select filter options
        pause(600);

        // 3) If "No results found" appears, close dropdown and return
        By noResults = By.xpath(
                "//div[contains(@class,'menu') or contains(@class,'-menu')]//*[contains(text(),'No results found')]"
        );
        if (!driver.findElements(noResults).isEmpty()) {
            System.out.println("Notice Group: No results found for '" + searchText + "'");
            input.sendKeys(Keys.ESCAPE);   // Close only the dropdown, NOT the modal
            pause(500);
            return;
        }

        // 4) Press ENTER to select the highlighted option
        input.sendKeys(Keys.ENTER);

        // Optional: tiny pause so selected value appears
        pause(600);
    }


    // Step 6: Click Cancel (close modal)
    public void clickCancelTemplateButton() {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(cancelTemplateButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn
        );

        btn.click();

        pause(800);
    }


    // Step 5: Click Create Template
    public void clickCreateTemplateButton() {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(createTemplateButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn
        );

        btn.click();

        pause(1000);
    }

    // Step 4: Set Reminder Frequency (days) – inside the modal only
    public void setReminderFrequency(String days) {

        // Ensure modal is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(addNewTemplateModal));

        // Find the Reminder Frequency input inside the modal
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(reminderFrequencyInput)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input
        );

        input.click();
        input.clear();
        input.sendKeys(days);   // e.g., "1"

        pause(500);
    }




    // Step 1: Set Template Name
    public void setTemplateName(String templateName) {

        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(templateNameInput)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input
        );

        input.clear();
        input.sendKeys(templateName);

        pause(500);
    }


    public void clickAddNewTemplate() {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(addNewTemplateButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn
        );

        btn.click();

        pause(800); // your standard pause
    }

    public void clickCommunicationExportControl() {

        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(communicationExportControlLink)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", link
        );

        link.click();
        pause(800);
    }



    public void expandCommunicationManagement() {

        WebElement toggle = wait.until(
                ExpectedConditions.elementToBeClickable(communicationManagementToggle)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", toggle
        );

        // Only click if it is closed
        String expanded = toggle.getAttribute("aria-expanded");

        if (expanded != null && expanded.equals("false")) {
            toggle.click();
            pause(800);
        }
    }

}
