package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class AdobeEsignatureReportpage extends BasePage {

    public AdobeEsignatureReportpage(WebDriver driver) {super(driver);
    }






    //*****  Locator   ***************

    // Left sidebar > "Administration" (label button)
    private By administrationDropdownBtn =
            By.xpath("//button[contains(@class,'label') and normalize-space()='Administration']");

    // (optional) the tiny chevron toggle next to Administration
    private By administrationToggleBtn =
            By.xpath("//div[contains(@class,'menu-item-holder')][.//button[normalize-space()='Administration']]//button[contains(@class,'toggle-menu-icon-button')]");

    // Child link: "E-Signature Report"
    private By eSignatureReportLink =
            By.xpath("//a[contains(@href,'/agreements/admin/e-signature-report') or .//span[normalize-space()='E-Signature Report']]");


    private By fromInput = By.id("dateFrom");
    private By toInput   = By.id("dateTo");

    private By reportTypeControl =
            By.xpath("//label[@for='reportType']/following::div[contains(@class,'select-control')][1]");


    private By reportTypeControl01 =
            By.xpath("//label[@for='reportType']/following::div[contains(@class,'select-control')][2]");

    private By reportTypeOption(String text) {
        return By.xpath("(//div[@role='option' or contains(@class,'select__option') or contains(@class,'_option_')][normalize-space()='" + text + "'])[1]");
    }

    private By searchBtn = By.xpath("//form[contains(@class,'base-search-form')]//button[@type='submit' and normalize-space()='Search']");
    private By clearBtn  = By.xpath("//form[contains(@class,'base-search-form')]//button[@type='button' and normalize-space()='Clear']");

    private By downloadBtn = By.xpath("//button[contains(@class,'button') and .//span[contains(@class,'label') and normalize-space()='Download']]");


    //************   Action   **************



    public void clickDownload() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(downloadBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
        pause(600);
    }

    // ===== Helpers =====
    private WebElement waitClickable(By locator, int sec) {
        return new WebDriverWait(driver, java.time.Duration.ofSeconds(sec))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
    private WebElement waitVisible(By locator, int sec) {
        return new WebDriverWait(driver, java.time.Duration.ofSeconds(sec))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    private void jsClick(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }
    private void scrollIntoView(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }
    private static String fmt(LocalDate d) {
        return d.format(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH));
    }
    private void setDate(By input, String value) {
        WebElement el = waitVisible(input, 15);
        scrollIntoView(el);
        try {
            waitClickable(input, 10).click();
            el.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
            el.sendKeys(value);
            el.sendKeys(Keys.TAB);
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].value=arguments[1];" +
                            "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));" +
                            "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
                    el, value);
        }
    }

    // ===== Public actions used by your test =====

    /** Expand left-menu Administration and open E-Signature Report */
    public void openESignatureReport() {
        WebElement admin = waitVisible(administrationDropdownBtn, 15);
        scrollIntoView(admin);
        if (!"true".equalsIgnoreCase(admin.getAttribute("aria-expanded"))) {
            try { admin.click(); } catch (Exception e) { jsClick(admin); }
        }
        WebElement link = waitClickable(eSignatureReportLink, 20);
        scrollIntoView(link);
        try { link.click(); } catch (Exception e) { jsClick(link); }
    }

    /** Step 1 & 2: enter random valid From/To dates and return them for logging */
    public String[] enterRandomDateRange() {
        LocalDate today = LocalDate.now();
        LocalDate from  = today.minusDays(ThreadLocalRandom.current().nextInt(10, 15)); // 10–14 days ago
        LocalDate to    = from.plusDays(ThreadLocalRandom.current().nextInt(1, 6));     // +1–5 days
        if (to.isAfter(today)) to = today;
        pause(3000);

        setDate(fromInput, fmt(from));
        setDate(toInput, fmt(to));
        return new String[] { fmt(from), fmt(to) };
    }

    /** Step 3: select Pending Signature Report from the React-Select */
    public void selectPendingReportType() {
        pause(3000);

        WebElement control = waitClickable(reportTypeControl, 30);
        scrollIntoView(control);
        try { control.click(); } catch (Exception e) { jsClick(control); }

        WebElement option = waitVisible(reportTypeOption("Pending Signature Report"), 15);
        scrollIntoView(option);
        try { option.click(); } catch (Exception e) { jsClick(option); }
    }

    public void selectPendingReportType02() {
        pause(3000);

        WebElement control = waitClickable(reportTypeControl, 30);
        scrollIntoView(control);
        try { control.click(); } catch (Exception e) { jsClick(control); }

        WebElement option = waitVisible(reportTypeOption("Pending Signature Report"), 15);
        scrollIntoView(option);
        try { option.click(); } catch (Exception e) { jsClick(option); }
    }

    /** Step 3: click Search */
    public void clickSearch() {
        pause(3000);

        WebElement btn = waitClickable(searchBtn, 30);
        scrollIntoView(btn);
        try { btn.click(); } catch (Exception e) { jsClick(btn); }
    }

    /** Step 4: click Clear */
    public void clickClear() {
        pause(3000);

        WebElement btn = waitClickable(clearBtn, 30);
        scrollIntoView(btn);
        try { btn.click(); } catch (Exception e) { jsClick(btn); }
    }




    public void expandAdministrationMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement adminBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(administrationDropdownBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", adminBtn);

        // Expand only if collapsed
        String expanded = adminBtn.getAttribute("aria-expanded");
        if (!"true".equalsIgnoreCase(expanded)) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(adminBtn)).click();
            } catch (ElementClickInterceptedException e) {
                // fallback: click the chevron toggle
                try {
                    WebElement toggle = driver.findElement(administrationToggleBtn);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", toggle);
                } catch (NoSuchElementException ignore) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", adminBtn);
                }
            }
        }

        // Wait for children to render (any child under Administration; we use E-Signature Report)
        wait.until(ExpectedConditions.visibilityOfElementLocated(eSignatureReportLink));
        pause(400);
    }

    public void clickESignatureReport() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        expandAdministrationMenu(); // ensure expanded

        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(eSignatureReportLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        try {
            link.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }
        pause(600);
    }








}
