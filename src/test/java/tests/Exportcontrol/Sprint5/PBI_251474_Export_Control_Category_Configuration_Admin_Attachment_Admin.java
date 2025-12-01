package tests.Exportcontrol.Sprint5;


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
import pages.Administration.Attachment_Types.AttachmentTypes_ExportControlPage;
import pages.Adobe.AgreementPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class PBI_251474_Export_Control_Category_Configuration_Admin_Attachment_Admin {
    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AttachmentTypes_ExportControlPage attachmentTypesExportControlPage;

    @BeforeMethod
    public void setupBrowser(){
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
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        attachmentTypesExportControlPage = new AttachmentTypes_ExportControlPage(driver);
    }

    @Test
    public void Export_control_Category_Configuration_Admin_Attachment_Admin () {
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

            basePage.pause(1500);
            attachmentTypesExportControlPage.expandAttachmentTypes();
            ExtentReportListener.getExtentTest().pass("Expanded Attachment Types menu");

            basePage.pause(1500);
            attachmentTypesExportControlPage.clickAttachmentTypesExportControl();
            ExtentReportListener.getExtentTest().pass("Clicked Export Control under Attachment Types successfully");

            basePage.pause(2000);
            attachmentTypesExportControlPage.clickAddAttachmentType();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Attachment Type' button successfully");

            basePage.pause(2000);
            attachmentTypesExportControlPage.enterRandomTypeName(8);
            ExtentReportListener.getExtentTest().pass("Entered random Type Name successfully");

            basePage.pause(2000);
            attachmentTypesExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked Cancel successfully");

            basePage.pause(2000);
            attachmentTypesExportControlPage.clickAddAttachmentType();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Attachment Type' button successfully");

            basePage.pause(2000);
            attachmentTypesExportControlPage.enterRandomTypeName(8);
            ExtentReportListener.getExtentTest().pass("Entered random Type Name successfully");

            basePage.pause(2000);
            attachmentTypesExportControlPage.clickAddButton();
            ExtentReportListener.getExtentTest().pass("Clicked Add button successfully");

            basePage.pause(2000);
            attachmentTypesExportControlPage.clickFirstEditButton();
            ExtentReportListener.getExtentTest().pass("Clicked first Edit button successfully");

            basePage.pause(1000);
            attachmentTypesExportControlPage.appendTypeNameWithTest();
            ExtentReportListener.getExtentTest().pass("Appended 'Test' to Type Name successfully");

            attachmentTypesExportControlPage.clickCancel();
            ExtentReportListener.getExtentTest().pass("Clicked Cancel button successfully");

            basePage.pause(2000);
            attachmentTypesExportControlPage.clickFirstEditButton();
            ExtentReportListener.getExtentTest().pass("Clicked first Edit button successfully");

            basePage.pause(1000);
            attachmentTypesExportControlPage.appendTypeNameWithTest();
            ExtentReportListener.getExtentTest().pass("Appended 'Test' to Type Name successfully");

            attachmentTypesExportControlPage.clickSave();
            ExtentReportListener.getExtentTest().pass("Clicked Save button successfully");
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