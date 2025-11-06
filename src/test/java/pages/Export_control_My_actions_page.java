package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Export_control_My_actions_page extends BasePage {

    public Export_control_My_actions_page(WebDriver driver) {
        super(driver);
    }



    //Locators

    private By exportControlLink = By.xpath("//a[@href='/export-control' and contains(@class,'module-link')]");


    private By actionRequiredLink = By.xpath("//a[@href='/export-control/action-required' and contains(@class,'label')]");

    // --- React-Select dropdowns ---
    private By recordNumberInput = By.xpath("//label[normalize-space()='Record Number']/following::input[@type='text'][1]");
    private By agreementNumberInput = By.xpath("//label[normalize-space()='Agreement Numbers']/following::input[@type='text'][1]");
    private By searchButton = By.xpath("//button[normalize-space()='Search']");

    private By clearSelectionsButton = By.xpath("//button[normalize-space()='Clear Selections']");

    // Dynamic locator for a specific Record Number link
    private By recordNumberLink(String recordNumber) {
        return By.xpath("//table//a[normalize-space()='" + recordNumber + "']");
    }



    //Action



    public void clickRecordNumberLink(String recordNumber) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By linkBy = recordNumberLink(recordNumber);
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(linkBy));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        try {
            link.click();
        } catch (Exception e) {
            // Fallback if intercepted
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }

        pause(2000); // small wait for navigation
    }




    public void clickClearSelections() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Scroll and wait
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(clearSelectionsButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", button);

        // Try normal click, fallback to JS click
        try {
            button.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }

        pause(1000); // short stability pause
    }


    private void selectReactSelectSingleByLabel(String label, String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        By controlBy = By.xpath("//label[normalize-space()='" + label + "']/following::div[contains(@class,'select-control')][1]");
        WebElement controlEl = wait.until(ExpectedConditions.elementToBeClickable(controlBy));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", controlEl);
        controlEl.click();

        By inputBy = By.xpath("//label[normalize-space()='" + label + "']/following::div[contains(@class,'select-control')][1]//input[@role='combobox']");
        WebElement inputEl = wait.until(ExpectedConditions.visibilityOfElementLocated(inputBy));

        String listboxId = inputEl.getAttribute("aria-controls");
        if (listboxId == null || listboxId.isEmpty()) {
            String inputId = inputEl.getAttribute("id");
            if (inputId != null) listboxId = inputId.replace("input", "listbox");
        }
        By listboxBy = By.id(listboxId);

        // (optional) prefilter
        try { inputEl.clear(); inputEl.sendKeys(optionText); } catch (Exception ignored) {}
        wait.until(ExpectedConditions.visibilityOfElementLocated(listboxBy));

        By optionBy = By.xpath("//*[@id='" + listboxId + "']//*[normalize-space()='" + optionText + "']");
        WebElement optionEl = wait.until(ExpectedConditions.elementToBeClickable(optionBy));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", optionEl);
        optionEl.click();

        // ---- remove fragile invisibility wait; close + assert by value instead ----
        try { inputEl.sendKeys(Keys.ESCAPE); } catch (Exception ignored) {}
        try { ((JavascriptExecutor) driver).executeScript("arguments[0].blur();", inputEl); } catch (Exception ignored) {}
        try { driver.findElement(By.tagName("body")).click(); } catch (Exception ignored) {}

        By selectedValueBy = By.xpath(
                "//label[normalize-space()='" + label + "']/following::div[contains(@class,'select-control')][1]" +
                        "//*[contains(@class,'singleValue') or contains(@class,'valueContainer')]"
        );
        wait.until(d -> d.findElement(selectedValueBy).getText().trim().equals(optionText));
    }


    public void setRecordType(String value) {
        selectReactSelectSingleByLabel("Record Type", value);
    }
    public void setTransactionType(String value) {
        selectReactSelectSingleByLabel("Transaction Type", value);
    }



    public void setRecordNumber(String value) {
        WebElement input = driver.findElement(recordNumberInput);
        input.clear();
        input.sendKeys(value);
    }



    public void setAgreementNumbers(String value) {
        WebElement input = driver.findElement(agreementNumberInput);
        input.clear();
        input.sendKeys(value);
    }

    public void clickSearch() {
        WebElement btn = driver.findElement(searchButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
    }


    public void dismissAnyOpenReactSelectMenus() {
        List<WebElement> menus = driver.findElements(
                By.cssSelector("ul[id^='react-select-'][id$='-listbox'], div[id^='react-select-'][id$='-listbox']")
        );
        if (!menus.isEmpty()) {
            try { driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE); } catch (Exception ignored) {}
            try { driver.findElement(By.tagName("body")).click(); } catch (Exception ignored) {}
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(d -> d.findElements(By.cssSelector("ul[id^='react-select-'][id$='-listbox'], div[id^='react-select-'][id$='-listbox']")).isEmpty());
        }
    }


    //Actions


    public void clickActionRequiredLink() {
        dismissAnyOpenReactSelectMenus(); // <— add this
        WebElement link = driver.findElement(actionRequiredLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(link));
        link.click();
        pause(1000);
    }



    public void clickExportControlLink() {
        dismissAnyOpenReactSelectMenus(); // <— add this
        WebElement link = driver.findElement(exportControlLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(link));
        link.click();
        pause(1000);
    }





}