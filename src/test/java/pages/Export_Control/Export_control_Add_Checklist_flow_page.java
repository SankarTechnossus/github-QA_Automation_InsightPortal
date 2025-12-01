package pages.Export_Control;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Export_control_Add_Checklist_flow_page extends BasePage {


    private WebDriverWait wait;

    public Export_control_Add_Checklist_flow_page (WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Locators
    // Administration → Workflow Management (left sidebar)
    By workflowManagementMenu = By.xpath("//div[@id='left-sidebar']//a[@href='/administration/workflow-management']" + "/span[normalize-space()='Workflow Management']");

    // Export Control → Workflows (inside Workflow Management module)
    By exportControlWorkflowsLink = By.xpath(
            "//button[normalize-space()='Export Control']" +
                    "/ancestor::div[contains(@class,'menu-item-holder')][1]" +
                    "//a[contains(@href,'/administration/workflow-management') and contains(@href,'workflows-export-control') " +
                    " and normalize-space(text())='Workflows']"
    );

    By personnelWorkflowLink = By.xpath(
            "//td[@data-column='name' and @data-value='Personnel']//a[normalize-space()='Personnel']"
    );

    // "Add New" button on Export Control Workflows page
    By addNewWorkflowButton = By.xpath(
            "//div[contains(@class,'top-bar')][.//header[normalize-space()='Workflows']]" +
                    "//button[contains(@class,'button') and contains(@class,'-primary')]"
    );

    By addNewWorkflowVersionButton = By.xpath(
            "//div[contains(@class,'top-bar')][.//header[normalize-space()='Versions']]" +
                    "//button[contains(@class,'-primary')][normalize-space()='Add new']"
    );

    // Description textarea for the newly added (editable) version – Version 90
    By editableVersionDescription = By.xpath(
            "//div[contains(@class,'_versionsList')]" +
                    "//textarea[contains(@class,'_descriptionField') and not(@disabled)]"
    );

    // Save button for the editable version (Version 90)
    By saveVersionButton = By.xpath("//div[contains(@class,'_versionItemHeader')]" + "//button[contains(@class,'button') and contains(@class,'-primary') and normalize-space()='Save']");
    By latestVersionLink = By.xpath("(//div[contains(@class,'_versionItemHeader')]//a)[1]");
    By draftNodeBox = By.xpath("//p[normalize-space()='Draft (draft1)']/ancestor::div[contains(@class,'_workflowNode')]");
    By actionsTab = By.xpath("//button[normalize-space()='Actions']");
    By submitActionButton = By.xpath("//div[@class='_entityListContainer_1q559_1']//button[normalize-space()='Submit']");
    By checklistsToggle = By.xpath("//button[normalize-space()='Checklists']");
    By updateButton = By.xpath("//button[normalize-space()='Update']");
    By saveButton = By.xpath("//button[normalize-space()='Save']");

    //Actions
    public void clickSaveButton() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(saveButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(1000);
    }

    public void clickUpdateButton() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(updateButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(1000);
    }

    public void clickChecklistsToggle() {
        WebElement toggle = wait.until(
                ExpectedConditions.elementToBeClickable(checklistsToggle)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", toggle);

        toggle.click();

        pause(1000);
    }

    public void clickSubmitAction() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(submitActionButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", btn);

        btn.click();

        pause(1000);
    }

    public void clickActionsTab() {
        WebElement tab = wait.until(
                ExpectedConditions.elementToBeClickable(actionsTab)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", tab);

        tab.click();

        pause(1000);
    }

    public void clickDraftNode() {
        WebElement node = wait.until(ExpectedConditions.elementToBeClickable(draftNodeBox));

        // Scroll to the Draft box
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", node);

        node.click();

        pause(1000);
    }

    public void clickLatestVersionLink() {

        WebElement versionLink = wait.until(
                ExpectedConditions.elementToBeClickable(latestVersionLink));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", versionLink);

        try {
            versionLink.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", versionLink);
        }

        pause(1000);
    }

    public void clickSaveVersion() {
        WebElement saveBtn = wait.until(
                ExpectedConditions.elementToBeClickable(saveVersionButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", saveBtn);

        try {
            saveBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        }

        pause(1000);
    }

    public void enterDescriptionForLatestVersion(String descriptionText) {

        WebElement descriptionArea = wait.until(
                ExpectedConditions.visibilityOfElementLocated(editableVersionDescription));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", descriptionArea);

        descriptionArea.clear();
        descriptionArea.sendKeys(descriptionText);

        pause(1000);
    }

    public void clickAddNewWorkflowVersion() {

        WebElement addNewBtn = wait.until(
                ExpectedConditions.elementToBeClickable(addNewWorkflowVersionButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", addNewBtn);

        try {
            addNewBtn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNewBtn);
        }

        pause(1000);
    }

    public void clickPersonnelWorkflow() {

        WebElement personnel = wait.until(
                ExpectedConditions.elementToBeClickable(personnelWorkflowLink));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", personnel);

        try {
            personnel.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", personnel);
        }

        pause(1000);
    }

    public void openExportControlWorkflows() {

        // 1) Click Administration → Workflow Management from sidebar
        WebElement workflowMgmt = wait.until(
                ExpectedConditions.elementToBeClickable(workflowManagementMenu));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", workflowMgmt);
        workflowMgmt.click();
        pause(800);

        WebElement workflows = wait.until(
                ExpectedConditions.visibilityOfElementLocated(exportControlWorkflowsLink));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", workflows);

        try {
            workflows.click();
        } catch (Exception e) {
            // Fallback to JS click if normal click fails
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", workflows);
        }

        pause(1000);
    }
}