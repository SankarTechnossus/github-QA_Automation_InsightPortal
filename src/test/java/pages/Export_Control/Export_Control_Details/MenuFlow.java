package pages.Export_Control.Export_Control_Details;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MenuFlow extends BasePage {

    public MenuFlow(WebDriver driver) {
        super(driver);
    }

    // ************************************** Locators ********************************************************************

    By exportControlLink = By.xpath("//a[@href='/export-control' and contains(@class,'module-link')]");
    By searchLink = By.xpath("//a[@href='/export-control/search' and contains(@class,'label')]");

    // Text inputs
    By recordNumberInput = By.xpath("//label[normalize-space()='Record Number']/following::input[@type='text'][1]");
    By agreementNumbersInp = By.xpath("//label[normalize-space()='Agreement Numbers']/following::input[@type='text'][1]");

    // Buttons
    By searchButton = By.xpath("//button[normalize-space()='Search']");
    By clearSelectionsBtn = By.xpath("//button[normalize-space()='Clear Selections']");

    // Created On
    By createdFromInput = By.xpath("//label[normalize-space()='Created On']" + "/following::*[normalize-space()='From:'][1]/following::input[@type='text'][1]");
    By createdToInput = By.xpath("//label[normalize-space()='Created On']" + "/following::*[normalize-space()='To:'][1]/following::input[@type='text'][1]");

    // Review date
    By reviewFromInput = By.xpath("//label[normalize-space()='Review date']" + "/following::*[normalize-space()='From:'][1]/following::input[@type='text'][1]");
    By reviewToInput = By.xpath("//label[normalize-space()='Review date']" + "/following::*[normalize-space()='To:'][1]/following::input[@type='text'][1]");
    By recordNumberSearchGridLink = By.xpath("//table[contains(@class,'item-grid')]//tbody/tr[1]//td[@data-column='_exportControlNumber']//a");

    // ************************************** Functions ********************************************************************

    public By controlRoot(String label) {
        return By.xpath("//label[normalize-space()='" + label + "']/following::div[contains(@class,'select-control')][1]");
    }

    public void selectReactSelectSingleByLabel(String label, String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement root = wait.until(ExpectedConditions.elementToBeClickable(controlRoot(label)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", root);
        root.click();

        WebElement inputEl = root.findElement(By.xpath(".//input[@role='combobox']"));

        String listboxId = inputEl.getAttribute("aria-controls");
        if (listboxId == null || listboxId.isEmpty()) {
            String inputId = inputEl.getAttribute("id");
            if (inputId != null) listboxId = inputId.replace("input","listbox");
        }
        By listboxBy = By.id(listboxId);

        // pre-filter & open
        inputEl.clear();
        inputEl.sendKeys(optionText);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(listboxBy));

        By optionBy = By.xpath("//*[@id='" + listboxId + "']//*[normalize-space()='" + optionText + "']");
        WebElement optionEl = wait.until(ExpectedConditions.elementToBeClickable(optionBy));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", optionEl);
        optionEl.click();

        // close & assert by value (don’t wait for invisibility)
        try { inputEl.sendKeys(Keys.ESCAPE); } catch (Exception ignored) {}
        try { driver.findElement(By.tagName("body")).click(); } catch (Exception ignored) {}

        By selectedValueBy = By.xpath(
                "//label[normalize-space()='" + label + "']/following::div[contains(@class,'select-control')][1]" +
                        "//*[contains(@class,'singleValue') or contains(@class,'valueContainer')]"
        );
        wait.until(d -> d.findElement(selectedValueBy).getText().trim().equals(optionText));
    }

    private void setDate(By inputBy, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(inputBy));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        el.sendKeys(Keys.DELETE);
        el.sendKeys(value);
        // Close any open datepicker so it doesn't block next actions
        try { el.sendKeys(Keys.ESCAPE); } catch (Exception ignored) {}
    }

    private final DateTimeFormatter DF = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public String fmt(LocalDate d) { return d.format(DF); }

    public void setCreatedOnFrom(LocalDate from)  { setDate(createdFromInput, fmt(from)); }
    public void setCreatedOnTo(LocalDate to)      { setDate(createdToInput,   fmt(to)); }

    public void setReviewDateFrom(LocalDate from) { setDate(reviewFromInput,  fmt(from)); }
    public void setReviewDateTo(LocalDate to)     { setDate(reviewToInput,    fmt(to)); }

    public void clickRecordNumberFromSearchGrid() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(recordNumberSearchGridLink));

        // Scroll into view
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        // Optional highlight
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.outline='3px solid red';", link);
        } catch (StaleElementReferenceException ignored) {}

        pause(800);

        // Click safely
        try {
            link.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }

        //  DO NOT remove highlight after click → page navigates → element becomes stale
        // So we wrap it in a try-catch and ignore any error.
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.outline='';", link);
        } catch (Exception ignored) {}

        // Small buffer only for stability
        pause(1000);
    }

    public String[] enterCreatedOnRandomRange() {
        LocalDate today = LocalDate.now();
        LocalDate from  = today.minusDays(ThreadLocalRandom.current().nextInt(10,15));
        LocalDate to    = from.plusDays(ThreadLocalRandom.current().nextInt(1,6));
        if (to.isAfter(today)) to = today;

        setCreatedOnFrom(from);
        pause(3000);
        setCreatedOnTo(to);
        return new String[]{ fmt(from), fmt(to) };
    }

    public String[] enterReviewDateRandomRange() {
        LocalDate today = LocalDate.now();
        LocalDate from  = today.minusDays(ThreadLocalRandom.current().nextInt(7,11));
        LocalDate to    = from.plusDays(ThreadLocalRandom.current().nextInt(1,4));
        if (to.isAfter(today)) to = today;

        setReviewDateFrom(from);
        pause(3000);
        setReviewDateTo(to);
        return new String[]{ fmt(from), fmt(to) };
    }

    public void setStatusCompleted() {
        selectReactSelectSingleByLabel("Status", "Completed");
    }

    public void setRecordNumber(String value) {
        WebElement input = driver.findElement(recordNumberInput);
        input.clear();
        input.sendKeys(value);
    }

    public void setAgreementNumbers(String value) {
        WebElement input = driver.findElement(agreementNumbersInp);
        input.clear();
        input.sendKeys(value);
    }

    public void clickSearchButton() {
        WebElement btn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(searchButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        btn.click();
        pause(1000);
    }

    public void clickClearSelections() {
        // optional: dismissAnyOpenReactSelectMenus();
        WebElement btn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(clearSelectionsBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        try { btn.click(); } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        pause(700);
    }

    public void clickSearchLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(searchLink));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);

        try {
            link.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }

        pause(1000);
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

    public void clickExportControlLink() {
        dismissAnyOpenReactSelectMenus(); // <— add this
        WebElement link = driver.findElement(exportControlLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(link));
        link.click();
        pause(1000);
    }
}