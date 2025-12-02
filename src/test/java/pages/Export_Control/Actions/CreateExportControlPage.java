package pages.Export_Control.Actions;

import base.BasePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;

import java.time.Duration;


public class CreateExportControlPage extends BasePage {

    private WebDriverWait wait;

    // Constructor
    public CreateExportControlPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ****************************** Sankar Locator*************************************************************

    // Left navigation – Actions toggle button
    By actionsToggleButton = By.xpath("//div[contains(@class,'export-control-nav-block')]//button[@aria-label='Expand Actions']");

    // Left navigation – 'Create Export Control' menu item
    By createExportControlLink = By.xpath("//div[contains(@class,'export-control-nav-block')]//a[contains(@class,'label')]//span[normalize-space()='Create Export Control']");

    // Step 1 – Radio: Export Control Request
    By exportControlRequestRadio = By.cssSelector("input[name='RadioButtonList1'][value='ExportControlRequest']");
    By workflowSaveButton = By.xpath("//div[contains(@class,'_workflowEngineActions')]" + "//button[@type='button' and @aria-label='Save']");
    By workflowSubmitButton = By.xpath("//div[contains(@class,'_workflowEngineActions')]" + "//button[@type='button' and @aria-label='Submit']");
    By yourNameInput = By.id("dynamic-form-field-input-74877-TextBox1");
    By genderMaleRadio = By.xpath("//div[@id='dynamic-form-field-input-74878-RadioButtonList2']" + "//input[@type='radio' and @value='Male']");

    // Workflow action buttons
    By saveButton = By.xpath("//button[@aria-label='Save' and normalize-space()='Save']");
    By submitButton = By.xpath("//button[@aria-label='Submit' and normalize-space()='Submit']");
    By nextButton = By.xpath("//button[contains(@class,'next-btn') and starts-with(normalize-space(),'Next')]");

    // Step 2 – PI Name input (typeahead)
    By piNameInput = By.id("dynamic-form-field-input-74472-PiName");
    By actionRequiredLink = By.xpath("//div[@id='left-sidebar']//a[contains(@class,'label')][span[normalize-space()='Action Required']]");

    // Step 3 – Create button in right sidebar footer
    By createButton = By.xpath("//aside//button[normalize-space()='Create']");

    // ****************************** Sankar Functions *************************************************************

    private By dynamicFormFieldByQuestion(String questionText) {
        return By.xpath(
                "//div[contains(@class,'dynamic-form-field')]" +
                        "[.//div[contains(@class,'fr-element') and normalize-space()='" + questionText + "']]");
    }

    public void clickActionRequired() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(actionRequiredLink));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        link.click();

        pause(1000);
    }

    public void selectGenderMale() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement maleRadio = wait.until(ExpectedConditions.elementToBeClickable(genderMaleRadio));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", maleRadio);

        maleRadio.click();
        pause(500);
    }

    public void clickSaveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);
        saveBtn.click();
        pause(1000);
    }

    public void clickSubmitButton() {
        // Submit is initially disabled -> wait until it becomes enabled & clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(5));
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", submitBtn);
        submitBtn.click();
        pause(1000);
    }

    // Generic option for PI dropdown – use visible text (e.g., "Chandra, Mohan")
    private By piOptionByText(String fullName) {
        // Keep it generic for react-select style options
        return By.xpath("//div[contains(@class,'menu') or contains(@class,'select')]"
                + "//div[normalize-space()='" + fullName + "']");
    }

    public void enterYourName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(yourNameInput));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(name);

        pause(500);
    }

    public void selectRadioOptionByQuestion(String questionText, String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1) Locate the whole block for this question
        By fieldBy = dynamicFormFieldByQuestion(questionText);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldBy));

        // 2) Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", field);

        // 3) Find option
        By optionSpanBy = By.xpath(".//div[@role='radiogroup']//span[normalize-space()='" + optionText + "']");
        WebElement optionSpan = field.findElement(optionSpanBy);

        // 4) Safe click (normal → JS fallback)
        try {
            optionSpan.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionSpan);
        }

        // 5) Pause
        pause(500);
    }

    public void clickWorkflowSave() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(workflowSaveButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", saveBtn);

        saveBtn.click();
        pause(500);
    }

    public void clickWorkflowSubmit() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(workflowSubmitButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", submitBtn);

        submitBtn.click();
        pause(500);
    }

    public void selectTextQuestionOption2() {
        selectRadioOptionByQuestion("Text??", "Option (2)");
    }

    public void selectQ2Option2() {
        selectRadioOptionByQuestion("q2", "Option (2)");
    }

    public void clickNextButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement next = wait.until(ExpectedConditions.elementToBeClickable(nextButton));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", next);

        try {
            next.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", next);
        }

        pause(1000);
    }

    public void enterTextInputByQuestion(String questionText, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1) Locate block for this question (enter age)
        By fieldBy = dynamicFormFieldByQuestion(questionText);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldBy));

        // 2) Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", field);

        // 3) Find input inside that block
        By inputBy = By.xpath(".//input[@type='text' and contains(@class,'text-input')]");
        WebElement input = field.findElement(inputBy);

        // 4) Type value
        input.clear();
        input.sendKeys(value);

        // 5) Small pause
        pause(500);
    }

    public void enterAge(String age) {
        enterTextInputByQuestion("enter age", age);
    }

    /**
     * Open Actions and click "Create Export Control" from left navigation.
     */
    public void clickCreateExportControl() {

        // 1) Scroll and open Actions dropdown
        WebElement actionsToggle = wait.until(
                ExpectedConditions.elementToBeClickable(actionsToggleButton));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", actionsToggle);
        actionsToggle.click();
        pause(2000);

        // 2) Wait for and click 'Create Export Control'
        WebElement createExportControl = wait.until(
                ExpectedConditions.elementToBeClickable(createExportControlLink));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", createExportControl);
        createExportControl.click();

        // 3) Small pause for navigation to complete
        pause(2000);
    }

    /**
     * Step 1: Select Export Control Request radio.
     */
    public void selectExportControlRequest() {
        WebElement radio = wait.until(
                ExpectedConditions.elementToBeClickable(exportControlRequestRadio));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", radio);
        radio.click();
        pause(800);
    }

    /**
     * Step 2: Type into PI Name and select the full name from dropdown.
     * Example:
     *   searchText = "mohan"
     *   fullNameToSelect = "Chandra mohan"
     */
    public void selectPiName(String searchText, String fullNameToSelect) {

        // Focus input
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(piNameInput));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", input);
        input.click();
        input.clear();
        input.sendKeys(searchText);

        // Small wait to let dropdown options appear
        pause(800);

        // Select the desired option from dropdown
        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(piOptionByText(fullNameToSelect)));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", option);
        option.click();

        pause(800);
    }

    // ---- LEFT NAV HELPERS ----

    // Child form link by name under the current record (e.g. "test form")
    private By leftNavChildFormLink(String formName) {
        return By.xpath(
                "//div[@id='left-sidebar']" +
                        "//div[contains(@class,'-level-2')]" +
                        "[.//a[contains(@class,'label')]//span[normalize-space()='" + formName + "']]" +
                        "//a[contains(@class,'label')]"
        );
    }

    public void clickLeftNavForm(String formName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(leftNavChildFormLink(formName)));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", link);

        // Safe click (handle intercepted click)
        try {
            link.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }

        pause(1000);
    }

    private By radioOptionByQuestion(String questionText, String optionText) {
        return By.xpath(
                "//div[contains(@class,'dynamic-form-field')]" +
                        "  [.//div[contains(@class,'fr-element') and normalize-space()='" + questionText + "']]" +
                        "//div[contains(@class,'radio-group')]" +
                        "//label[contains(@class,'option-label')][.//span[normalize-space()='" + optionText + "']]"
        );
    }

    public void selectGender(String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Find the label for the radio and scroll to it
        By menOptionLabelBy = radioOptionByQuestion("Select gender", optionText);
        WebElement menOptionLabel = wait.until(
                ExpectedConditions.elementToBeClickable(menOptionLabelBy));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", menOptionLabel);

        // Safe click – handle intercepted click
        try {
            menOptionLabel.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", menOptionLabel);
        }

        pause(500);
    }

    // Convenience wrapper if you always want Men
    public void selectGenderAsMen() {
        selectGender("Men");
    }

    // ---- Dynamic form checkbox helper anchored by question text ----
    private By checkboxOptionByQuestion(String questionText, String optionText) {
        return By.xpath(
                "//div[contains(@class,'dynamic-form-field')]" +
                        "  [.//div[contains(@class,'fr-element') and normalize-space()='" + questionText + "']]" +
                        "//div[contains(@class,'checkbox-group')]" +
                        "//label[contains(@class,'option')][.//span[normalize-space()='" + optionText + "']]" +
                        "//input[@type='checkbox']"
        );
    }

    public void selectCheckboxOption(String questionText, String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By checkboxBy = checkboxOptionByQuestion(questionText, optionText);
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(checkboxBy));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", checkbox);

        // Click only if not already selected
        if (!checkbox.isSelected()) {
            try {
                checkbox.click();
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
            }
        }

        pause(500);
    }

    // ---------- Numeric input anchored by question text ----------
    private By numericInputByQuestion(String questionText) {
        return By.xpath(
                "//div[contains(@class,'dynamic-form-field')]" +
                        "  [.//div[contains(@class,'fr-element') and normalize-space()='" + questionText + "']]" +
                        "//input[contains(@class,'default-input') and @type='text']"
        );
    }

    public void enterNumericAnswer(String questionText, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By inputBy = numericInputByQuestion(questionText);
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputBy));

        // Scroll into view
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.clear();
        input.sendKeys(value);

        pause(500);
    }

    // Convenience wrapper for this exact question
    public void selectTest1Checkbox() {
        selectCheckboxOption("Select any one?", "Test 1");
    }

    public void selectRadioOptionByQuestionone(String questionText, String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1) Find the whole block for that question
        By fieldBy = dynamicFormFieldByQuestion(questionText);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldBy));

        // 2) Scroll into view
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", field);

        // 3) Find the span with option text ("one" / "two")
        By optionSpanBy = By.xpath(".//div[contains(@class,'radio-group')]//span[normalize-space()='" + optionText + "']");
        WebElement optionSpan = field.findElement(optionSpanBy);

        // 4) Click safely (handle intercepted click)
        try {
            wait.until(ExpectedConditions.elementToBeClickable(optionSpan)).click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionSpan);
        }

        // 5) Tiny pause
        pause(500);
    }

    public void selectOneOptionone() {
        selectRadioOptionByQuestion("select?", "one");
    }

    /**
     * Step 3: Click Create button in sidebar.
     */
    public void clickCreateButton() {
        WebElement createBtn = wait.until(
                ExpectedConditions.elementToBeClickable(createButton));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", createBtn);
        createBtn.click();
        pause(1000);
    }


    // **************************** Sahil Code ************************************************************************

    // General Locators
    By buttonActions = By.xpath("//button[text()='Actions']");
    By linkCreateExportControl = By.xpath("//span[text()='Create Export Control']/..");
    By linkInitialReview = By.xpath("//div[text()='Initial Review (IR)']/..");

    // Create Export Control page locators
    By inputPersonnelExclusion = By.xpath("//input[@value='PersonnelExclusion']");
    By inputExportControlRequest = By.xpath("//input[@value='ExportControlRequest']");
    By inputSelectPI = By.xpath("//input[@id='dynamic-form-field-input-74472-PiName']");
    By piSelection = By.xpath("//div[text()='Mass General Brigham']/..");

    By buttonCancel = By.xpath("//button[text()='Cancel']");
    By buttonSave = By.xpath("//button[text()='Save']");
    By buttonCreate = By.xpath("//button[text()='Create']");

    // Export Control Details locators
    By exportControlRecordNo = By.xpath("//dt[text()='Record #']/../dd");
    By exportControlPI = By.xpath("//dt[text()='PI']/../dd");
    By exportControlType = By.xpath("//dt[text()='Type']/../dd");
    By exportControlStatus = By.xpath("//dt[text()='Status']/../dd");

    By lblExportControlSuccessfulCreation = By.xpath("//div[text()='Record has been created successfully.']");
    By lblExportControlRecordNum = By.xpath("//dt[text()='Record #']");
    By lblSubmissionChecklist = By.xpath("//span[text()='Submission Checklist']");

    //Attachment Locators
    By linkAttachments = By.xpath("//span[text()='Attachments']/..");
    By inputSearchAttachment = By.xpath("//input[@placeholder='Search by attachments...']");
    By buttonSearch = By.xpath("//button[text()='Search']");
    By linkSelectFilesFromComputer = By.xpath("//span[text()='select files from computer']");
    By fileInput = By.xpath("//input[@type='file']");
    By buttonClearSelections = By.xpath("//button[text()='Clear Selections']");

    By inputAttachmentType = By.xpath("//td[@data-column='exportControlAttachmentCategoryId']/div//input");
    By textareaAttachmentDescription = By.xpath("//td[@data-column='description']/textarea");
    By buttonDeleteAttachment = By.xpath("//button[@aria-label='Delete attachment']");
    By buttonOK = By.xpath("//button[text()='OK']");

    //People Locators
    By linkPeople = By.xpath("//span[text()='People']/..");
    By buttonAddNewPeople = By.xpath("//button[text()=' Add New People']");
    By inputSearchForUsers = By.xpath("(//div[text()='Search for users']/following::div/input)[1]");
    By buttonAdd = By.xpath("//button[text()='Add']");

    By buttonAddExternalPeople = By.xpath("//button[text()=' Add External People']");
    By inputFirstName = By.xpath("//input[@placeholder='First Name']");
    By inputLastName = By.xpath("//input[@placeholder='Last Name']");
    By inputAddExternalAffiliation = By.xpath("//input[@placeholder='Add External Affiliation']");
    By inputExternalAffiliation = By.xpath("(//div[text()='External Affiliation']/following::input)[1]");



    // Functions
    public void NavigateToCreateExportControlPage() {
        waitForPresence(buttonActions);
        click(buttonActions);
        pause(1000);

        waitForPresence(linkCreateExportControl);
        click(linkCreateExportControl);
        pause(3000);
    }

    public void CreateExportControl(String pi) {
        // Select Export Control Type
        waitForPresence(inputExportControlRequest);
        click(inputExportControlRequest);
        pause(1000);

        // Select PI Name
        type(inputSelectPI, pi);
        pause(1000);
        click(piSelection);
        pause(2000);

        // Click Create button
        click(buttonCreate);
        pause(2000);

        waitForPresence(lblExportControlSuccessfulCreation);
    }

    public boolean VerifyExportControlIsCreatedSuccessfully() {
        pause(5000);
        waitForPresence(lblExportControlRecordNum);
        return driver.findElement(lblExportControlRecordNum).isDisplayed();
    }

    public String GetExportControlRecordNumber() {
        pause(2000);
        return driver.findElement(exportControlRecordNo).getText();
    }

    public boolean VerifyCreatedFormIsVisible(String formName) {
        return driver.findElement(By.xpath("//span[text()='" + formName + "']/..")).isDisplayed();
    }

    public boolean VerifyInstructionsAssociatedWithFormIsVisibleUnderExportControl(String formName, String formInst) {
        boolean result = false;

        // Select the form
        driver.findElement(By.xpath("//span[text()='" + formName + "']/..")).click();
        pause(1000);

        // Click on Instructions link
        driver.findElement(By.xpath("//span[text()='Instructions']")).click();
        pause(1000);

        // Verify Instructions
        String name = driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).getText();
        if(Objects.equals(name, formInst))
        {
            result=true;
        }

        return result;
    }

    public void NavigateToAttachments() {
        click(linkAttachments);
        pause(2000);
    }

    public void NavigateToPeople() {
        click(linkPeople);
        pause(2000);
    }

    public void UploadAnAttachment(String filePath) {
        pause(2000);

        WebElement uploadElement = driver.findElement(fileInput);

        // Directly send file path (bypasses drag/drop UI)
        uploadElement.sendKeys(filePath);
        pause(3000);
    }

    public boolean VerifyIfFileIsUploadedSuccessfully(String name) {
        pause(3000);

        return driver.findElement(By.xpath("//div[text()='" + name + "']")).isDisplayed();
    }

    public boolean EnterAttachmentTypeAndDescriptionAndVerifyTheGrouping(String fileName, String typeName, String desc) {
        boolean result = false;

        // Select Attachment type
        click(inputAttachmentType);
        pause(1000);

        driver.findElement(By.xpath("//div[text()='" + typeName + "']")).click();
        pause(1000);

        // Enter Attachment Description
        type(textareaAttachmentDescription, desc);
        pause(3000);

        // Search for the attachment
        driver.findElement(inputSearchAttachment).clear();
        type(inputSearchAttachment, fileName);
        click(buttonSearch);
        pause(3000);

        // Verify of attachment group is visible
        if(driver.findElement(By.xpath("//span[text()='" + typeName + "']")).isDisplayed() && driver.findElement(By.xpath("//span[text()='1']")).isDisplayed())
        {
            result = true;
        }
        return result;
    }

    public boolean DeleteAttachmentAndVerifyAttachmentDeletedSuccessfully(String typeName) {
        boolean result = false;

        // Expand the group
        driver.findElement(By.xpath("//span[text()='" + typeName + "']")).click();
        pause(2000);

        click(buttonDeleteAttachment);
        if(driver.findElement(By.xpath("//div[text()='Are you sure you want to delete this attachment?']")).isDisplayed())
        {
            click(buttonOK);
            pause(2000);

            if(driver.findElement(By.xpath("//div[text()='Document was successfully deleted.']")).isDisplayed())
            {
                result = true;
                pause(5000);

                driver.findElement(inputSearchAttachment).clear();
                click(buttonSearch);
                pause(3000);

                //click(buttonClearSelections);
                driver.navigate().refresh();
                pause(2000);
            }
        }
        return result;
    }

    public boolean VerifyInstructionsForPeople(String formInst) {
        boolean result = false;

        // Click on Instructions link
        driver.findElement(By.xpath("//span[text()='Instructions']")).click();
        pause(2000);

        // Verify Instructions
        String name = driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).getText();
        if(Objects.equals(name, formInst))
        {
            result=true;
        }

        return result;
    }

    public boolean VerifyInstructionsForAttachments(String formInst) {
        boolean result = false;

        // Click on Instructions link
        driver.findElement(By.xpath("//span[text()='Instructions']")).click();
        pause(2000);

        // Verify Instructions
        String name = driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).getText();
        if(Objects.equals(name, formInst))
        {
            result=true;
        }

        return result;
    }

    public boolean VerifyInstructionsForReviewLetter(String formInst) {
        boolean result = false;

        // Click on Initial Review link
        click(linkInitialReview);
        pause(5000);

        // Verify Instructions
        if(driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).isDisplayed())
        {
            String name = driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).getText();
            if(Objects.equals(name, formInst))
            {
                result=true;
            }
        }
        else {
            // Click on Instructions link
            driver.findElement(By.xpath("//span[text()='Instructions']")).click();
            pause(3000);

            String name = driver.findElement(By.xpath("(//span[text()='Instructions']/following::section/div/div/div)[1]")).getText();
            if(Objects.equals(name, formInst))
            {
                result=true;
            }
        }
        return result;
    }

    public boolean VerifyExportControlDetailsOnPeoplePage(String recordNum, String piName, String ecType, String ecStatus) {
        boolean result = false;

        String recordNo = driver.findElement(exportControlRecordNo).getText();
        String pi = driver.findElement(exportControlPI).getText();
        String type = driver.findElement(exportControlType).getText();
        String status = driver.findElement(exportControlStatus).getText();

        if(Objects.equals(recordNum, recordNo) && Objects.equals(piName, pi) && Objects.equals(ecType, type) && Objects.equals(ecStatus, status))
        {
            result=true;
        }
        return result;
    }

    public boolean VerifyUserIsAutomaticallyAddedAsASubmitterInStaffList(String userName) {
        return driver.findElement(By.xpath("//div[text()='" + userName + "']")).isDisplayed();
    }

    public boolean VerifyUserIsAbleToAddExternalPeopleWithNewExternalAffiliation(String firstName, String lastName, String newExternalAffiliation) {
        boolean result = false;

        // Enter external people details
        type(inputFirstName, firstName);
        type(inputLastName, lastName);
        type(inputAddExternalAffiliation, newExternalAffiliation);
        pause(1000);

        click(buttonAdd);
        pause(2000);

        By userAdditionMsg = By.xpath("//div[text()='External People successfully added.']");
        waitForPresence(userAdditionMsg);

        if(driver.findElement(userAdditionMsg).isDisplayed())
        {
            result = true;
            pause(2000);
        }
        return result;
    }

    public boolean VerifyNewExternalPeopleIsVisibleInTheList(String name, String org, String type) {
        boolean result = false;

        By elementUserName = By.xpath("//div[text()='" + name + "']");
        waitForPresence(elementUserName);

        By elementOrgName = By.xpath("//div[text()='" + org + "']");
        waitForPresence(elementUserName);

        By elementTypeName = By.xpath("//div[text()='" + type + "']");
        waitForPresence(elementUserName);

        if(driver.findElement(elementUserName).isDisplayed() && driver.findElement(elementOrgName).isDisplayed() && driver.findElement(elementTypeName).isDisplayed())
        {
            result = true;
        }
        return result;
    }

    public boolean VerifyExistingExternalPeopleIsVisibleInTheList(String name, String type) {
        boolean result = false;

        By elementUserName = By.xpath("//div[text()='" + name + "']");
        waitForPresence(elementUserName);

        By elementTypeName = By.xpath("//div[text()='" + type + "']");
        waitForPresence(elementUserName);

        if(driver.findElement(elementUserName).isDisplayed() && driver.findElement(elementTypeName).isDisplayed())
        {
            result = true;
        }
        return result;
    }

    public boolean VerifyBothOrganizationAndTypeFieldsAreDisabledForNewlyAddedExternalPeople(String orgName, String typeName) {
        boolean result = false;

        String orgClassName = driver.findElement(By.xpath("//div[text()='"+ orgName + "']/../..")).getAttribute("class");
        String typeClassName = driver.findElement(By.xpath("//div[text()='"+ typeName + "']/../..")).getAttribute("class");

        if(orgClassName.contains("_controlDisabled") && typeClassName.contains("_controlDisabled"))
        {
            result = true;
        }

        return result;
    }

    public boolean VerifyUserIsAbleToAddExternalPeopleWithExistingExternalAffiliation(String firstName, String lastName, String existingExternalAffiliation) {
        boolean result = false;

        // Enter external people details
        type(inputFirstName, firstName);
        type(inputLastName, lastName);

        click(inputExternalAffiliation);
        type(inputExternalAffiliation, existingExternalAffiliation);
        pause(2000);
        driver.findElement(By.xpath("//div[text()='" + existingExternalAffiliation + "']")).click();
        pause(2000);

        click(buttonAdd);
        pause(2000);

        By userAdditionMsg = By.xpath("//div[text()='External People successfully added.']");
        waitForPresence(userAdditionMsg);

        if(driver.findElement(userAdditionMsg).isDisplayed())
        {
            result = true;
            pause(2000);
        }
        return result;
    }

    public boolean DeleteNewlyAddedPeople(String name) {
        boolean result = false;

        driver.findElement(By.xpath("//td[@data-value='" + name + "']/..//button")).click();
        pause(2000);

        // Switch to alert
        Alert alert = driver.switchTo().alert();

        // Accept the alert (click 'OK')
        alert.accept();
        pause(2000);

        By userDeletionMsg = By.xpath("//div[text()='People deleted successfully.']");
        waitForPresence(userDeletionMsg);

        if(driver.findElement(userDeletionMsg).isDisplayed())
        {
            pause(3000);

            List<WebElement> elements = driver.findElements(By.xpath("//div[text()='" + name + "']"));

            if (!elements.isEmpty()) {
                elements.get(0).isDisplayed();
            }
            {
                result = true;
            }
        }

        return result;
    }

    public void NavigateToAddNewInternalPeopleSection() {
        click(buttonAddNewPeople);
        pause(1000);
    }

    public void NavigateToAddNewExternalPeopleSection() {
        click(buttonAddNewPeople);
        pause(1000);

        click(buttonAddExternalPeople);
        pause(1000);
    }

    public boolean VerifyUserIsAbleToAddExistingExternalPeople(String existingExternalPeople) {
        boolean result = false;

        // Select Existing External People
        click(inputSearchForUsers);
        type(inputSearchForUsers, existingExternalPeople);
        pause(2000);

        By elementUser = By.xpath("//div[text()='" + existingExternalPeople + "']");
        click(elementUser);

        pause(2000);

        click(buttonAdd);
        pause(2000);

        By userAdditionMsg = By.xpath("//div[text()='External People successfully added.']");
        waitForPresence(userAdditionMsg);

        if(driver.findElement(userAdditionMsg).isDisplayed())
        {
            result = true;
            pause(2000);
        }
        return result;
    }

    public boolean VerifyExistingExternalPeopleDetailsAreAutoPopulatedUponNameSelection(String existingExternalPeople, String instAff, String departAff, String unitAff, String existingUserAff) {
        boolean result = false;

        // Select Existing External People
        click(inputSearchForUsers);
        type(inputSearchForUsers, existingExternalPeople);
        pause(2000);

        By elementUser = By.xpath("//div[text()='" + existingExternalPeople + "']");
        click(elementUser);

        pause(2000);

        // Get External Affiliation value
        String affName = driver.findElement(inputAddExternalAffiliation).getAttribute("value");

        if(driver.findElement(By.xpath("//div[text()='" + instAff + "']")).isDisplayed() && driver.findElement(By.xpath("//div[text()='" + departAff + "']")).isDisplayed() && driver.findElement(By.xpath("//div[text()='" + unitAff + "']")).isDisplayed())
        {
            if(Objects.equals(existingUserAff, affName))
            {
                result = true;
            }
        }
        return result;
    }

    public boolean VerifyUserIsAbleToAddInternalPeople(String existingInternalPeople) {
        boolean result = false;

        // Select Existing Internal People
        click(inputSearchForUsers);
        type(inputSearchForUsers, existingInternalPeople);
        pause(2000);

        By elementUser = By.xpath("//div[text()='" + existingInternalPeople + "']");
        click(elementUser);

        pause(2000);

        click(buttonAdd);
        pause(2000);

        By userAdditionMsg = By.xpath("//div[text()='People was successfully added.']");
        waitForPresence(userAdditionMsg);

        if(driver.findElement(userAdditionMsg).isDisplayed())
        {
            result = true;
            pause(2000);
        }
        return result;
    }

    public boolean VerifyNewInternalPeopleIsVisibleInTheList(String name, String org) {
        boolean result = false;

        By elementUserName = By.xpath("//div[text()='" + name + "']");
        waitForPresence(elementUserName);

        By elementOrgName = By.xpath("//div[text()='" + org + "']");
        waitForPresence(elementOrgName);

        if(driver.findElement(elementUserName).isDisplayed() && driver.findElement(elementOrgName).isDisplayed())
        {
            result = true;
        }
        return result;
    }

    public boolean VerifyBothOrganizationAndTypeFieldsAreEnabledForNewlyAddedInternalPeople(String orgName) {
        boolean result = false;

        driver.findElement(By.xpath("//div[text()='"+ orgName + "']/../..")).click();
        String orgClassName = driver.findElement(By.xpath("//div[text()='"+ orgName + "']/../..")).getAttribute("class");

        pause(2000);

        driver.findElement(By.xpath("(//td[@data-column='type'])[2]/div/div")).click();
        String typeClassName = driver.findElement(By.xpath("(//td[@data-column='type'])[2]/div/div")).getAttribute("class");

        if(orgClassName.contains("_controlMenuExpanded") && typeClassName.contains("_controlMenuExpanded"))
        {
            result = true;
        }

        return result;
    }

    public void SelectNewlyCreatedPeopleTypeForTheAddedInternalPeople(String typeName) {
        driver.findElement(By.xpath("(//td[@data-column='type'])[2]/div/div")).click();
        pause(2000);

        By peopleType = By.xpath("//div[text()='" + typeName + "']");
        waitForElement(peopleType);

        if(driver.findElement(peopleType).isDisplayed())
        {
            click(peopleType);
            pause(5000);
        }
    }

    public boolean VerifySubmissionChecklistMsgBeforeCompletion(String msg) {
        boolean result = false;
        click(lblSubmissionChecklist);
        pause(2000);

        By submissionChecklistInnerText = By.xpath("(//div[@class='submission-checklist-list-inner'])[1]");
        waitForPresence(submissionChecklistInnerText);

        String submissionMsg = driver.findElement(submissionChecklistInnerText).getText();
        if(Objects.equals(msg, submissionMsg))
        {
            result = true;
        }
        return result;
    }

    public void AssignInternalUserRole(String roleName) {
        driver.findElement(By.xpath("(//td[@data-column='roleId'])[2]/div/div")).click();
        pause(2000);

        By role = By.xpath("//div[text()='" + roleName + "']");
        waitForElement(role);

        if(driver.findElement(role).isDisplayed())
        {
            click(role);
            pause(5000);
        }
    }

    public boolean VerifySubmissionChecklistMsgAfterCompletion(String msg) {
        boolean result = false;

        By submissionChecklistSuccessText = By.xpath("(//li[@class='_successMessage_1jkn8_22'])[1]/span");
        waitForPresence(submissionChecklistSuccessText);

        String submissionMsg = driver.findElement(submissionChecklistSuccessText).getText();
        if(Objects.equals(msg, submissionMsg))
        {
            result = true;
        }
        return result;
    }

    public boolean VerifyChecklistMsgIfInternalUserIsAlsoAssignedPIRole(String msg, String typeName, String roleName) {
        boolean result = false;

        // Assign Type
        driver.findElement(By.xpath("(//td[@data-column='type'])[2]/div/div")).click();
        pause(2000);

        By peopleType = By.xpath("(//div[text()='" + typeName + "'])[2]");
        waitForElement(peopleType);

        if(driver.findElement(peopleType).isDisplayed())
        {
            click(peopleType);
            pause(5000);
        }

        // Assign Role
        driver.findElement(By.xpath("(//td[@data-column='roleId'])[2]/div/div")).click();
        pause(2000);

        By role = By.xpath("(//div[text()='" + roleName + "'])[2]");
        waitForElement(role);

        if(driver.findElement(role).isDisplayed())
        {
            click(role);
            pause(5000);
        }

        By submissionChecklistErrText = By.xpath("(//div[@class='submission-checklist-list-inner'])[1]");
        waitForPresence(submissionChecklistErrText);

        String submissionMsg = driver.findElement(submissionChecklistErrText).getText();
        if(Objects.equals(msg, submissionMsg))
        {
            result = true;
        }
        return result;
    }
}
