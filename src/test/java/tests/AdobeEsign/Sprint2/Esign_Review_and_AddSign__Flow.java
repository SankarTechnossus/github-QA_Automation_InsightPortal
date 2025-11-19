package tests.AdobeEsign.Sprint2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import pages.Adobe.Adobe_Deliverables_page;

import java.io.File;
import java.time.Duration;

public class Esign_Review_and_AddSign__Flow {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupExtentReport() {
        // The user creates a directory to store the HTML test report
        new File("test_reports").mkdirs();

        // The user initializes the ExtentSparkReporter to generate the HTML report
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test_reports/Esign_Review_and_Sign_Flow.html");

        // The user sets the document title and report name for the test
        htmlReporter.config().setDocumentTitle("Esign_Review_and_Sign_Flow");
        htmlReporter.config().setReportName("Sprint 1 Automation");

        // The user attaches the reporter to the ExtentReports instance
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setupBrowser() {
        // The user sets up and configures the Chrome WebDriver
        WebDriverManager.chromedriver().setup();

        // The user launches a new Chrome browser instance
        driver = new ChromeDriver();

        // The user maximizes the browser window
        driver.manage().window().maximize();

        // The user initializes explicit wait for element handling
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void createFormFlow() {
        // The user creates a new test entry in the Extent Report
        test = extent.createTest("Adding_Esign_Flow_From_Email");

        try {
            // The user opens the login page URL from the email
           driver.get("https://nam02.safelinks.protection.outlook.com/?url=https%3A%2F%2Fmassgen.na1.echosign.com%2Fpublic%2Fesign%3Ftsid%3DCBFCIBAACBSCTBABDUAAABACAABAAClzMZW403po8UiaxApycITMGXmeR2X43HVrqlNiF1UrJBB8zOZb1bHmPUCG3iPH8CSn-F7dT__4eZ8OwutvwmsMIxOjZ61H0OcyDW16wDOjwlgdVjtK5ZR654gvJDFeJ%26&data=05%7C02%7Csankar.venkatesan%40technossus.com%7C4f9a28a2875d4f7c9cf608de0d5a02bc%7C7284c0574078417092c01bcb11c55269%7C0%7C0%7C638962877414349801%7CUnknown%7CTWFpbGZsb3d8eyJFbXB0eU1hcGkiOnRydWUsIlYiOiIwLjAuMDAwMCIsIlAiOiJXaW4zMiIsIkFOIjoiTWFpbCIsIldUIjoyfQ%3D%3D%7C0%7C%7C%7C&sdata=i9T9G6e%2F15uFDde0L5gUQfo2a%2BUbg3UlXGFdeARKgog%3D&reserved=0");
//            driver.get("");
            test.info("Opened dashboard URL");



            // The user waits for the login page to load
            Thread.sleep(10000);

            // The user clicks the 'Continue' button to proceed
            WebElement continueButton = driver.findElement(By.xpath("//button[contains(@class, 'click-to-accept-tou') and text()='Continue']"));
            continueButton.click();
            test.pass("User clicked the 'Continue' button");

            // The user waits for the next screen to load
            Thread.sleep(10000);

            // The user uses JavaScript to click on the signature placeholder
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.querySelector(\"div[aria-label='Click to sign required']\").click();");
            test.pass("User clicked on the signature placeholder");

            // The user waits for the signature input field to become active and enters their name
            WebElement signatureInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type your signature here' and contains(@class, 'signature-type-name')]")));
            signatureInput.sendKeys("sankar Venkatesan");
            test.pass("User entered signature text 'sankar Venkatesan'");

            // The user waits before proceeding
            Thread.sleep(10000);

            // The user clicks the 'Apply' button to confirm the signature
            WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary apply' and text()='Apply']")));
            applyButton.click();
            test.pass("User clicked the 'Apply' button");

            // The user waits before interacting with the dropdown
            Thread.sleep(10000);

            // The user clicks on the 'Sign Reason' dropdown
            WebElement signReasonDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='signReasonFormControlDropdown']")));
            signReasonDropdown.click();
            test.pass("User clicked on the 'Sign Reason' dropdown");

            // The user waits before selecting the dropdown option
            Thread.sleep(10000);

            Adobe_Deliverables_page Adobedeliverablepage= new Adobe_Deliverables_page(driver);
            Adobedeliverablepage.selectSignReasonApprove();
            test.pass("Selected signing reason 'I approve this document' successfully");

//
//            // The user selects the option 'I approve this document' from the dropdown
//            WebElement approveOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='signReasonFormControlDropdown']/option[text()=' I approve this document']")));
//            approveOption.click();
//            test.pass("User selected 'I approve this document' from the dropdown");



            // The user waits before clicking 'OK'
            Thread.sleep(10000);

            // The user clicks the 'OK' button to confirm signing reason
            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary state-next' and text()='OK']")));
            okButton.click();
            test.pass("User clicked the 'OK' button");

            // The user clicks the 'Click to Sign' button to finalize signing
            WebElement clickToSignButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'btn btn-primary') and contains(text(),'Click to Sign')]")));
            clickToSignButton.click();
            test.pass("User clicked the 'Click to Sign' button");

            // The user waits for the signing process to complete
            Thread.sleep(20000);

            // The user clicks the 'Download' button to download the signed document
            WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'spectrum-Button') and normalize-space(text())='Download']")));
            downloadButton.click();
            test.pass("User clicked the 'Download' button after signing the document");

            // The user waits before ending the test
            Thread.sleep(10000);

        } catch (Exception e) {
            // The user logs any exceptions that occur during the test
            test.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        // The user closes the browser window after test execution
        if (driver != null) {
            // driver.quit();
        }

        // The user logs browser closure
        test.info("Browser was successfully closed.");
    }

    @AfterSuite
    public void flushExtentReport() {
        // The user flushes the Extent report to generate the final HTML output
        extent.flush();
    }
}
