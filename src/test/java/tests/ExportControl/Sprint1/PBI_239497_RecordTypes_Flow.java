package tests.ExportControl.Sprint1;
import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.testng.annotations.Listeners;
import pages.Administration.Record_Types.RecordTypes_ExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import pages.Adobe.AgreementPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
public class PBI_239497_RecordTypes_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AgreementPage agreementPage;

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
    }

    @Test
    public void ExportControl_RecordTypes_Test() {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            String initialRecordType = JsonDataReader.get(1, "InitialRecordType");
            String recordTypePrefix = JsonDataReader.get(1, "RecordTypePrefix");
            String positiveSearchText = JsonDataReader.get(1, "PositiveSearchText");
            String negativeSearchText = JsonDataReader.get(1, "NegativeSearchText");
            String refMeaningValue = JsonDataReader.get(1, "RefMeaningValue");

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

            basePage.pause(10000);
            agreementPage.clickAdministrationLink();
            ExtentReportListener.getExtentTest().pass("Clicked Administration link");

            RecordTypes_ExportControlPage adminPageRecordtypes = new RecordTypes_ExportControlPage(driver);

            basePage.pause(5000);
            adminPageRecordtypes.openRecordTypesExportControl();
            ExtentReportListener.getExtentTest().pass("Clicked 'Record Types' link successfully");

            basePage.pause(5000);
            adminPageRecordtypes.clickExportControlUnderRecordTypes();
            ExtentReportListener.getExtentTest().pass("Clicked 'Export Control' under 'Record Types' successfully");

            basePage.pause(5000);
            adminPageRecordtypes.clickAddRecordTypeLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Record Type' link successfully");

            basePage.pause(5000);
            adminPageRecordtypes.selectModuleAsExportControl();
            ExtentReportListener.getExtentTest().pass("Selected 'Export Control' from Module dropdown successfully");

            basePage.pause(5000);
            adminPageRecordtypes.enterRecordType(initialRecordType);
            ExtentReportListener.getExtentTest().pass("Entered '" + initialRecordType + "' into Record Type input field");

            basePage.pause(5000);
            adminPageRecordtypes.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("'Cancel' button clicked successfully");

            basePage.pause(5000);
            adminPageRecordtypes.clickAddRecordTypeLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add Record Type' link successfully");

            basePage.pause(5000);
            adminPageRecordtypes.selectModuleAsExportControl();
            ExtentReportListener.getExtentTest().pass("Selected 'Export Control' from Module dropdown successfully");

            basePage.pause(5000);
            String recordTypeName = "Test" + new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
            adminPageRecordtypes.enterRecordType(recordTypeName);
            ExtentReportListener.getExtentTest().pass("Entered '" + recordTypeName + "' into Record Type input field");

            basePage.pause(5000);
            adminPageRecordtypes.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("'Create' button is clicked successfully");

            basePage.pause(5000);
            adminPageRecordtypes.searchRecordByName(positiveSearchText);
            ExtentReportListener.getExtentTest().pass("Search executed with name '" + positiveSearchText + "'");

            basePage.pause(5000);
            adminPageRecordtypes.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");

            basePage.pause(5000);
            adminPageRecordtypes.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("'Clear Selections' button clicked successfully");

            basePage.pause(5000);
            adminPageRecordtypes.searchRecordByName(negativeSearchText);
            ExtentReportListener.getExtentTest().pass("Search executed with name '" + negativeSearchText + "'");

            basePage.pause(5000);
            adminPageRecordtypes.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");

            basePage.pause(5000);
            adminPageRecordtypes.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("'Clear Selections' button clicked successfully");

            basePage.pause(5000);
            adminPageRecordtypes.clickAddCategoryLink();
            ExtentReportListener.getExtentTest().pass("'Add Category' link clicked successfully");

            basePage.pause(5000);
            adminPageRecordtypes.selectCategory(recordTypeName);
            ExtentReportListener.getExtentTest().pass("Selected '" + recordTypeName + "' from Category dropdown");

            basePage.pause(2000);
            adminPageRecordtypes.enterRefMeaning(refMeaningValue);
            ExtentReportListener.getExtentTest().pass("Entered '" + refMeaningValue + "' into Ref Meaning input field");

            basePage.pause(5000);
            adminPageRecordtypes.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("'Cancel' button clicked successfully");

            basePage.pause(5000);
            adminPageRecordtypes.clickAddCategoryLink();
            ExtentReportListener.getExtentTest().pass("'Add Category' link clicked successfully");

            basePage.pause(5000);
            adminPageRecordtypes.selectCategory(recordTypeName);
            ExtentReportListener.getExtentTest().pass("Selected '" + recordTypeName + "' from Category dropdown");

            basePage.pause(2000);
            adminPageRecordtypes.enterRefMeaning(refMeaningValue);
            ExtentReportListener.getExtentTest().pass("Entered '" + refMeaningValue + "' into Ref Meaning input field");

            basePage.pause(5000);
            adminPageRecordtypes.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("'Create' button is clicked successfully");

            basePage.pause(5000);
            adminPageRecordtypes.searchRecordByName(positiveSearchText);
            ExtentReportListener.getExtentTest().pass("Search executed with name '" + positiveSearchText + "'");

            basePage.pause(5000);
            adminPageRecordtypes.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");

            basePage.pause(5000);
            adminPageRecordtypes.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("'Clear Selections' button clicked successfully");

            basePage.pause(5000);
            adminPageRecordtypes.searchRecordByName(negativeSearchText);
            ExtentReportListener.getExtentTest().pass("Search executed with name '" + negativeSearchText + "'");

            basePage.pause(5000);
            adminPageRecordtypes.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");

            basePage.pause(5000);
            adminPageRecordtypes.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("'Clear Selections' button clicked successfully");

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