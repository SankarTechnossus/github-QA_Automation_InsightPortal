package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Export_control_menu_flow_of_export_control_page extends BasePage {

    public Export_control_menu_flow_of_export_control_page(WebDriver driver) {
        super(driver);
    }



    // root div for a React-Select control for a given <label>
    private By controlRoot(String label) {
        return By.xpath("//label[normalize-space()='" + label + "']" +
                "/following::div[contains(@class,'select-control')][1]");
    }

    //helper
    // ---------- Generic React-Select (single) by <label> ----------
    private void selectReactSelectSingleByLabel(String label, String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement root = wait.until(
                ExpectedConditions.elementToBeClickable(controlRoot(label)));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", root);
        root.click();

        WebElement inputEl = root.findElement(By.xpath(".//input[@role='combobox']"));

        String listboxId = inputEl.getAttribute("aria-controls");
        if (listboxId == null || listboxId.isEmpty()) {
            String inputId = inputEl.getAttribute("id");
            if (inputId != null) {
                listboxId = inputId.replace("input", "listbox");
            }
        }
        By listboxBy = By.id(listboxId);

        // pre-filter text & wait for listbox
        inputEl.clear();
        inputEl.sendKeys(optionText);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(listboxBy));

        By optionBy = By.xpath("//*[@id='" + listboxId + "']//*[normalize-space()='" + optionText + "']");
        WebElement optionEl = wait.until(ExpectedConditions.elementToBeClickable(optionBy));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", optionEl);
        optionEl.click();

        // close dropdown
        try { inputEl.sendKeys(Keys.ESCAPE); } catch (Exception ignored) {}
        try { driver.findElement(By.tagName("body")).click(); } catch (Exception ignored) {}

        // assert selected value
        By selectedValueBy = By.xpath(
                "//label[normalize-space()='" + label + "']/following::div[contains(@class,'select-control')][1]" +
                        "//*[contains(@class,'singleValue') or contains(@class,'valueContainer')]"
        );
        wait.until(d -> d.findElement(selectedValueBy).getText().trim().equals(optionText));
    }



    // locators


    private By createdFromInput = By.xpath(
            "//label[normalize-space()='Created On']" +
                    "/following::*[normalize-space()='From:'][1]/following::input[@type='text'][1]"
    );
    private By createdToInput = By.xpath(
            "//label[normalize-space()='Created On']" +
                    "/following::*[normalize-space()='To:'][1]/following::input[@type='text'][1]"
    );

    // Review date
    private By reviewFromInput = By.xpath(
            "//label[normalize-space()='Review date']" +
                    "/following::*[normalize-space()='From:'][1]/following::input[@type='text'][1]"
    );
    private By reviewToInput = By.xpath(
            "//label[normalize-space()='Review date']" +
                    "/following::*[normalize-space()='To:'][1]/following::input[@type='text'][1]"
    );

    // First row Record Number link
    private By firstRecordNumberLink = By.xpath(
            "//table[contains(@class,'item-grid')]//tbody/tr[1]//td[@data-column='_exportControlNumber']//a"
    );


    public void clickFirstRecordNumberLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement recordLink = wait.until(
                ExpectedConditions.elementToBeClickable(firstRecordNumberLink)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", recordLink);

        recordLink.click();

        pause(1500);  // your pattern
    }



    private final DateTimeFormatter DF = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private String fmt(LocalDate d) { return d.format(DF); }

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


    public void setCreatedOnFrom(LocalDate from)  { setDate(createdFromInput, fmt(from)); }
    public void setCreatedOnTo(LocalDate to)      { setDate(createdToInput,   fmt(to)); }

    public void setReviewDateFrom(LocalDate from) { setDate(reviewFromInput,  fmt(from)); }
    public void setReviewDateTo(LocalDate to)     { setDate(reviewToInput,    fmt(to)); }






//    /** Created On: enter random From/To (10–14 days ago to +1–5 days, capped at today) */
//    public String[] enterCreatedOnRandomRange() {
//        LocalDate today = LocalDate.now();
//        LocalDate from  = today.minusDays(ThreadLocalRandom.current().nextInt(10, 15));
//        LocalDate to    = from.plusDays(ThreadLocalRandom.current().nextInt(1, 6));
//        if (to.isAfter(today)) to = today;
//
//        setDate(createdFromInput, fmt(from));
//        pause(300); // small settle
//        setDate(createdToInput, fmt(to));
//        return new String[] { fmt(from), fmt(to) };
//    }
//
//    /** Review date: enter random From/To (7–10 days ago to +1–3 days, capped at today) */
//    public String[] enterReviewDateRandomRange() {
//        LocalDate today = LocalDate.now();
//        LocalDate from  = today.minusDays(ThreadLocalRandom.current().nextInt(7, 11));
//        LocalDate to    = from.plusDays(ThreadLocalRandom.current().nextInt(1, 4));
//        if (to.isAfter(today)) to = today;
//
//        setDate(reviewFromInput, fmt(from));
//        pause(300);
//        setDate(reviewToInput, fmt(to));
//        return new String[] { fmt(from), fmt(to) };
//    }

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






    //Locators

    private By exportControlLink = By.xpath("//a[@href='/export-control' and contains(@class,'module-link')]");
    private By searchLink = By.xpath("//a[@href='/export-control/search' and contains(@class,'label')]");
    // React-Select controls by label (generic roots)


    // Text inputs
    private By recordNumberInput   = By.xpath("//label[normalize-space()='Record Number']/following::input[@type='text'][1]");
    private By agreementNumbersInp = By.xpath("//label[normalize-space()='Agreement Numbers']/following::input[@type='text'][1]");

    // Buttons
    private By searchButton        = By.xpath("//button[normalize-space()='Search']");
    private By clearSelectionsBtn  = By.xpath("//button[normalize-space()='Clear Selections']");




    //Actions

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
