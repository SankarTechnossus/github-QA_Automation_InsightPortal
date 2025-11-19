package tests.Exportcontrol.Sprint4;


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
import pages.Adobe.AgreementPage;
import pages.Administration.Exportcontrol_RecordTypes_Page;
import utils.DriverManager;
import workflow_helper.LoginPageHelper;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class Export_control_Administration_Drop_down {

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
    public void Export_control_Administration_Drop_down() {
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

            Exportcontrol_RecordTypes_Page adminPageRecordtypes = new Exportcontrol_RecordTypes_Page(driver);

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
            adminPageRecordtypes.enterRecordType("Test01");
            ExtentReportListener.getExtentTest().pass("Entered 'Test' into Record Type input field");




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


            String searchKeyword = "Test";

            basePage.pause(5000);
            adminPageRecordtypes.searchRecordByName(searchKeyword);
            ExtentReportListener.getExtentTest().pass("Search executed with name '" + searchKeyword + "'");


            basePage.pause(5000);
            adminPageRecordtypes.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");


            basePage.pause(5000);
            adminPageRecordtypes.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("'Clear Selections' button clicked successfully");


            String searchKeyword01 = "@@@@@@@";

            basePage.pause(5000);
            adminPageRecordtypes.searchRecordByName(searchKeyword01);
            ExtentReportListener.getExtentTest().pass("Search executed with name '" + searchKeyword01 + "'");


            basePage.pause(5000);
            adminPageRecordtypes.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");


            basePage.pause(5000);
            adminPageRecordtypes.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("'Clear Selections' button clicked successfully");


            basePage.pause(5000);
            adminPageRecordtypes.clickFirstRecordTypeEditIcon();
            ExtentReportListener.getExtentTest().pass("Clicked Edit icon for the first Record Type in the list");

            basePage.pause(2000);
            adminPageRecordtypes.clickCancelOnEditRecordTypeModal();
            ExtentReportListener.getExtentTest().pass("Clicked Cancel on Edit Record Type modal");

            basePage.pause(5000);
            adminPageRecordtypes.clickFirstRecordTypeEditIcon();
            ExtentReportListener.getExtentTest().pass("Clicked Edit icon for the first Record Type in the list");

            basePage.pause(2000);
            adminPageRecordtypes.clickSaveOnEditRecordTypeModal();
            ExtentReportListener.getExtentTest().pass("Clicked Save on Edit Record Type modal");


            basePage.pause(5000);
            adminPageRecordtypes.clickAddCategoryLink();
            ExtentReportListener.getExtentTest().pass("'Add Category' link clicked successfully");


            basePage.pause(5000);
            adminPageRecordtypes.selectCategory(recordTypeName);
            ExtentReportListener.getExtentTest().pass("Selected '" + recordTypeName + "' from Category dropdown");


            String refValue = "1";
            basePage.pause(2000);
            adminPageRecordtypes.enterRefMeaning(refValue);
            ExtentReportListener.getExtentTest().pass("Entered '" + refValue + "' into Ref Meaning input field");


            basePage.pause(5000);
            adminPageRecordtypes.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("'Cancel' button clicked successfully");

            basePage.pause(5000);
            adminPageRecordtypes.clickAddCategoryLink();
            ExtentReportListener.getExtentTest().pass("'Add Category' link clicked successfully");


            basePage.pause(5000);
            adminPageRecordtypes.selectCategory(recordTypeName);
            ExtentReportListener.getExtentTest().pass("Selected '" + recordTypeName + "' from Category dropdown");


            String refValue01 = "1";
            basePage.pause(2000);
            adminPageRecordtypes.enterRefMeaning(refValue01);
            ExtentReportListener.getExtentTest().pass("Entered '" + refValue01 + "' into Ref Meaning input field");



            basePage.pause(5000);
            adminPageRecordtypes.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("'Create' button is clicked successfully");



            String searchKeyword02 = "Test";

            basePage.pause(5000);
            adminPageRecordtypes.searchRecordByName(searchKeyword02);
            ExtentReportListener.getExtentTest().pass("Search executed with name '" + searchKeyword02 + "'");


            basePage.pause(5000);
            adminPageRecordtypes.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");


            basePage.pause(5000);
            adminPageRecordtypes.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("'Clear Selections' button clicked successfully");


            String searchKeyword03 = "@@@@@@@";

            basePage.pause(5000);
            adminPageRecordtypes.searchRecordByName(searchKeyword03);
            ExtentReportListener.getExtentTest().pass("Search executed with name '" + searchKeyword03 + "'");


            basePage.pause(5000);
            adminPageRecordtypes.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("'Search' button clicked successfully");


            basePage.pause(5000);
            adminPageRecordtypes.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("'Clear Selections' button clicked successfully");

            basePage.pause(5000);
            adminPageRecordtypes.clickFirstRecordTypeEditIcon();
            ExtentReportListener.getExtentTest().pass("Clicked Edit icon for the first Record Type in the list");

            basePage.pause(2000);
            adminPageRecordtypes.clickCancelOnEditRecordTypeModal();
            ExtentReportListener.getExtentTest().pass("Clicked Cancel on Edit Record Type modal");

            basePage.pause(5000);
            adminPageRecordtypes.clickFirstRecordTypeEditIcon();
            ExtentReportListener.getExtentTest().pass("Clicked Edit icon for the first Record Type in the list");

            basePage.pause(2000);
            adminPageRecordtypes.clickSaveOnEditRecordTypeModal();
            ExtentReportListener.getExtentTest().pass("Clicked Save on Edit Record Type modal");


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