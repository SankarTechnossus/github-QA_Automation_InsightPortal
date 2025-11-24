package tests.Exportcontrol.Sprint5;


import base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ExtentReportListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Administration.Attachment_Types.AttachmentTypes_ExportControlPage;
import pages.Administration.Instructions_Management.InstructionsManagement_ExportControlPage;
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;

@Listeners(ExtentReportListener.class)

public class PBI_248110_Instruction_Pages {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CreateExportControlPage createExportControlPage;
    InstructionsManagement_ExportControlPage instructionsManagementExportControlPage;

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
        createExportControlPage = new CreateExportControlPage(driver);
        instructionsManagementExportControlPage = new InstructionsManagement_ExportControlPage(driver);
    }

    @Test
    public void Instruction_Pages ()
    {
        try
        {
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

            // Navigate to Administration module
            dashboardPage.NavigateToAdministrationModule();
            ExtentReportListener.getExtentTest().info("User navigated to Administration module.");

            // Navigate to Export Control under Instructions Management module
            instructionsManagementExportControlPage.NavigateToInstructionsManagementExportControlPage();
            Assert.assertEquals(driver.getCurrentUrl(), "https://hollywood-insight4.partners.org/administration/instructions-management");
            ExtentReportListener.getExtentTest().pass("User navigated to Export Control page under Instructions Management.");

            String officeCode = JsonDataReader.get(5,"OfficeCode");
            String pageName = "";
            String content = "";

            for (int i=1; i<=3; i++)
            {
                if(i==1)
                {
                    pageName = JsonDataReader.get(5,"Page1");
                    content = "Test " + pageName + " Instructions.";
                } else if (i==2)
                {
                    pageName = JsonDataReader.get(5,"Page2");
                    content = "Test " + pageName + " Instructions.";
                }
                else
                {
                    pageName = JsonDataReader.get(5,"Page3");
                    content = "Test " + pageName + " Instructions.";
                }

                // Delete previously added instructions if any
                instructionsManagementExportControlPage.DeleteInstructionsIfAlreadyExist(pageName);
                ExtentReportListener.getExtentTest().info("There are no instructions with existing key and record type.");

                // Add New instructions
                instructionsManagementExportControlPage.AddNewInstructions(pageName, officeCode, content);

                String office = JsonDataReader.get(5,"Office");

                Assert.assertTrue(instructionsManagementExportControlPage.VerifyInstructionsAreAddedSuccessfully(pageName, office, content));
                ExtentReportListener.getExtentTest().pass("Instructions : " + content + " added successfully for page : " + pageName + " and office :" + office);
            }
        }
        catch (Exception e)
        {
            // User will capture and log any exceptions that occur during the test
            ExtentReportListener.getExtentTest().fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown()
    {
        DriverManager.quitDriver();

        // User will record browser closure in the test report
        ExtentReportListener.getExtentTest().info("Browser was successfully closed.");
    }
}