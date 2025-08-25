package tests.AdobeEsign.Sprint3;

import base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AdobeEsignatureReportpage;
import pages.AgreementPage;
import pages.LoginPage;
import utils.DriverManager;

import java.time.Duration;

    @Listeners(listeners.ExtentReportListener.class)
    public class EsignatureReport {


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

            basePage = new BasePage(driver);
        }


        @Test
        public void AdobeEsignatureReportpageAdobeFlow () {
            ExtentReportListener.getExtentTest().info("your log message");
            try {
                // User will open the login page of the Insight Portal application
                driver.get("https://sacramento-insight4.partners.org/");
                ExtentReportListener.getExtentTest().info("Opened dashboard URL");

                // User will wait for the login screen to load completely before performing actions
                basePage.pause(20000);

                // Create an instance of LoginPage
                LoginPage loginPage = new LoginPage(driver);

                // User will enter the username into the username input field
                loginPage.enterUsername("SV1179");
                ExtentReportListener.getExtentTest().pass("Entered username");

                // User will click the 'Next' button to proceed to the password entry screen
                loginPage.clickNext();
                ExtentReportListener.getExtentTest().pass("Clicked Next");

                // User will input the user's password into the password field
                loginPage.enterPassword("Devinivetha@1930");
                ExtentReportListener.getExtentTest().pass("Entered password");

                // User will click the 'Verify' button to authenticate the user
                loginPage.clickVerify();
                ExtentReportListener.getExtentTest().pass("Clicked Verify");

                // Optional: pause if any post-login actions needed
                basePage.pause(20000);

                // Agreement Page Actions
                AgreementPage agreementPage = new AgreementPage(driver);

                // Wait and click the 'Agreements' link from the sidebar
                agreementPage.clickAgreementsLink();
                basePage.pause(5000);
                ExtentReportListener.getExtentTest().pass("Clicked 'Agreements' link from sidebar");


                AdobeEsignatureReportpage agreementpagesAdobe = new AdobeEsignatureReportpage(driver);


                basePage.pause(5000);
                agreementpagesAdobe.openESignatureReport();
                ExtentReportListener.getExtentTest().pass("Opened Administration > E-Signature Report successfully");


                // Dates
                basePage.pause(3000);
                String[] range = agreementpagesAdobe.enterRandomDateRange();
                ExtentReportListener.getExtentTest().pass("Entered From: " + range[0] + "  To: " + range[1]);

                // Report Type
                basePage.pause(3000);
                agreementpagesAdobe.selectPendingReportType();
                ExtentReportListener.getExtentTest().pass("Selected Report Type: Pending Signature Report");

                // Search
                basePage.pause(3000);
                agreementpagesAdobe.clickSearch();
                ExtentReportListener.getExtentTest().pass("Clicked Search");

                // Clear
                basePage.pause(3000);
                agreementpagesAdobe.clickClear();
                ExtentReportListener.getExtentTest().pass("Clicked Clear");

                // Dates
                basePage.pause(3000);
                String[] range01 = agreementpagesAdobe.enterRandomDateRange();
                ExtentReportListener.getExtentTest().pass("Entered From: " + range01[0] + "  To: " + range01[1]);

                // Report Type
                basePage.pause(3000);
                agreementpagesAdobe.selectPendingReportType02();
                ExtentReportListener.getExtentTest().pass("Selected Report Type: Complete signature report");


                // Search
                basePage.pause(3000);
                agreementpagesAdobe.clickSearch();
                ExtentReportListener.getExtentTest().pass("Clicked Search");

                // Clear
                basePage.pause(3000);
                agreementpagesAdobe.clickClear();
                ExtentReportListener.getExtentTest().pass("Clicked Clear");


                basePage.pause(1000);
                agreementpagesAdobe.clickDownload();
                ExtentReportListener.getExtentTest().pass("Clicked Download");





















            } catch (Exception e) {
                // User will capture and log any exceptions that occur during the test
                ExtentReportListener.getExtentTest().fail("Test failed due to exception: " + e.getMessage());
            }
        }

        @AfterMethod
        public void tearDown() {

//        DriverManager.quitDriver();
            // User will record browser closure in the test report
            ExtentReportListener.getExtentTest().info("Browser was successfully closed.");

        }

    }
