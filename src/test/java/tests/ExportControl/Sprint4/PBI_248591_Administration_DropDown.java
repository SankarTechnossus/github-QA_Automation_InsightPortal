package tests.ExportControl.Sprint4;


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
import pages.Adobe.AgreementPage;
import pages.Administration.Record_Types.RecordTypes_ExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class PBI_248591_Administration_DropDown {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    RecordTypes_ExportControlPage recordTypesExportControlPage;

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
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        recordTypesExportControlPage = new RecordTypes_ExportControlPage(driver);

    }

    @Test
    public void Export_control_Administration_Drop_down() {
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

            basePage.pause(5000);
            recordTypesExportControlPage.openRecordTypesExportControl();
            ExtentReportListener.getExtentTest().pass("Clicked 'Record Types' link successfully");

            basePage.pause(5000);
            recordTypesExportControlPage.clickExportControlUnderRecordTypes();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control' under 'Record Types' successfully");

            basePage.pause(5000);
            recordTypesExportControlPage.clickAddRecordTypeLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Record Type' link successfully");

            basePage.pause(5000);
            recordTypesExportControlPage.selectModuleAsExportControl();
            ExtentReportListener.getExtentTest().pass("Selected 'Export Control' from Module dropdown successfully");

            basePage.pause(5000);
            recordTypesExportControlPage.enterRecordType("Test01");
            ExtentReportListener.getExtentTest().pass("Entered 'Test' into Record Type input field");

            basePage.pause(5000);
            recordTypesExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("'Cancel' button clicked successfully");

            basePage.pause(5000);
            recordTypesExportControlPage.clickAddRecordTypeLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Record Type' link successfully");

            basePage.pause(5000);
            recordTypesExportControlPage.selectModuleAsExportControl();
            ExtentReportListener.getExtentTest().pass("Selected 'Export Control' from Module dropdown successfully");

            basePage.pause(5000);
            String recordTypeName = "Test" + new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
            recordTypesExportControlPage.enterRecordType(recordTypeName);
            ExtentReportListener.getExtentTest().pass("Entered '" + recordTypeName + "' into Record Type input field");

            basePage.pause(5000);
            recordTypesExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("'Create' button is clicked successfully");

            String searchKeyword = "Test";

            basePage.pause(5000);
            recordTypesExportControlPage.searchRecordByName(searchKeyword);
            ExtentReportListener.getExtentTest().pass("Search executed with name '" + searchKeyword + "'");

            basePage.pause(5000);
            recordTypesExportControlPage.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");

            basePage.pause(5000);
            recordTypesExportControlPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("'Clear Selections' button clicked successfully");

            String searchKeyword01 = "@@@@@@@";

            basePage.pause(5000);
            recordTypesExportControlPage.searchRecordByName(searchKeyword01);
            ExtentReportListener.getExtentTest().pass("Search executed with name '" + searchKeyword01 + "'");

            basePage.pause(5000);
            recordTypesExportControlPage.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");

            basePage.pause(5000);
            recordTypesExportControlPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("'Clear Selections' button clicked successfully");

            basePage.pause(5000);
            recordTypesExportControlPage.clickFirstRecordTypeEditIcon();
            ExtentReportListener.getExtentTest().pass("Clicked Edit icon for the first Record Type in the list");

            basePage.pause(2000);
            recordTypesExportControlPage.clickCancelOnEditRecordTypeModal();
            ExtentReportListener.getExtentTest().pass("Clicked Cancel on Edit Record Type modal");

            basePage.pause(5000);
            recordTypesExportControlPage.clickFirstRecordTypeEditIcon();
            ExtentReportListener.getExtentTest().pass("Clicked Edit icon for the first Record Type in the list");

            basePage.pause(2000);
            recordTypesExportControlPage.clickSaveOnEditRecordTypeModal();
            ExtentReportListener.getExtentTest().pass("Clicked Save on Edit Record Type modal");

            basePage.pause(5000);
            recordTypesExportControlPage.clickAddCategoryLink();
            ExtentReportListener.getExtentTest().pass("'Add Category' link clicked successfully");

            basePage.pause(5000);
            recordTypesExportControlPage.selectCategory(recordTypeName);
            ExtentReportListener.getExtentTest().pass("Selected '" + recordTypeName + "' from Category dropdown");

            String refValue = "1";
            basePage.pause(2000);
            recordTypesExportControlPage.enterRefMeaning(refValue);
            ExtentReportListener.getExtentTest().pass("Entered '" + refValue + "' into Ref Meaning input field");

            basePage.pause(5000);
            recordTypesExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("'Cancel' button clicked successfully");

            basePage.pause(5000);
            recordTypesExportControlPage.clickAddCategoryLink();
            ExtentReportListener.getExtentTest().pass("'Add Category' link clicked successfully");

            basePage.pause(5000);
            recordTypesExportControlPage.selectCategory(recordTypeName);
            ExtentReportListener.getExtentTest().pass("Selected '" + recordTypeName + "' from Category dropdown");

            String refValue01 = "1";
            basePage.pause(2000);
            recordTypesExportControlPage.enterRefMeaning(refValue01);
            ExtentReportListener.getExtentTest().pass("Entered '" + refValue01 + "' into Ref Meaning input field");

            basePage.pause(5000);
            recordTypesExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("'Create' button is clicked successfully");

            String searchKeyword02 = "Test";

            basePage.pause(5000);
            recordTypesExportControlPage.searchRecordByName(searchKeyword02);
            ExtentReportListener.getExtentTest().pass("Search executed with name '" + searchKeyword02 + "'");

            basePage.pause(5000);
            recordTypesExportControlPage.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");

            basePage.pause(5000);
            recordTypesExportControlPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("'Clear Selections' button clicked successfully");

            String searchKeyword03 = "@@@@@@@";

            basePage.pause(5000);
            recordTypesExportControlPage.searchRecordByName(searchKeyword03);
            ExtentReportListener.getExtentTest().pass("Search executed with name '" + searchKeyword03 + "'");

            basePage.pause(5000);
            recordTypesExportControlPage.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");

            basePage.pause(5000);
            recordTypesExportControlPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("'Clear Selections' button clicked successfully");

            basePage.pause(5000);
            recordTypesExportControlPage.clickFirstRecordTypeEditIcon();
            ExtentReportListener.getExtentTest().pass("Clicked Edit icon for the first Record Type in the list");

            basePage.pause(2000);
            recordTypesExportControlPage.clickCancelOnEditRecordTypeModal();
            ExtentReportListener.getExtentTest().pass("Clicked Cancel on Edit Record Type modal");

            basePage.pause(5000);
            recordTypesExportControlPage.clickFirstRecordTypeEditIcon();
            ExtentReportListener.getExtentTest().pass("Clicked Edit icon for the first Record Type in the list");

            basePage.pause(2000);
            recordTypesExportControlPage.clickSaveOnEditRecordTypeModal();
            ExtentReportListener.getExtentTest().pass("Clicked Save on Edit Record Type modal");
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