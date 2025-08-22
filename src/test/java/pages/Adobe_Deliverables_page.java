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








    // ******** Actions *********

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
