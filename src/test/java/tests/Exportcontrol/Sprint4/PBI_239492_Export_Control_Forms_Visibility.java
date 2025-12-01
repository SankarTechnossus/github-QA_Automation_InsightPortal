package tests.Exportcontrol.Sprint4;


import base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Administration.Form_Visibility.FormsVisibility_ExportControlPage;
import pages.Adobe.AgreementPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class PBI_239492_Export_Control_Forms_Visibility {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    FormsVisibility_ExportControlPage formsVisibilityExportControlPage;

    @BeforeMethod
    public void setupBrowser() {
        // User will set up and configure the Chrome WebDriver using WebDriverManager
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
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        formsVisibilityExportControlPage = new FormsVisibility_ExportControlPage(driver);
    }

    @Test
    public void Forms_Visibility () {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            // User will open the login page of the Insight Portal application
            driver.get(url);
            ExtentReportListener.getExtentTest().info("Opened dashboard URL");

            // User will wait for the login screen to load completely before performing actions
            basePage.pause(20000);

            // Login into the application
            loginPage.LoginIntoApplication(userName, password);

            Assert.assertTrue(dashboardPage.VerifyUserLandsOnDashboardPage());
            ExtentReportListener.getExtentTest().pass("User logged into the application successfully and lands on the dashboard page.");

            // Agreement Page Actions
            AgreementPage agreementPage = new AgreementPage(driver);

            basePage.pause(10000);
            agreementPage.clickAdministrationLink();
            ExtentReportListener.getExtentTest().pass("Clicked Administration link");

            basePage.pause(2000);
            formsVisibilityExportControlPage.openExportControlUnderFormVisibility();
            ExtentReportListener.getExtentTest().pass("Opened 'Export Control' under Form Visibility successfully");

            basePage.pause(2000);
            formsVisibilityExportControlPage.clickAddRuleButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add rule' button successfully");

            basePage.pause(1000);
            formsVisibilityExportControlPage.waitForFormVisibilityModal();
            ExtentReportListener.getExtentTest().pass("Form Visibility modal loaded successfully");
            // Step 1 – Form
            basePage.pause(500);
            formsVisibilityExportControlPage.selectForm("Test");
            ExtentReportListener.getExtentTest().pass("Selected Form: Test successfully");

            // Step 2 – Access
            basePage.pause(500);
            formsVisibilityExportControlPage.selectAccess("Hidden");
            ExtentReportListener.getExtentTest().pass("Selected Access: Hidden successfully");

            // Step 3 – Query Builder
            basePage.pause(500);
            formsVisibilityExportControlPage.selectQueryBuilder("AND");
            ExtentReportListener.getExtentTest().pass("Selected Query Builder: AND successfully");

            basePage.pause(500);
            formsVisibilityExportControlPage.clickAddRuleInsideModal();
            ExtentReportListener.getExtentTest().pass("Clicked 'Rule' button inside the modal successfully");

            basePage.pause(500);
            formsVisibilityExportControlPage.clickRemoveRule();
            ExtentReportListener.getExtentTest().pass("Clicked 'Remove rule' button successfully");

            basePage.pause(500);
            formsVisibilityExportControlPage.clickAddGroup();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Group' button successfully");

            basePage.pause(500);
            formsVisibilityExportControlPage.clickRemoveGroup();
            ExtentReportListener.getExtentTest().pass("Clicked 'Remove Group' button successfully");

            basePage.pause(500);
            formsVisibilityExportControlPage.clickMigration();
            ExtentReportListener.getExtentTest().pass("Clicked 'Migration' button successfully");

            basePage.pause(500);
            formsVisibilityExportControlPage.clickMigration();
            ExtentReportListener.getExtentTest().pass("Clicked 'Migration' button successfully");

            basePage.pause(500);
            formsVisibilityExportControlPage.clickSave();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");

            basePage.pause(500);
            formsVisibilityExportControlPage.clickCancel();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");

            basePage.pause(1000);
            formsVisibilityExportControlPage.clickFirstEditButton();
            ExtentReportListener.getExtentTest().pass("Clicked first 'Edit' button successfully");

            basePage.pause(800);
            formsVisibilityExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");

            basePage.pause(1000);
            formsVisibilityExportControlPage.clickFirstEditButton();
            ExtentReportListener.getExtentTest().pass("Clicked first 'Edit' button successfully");

            basePage.pause(800);
            formsVisibilityExportControlPage.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");

        } catch (Exception e) {
            // User will capture and log any exceptions that occur during the test
            ExtentReportListener.getExtentTest().fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
        // User will record browser closure in the test report
        ExtentReportListener.getExtentTest().info("Browser was successfully closed.");
    }
}