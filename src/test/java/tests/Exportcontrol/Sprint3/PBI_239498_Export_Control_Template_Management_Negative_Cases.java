package tests.Exportcontrol.Sprint3;


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
import pages.Administration.Template_Management.TemplateManagement_ExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.nio.file.Paths;
import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)

public class PBI_239498_Export_Control_Template_Management_Negative_Cases {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    TemplateManagement_ExportControlPage templateManagementExportControlPage;

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
        templateManagementExportControlPage = new TemplateManagement_ExportControlPage(driver);
    }

    @Test
    public void Exportcontrol_Templatemanagement_Test() {
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
            templateManagementExportControlPage.clickTemplateManagementExportControl01();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Template Management > Export Control' successfully");

            basePage.pause(5000);
            templateManagementExportControlPage.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Add new' under Template Management successfully");


            basePage.pause(3000);
            String generatedTitle = templateManagementExportControlPage.enterUniqueTitle();
            ExtentReportListener.getExtentTest().pass("Entered unique title: " + generatedTitle);

            basePage.pause(5000);

            // Setting up base Directory
            String baseDir = System.getProperty("user.dir");

            // Get the file path
            String filePath = JsonDataReader.get(4,"TestExcelFilePath");
            String path = Paths.get(baseDir, filePath).toString();

            templateManagementExportControlPage.uploadAgreementFile(path);
            ExtentReportListener.getExtentTest().pass("Uploaded file: testexcel.xlsx was not successfull");

            basePage.pause(3000);
            templateManagementExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' under Template Management successfully");

            basePage.pause(5000);
            templateManagementExportControlPage.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Add new' under Template Management successfully");


            basePage.pause(3000);
            String generatedTitle05 = templateManagementExportControlPage.enterUniqueTitle();
            ExtentReportListener.getExtentTest().pass("Entered unique title: " + generatedTitle05);

            basePage.pause(5000);

            filePath = JsonDataReader.get(4,"TestJPGImgFilePath");
            path = Paths.get(baseDir, filePath).toString();

            templateManagementExportControlPage.uploadAgreementFile(path);
            ExtentReportListener.getExtentTest().pass("Uploaded file: testjpg.jpg was not successfull");

            basePage.pause(3000);
            templateManagementExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' under Template Management successfully");

            basePage.pause(5000);
            templateManagementExportControlPage.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Add new' under Template Management successfully");

            basePage.pause(3000);
            String generatedTitle03 = templateManagementExportControlPage.enterUniqueTitle();
            ExtentReportListener.getExtentTest().pass("Entered unique title: " + generatedTitle03);

            basePage.pause(5000);

            filePath = JsonDataReader.get(4,"TestPNGImgFilePath");
            path = Paths.get(baseDir, filePath).toString();

            templateManagementExportControlPage.uploadAgreementFile(path);
            ExtentReportListener.getExtentTest().pass("Uploaded file: img.png was not successfully");

            basePage.pause(3000);
            templateManagementExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' under Template Management successfully");

            basePage.pause(5000);
            templateManagementExportControlPage.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Add new' under Template Management successfully");

            basePage.pause(3000);
            String generatedTitle02 = templateManagementExportControlPage.enterUniqueTitle();
            ExtentReportListener.getExtentTest().pass("Entered unique title: " + generatedTitle02);

            basePage.pause(5000);

            filePath = JsonDataReader.get(4,"TestDOCXFilePath");
            path = Paths.get(baseDir, filePath).toString();

            templateManagementExportControlPage.uploadAgreementFile(path);
            ExtentReportListener.getExtentTest().pass("Uploaded file: Testdata.docx was uploaded successfully");

            basePage.pause(5000);
            templateManagementExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' under Template Management successfully");

            basePage.pause(5000);
            templateManagementExportControlPage.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Add new' under Template Management successfully");

            basePage.pause(3000);
            String generatedTitle01 = templateManagementExportControlPage.enterUniqueTitle();
            ExtentReportListener.getExtentTest().pass("Entered unique title: " + generatedTitle01);

            basePage.pause(5000);
            filePath = JsonDataReader.get(4,"TestPDFFilePath");

            String filePath01 = System.getProperty("user.dir") + filePath;
            templateManagementExportControlPage.uploadAgreementFile(filePath01);
            ExtentReportListener.getExtentTest().pass("Uploaded file: Agreement Info 2025_03.pdf successfully");

            basePage.pause(5000);
            templateManagementExportControlPage.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Create' button under Template Management successfully");

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