package tests.AdobeEsign.Sprint1;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.testng.annotations.Listeners;
import utils.DriverManager;
import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
public class Esign_Agreement_PDF_Attachment_Positive_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;

    @BeforeMethod
    public void setupBrowser() {
//         User will setup and configure the Chrome WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // User will launch a new Chrome browser instance
        driver = new ChromeDriver();

        // Set driver to DriverManager for global access
        DriverManager.setDriver(driver);

        // User will maximize the browser window to ensure all UI elements are visible
        driver.manage().window().maximize();

        // User will initialize explicit wait with a timeout of 10 seconds for dynamic element handling
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        basePage = new BasePage (driver);
    }


    @Test
    public void createFormFlow() {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            // User will open the login page of the Insight Portal application
            driver.get("https://sacramento-insight4.partners.org/");
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");
            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);


            // User will enter the username into the username input field
            WebElement username = basePage.waitForElement(By.xpath("//input[@id='input28']"));
            username.sendKeys("SV1179");
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Entered username");


            // User will click the 'Next' button to proceed to the password entry screen
            WebElement nextBtn = basePage.waitForElement(By.xpath("//input[@value='Next']"));
            nextBtn.click();
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Clicked Next");


            // User will input the user's password into the password field
            WebElement password = basePage.waitForElement(By.xpath("//input[@name='credentials.passcode']"));
            password.sendKeys("Devinivetha@1930");
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Entered password");


            // User will click the 'Verify' button to authenticate the user
            WebElement verifyBtn = basePage.waitForElement(By.xpath("//input[@value='Verify']"));
            verifyBtn.click();
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Clicked Verify");


            // Wait and click the 'Agreements' link from the sidebar
            WebElement agreementsLink = basePage.waitForElement50(By.xpath("//a[contains(text(),'Agreements')]"));
            Assert.assertTrue(agreementsLink.isDisplayed(), "Agreements link is not visible after login");
            agreementsLink.click();
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Clicked 'Agreements' link from sidebar");

            // User will enter the agreement number into the corresponding input field
            WebElement agreementNumberInput = basePage.waitForElement50(By.xpath("//input[@id='agreementNumber']"));
            Assert.assertTrue(agreementNumberInput.isDisplayed(), "AgreementNumberInput is not visible after login");
            agreementNumberInput.sendKeys("2025A012368");
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Entered Agreement Number: 2025A012368");

            // User will click the 'Search' button to search for the agreement
            WebElement searchButton = basePage.waitForElement(By.xpath("//button[text()='Search']"));
            searchButton.click();
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Clicked Search button");



            // User will click on the agreement number span to open agreement details
            WebElement agreementSpan = basePage.waitForElement(By.xpath("//span[text()='2025A012368']"));
            agreementSpan.click();
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Clicked Search button");


            basePage.scrollAndJsClick(By.xpath("//a[@href='/agreements/2025A012368/latest/deliverables']//span[text()='Deliverables']"), 30);
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Clicked 'Deliverables' tab using JavaScript after scroll");


            WebElement toggleButton = basePage.waitForElement(By.xpath("//button[@type='button' and @aria-label='Expand/collapse' and contains(@class, 'toggle-button')]"));
            toggleButton.click();
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Clicked the expand/collapse toggle button");



            // User will click the 'Testing 03' link under E-Sign section
            WebElement eSignTesting03Link = basePage.waitForElement(By.xpath("//a[@href='/agreements/2025A012368/latest/deliverables/1113382']//span[text()='Test']"));
            eSignTesting03Link.click();
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Clicked 'Test' link under E-Sign section");


            // User will click the Adobe integration icon to start the e-sign process
            WebElement adobeIcon = basePage.waitForElement(By.xpath("//img[@alt='adobe-icon']"));
            adobeIcon.click();
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Clicked Adobe integration icon");

            // User will upload the agreement PDF file via file input
            String AgreementFileName = System.getProperty("user.dir")+"/Test_Data/Agreement Info 2025_03.pdf";
            WebElement fileInput =basePage.waitForPresence(By.xpath("//input[@type='file' and @accept='application/pdf']"));
            fileInput.sendKeys(AgreementFileName);
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Successfully uploaded 'Agreement Info 2025.pdf'");



            // User will click the 'Add Recipient' button
            WebElement addRecipientButton = basePage.waitForElement(By.xpath("//button[text()='Add Recipient']"));
            addRecipientButton.click();
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Recipient' button");


            // User will enter the recipient email in the input field
            WebElement emailInput = basePage.waitForElement(By.xpath("//input[@placeholder='Email' and @type='email']"));
            emailInput.sendKeys("Sankar.Venkatesan@technossus.com");
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Entered value in recipient input field");


            // User will click the 'Preview' button inside 'add-recipients-section'
            WebElement previewButton = basePage.waitForElement(By.xpath("//div[contains(@class, 'add-recipients-section')]//button[normalize-space(text())='Preview']"));
            previewButton.click();
            basePage.pause(40000);
            ExtentReportListener.getExtentTest().pass("Clicked 'Preview' button inside 'add-recipients-section'");



//            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='sign-in-iframe']")));
//            basePage.pause(10000);
//            ExtentReportListener.getExtentTest().pass("Successfully Switched to iframe");
//
//
//            WebElement sourceEle  = driver.findElement(By.xpath("//div[@data-testid='menu-item-signature-form-field']//button//span"));
//            WebElement targetEle= driver.findElement(By.xpath("//div[@data-testid='overlay-drop-target']"));
//
//
//            Actions actions = new Actions(driver);
//            actions.dragAndDrop(sourceEle,targetEle).build().perform();
//
//            basePage.pause(30000);
//            ExtentReportListener.getExtentTest().pass("drag and drop was successfully");
//            // User will wait after clicking E-signature field
//            basePage.pause(10000);
//
//            // Step 1: Ensure you're back to default content (especially if you used switchTo().frame earlier)
//            driver.switchTo().defaultContent();
//            ExtentReportListener.getExtentTest().info("Switched to default content");
//
//
            basePage.switchToFrame(By.xpath("//iframe[@class='sign-in-iframe']"), 15);
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Successfully Switched to iframe");

            basePage.dragAndDrop(
                    By.xpath("//div[@data-testid='menu-item-signature-form-field']//button//span"),
                    By.xpath("//div[@data-testid='overlay-drop-target']"),
                    15);
            basePage.pause(30000);
            ExtentReportListener.getExtentTest().pass("Drag and drop was successful");

            basePage.switchToDefaultContent();
            ExtentReportListener.getExtentTest().info("Switched to default content");


            // Step 1: Scroll the modal
            By modalLocator = By.xpath("//div[contains(@class, 'modal-content-wrapper')]");
            basePage.scrollToBottomOfModal(modalLocator, 20);
            ExtentReportListener.getExtentTest().pass("Successfully scrolled the modal-content-wrapper");

            basePage.pause(10000);

            // Step 2: Switch to iframe
            By iframeLocator = By.xpath("//iframe[@class='sign-in-iframe']");
            basePage.switchToFrame(iframeLocator, 20);
            ExtentReportListener.getExtentTest().pass("Successfully Switched to iframe");

            basePage.pause(10000);

            // Step 3: Click the 'Send' button
            By sendButton = By.xpath("//span[text()='Send' and @class='ntVziG_spectrum-Button-label']");
            basePage.clickElement(sendButton, 20);
            ExtentReportListener.getExtentTest().pass("Clicked 'Send' button");

            basePage.pause(20000);

            // Step 4: Switch back to default content
            basePage.switchToDefaultContent();
            ExtentReportListener.getExtentTest().pass("Switched back to default content");

            // Step 5: Click the 'Status' tab
            By statusTab = By.xpath("//a[text()='Status']");
            basePage.clickElement(statusTab, 20);
            basePage.pause(10000);
            ExtentReportListener.getExtentTest().pass("Clicked 'Status' tab");


//            // Step 2: Locate the scrollable modal div using XPath
//            WebElement scrollableDiv = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'modal-content-wrapper')]")));
//            ExtentReportListener.getExtentTest().pass("Located scrollable modal-content-wrapper");
//
//            // Step 3: Perform scroll using JavaScript
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", scrollableDiv);
//            ExtentReportListener.getExtentTest().pass("Successfully scrolled the modal-content-wrapper");
//
//            // User will wait until user is scroll down
//            basePage.pause(10000);
//
//
//            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='sign-in-iframe']")));
//            ExtentReportListener.getExtentTest().pass("Successfully Switched to iframe");
//
//            // User will wait until user is scroll down
//            basePage.pause(10000);
//
//
//            // User will click the 'Send' button to send the document for signature
//            WebElement sendButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Send' and @class='ntVziG_spectrum-Button-label']")));
//            sendButton.click();
//            ExtentReportListener.getExtentTest().pass("Clicked 'Send' button");
//
//            // User will wait after clicking send button
//            basePage.pause(20000);
//
//
//            driver.switchTo().defaultContent();
//            ExtentReportListener.getExtentTest().pass("Switched back to default content");
//
//            // User will click the 'Status' tab to verify the current document status
//            WebElement statusTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Status']")));
//            statusTab.click();
//            basePage.pause(10000);
//            ExtentReportListener.getExtentTest().pass("Clicked 'Status' tab");

            // User will wait after navigating to the status tab


        } catch (Exception e) {
            // User will capture and log any exceptions that occur during the test
            ExtentReportListener.getExtentTest().fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        // User will close the browser instance after test execution is complete
//        if (driver != null) {
//            driver.quit();
//        }

        // Quit the WebDriver instance managed by DriverManager
        DriverManager.quitDriver();

        // User will record browser closure in the test report
//        test.info("Browser was successfully closed.");
        ExtentReportListener.getExtentTest().info("Browser was successfully closed.");

    }

//    @AfterSuite
//    public void flushExtentReport() {
//        // User will flush all the test information and generate the final HTML report
//        extent.flush();
//    }
}
