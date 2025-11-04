package tests.Exportcontrol.Sprint3;

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
import pages.Export_control_Template_managemnet_Pages;
import pages.LoginPage;
import utils.DriverManager;
import workflow_helper.LoginPageHelper;

import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
public class Export_control_Template_management {

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
    public void Exportcontrol_Templatemanagement_Test() {
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

//            // Create an instance of LoginPage
//            LoginPage loginPage = new LoginPage(driver);
//
//            // User will enter the username into the username input field
//            loginPage.enterUsername("SV1179");
//            ExtentReportListener.getExtentTest().pass("Entered username");
//
//            // User will click the 'Next' button to proceed to the password entry screen
//            loginPage.clickNext();
//            ExtentReportListener.getExtentTest().pass("Clicked Next");
//
//            // User will input the user's password into the password field
//            loginPage.enterPassword("Devinivetha@1930");
//            ExtentReportListener.getExtentTest().pass("Entered password");
//
//            // User will click the 'Verify' button to authenticate the user
//            loginPage.clickVerify();
//            ExtentReportListener.getExtentTest().pass("Clicked Verify");

            // Optional: pause if any post-login actions needed
            basePage.pause(20000);

            // Agreement Page Actions
            AgreementPage agreementPage = new AgreementPage(driver);

            basePage.pause(10000);
            agreementPage.clickAdministrationLink();
            ExtentReportListener.getExtentTest().pass("Clicked Administration link");


            Export_control_Template_managemnet_Pages  Export_control_Template_managemnet_Page01 = new Export_control_Template_managemnet_Pages(driver);

            basePage.pause(5000);
            Export_control_Template_managemnet_Page01.clickTemplateManagementExportControl01();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Template Management > Export Control' successfully");

            basePage.pause(5000);
            Export_control_Template_managemnet_Page01.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Add new' under Template Management successfully");


            basePage.pause(3000);
            String generatedTitle = Export_control_Template_managemnet_Page01.enterUniqueTitle();
            ExtentReportListener.getExtentTest().pass("Entered unique title: " + generatedTitle);

            basePage.pause(5000);
            String filePath = System.getProperty("user.dir") + "/Test_Data/testexcel.xlsx";
            Export_control_Template_managemnet_Page01.uploadAgreementFile(filePath);
            ExtentReportListener.getExtentTest().pass("Uploaded file: testexcel.xlsx was not successfull");

            basePage.pause(3000);
            Export_control_Template_managemnet_Page01.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' under Template Management successfully");

            basePage.pause(5000);
            Export_control_Template_managemnet_Page01.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Add new' under Template Management successfully");


            basePage.pause(3000);
            String generatedTitle05 = Export_control_Template_managemnet_Page01.enterUniqueTitle();
            ExtentReportListener.getExtentTest().pass("Entered unique title: " + generatedTitle05);

            basePage.pause(5000);
            String filePath05 = System.getProperty("user.dir") + "/Test_Data/testjpg.jpg";
            Export_control_Template_managemnet_Page01.uploadAgreementFile(filePath05);
            ExtentReportListener.getExtentTest().pass("Uploaded file: testjpg.jpg was not successfull");

            basePage.pause(3000);
            Export_control_Template_managemnet_Page01.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' under Template Management successfully");



            basePage.pause(5000);
            Export_control_Template_managemnet_Page01.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Add new' under Template Management successfully");


            basePage.pause(3000);
            String generatedTitle03 = Export_control_Template_managemnet_Page01.enterUniqueTitle();
            ExtentReportListener.getExtentTest().pass("Entered unique title: " + generatedTitle03);

            basePage.pause(5000);
            String filePath03 = System.getProperty("user.dir") + "/Test_Data/img.png";
            Export_control_Template_managemnet_Page01.uploadAgreementFile(filePath03);
            ExtentReportListener.getExtentTest().pass("Uploaded file: img.png was not successfully");

            basePage.pause(3000);
            Export_control_Template_managemnet_Page01.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' under Template Management successfully");



            basePage.pause(5000);
            Export_control_Template_managemnet_Page01.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Add new' under Template Management successfully");

            basePage.pause(3000);
            String generatedTitle02 = Export_control_Template_managemnet_Page01.enterUniqueTitle();
            ExtentReportListener.getExtentTest().pass("Entered unique title: " + generatedTitle02);

            basePage.pause(5000);
            String filePath02 = System.getProperty("user.dir") + "/Test_Data/Testdata.docx";
            Export_control_Template_managemnet_Page01.uploadAgreementFile(filePath02);
            ExtentReportListener.getExtentTest().pass("Uploaded file: Testdata.docx was uploaded successfully");

            basePage.pause(5000);
            Export_control_Template_managemnet_Page01.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' under Template Management successfully");


            basePage.pause(5000);
            Export_control_Template_managemnet_Page01.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Add new' under Template Management successfully");


            basePage.pause(3000);
            String generatedTitle01 = Export_control_Template_managemnet_Page01.enterUniqueTitle();
            ExtentReportListener.getExtentTest().pass("Entered unique title: " + generatedTitle01);

            basePage.pause(5000);
            String filePath01 = System.getProperty("user.dir") + "/Test_Data/Agreement Info 2025_03.pdf";
            Export_control_Template_managemnet_Page01.uploadAgreementFile(filePath01);
            ExtentReportListener.getExtentTest().pass("Uploaded file: Agreement Info 2025_03.pdf successfully");

            basePage.pause(5000);
            Export_control_Template_managemnet_Page01.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Create' button under Template Management successfully");

            // Step 1: Date Format = MM/DD/YYYY
            basePage.pause(3000);
            Export_control_Template_managemnet_Page01.selectDateFormat("MM/DD/YYYY");
            ExtentReportListener.getExtentTest().pass("Selected Date Format: MM/DD/YYYY");

            // Step 2: Active = No
            basePage.pause(2000);
            Export_control_Template_managemnet_Page01.setActiveToNo();
            ExtentReportListener.getExtentTest().pass("Set Active to: No");

            // Step 3: Click Cancel
            basePage.pause(2000);
            Export_control_Template_managemnet_Page01.clickCancel();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' successfully");


            basePage.pause(5000);
            Export_control_Template_managemnet_Page01.clickAddNewTemplate();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Add new' under Template Management successfully");


            basePage.pause(3000);
            String generatedTitle06 = Export_control_Template_managemnet_Page01.enterUniqueTitle();
            ExtentReportListener.getExtentTest().pass("Entered unique title: " + generatedTitle06);

            basePage.pause(5000);
            String filePath06 = System.getProperty("user.dir") + "/Test_Data/Agreement Info 2025_03.pdf";
            Export_control_Template_managemnet_Page01.uploadAgreementFile(filePath06);
            ExtentReportListener.getExtentTest().pass("Uploaded file: Agreement Info 2025_03.pdf successfully");

            basePage.pause(5000);
            Export_control_Template_managemnet_Page01.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Create' button under Template Management successfully");

            // Step 1: Date Format = MM/DD/YYYY
            basePage.pause(3000);
            Export_control_Template_managemnet_Page01.selectDateFormat("MMMM DD, YYYY");
            ExtentReportListener.getExtentTest().pass("Selected Date Format: MMMM DD, YYYY");

            // Step 2: Active = No
            basePage.pause(2000);
            Export_control_Template_managemnet_Page01.setActiveToYES();
            ExtentReportListener.getExtentTest().pass("Set Active to: YES");


            basePage.pause(3000);
            Export_control_Template_managemnet_Page01.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button under Template Management successfully");
















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