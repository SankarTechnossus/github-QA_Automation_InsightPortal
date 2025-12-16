package pages.Adobe;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BrowserUtility;

import java.time.Duration;
import java.util.Map;

public class DeliverablesPage extends BasePage {


    public DeliverablesPage(WebDriver driver) {super(driver);
    }

    // ******** Locators *********

    // Button: Delete Selected
    By deleteSelectedBtn = By.xpath("//button[normalize-space(.)='Delete Selected']");
    By signatureModalTitle = By.xpath("//h1[@id='modal-title-view58' and normalize-space()='Signature Preview']");
    By signReasonDropdown  = By.id("signReasonFormControlDropdown");
    By addDeliverableOverlay = By.cssSelector("div.add-new-deliverable-overlay");
    By deliverableNameInput = By.xpath("//div[contains(@class,'add-new-deliverable-overlay')]//label[normalize-space(.)='Deliverable Name']/following::input[@type='text'][1]");

    // Buttons
    By cancelBtn = By.xpath("//div[contains(@class,'add-new-deliverable-overlay')]//button[normalize-space(.)='Cancel']");
    By topSearchBtn = By.xpath("//button[normalize-space(.)='Add New Deliverable']/preceding::button[normalize-space(.)='Search'][1]");
    By submitBtn = By.xpath("//div[contains(@class,'add-new-deliverable-overlay')]//button[@type='submit' and normalize-space(.)='Submit']");
    By deliverablesSearchInput = By.cssSelector("input[placeholder^='Search by deliverables']");

    // Top bar "Clear Selections" (left of "Add New Deliverable")
    By clearSelectionsBtn = By.xpath("//button[normalize-space(.)='Clear Selections']");
    By cloneBtn = By.xpath("//button[normalize-space(.)='Clone' and contains(@class,'-small')]");
    By firstCheckbox = By.xpath("(//table[@class='item-grid -sticky']//input[@type='checkbox'])[2]");
    By downloadSelectedBtn = By.xpath("//button[normalize-space(.)='Download Selected']");

    // Reminders dropdown (open it by clicking the control/arrow next to the label)
    By remindersControl = By.xpath("//label[@for='reminderFrequency']/following::div[contains(@class,'select-control')][1]");

    // First recipient row's delete button (next to the Email input)
    By firstRecipientDeleteBtn = By.xpath("//div[contains(@class,'email-row')][1]//button[@aria-label='Delete']");

    // Use data-testid or id (stable across builds)
    By sendButton01 = By.cssSelector("button[data-testid='sendButton']");

    // React modal overlay and the Send button inside it
    By modalOverlay = By.cssSelector("div.ReactModal__Overlay.ReactModal__Overlay--after-open");
    By sendButton   = By.xpath("//div[contains(@class,'ReactModal__Overlay--after-open')]//button[.//span[normalize-space()='Send'] and not(@disabled)]");
    // IFRAME + editor readiness
    By previewIframe       = By.xpath("//iframe[@class='sign-in-iframe']");
    By signatureTool       = By.xpath("//div[@data-testid='menu-item-signature-form-field']//button//span");
    By dropTargetOverlay   = By.xpath("//div[@data-testid='overlay-drop-target']");
    By circleLoader        = By.cssSelector("[class*='CircleLoader'], [role='progressbar']"); // generic loader
    By modalContentWrapper = By.cssSelector("div.modal-content-wrapper");

    // Send (outside iframe)
    By sendButtonInModal   = By.xpath("//div[contains(@class,'ReactModal__Overlay--after-open')]//button[.//span[normalize-space()='Send'] and not(@disabled)]");

    // Always target the latest modal, then the concrete button id/testid
    By activeModalOverlay = By.xpath("(//div[contains(@class,'ReactModal__Overlay--after-open')])[last()]");
    By sendButtonnew = By.cssSelector("div.modal-content-wrapper #sendButton"); // or: By.cssSelector("button[data-testid='footer-button-send-button']");

    // Overlay & Send button (scope to the latest modal)
    By activeOverlay = By.xpath("(//div[contains(@class,'ReactModal__Overlay--after-open')])[last()]");
    By modalContent  = By.cssSelector("div.modal-content-wrapper");
    By sendButtonnew01    = By.cssSelector("button#sendButton, button[data-testid='footer-button-send-button']");
    By deleteIcon = By.xpath("//div[@aria-label='Delete' and contains(@class,'nav-icon')]");
    By sentForSignaturesButton = By.xpath("//button[@type='button' and @aria-label='Status' and contains(@class,'esign-module__pending')]");
    By editRecipientButton = By.xpath("//td[@data-column='options']//button[@aria-label='Edit Recipient']");
    By cancelButtonemail = By.xpath("//button[@type='button' and @aria-label='Cancel' and normalize-space()='Cancel']");
    By saveButtonemail = By.xpath("//button[@type='button' and @aria-label='Save' and normalize-space()='Save']");
    By recipientEmailInput = By.xpath("//input[@id='email' and @type='text']");
    By pendingRecipientEditButton = By.xpath("//tr[td[@data-column='status' and @data-value='Pending']]//button[@aria-label='Edit Recipient' and not(@disabled)]");
    By closeModalButton = By.xpath("//button[@type='button' and @aria-label='Close modal']");
    By unsignedDocDownloadButton = By.xpath("//div[contains(@class,'esign-module__downloadLink')]//button[contains(text(),'Download') and not(@disabled)]");
    By eSignOptionsMenuButton = By.xpath("//table[.//th//div[normalize-space()='Options']]//tbody//tr[1]//td[last()]//button[not(@disabled)]");
    By deleteIconemail = By.xpath("//div[@aria-label='Delete' and contains(@class,'nav-icon')]");
    By cancelButtonfrom = By.xpath("//button[@type='button' and @aria-label='Cancel' and normalize-space()='Cancel']");
    By closeButtonfrom = By.xpath("//button[@type='button' and @aria-label='Close' and normalize-space()='Close']");

    // --- Locators (keep only one copy) ---
    By deliverableCategoryControl = By.xpath("//form[contains(@class,'addNewDeliverable')]" +
                    "//label[normalize-space()='Deliverable Category']/following::div[contains(@class,'select-control')][1]"
    );

    By deliverableCategoryInput = By.xpath("//form[contains(@class,'addNewDeliverable')]" +
                    "//label[normalize-space()='Deliverable Category']/following::input[contains(@id,'react-select') and contains(@id,'-input')]"
    );

    // "any option" – used for ARROW_DOWN/ENTER fallback check
    By anyReactSelectOption = By.xpath("//div[@role='option'] | //div[contains(@class,'option')]"
    );

    // ************************** Actions ***************************************************************************

    // Category accordion (scope by title text)
    public By categoryBar(String category) {
        return By.xpath("//div[contains(@class,'collapsible-bar')][.//span[contains(@class,'title-text') and normalize-space()='"+category+"']]");
    }

    public By categoryToggleBtn(String category) {
        return By.xpath("//div[contains(@class,'collapsible-bar')][.//span[contains(@class,'title-text') and normalize-space()='"+category+"']]//button[contains(@class,'content-toggler-button')]");
    }

    public void confirmDeleteOK() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for JS alert to appear
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();

        // Optional capture of alert text if needed
        String alertMsg = alert.getText();
        System.out.println("Alert Message: " + alertMsg);

        alert.accept(); // Click OK
        pause(800);
    }

    public void clickDeleteSelected01() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(deleteSelectedBtn));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        try {
            btn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }

        pause(600); // small stability wait inside page
    }

    public void scrollUpInPreviewModal() {
        // Locate the scrollable container
        WebElement modalContentWrapper = new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("div.modal-content-wrapper")));

        // Use JavaScript to scroll to the top
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = 0;", modalContentWrapper);

        pause(2000); // optional pause to let UI stabilize
    }

    public void clickCloseButton() {
        WebElement closeBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(closeButtonfrom));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", closeBtn);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(closeBtn));

        closeBtn.click();
        pause(2000);
    }

    public void clickCancelButtonfrom() {
        WebElement cancelBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(cancelButtonfrom));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cancelBtn);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(cancelBtn));

        cancelBtn.click();
        pause(2000);
    }

    public void clickDeleteIconemail() {
        WebElement deleteEl = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(deleteIconemail));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", deleteEl);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(deleteEl));

        deleteEl.click();
        pause(2000);
    }

    public void clickESignOptionsMenu() {
        WebElement menuBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(eSignOptionsMenuButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", menuBtn);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(menuBtn));

        menuBtn.click();
        pause(1500);
    }

    public void clickUnsignedDocDownloadButton() {
        WebElement downloadBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(unsignedDocDownloadButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", downloadBtn);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(downloadBtn));

        downloadBtn.click();
        pause(2000);
    }

    public void clickCloseModalButton() {
        WebElement closeBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(closeModalButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", closeBtn);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(closeBtn));

        closeBtn.click();
        pause(2000);
    }

    public void clickPendingRecipientEditButton() {
        WebElement editBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(pendingRecipientEditButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", editBtn);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(editBtn));

        editBtn.click();
        pause(2000);
    }

    public void setRecipientEmail(String email) {
        WebElement emailInput = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(recipientEmailInput));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", emailInput);

        emailInput.clear();
        emailInput.sendKeys(email);

        pause(1000);
    }

    public void clickSaveButton() {
        WebElement saveBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(saveButtonemail));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", saveBtn);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(saveBtn));

        saveBtn.click();
        pause(2000);
    }

    public void clickCancelButton() {
        WebElement cancelBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(cancelButtonemail));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", cancelBtn);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(cancelBtn));

        cancelBtn.click();
        pause(2000);
    }

    public void clickEditRecipientButton() {
        WebElement editButton = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(editRecipientButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", editButton);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(editButton));

        editButton.click();
        pause(2000);
    }

    public void clickSentForSignaturesButton() {
        WebElement sentButton = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(sentForSignaturesButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", sentButton);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(sentButton));

        sentButton.click();
        pause(2000);
    }

    public void clickDeleteIcon() {
        WebElement deleteElement = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(deleteIcon));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", deleteElement);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(deleteElement));

        deleteElement.click();
        pause(2000);
    }

    public void clickOnSendButton(){
        BrowserUtility.click(driver,By.xpath("//button[@id='sendButton']"),"Send Button");
    }

    public void clickSendnew01() {
        // Send lives OUTSIDE the iframe
//        driver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Ensure the latest overlay & its content are visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(activeOverlay));
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalContent));

        // Bring footer into view (your helper)
        scrollToBottomOfModal(By.cssSelector("div.modal-content-wrapper"), 10);

        // Wait until the button is truly click-ready
        wait.until(d -> {
            try {
                WebElement btn = d.findElement(sendButtonnew01);               // re-find each poll (handles stale)
                if (!btn.isDisplayed() || !btn.isEnabled()) return false;
                String aria = btn.getAttribute("aria-disabled");
                if ("true".equalsIgnoreCase(aria)) return false;

                // Center it and ensure nothing covers it
                ((JavascriptExecutor) d).executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
                Map<?,?> pt = (Map<?,?>) ((JavascriptExecutor) d).executeScript(
                        "const r=arguments[0].getBoundingClientRect();" +
                                "return {x: Math.floor(r.left + r.width/2), y: Math.floor(r.top + r.height/2)};", btn);
                WebElement top = (WebElement) ((JavascriptExecutor) d).executeScript(
                        "return document.elementFromPoint(arguments[0], arguments[1]);", pt.get("x"), pt.get("y"));
                return top != null && (top.equals(btn) ||
                        (Boolean) ((JavascriptExecutor) d).executeScript(
                                "return arguments[0].closest('button')===arguments[1];", top, btn));
            } catch (StaleElementReferenceException | NoSuchElementException ignore) {
                return false; // retry
            }
        });

        // Click with safe fallback
        try {
            driver.findElement(sendButtonnew01).click();
        } catch (Exception e) {
            WebElement btn = driver.findElement(sendButtonnew01);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }

        pause(2000);
    }

    public void clickSendnew() {
        // 1) Ensure default content (Send is OUTSIDE iframe)
        driver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // 2) Wait for the active modal overlay and its content
        wait.until(ExpectedConditions.visibilityOfElementLocated(activeModalOverlay));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.modal-content-wrapper")));

        // 3) Bring footer into view (your existing helper)
        scrollToBottomOfModal(By.cssSelector("div.modal-content-wrapper"), 10);

        // 4) Wait for Send to be visible (not using elementToBeClickable which is flaky with overlays)
        WebElement send = wait.until(ExpectedConditions.visibilityOfElementLocated(sendButton));

        // 5) Custom "click-ready" wait: ensure no overlay is intercepting the center of the button
        wait.until(driver -> {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", send);
                Long[] xy = (Long[]) ((JavascriptExecutor) driver).executeScript(
                        "const r=arguments[0].getBoundingClientRect();" +
                                "return [Math.floor(r.left + r.width/2), Math.floor(r.top + r.height/2)];", send);
                Object el = ((JavascriptExecutor) driver).executeScript(
                        "return document.elementFromPoint(arguments[0], arguments[1]);",
                        xy[0], xy[1]);
                return el instanceof WebElement && (send.equals(el) || ((WebElement) el).isDisplayed());
            } catch (Exception e) { return false; }
        });

        // 6) Click with safe fallback
        try {
            send.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", send);
        }

        pause(2000);
    }

    public void clickSend() {
        // Ensure we are not in the preview iframe
        driver.switchTo().defaultContent();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait for modal overlay to appear
        WebElement overlay = wait.until(ExpectedConditions.visibilityOfElementLocated(modalOverlay));

        // Scroll modal content so footer buttons are in view (your existing util)
        By modalContent = By.cssSelector("div.modal-content-wrapper");
        scrollToBottomOfModal(modalContent, 20);

        // Wait until Send is clickable
        WebElement send = wait.until(ExpectedConditions.elementToBeClickable(sendButton));

        // Smooth scroll to center and click with fallback
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", send);
        try {
            send.click();
        } catch (Exception e) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", send);
            } catch (Exception ignored) {
                // Final fallback
                new Actions(driver).moveToElement(send).click().perform();
            }
        }

        pause(5000);
    }

    public void clickSendButton010() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement sendBtn = wait.until(ExpectedConditions.elementToBeClickable(sendButton01));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", sendBtn);

        // Try normal click
        try {
            sendBtn.click();
        } catch (Exception e) {
            // JS fallback if normal click fails
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sendBtn);
        }

        pause(5000); // controlled pause
    }

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

    public By categoryContainerBy(String category) {
        return By.xpath(
                "//span[contains(@class,'title-text')][contains(normalize-space(),'" + category + "')]"
                        + "/ancestor::div[contains(@class,'collapsible-bar')]"
        );
    }

    public By deliverableLinkBy(String category, String deliverableName) {
        return By.xpath(
                "//span[contains(@class,'title-text')][contains(normalize-space(),'" + category + "')]"
                        + "/ancestor::div[contains(@class,'collapsible-bar')]"
                        + "//tbody//a[.//span[normalize-space()='" + deliverableName + "']]"
        );
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

        // open control
        WebElement control = wait.until(ExpectedConditions.elementToBeClickable(deliverableCategoryControl));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);

        // focus input & type filter
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(deliverableCategoryInput));
        input.clear();
        input.sendKeys(categoryText);

        // ---- Strategy A: click exact option if it appears
        try {
            WebElement exact = wait.until(ExpectedConditions.visibilityOfElementLocated(reactSelectOptionExact(categoryText)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", exact);
            pause(300);
            return;
        } catch (TimeoutException ignore) {
            // fall through
        }

        try {
            // ensure menu is open (some skins close after typing)
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);
            // wait briefly for any option to appear; if not, still try keys
            try { wait.until(ExpectedConditions.presenceOfElementLocated(anyReactSelectOption)); }
            catch (TimeoutException ignored) { /* menu may not expose option DOM; keys still work */ }

            input.sendKeys(Keys.ARROW_DOWN);
            input.sendKeys(Keys.ENTER);
            pause(300);
            return;
        } catch (Exception e) {
            // ---- Strategy C: one retry from the top (handles flicker)
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);
            input.clear();
            input.sendKeys(categoryText);
            WebElement exact2 = wait.until(ExpectedConditions.visibilityOfElementLocated(reactSelectOptionExact(categoryText)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", exact2);
            pause(300);
        }
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

    public By categoryToggleBy(String category) {
        return By.xpath(
                "//span[contains(@class,'title-text')][contains(normalize-space(),'" + category + "')]"
                        + "/ancestor::div[contains(@class,'toggleable-title')]"
                        + "//button[contains(@class,'content-toggler-button')]"
        );
    }

    public By deliverableLinkExact(String category, String deliverableName) {
        return By.xpath(
                "//span[contains(@class,'title-text')][contains(normalize-space(),'" + category + "')]"
                        + "/ancestor::div[contains(@class,'collapsible-bar')]"
                        + "//td[@data-column='col_204' and @data-value='" + deliverableName + "']//a"
        );
    }

    public void clickDeliverableExact(String category, String deliverableName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 1: Expand category if collapsed
        By toggleBy = categoryToggleBy(category);
        WebElement toggle = wait.until(ExpectedConditions.visibilityOfElementLocated(toggleBy));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", toggle);
        String expanded = toggle.getAttribute("aria-expanded");
        if (expanded == null || expanded.equalsIgnoreCase("false")) {
            toggle.click();
            wait.until(ExpectedConditions.attributeToBe(toggle, "aria-expanded", "true"));
        }

        // Step 2: Click deliverable link by data-value
        By linkBy = deliverableLinkExact(category, deliverableName);
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(linkBy));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
        try {
            link.click();
        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            link = wait.until(ExpectedConditions.visibilityOfElementLocated(linkBy));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }

        pause(600);
    }

    // OPTION match that works whether the menu is portaled or not (no reliance on role = listbox)
    public By reactSelectOptionExact(String text) {
        return By.xpath(
                // look anywhere on the page for a react-select option with exact text
                "//div[@role='option' and normalize-space()='" + text + "']" +
                        " | //div[contains(@class,'option') and normalize-space()='" + text + "']"
        );
    }

    // Anchored to the Deliverable Name column (col_196); matches the exact visible text inside the <span>
    public By deliverableLinkByName(String name) {
        return By.xpath("//table[contains(@class,'item-grid')]//td[@data-column='col_196']" +
                "//a[.//span[normalize-space()='" + name + "']]");
    }

    // Option matcher (works for their various option class names / role)
    public By reminderOption(String text) {
        return By.xpath(
                "(//div[@role='option' and normalize-space()='" + text + "']" +
                        " | //div[contains(@class,'select-option') and normalize-space()='" + text + "']" +
                        " | //div[contains(@class,'select__option') and normalize-space()='" + text + "']" +
                        " | //div[contains(@class,'_option_') and normalize-space()='" + text + "'])[1]"
        );
    }

    // Row checkbox by deliverable name (choose last match => newest)
    public By rowCheckboxByName(String name) {return By.xpath("(//table[contains(@class,'item-grid')]//tbody//tr[.//span[normalize-space(.)='"+ name + "']]//input[@type='checkbox'])[last()]");}
}