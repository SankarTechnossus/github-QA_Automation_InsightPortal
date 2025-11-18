package tests.Exportcontrol.Sprint5;


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
import pages.AgreementPage;
import pages.Export_control_Administration_Drop_down_page;
import pages.Export_control_Notes_page;
import pages.Export_control_menu_flow_of_export_control_page;
import utils.DriverManager;
import workflow_helper.LoginPageHelper;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class Export_control_Notes {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    @BeforeMethod
    public void setupBrowser() {
        // User will setup and configure the Chrome WebDriver using WebDriverManager
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
    public void Export_control_Notes () {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            // User will open the login page of the Insight Portal application
            driver.get("https://hollywood-insight4.partners.org/");
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Create an instance of LoginPage
            LoginPageHelper loginPageHelper =new LoginPageHelper(driver);
            loginPageHelper.enterUserName("SV1179");
            ExtentReportListener.getExtentTest().pass("Entered username");

            loginPageHelper.clickNext();
            ExtentReportListener.getExtentTest().pass("Clicked Next");

            loginPageHelper.enterPassword();
            ExtentReportListener.getExtentTest().pass("Entered password");

            loginPageHelper.clickVerify();
            ExtentReportListener.getExtentTest().pass("Clicked Verify");

            // Optional: pause if any post-login actions needed
            basePage.pause(20000);

            // Agreement Page Actions
            AgreementPage agreementPage = new AgreementPage(driver);

            basePage.pause(10000);
            agreementPage.clickAdministrationLink();
            ExtentReportListener.getExtentTest().pass("Clicked Administration link");


            Export_control_Notes_page exportcontrolnotes = new Export_control_Notes_page(driver);


            Export_control_menu_flow_of_export_control_page menuflowexport = new Export_control_menu_flow_of_export_control_page(driver);


            basePage.pause(3000);
            menuflowexport.clickExportControlLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control' module link successfully");


            basePage.pause(1000);
            menuflowexport.clickSearchLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Search' link successfully from Export Control sidebar");

            basePage.pause(1000);
            menuflowexport.setStatusCompleted();
            ExtentReportListener.getExtentTest().pass("Selected Status: Completed");

            basePage.pause(2000);
            menuflowexport.setRecordNumber("2025E006129");
            ExtentReportListener.getExtentTest().pass("Entered Record Number: 2025E006129");


            basePage.pause(3000);
            String[] created = menuflowexport.enterCreatedOnRandomRange();
            ExtentReportListener.getExtentTest().pass("Created On  From: " + created[0] + "  To: " + created[1]);

            String[] review = menuflowexport.enterReviewDateRandomRange();
            ExtentReportListener.getExtentTest().pass("Review date From: " + review[0] + "  To: " + review[1]);

            basePage.pause(2000);
            menuflowexport.setAgreementNumbers("01");
            ExtentReportListener.getExtentTest().pass("Entered Agreement Numbers: 01");

            basePage.pause(2000);
            menuflowexport.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("Clicked Search");

            basePage.pause(2000);
            menuflowexport.clickClearSelections();
            ExtentReportListener.getExtentTest().pass("Clicked Clear Selections");

            basePage.pause(2000);
            menuflowexport.setRecordNumber("2025E006129");
            ExtentReportListener.getExtentTest().pass("Entered Record Number: 2025E006129");

            basePage.pause(2000);
            menuflowexport.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("Clicked Search");

            basePage.pause(2000);
            menuflowexport.clickRecordNumberFromSearchGrid();
            ExtentReportListener.getExtentTest().pass("Clicked Record Number link 2025E006129 from Search grid");















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