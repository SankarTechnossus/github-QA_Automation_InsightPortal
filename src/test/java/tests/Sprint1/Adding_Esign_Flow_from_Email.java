package tests.Sprint1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.time.Duration;

public class Adding_Esign_Flow_from_Email {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupExtentReport() {
        // User will create the directory where the HTML report will be stored, if it does not already exist
        new File("test_reports").mkdirs();

        // User will initialize ExtentSparkReporter to generate the HTML test execution report
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test_reports/Adding_Esign_Flow_From_Email.html");

        // User will set the document title and report name for the test report
        htmlReporter.config().setDocumentTitle("Adding_Esign_Flow_From_Email");
        htmlReporter.config().setReportName("Sprint 1 Automation");

        // User will attach the reporter to the ExtentReports instance
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setupBrowser() {
        // User will setup and configure the Chrome WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        // User will launch a new Chrome browser instance
        driver = new ChromeDriver();

        // User will maximize the browser window to ensure all UI elements are visible
        driver.manage().window().maximize();

        // User will initialize explicit wait with a timeout of 10 seconds for dynamic element handling
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void createFormFlow() {
        // User will create a new test entry in the Extent Report for the form creation flow
        test = extent.createTest("Adding_Esign_Flow_From_Email");

        try {
            // User will open the login page of the Insight Portal application
            //Enter the mail URL to Sign the Document
            driver.get("https://nam02.safelinks.protection.outlook.com/?url=https%3A%2F%2Fmassgen.na1.echosign.com%2Fpublic%2Fesign%3Ftsid%3DCBFCIBAACBSCTBABDUAAABACAABAAuyDbSGHJrsJ-QElo9WMhaiLwSJWfn-nAgCjhFtd8kd_t62-LIdZAaZPkWqSh4jJ_uHg17Ufd80CTM34qecqaSygfbD8VsRrAJpz2SZtngJzF9l4JjvfJ2ebgPb2XgbJ9%26&data=05%7C02%7Csankar.venkatesan%40technossus.com%7C2cf39b87c41a47d1604808ddb7cbd971%7C7284c0574078417092c01bcb11c55269%7C0%7C0%7C638868808340207098%7CUnknown%7CTWFpbGZsb3d8eyJFbXB0eU1hcGkiOnRydWUsIlYiOiIwLjAuMDAwMCIsIlAiOiJXaW4zMiIsIkFOIjoiTWFpbCIsIldUIjoyfQ%3D%3D%7C0%7C%7C%7C&sdata=yAxasdzmdP9YJqt%2F9ZEsQhSF5HQtGrx7yeLtLNhRE4U%3D&reserved=0");
            test.info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            Thread.sleep(10000);

            // User will click the 'Continue' button
            WebElement continueButton = driver.findElement(By.xpath("//button[contains(@class, 'click-to-accept-tou') and text()='Continue']"));continueButton.click();
            test.pass("Clicked 'Continue' button");

            // User Clicked 'Continue' button
            Thread.sleep(10000);

            // Click the yellow "Click here to sign" button
            WebElement clickToSign = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='faux_field' and @aria-label='Click to sign required']")));clickToSign.click();
            test.pass("Clicked 'Click here to sign' button");


            // User will click the signature area
            Thread.sleep(10000);

            // User will enter signature in the signature type input field
            WebElement signatureInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='form-control signature-type-name adobehandb' and @placeholder='Type your signature here']")));signatureInput.sendKeys("sankar Venkatesan");
            test.pass("Entered signature text 'sankar Venkatesan' in the input field");

            // Entered signature text 'sankar Venkatesan' in the input field
            Thread.sleep(10000);

            // User will click the 'Apply' button
            WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary apply' and text()='Apply']")));applyButton.click();
            test.pass("Clicked 'Apply' button");

            // Clicked 'Apply' button
            Thread.sleep(10000);


            // User will click the 'Sign Reason' dropdown
            WebElement signReasonDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='signReasonFormControlDropdown']")));signReasonDropdown.click();
            test.pass("Clicked 'Sign Reason' dropdown");

            // Clicked 'Sign Reason' dropdown
            Thread.sleep(10000);

            // User will select the 'I approve this document' option from the dropdown
            WebElement approveOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='signReasonFormControlDropdown']/option[text()=' I approve this document']")));approveOption.click();
            test.pass("Selected 'I approve this document' from dropdown");

            // Selected 'I approve this document' from dropdown
            Thread.sleep(10000);


            // Locate and click the 'OK' button
            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary state-next' and text()='OK']")));okButton.click();
            test.pass("Clicked 'OK' button");


            // Locate and click the 'Click to Sign' button
            WebElement clickToSignButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'btn btn-primary') and contains(text(),'Click to Sign')]")));clickToSignButton.click();
            test.pass("Clicked 'Click to Sign' button");

            // Clicked 'OK' button
            Thread.sleep(10000);

            // Click the 'Download' button after successful signing
            WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'spectrum-Button') and .//span[text()='Download']]")));downloadButton.click();
            test.pass("Clicked 'Download' button after successful signing");


            // Clicked the 'Download' button after successful signing
            Thread.sleep(10000);

        } catch (Exception e) {
            // User will capture and log any exceptions that occur during the test
            test.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        // User will close the browser instance after test execution is complete
        if (driver != null) {
//            driver.quit();
        }

        // User will record browser closure in the test report
        test.info("Browser was successfully closed.");
    }

    @AfterSuite
    public void flushExtentReport() {
        // User will flush all the test information and generate the final HTML report
        extent.flush();
    }
}
