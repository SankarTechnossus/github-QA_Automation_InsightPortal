package pages.Administration;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import java.time.Duration;

public class Exportcontrol_formBuilder_Page extends BasePage {

    public Exportcontrol_formBuilder_Page(WebDriver driver) {super(driver);}

    private By addRootLevelQuestionButton = By.xpath("//button[@type='button' and contains(@class, 'button') and contains(., 'Add root level question')]");
    private By radioButtonGroupCard = By.xpath("//div[contains(@class,'possible-question') and .//text()[contains(., 'Radio button group')]]");
    private By radioOption1Input = By.xpath("//input[@class='value-input text-input default-input' and @value='Option1']");
    private By addOptionButton = By.xpath("//button[@type='button' and div/i[contains(@class, 'fi-add')] and contains(., 'Add option')]");
    private By radioOption2Input = By.xpath("//input[@class='value-input text-input default-input' and @value='Option2']");
    private By readOnlyCheckbox = By.xpath("//label[contains(normalize-space(), 'Read only')]/input[@type='checkbox']");
    private By helpTextArea = By.xpath("//span[text()='Help text:']/following-sibling::textarea");
    private By applyButton = By.xpath("//button[contains(@class,'-primary') and contains(@class,'-submission') and contains(@class,'-small') and text()='Apply']");
    private By previewLink = By.xpath("//a[contains(@href, '/preview') and text()='Preview']");
    private By closePreviewLink = By.xpath("//a[contains(@href, '/edit') and text()='Close preview']");
    private By saveButton = By.xpath("//button[@type='button' and contains(@class, '-submission') and text()='Save']");
    private By addChildQuestionButton = By.xpath("//button[normalize-space()='Add child question']");
    private By cancelAddingButton = By.xpath("//button[normalize-space()='Cancel adding']");
    private By moveButton = By.xpath("//button[normalize-space()='Move']");
    private By cancelMovingButton = By.xpath("//button[normalize-space()='Cancel moving']");
    private By editButton = By.xpath("//button[normalize-space()='Edit']");
    private By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    private By removeButton = By.xpath("//button[normalize-space()='Remove']");
    private By undoButton = By.xpath("//button[normalize-space()='Undo']");
    private By formManagementLink = By.xpath("//a[@class='_link_ogtko_1' and contains(@href, '/forms-management-export-control') and normalize-space()='Form Management']");
    private By testFormLink = By.xpath("//a[contains(@class, '_link_ogtko_1') and contains(@href, '/administration/forms-management-export-control/edit/')]");
    private By editDescriptionButton = By.xpath("//button[normalize-space()='Edit description']");
    private By cancelButtononversionedit = By.xpath("//button[normalize-space()='Cancel']");
//    private By versionTextArea = By.xpath("//textarea[contains(@class,'default-input') and contains(@class,'textarea')]");
//  private By versionTextArea = By.xpath("//div[contains(@class, 'versionHeader')]/following-sibling::div//textarea[contains(@class,'default-input')]");
    private By versionTextArea = By.xpath("(//textarea[contains(@class,'default-input')])[last()]");
    private By saveButtonsmall = By.xpath("//button[@class='button -primary -small' and normalize-space()='Save']");
    private By addNewButton = By.xpath("//button[@class='button -primary -small' and normalize-space()='Add new']");
    private By cancelButtonaddnewcancel = By.xpath("//button[@class='button -small -unstyled' and normalize-space()='Cancel']");



    // ***********__________Actions__________*************


    public void clickCancelButtonaddnewcancel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(cancelButtonaddnewcancel));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
            pause(300);
            button.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Cancel' button: " + e.getMessage(), e);
        }
    }



    public void clickAddNewButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addNewButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
            pause(300);
            button.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Add new' button: " + e.getMessage(), e);
        }
    }



    public void clickSaveButtonsmall() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(saveButtonsmall));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
            pause(300);
            button.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Save' button: " + e.getMessage(), e);
        }
    }



    public void enterVersionText(String versionName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(versionTextArea));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", textarea);
            pause(300);
            textarea.clear();
            textarea.sendKeys(versionName);
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter text into version description box: " + e.getMessage(), e);
        }
    }



    public void clickCancelButtononversionedit01() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(cancelButtononversionedit));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
            pause(300);
            button.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Cancel' button: " + e.getMessage(), e);
        }
    }




    public void clickEditDescriptionButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(editDescriptionButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
            pause(300);
            button.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Edit description' button: " + e.getMessage(), e);
        }
    }



    public void clickTestFormLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(testFormLink));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
            pause(300);
            link.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on the test form link: " + e.getMessage(), e);
        }
    }




    public void clickFormManagementLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(formManagementLink));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
            pause(300);
            link.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Form Management' link: " + e.getMessage(), e);
        }
    }



    public void clickOutsidePopupByOffset() {
        try {
            Actions actions = new Actions(driver);
            actions.moveByOffset(50, 50).click().perform(); // Click on top-left part of the screen
            pause(500); // Allow popup to close
        } catch (Exception e) {
            throw new RuntimeException("Failed to click outside popup using offset: " + e.getMessage(), e);
        }
    }



    public void clickUndoButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement undoBtn = wait.until(ExpectedConditions.elementToBeClickable(undoButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", undoBtn);
            pause(300);
            undoBtn.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Undo' button: " + e.getMessage(), e);
        }
    }


    public void clickRemoveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(removeButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", removeBtn);
            pause(300);
            removeBtn.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Remove' button: " + e.getMessage(), e);
        }
    }


    public void clickCancelButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cancelBtn);
            pause(300);
            cancelBtn.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Cancel' button: " + e.getMessage(), e);
        }
    }


    public void clickEditButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(editButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", editBtn);
            pause(300);
            editBtn.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Edit' button: " + e.getMessage(), e);
        }
    }



    public void clickCancelMovingButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(cancelMovingButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cancelBtn);
            pause(300);
            cancelBtn.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Cancel moving' button: " + e.getMessage(), e);
        }
    }



    public void clickMoveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement moveBtn = wait.until(ExpectedConditions.elementToBeClickable(moveButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", moveBtn);
            pause(300); // optional wait
            moveBtn.click();
            pause(500); // wait for any move dialog/behavior
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Move' button: " + e.getMessage(), e);
        }
    }



    public void clickCancelAddingButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(cancelAddingButton));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cancelBtn);
            pause(300); // brief wait after scroll

            cancelBtn.click();
            pause(500); // wait for UI to update

        } catch (Exception e) {
            throw new RuntimeException(" Failed to click 'Cancel adding' button: " + e.getMessage(), e);
        }
    }



    public void clickAddChildQuestion() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addChildQuestionButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", button);
            pause(300); // optional
            button.click();
            pause(500); // wait for action
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Add child question' button: " + e.getMessage(), e);
        }
    }



    public void clickSaveButton() {
        WebElement button = driver.findElement(saveButton);
        button.click();
        pause(3000); // Optional: wait for the form save to complete
    }


    public void clickClosePreviewLink() {
        WebElement link = driver.findElement(closePreviewLink);
        link.click();
        pause(3000); // Optional wait for the editor to reload
    }



    public void clickPreviewLink() {
        WebElement link = driver.findElement(previewLink);
        link.click();
        pause(3000); // Optional wait for preview page to load
    }




//    public void clickApplyButton() {
//        WebElement button = driver.findElement(applyButton);
//        button.click();
//        pause(5000); // Optional wait for changes to take effect
//    }


    public void clickApplyButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(applyButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", button);
            pause(300);
            button.click();
            pause(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click 'Apply' button: " + e.getMessage(), e);
        }
    }




    public void enterHelpText(String text) {
        WebElement helpTextField = driver.findElement(helpTextArea);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", helpTextField);
        helpTextField.clear(); // Optional: clear existing text
        helpTextField.sendKeys(text);
        pause(500); // Optional: slight wait after entering text
    }


    public void checkReadOnly() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(readOnlyCheckbox));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", checkbox);
            pause(300); // Optional pause

            if (!checkbox.isSelected()) {
                checkbox.click(); // or JS click if needed
                pause(500); // Optional post-click pause
            }

        } catch (Exception e) {
            throw new RuntimeException("Could not check 'Read only': " + e.getMessage(), e);
        }
    }



    public void enterRadioOption2Text(String text) {
        WebElement input = driver.findElement(radioOption2Input);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);
        input.clear();
        input.sendKeys(text);
        pause(1000);  // Optional delay
    }



    public void clickAddOptionButton() {
        WebElement addButton = driver.findElement(addOptionButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", addButton);
        pause(1000);  // Optional scroll wait
        addButton.click();
        pause(2000);  // Allow new input field to load
    }



    public void enterRadioOption1Text(String text) {
        WebElement input = driver.findElement(radioOption1Input);
        input.clear();
        input.sendKeys(text);
        pause(1000);  // Let input settle
    }


    public void clickRadioButtonGroupOption() {
        WebElement radioOption = driver.findElement(radioButtonGroupCard);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", radioOption);
        pause(1000);  // Let the scroll complete
        radioOption.click();
        pause(3000);  // Let the new field render
    }



    public void clickAddRootLevelQuestionButton() {
        WebElement button = driver.findElement(addRootLevelQuestionButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);  // Optional: make visible
        pause(1000);  // Wait after scroll
        button.click();
        pause(3000);  // Wait for action completion
    }





}
