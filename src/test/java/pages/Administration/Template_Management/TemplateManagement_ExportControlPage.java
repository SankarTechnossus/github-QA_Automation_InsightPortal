package pages.Administration.Template_Management;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TemplateManagement_ExportControlPage extends BasePage {

    public TemplateManagement_ExportControlPage(WebDriver driver) {
        super(driver);
    }



    //Locators

    // Parent: Template Management
    private By templateManagementMenu = By.xpath("//a[@class='label' and normalize-space()='Template Management']");

    // Child: Export Control under Template Management
    private By exportControlLink = By.xpath("//a[@class='label' and normalize-space()='Export Control' and contains(@href,'template-management/export-control')]");

    private By addNewTemplateLink = By.xpath("//a[@href='/administration/template-management/new' and normalize-space()='Add new']");

    private By titleInput = By.id("title");

    private By templateManagementMenu01 = By.xpath("//div[@id='left-sidebar']//a[@class='label' and normalize-space()='Template Management']");
    private By exportControlLink01 = By.xpath("//div[@id='left-sidebar']//a[@class='label' and normalize-space()='Export Control' and contains(@href,'template-management/export-control')]");
    // Cancel button under Template Management
    private By cancelButton = By.xpath("//a[normalize-space()='Cancel']");
    // Create button under Template Management
    private By createButton = By.xpath("//button[normalize-space()='Create']");
    // Date Format (react-select) control that owns the input id=configurationPayload.dateFormat
//    private By dateFormatControl = By.xpath(
//            "//div[contains(@class,'select-control')][.//input[@id='configurationPayload.dateFormat']]");

    // Generic option by visible text (react-select renders role='option')
    private By dateFormatOption(String text) {
        return By.xpath("//div[@role='option' and normalize-space()='" + text + "']");
    }

//    // Active (react-select) control that owns the input id=isActive
//    private By activeControl = By.xpath(
//            "//div[contains(@class,'select-control')][.//input[@id='isActive']]");

    // Active option locators
    private By activeOptionYes = By.xpath("//div[@role='option' and normalize-space()='Yes']");
    private By activeOptionNo  = By.xpath("//div[@role='option' and normalize-space()='No']");

    // Save button under Template Management
    private By saveButton = By.xpath("//button[normalize-space()='Save']");
    // Date Format control & input

    // Date Format control (clickable area)
    private By dateFormatControl = By.xpath("//label[normalize-space()='Date Format']/following::div[contains(@class,'select-control')][1]");

    // The real input for React-Select (has aria-expanded / aria-controls)
    private By dateFormatInput = By.id("configurationPayload.dateFormat");

//    // Build an option inside the computed listbox id
//    private By optionInListbox(String listboxId, String text) {
//        return By.xpath("//*[@id='" + listboxId + "']//div[@role='option' and contains(normalize-space(.),'" + text + "')]");
//    }

//    // Global fallback when menu is portaled to <body>
//    private By globalOption(String text) {
//        return By.xpath("//div[@role='listbox']//div[@role='option' and contains(normalize-space(.),'" + text + "')]");
//    }


//    // Active dropdown control (React-Select wrapper)
//    private By activeControl01 = By.xpath("//label[normalize-space()='Active']/following::div[contains(@class,'select-control')][1]");

//    // Input field inside control (has aria-expanded and aria-controls)
//    private By activeInput = By.id("isActive");

    // Option inside the dynamic listbox
    private By activeOptionNoInListbox(String listboxId) {
        return By.xpath("//*[@id='" + listboxId + "']//div[@role='option' and normalize-space()='No']");
    }

//    // Fallback when portal renders to <body>
//    private By activeOptionNoGlobal = By.xpath("//div[@role='listbox']//div[@role='option' and normalize-space()='No']");

    // Save button (primary action)
    private By saveButton01 = By.xpath("//button[@type='button' and contains(@class,'button') and contains(@class,'-primary') and normalize-space(text())='Save']");



    // Active dropdown wrapper & input
    private By activeControl = By.xpath("//label[normalize-space()='Active']/following::div[contains(@class,'select-control')][1]");
    private By activeInput   = By.id("isActive");

    // Option inside computed listbox id (e.g., react-select-3-listbox)
    private By optionInListbox(String listboxId, String text) {
        return By.xpath("//*[@id='" + listboxId + "']//div[@role='option' and normalize-space()='" + text + "']");
    }
    // Global fallback when menu is portaled to <body>
    private By globalOption(String text) {
        return By.xpath("//div[@role='listbox']//div[@role='option' and normalize-space()='" + text + "']");
    }

    // Readback of selected single value (“Yes”/“No”)
    private By activeSingleValue = By.xpath("//label[normalize-space()='Active']/following::div[contains(@class,'select-control')][1]//div[contains(@class,'singleValue')]");


    private By activeValueTxt = By.xpath("//label[normalize-space()='Active']/following::div[contains(@class,'select-control')][1]//div[contains(@class,'singleValue')]");

    // Cancel button
    private By cancelButtonnew = By.xpath("//a[contains(@class,'_link_') and normalize-space(text())='Cancel']");


    // Scrollable grid container
    private By gridScroller = By.cssSelector(".item-grid-wrapper.-scrollable, .item-grid-wrapper");

    // Exact-match link by title
    private By linkByExactTitle(String title) {
        return By.xpath("//table[contains(@class,'item-grid')]//a[normalize-space(.)='" + title + "']");
    }

    // Fallback: contains() in case UI adds/omits spaces
    private By linkByContainsTitle(String title) {
        return By.xpath("//table[contains(@class,'item-grid')]//a[contains(normalize-space(.),'" + title + "')]");
    }



    //Action




    public void clickTemplateByTitle(String titleText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ensure the grid is in view (scroll container if present)
        try {
            WebElement scroller = driver.findElement(gridScroller);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = 0;", scroller);
        } catch (NoSuchElementException ignore) {}

        // Try exact match first
        By exact = linkByExactTitle(titleText);
        By fuzzy = linkByContainsTitle(titleText);

        WebElement link;
        try {
            link = wait.until(ExpectedConditions.visibilityOfElementLocated(exact));
        } catch (TimeoutException te) {
            link = wait.until(ExpectedConditions.visibilityOfElementLocated(fuzzy));
        }

        // Scroll that link into view (works even inside the grid scroller)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);

        // Click (with JS fallback)
        try { link.click(); }
        catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link); }

        // Wait for edit page to load (URL contains /edit/ and the title appears on the form)
        wait.until(ExpectedConditions.urlContains("/administration/template-management/edit/"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[@for='title']/following::input[@id='title' and contains(@value,'" + titleText + "')]")
        ));

        pause(800);
    }



    public void clickCancelButtonnew() {
        WebElement cancel = driver.findElement(cancelButtonnew);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", cancel);

        try {
            cancel.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cancel);
        }

        pause(2000);
    }


    public void setActive(String expected) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement control = driver.findElement(activeControl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        try { control.click(); }
        catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control); }

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(activeInput));
        wait.until(d -> "true".equals(input.getAttribute("aria-expanded")));   // menu open

        String listboxId = input.getAttribute("aria-controls");
        boolean selected = false;

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(optionInListbox(listboxId, expected))).click();
            selected = true;
        } catch (TimeoutException ignore) {}

        if (!selected) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(globalOption(expected))).click();
                selected = true;
            } catch (TimeoutException ignore) {}
        }

        if (!selected) {
            input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
            input.sendKeys(expected);
            pause(200);
            input.sendKeys(Keys.ENTER);
        }

        // verify selected value
        String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(activeValueTxt)).getText().trim();
        if (!expected.equals(actual)) {
            throw new AssertionError("Active not set. Expected: " + expected + ", Actual: " + actual);
        }
        pause(800);
    }


    public void clickSaveButton01() {
        WebElement save = driver.findElement(saveButton01);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", save);

        // Try normal click first, fallback to JS click if intercepted
        try {
            save.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", save);
        }

        pause(2000);
    }


    public void setActiveToNo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open dropdown (scroll + click, with JS fallback)
        WebElement control = driver.findElement(activeControl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        try { control.click(); }
        catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control); }

        // Wait until input is visible and menu is open
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(activeInput));
        wait.until(d -> "true".equals(input.getAttribute("aria-expanded")));

        String listboxId = input.getAttribute("aria-controls"); // e.g., react-select-3-listbox

        boolean selected = false;

        // Path 1: scoped listbox
        try {
            WebElement opt = wait.until(ExpectedConditions.visibilityOfElementLocated(optionInListbox(listboxId, "No")));
            opt.click();
            selected = true;
        } catch (TimeoutException ignore) { }

        // Path 2: global portal
        if (!selected) {
            try {
                WebElement opt = wait.until(ExpectedConditions.visibilityOfElementLocated(globalOption("No")));
                opt.click();
                selected = true;
            } catch (TimeoutException ignore) { }
        }

        // Path 3: type-ahead + ENTER
        if (!selected) {
            input.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
            input.sendKeys("No");
            pause(200); // tiny debounce, matches your pattern
            input.sendKeys(Keys.ENTER);
        }

        // Verify selected value shows “No”
        WebElement value = wait.until(ExpectedConditions.visibilityOfElementLocated(activeSingleValue));
        if (!"No".equals(value.getText().trim())) {
            throw new AssertionError("Active dropdown did not select 'No'. Current value: " + value.getText());
        }

        pause(800);
    }



    public void selectDateFormat(String visibleText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1) Open the menu
        WebElement control = driver.findElement(dateFormatControl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        try { control.click(); }
        catch (Exception e) { ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control); }

        // 2) Wait until menu is actually open: aria-expanded=true
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(dateFormatInput));
        wait.until(d -> "true".equals(input.getAttribute("aria-expanded")));

        // 3) Prefer a scoped lookup using aria-controls -> listbox id (e.g., react-select-2-listbox)
        String listboxId = input.getAttribute("aria-controls");
        try {
            WebElement option = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(optionInListbox(listboxId, visibleText)));
            option.click();
        } catch (TimeoutException t1) {
            // 4) Fallback A: global listbox (portal)
            try {
                WebElement option = wait.until(ExpectedConditions
                        .visibilityOfElementLocated(globalOption(visibleText)));
                option.click();
            } catch (TimeoutException t2) {
                // 5) Fallback B: type-ahead + ENTER (most reliable for RS)
                input.clear();
                input.sendKeys(visibleText);
                pause(300);          // tiny debounce per your pattern
                input.sendKeys(Keys.ENTER);
            }
        }

        pause(1000); // settle
    }






    public void clickSaveButton() {
        WebElement save = driver.findElement(saveButton);

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", save);

        // Wait until clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(save));

        // Click
        save.click();

        // Controlled pause
        pause(1000);
    }



//    public void selectDateFormat(String formatText) {
//        WebElement control = driver.findElement(dateFormatControl);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
//        control.click();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement option = wait.until(ExpectedConditions
//                .visibilityOfElementLocated(dateFormatOption(formatText)));
//        option.click();
//
//        pause(1000);
//    }

//    public void setActiveToNo() {
//        WebElement control = driver.findElement(activeControl);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
//        control.click();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(activeOptionNo));
//        option.click();
//
//        pause(1000);
//    }


    public void setActiveToYES() {
        WebElement control = driver.findElement(activeControl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        control.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(activeOptionYes));
        option.click();

        pause(1000);
    }





    public void clickCreateButton() {
        WebElement create = driver.findElement(createButton);

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", create);

        // Wait until clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(create));

        // Click
        create.click();

        // Controlled pause
        pause(1000);
    }



    public void clickCancelButton() {
        WebElement cancel = driver.findElement(cancelButton);

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cancel);

        // Wait until clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cancel));

        // Click
        cancel.click();

        // Controlled pause
        pause(1000);
    }

    // Locator for the hidden file input
    private By fileUploadInput = By.xpath("//input[@type='file']");

    public void uploadAgreementFile(String filePath) {
        WebElement uploadElement = driver.findElement(fileUploadInput);
        // Directly send file path (bypasses drag/drop UI)
        uploadElement.sendKeys(filePath);
    }


    public void clickTemplateManagementExportControl01() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement templateMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(templateManagementMenu01));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", templateMenu);

        try {
            templateMenu.click(); // parent often navigates directly
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", templateMenu);
        }

        // EITHER: we already navigated to the page, OR we need to click the child link
        boolean onTarget =
                wait.until(d -> d.getCurrentUrl().contains("/administration/template-management/export-control")
                        || !driver.findElements(exportControlLink01).isEmpty());

        if (!driver.getCurrentUrl().contains("/administration/template-management/export-control")) {
            // Sidebar path: click the child
            WebElement child = wait.until(ExpectedConditions.elementToBeClickable(exportControlLink01));
            try {
                child.click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", child);
            }
        }

        pause(1000);
    }


    public String enterUniqueTitle() {
        // Generate unique name starting with "Test"
        String uniqueTitle = "Test" + System.currentTimeMillis();

        WebElement input = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(titleInput));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", input);
        input.clear();
        input.sendKeys(uniqueTitle);

        pause(500);
        return uniqueTitle; // return value if you want to use it in test for validation
    }


    public void clickAddNewTemplate() {
        WebElement addNew = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(addNewTemplateLink));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", addNew);
        addNew.click();

        pause(1000);
    }


    public void clickTemplateManagementExportControl() {
        // Scroll Template Management into view
        WebElement templateMenu = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(templateManagementMenu));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", templateMenu);

        // Click Template Management to expand (if not already expanded)
        templateMenu.click();
        pause(1000);

        // Wait for Export Control link to appear and click
        WebElement exportCtrl = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(exportControlLink));
        exportCtrl.click();

        pause(1000);
    }








}