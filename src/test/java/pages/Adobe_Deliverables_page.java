package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Adobe_Deliverables_page extends BasePage {


    public Adobe_Deliverables_page(WebDriver driver) {super(driver);
    }


    // ******** Locators *********

    // Overlay
    private By addDeliverableOverlay = By.cssSelector("div.add-new-deliverable-overlay");

    // Fields & controls (scoped to overlay)
    private By deliverableNameInput = By.xpath("//div[contains(@class,'add-new-deliverable-overlay')]//label[normalize-space(.)='Deliverable Name']/following::input[@type='text'][1]");
    private By deliverableCategoryControl = By.xpath("//div[contains(@class,'add-new-deliverable-overlay')]//label[normalize-space(.)='Deliverable Category']/following::div[contains(@class,'select-control')][1]");

    // Options (react-select)
    private By deliverableCategoryOption(String text) {
        return By.xpath("//div[(contains(@class,'select__option') or contains(@class,'select-option') or contains(@class,'_option')) and normalize-space(.)='" + text + "']");
    }

    // Buttons
    private By cancelBtn = By.xpath("//div[contains(@class,'add-new-deliverable-overlay')]//button[normalize-space(.)='Cancel']");
    private By topSearchBtn = By.xpath("//button[normalize-space(.)='Add New Deliverable']/preceding::button[normalize-space(.)='Search'][1]");
//    private By addDeliverableOverlay = By.cssSelector("div.add-new-deliverable-overlay");
    private By submitBtn = By.xpath("//div[contains(@class,'add-new-deliverable-overlay')]//button[@type='submit' and normalize-space(.)='Submit']");
    private By deliverablesSearchInput = By.cssSelector("input[placeholder^='Search by deliverables']");

    // Top bar "Clear Selections" (left of "Add New Deliverable")
    private By clearSelectionsBtn = By.xpath("//button[normalize-space(.)='Clear Selections']");
    private By cloneBtn = By.xpath("//button[normalize-space(.)='Clone' and contains(@class,'-small')]");
    // Row checkbox by deliverable name (choose last match => newest)
    private By rowCheckboxByName(String name) {return By.xpath("(//table[contains(@class,'item-grid')]//tbody//tr[.//span[normalize-space(.)='"+ name + "']]//input[@type='checkbox'])[last()]");}

    private By firstCheckbox = By.xpath("(//table[@class='item-grid -sticky']//input[@type='checkbox'])[2]");

    private By deleteSelectedBtn = By.xpath("//button[normalize-space(.)='Delete Selected' and contains(@class,'-small')]");


    private By downloadSelectedBtn = By.xpath("//button[normalize-space(.)='Download Selected']");

    // Anchored to the Deliverable Name column (col_196); matches the exact visible text inside the <span>
    private By deliverableLinkByName(String name) {
        return By.xpath("//table[contains(@class,'item-grid')]//td[@data-column='col_196']" +
                "//a[.//span[normalize-space()='" + name + "']]");
    }
    // Reminders dropdown (open it by clicking the control/arrow next to the label)
    private By remindersControl = By.xpath("//label[@for='reminderFrequency']/following::div[contains(@class,'select-control')][1]");

    // Option matcher (works for their various option class names / role)
    private By reminderOption(String text) {
        return By.xpath(
                "(//div[@role='option' and normalize-space()='" + text + "']" +
                        " | //div[contains(@class,'select-option') and normalize-space()='" + text + "']" +
                        " | //div[contains(@class,'select__option') and normalize-space()='" + text + "']" +
                        " | //div[contains(@class,'_option_') and normalize-space()='" + text + "'])[1]"
        );
    }



    // First recipient row's delete button (next to the Email input)
    private By firstRecipientDeleteBtn =
            By.xpath("//div[contains(@class,'email-row')][1]//button[@aria-label='Delete']");


    // ******** Actions *********


    public void clickFirstRecipientDelete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(firstRecipientDeleteBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        pause(600);
    }



    public void selectReminderFrequency(String frequency) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 1) Open the dropdown
        WebElement control = wait.until(ExpectedConditions.visibilityOfElementLocated(remindersControl));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        wait.until(ExpectedConditions.elementToBeClickable(control));
        try {
            control.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);
        }

        // 2) Pick the option (menu is rendered in a portal, so search globally)
        By optionLocator = reminderOption(frequency);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);
        try {
            option.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
        }
        pause(600);
    }

    // Convenience wrapper for this specific case
    public void selectReminderEveryDay() {
        selectReminderFrequency("Every day");
    }







    public void clickDeliverableByEnteredName(String enteredName01) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By linkLocator = deliverableLinkByName(enteredName01);

        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(linkLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        wait.until(ExpectedConditions.elementToBeClickable(link));

        try {
            link.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }
        pause(600);
    }






    public void acceptDeleteAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();   // Clicks on "OK"
        } catch (TimeoutException e) {
            throw new RuntimeException("Delete confirmation alert did not appear");
        }
        pause(500);
    }


    public void clickDownloadSelected() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(downloadSelectedBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        pause(600);
    }



    public void clickDeleteSelected() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(deleteSelectedBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        pause(600);
    }




    public void tickFirstCheckbox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(firstCheckbox));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", checkbox);

        if (!checkbox.isSelected()) {
            checkbox.click();
        }
        pause(500);
    }


    public void checkRowByName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By target = rowCheckboxByName(name);

        wait.until(ExpectedConditions.presenceOfElementLocated(target));
        WebElement cb = wait.until(ExpectedConditions.elementToBeClickable(target));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", cb);

        try { cb.click(); }
        catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cb); }

        wait.until(d -> d.findElement(target).isSelected());
        pause(300);
    }


    public void clickClone() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(cloneBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        pause(600);
    }



    public void clickClearSelections() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(clearSelectionsBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        pause(500);
    }



    public void typeDeliverablesSearch(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(deliverablesSearchInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);
        input.clear();
        input.sendKeys(text);
        pause(300);
    }



    public void clickSubmitOnOverlay() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(addDeliverableOverlay));

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }

        // overlay should close after submit
        wait.until(ExpectedConditions.invisibilityOfElementLocated(addDeliverableOverlay));
        pause(500);
    }



    public String typeDeliverableNameUnique(String base) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(addDeliverableOverlay));

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(deliverableNameInput));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        String unique = base + "_" + System.currentTimeMillis();
        input.sendKeys(unique);
        pause(300);
        return unique;
    }

    public void selectDeliverableCategory(String categoryText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement control = wait.until(ExpectedConditions.elementToBeClickable(deliverableCategoryControl));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        control.click();

        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(deliverableCategoryOption(categoryText)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option); // more stable on react-select
        pause(300);
    }

    public void clickCancelOnOverlay() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(cancelBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(addDeliverableOverlay));
        pause(500);
    }

    public void clickTopSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(topSearchBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
        pause(800);
    }




}
