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
import pages.Administration.Forms_Management.FormsManagement_ExportControlPage;
import pages.Export_Control.Actions.CreateExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import utils.DriverManager;
import utils.JsonDataReader;
import java.nio.file.Paths;

import java.time.Duration;
import java.io.File;

@Listeners(ExtentReportListener.class)

public class PBI_247368_Attachment_Component_Integration_EndUser {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    FormsManagement_ExportControlPage formsManagementExportControlPage;
    CreateExportControlPage createExportControlPage;

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
        formsManagementExportControlPage = new FormsManagement_ExportControlPage(driver);
        createExportControlPage = new CreateExportControlPage(driver);
    }

    @Test
    public void AttachmentComponentIntegration_EndUser ()
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

            // Navigate to Export Control module
            dashboardPage.NavigateToExportControlModule();
            ExtentReportListener.getExtentTest().info("User navigated to Export Control module.");

            // Navigate to Create Export Control page
            createExportControlPage.NavigateToCreateExportControlPage();
            ExtentReportListener.getExtentTest().info("User navigated to Create Export Control page.");

            // Create Export Control
            String piName = JsonDataReader.get(2,"PIName");

            createExportControlPage.CreateExportControl(piName);
            Assert.assertTrue(createExportControlPage.VerifyExportControlIsCreatedSuccessfully());
            String recordNo = createExportControlPage.GetExportControlRecordNumber();
            ExtentReportListener.getExtentTest().pass("Export Control created successfully with Record Number : " + recordNo);

            // Navigate to attachments
            createExportControlPage.NavigateToAttachments();
            ExtentReportListener.getExtentTest().pass("User navigated to Attachments screen.");

            // Build absolute path from project
            String baseDir = System.getProperty("user.dir");
            String folderPath = JsonDataReader.get(4,"TestFilesFolderPath");

            // Create folder object
            File folder = new File(folderPath);

            if(folder.exists() && folder.isDirectory())
            {
                File[] files = folder.listFiles();
                Assert.assertNotNull(files);

                System.out.println("Total files: " + files.length);

                for (File file : files)
                {
                    if (file.isFile())
                    {
                        // Get file name
                        String fileName = file.getName();
                        System.out.println("File: " + fileName);

                        // Get file path
                        String filePath = Paths.get(baseDir, folderPath, fileName).toString();

                        // Upload file
                        createExportControlPage.UploadAnAttachment(filePath);
                    }
                }
            } else
            {
                System.out.println("Folder not found!");
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