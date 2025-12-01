package tests.Exportcontrol.Sprint1;
import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.testng.annotations.Listeners;
import pages.Administration.Transaction_Types.TransactionTypes_ExportControlPage;
import pages.Administration.Record_Types.RecordTypes_ExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import pages.Adobe.AgreementPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.time.Duration;


@Listeners(listeners.ExtentReportListener.class)
public class PBI_239497_Export_Control_TransactionTypes_Flow {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    AgreementPage agreementPage;
    TransactionTypes_ExportControlPage transactionTypesExportControlPage;
    RecordTypes_ExportControlPage recordTypesExportControlPage;

    @BeforeMethod
    public void setupBrowser() {
        //User will setup and configure the Chrome WebDriver using WebDriverManager
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
        agreementPage = new AgreementPage(driver);
        transactionTypesExportControlPage = new TransactionTypes_ExportControlPage(driver);
        recordTypesExportControlPage = new RecordTypes_ExportControlPage(driver);
    }

    @Test
    public void Exportcontrol_TransactionTypes_Test () {
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

            basePage.pause(10000);

            agreementPage.clickAdministrationLink();
            ExtentReportListener.getExtentTest().pass("Clicked Administration link");

            basePage.pause(800);
            transactionTypesExportControlPage.clickTransactionTypesLink();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Transaction Types' link from left navigation");

            basePage.pause(800);
            transactionTypesExportControlPage.clickAddNewTransactionType();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add new' link on Transaction Types page");

            basePage.pause(800);
            transactionTypesExportControlPage.enterTransactionType("Test");
            ExtentReportListener.getExtentTest().pass("Entered 'Test' into Transaction Type input field");

            basePage.pause(1000);
            transactionTypesExportControlPage.checkActiveCheckbox();
            ExtentReportListener.getExtentTest().pass("Checked 'Active' checkbox");

            basePage.pause(1000);
            transactionTypesExportControlPage.clickCancel();
            ExtentReportListener.getExtentTest().pass("Clicked Cancel link successfully");

            basePage.pause(800);
            transactionTypesExportControlPage.clickAddNewTransactionType();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add new' link on Transaction Types page");

            basePage.pause(1000);
            String transactionTypeName = transactionTypesExportControlPage.generateUniqueTransactionTypeName();
            transactionTypesExportControlPage.enterTransactionType(transactionTypeName);
            ExtentReportListener.getExtentTest().pass("Entered '" + transactionTypeName + "' into Transaction Type input field");

            basePage.pause(800);
            transactionTypesExportControlPage.checkActiveCheckbox();
            ExtentReportListener.getExtentTest().pass("Checked 'Active' checkbox");

            basePage.pause(800);
            recordTypesExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("'Create' button is clicked successfully");

            basePage.pause(10000);
            transactionTypesExportControlPage.enterSearchByName("Test");
            ExtentReportListener.getExtentTest().pass("Entered 'Test001' into Search by Name input field");

            basePage.pause(800);
            recordTypesExportControlPage.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");

            basePage.pause(800);
            recordTypesExportControlPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("'Clear Selections' button clicked successfully");

            basePage.pause(800);
            transactionTypesExportControlPage.enterSearchByName("@@@@@@@@@@");
            ExtentReportListener.getExtentTest().pass("Entered 'Test001' into Search by Name input field");

            basePage.pause(800);
            recordTypesExportControlPage.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");

            basePage.pause(800);
            recordTypesExportControlPage.clickClearSelectionsButton();
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