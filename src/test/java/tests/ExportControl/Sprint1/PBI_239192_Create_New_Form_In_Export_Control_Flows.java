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
import pages.Administration.Forms_Management.FormsManagement_ExportControlPage;
import pages.Home.DashboardPage;
import pages.Home.LoginPage;
import pages.Adobe.AgreementPage;
import utils.DriverManager;
import utils.JsonDataReader;
import utils.UniqueNameGenerator;
import java.time.Duration;

@Listeners(listeners.ExtentReportListener.class)
public class PBI_239192_Create_New_Form_In_Export_Control_Flows {

    WebDriver driver;
    WebDriverWait wait;
    BasePage basePage;
    LoginPage loginPage;
    DashboardPage dashboardPage;
    FormsManagement_ExportControlPage formsManagementExportControlPage;
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
        formsManagementExportControlPage = new FormsManagement_ExportControlPage(driver);
    }

    @Test
    public void createFormFlowExportControl() {
        ExtentReportListener.getExtentTest().info("your log message");
        try {
            String url = JsonDataReader.get(0,"URL");
            String userName = JsonDataReader.get(0,"Username");
            String password = JsonDataReader.get(0,"Password");

            // === Form & flow test data ===
            String positiveSearchText = JsonDataReader.get(1, "PositiveSearchText");
            String negativeSearchText = JsonDataReader.get(1, "NegativeSearchText");
            String formDescription    = JsonDataReader.get(1, "FormDescription");
            String formCategorySeqNo  = JsonDataReader.get(1, "CategorySeqNo");
            String radioYes           = JsonDataReader.get(1, "RadioOptionYes");
            String radioNo            = JsonDataReader.get(1, "RadioOptionNo");
            String helpText           = JsonDataReader.get(1, "HelpText");
            String versionDesc        = JsonDataReader.get(1, "VersionDescription");

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
            Assert.assertTrue(agreementPage.isAdministrationPageDisplayed(), "Administration page is not displayed after clicking Administration link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Administration page.");

            basePage.pause(10000);
            agreementPage.clickFormsManagementLink();
            ExtentReportListener.getExtentTest().pass("Clicked Forms Management link");

            basePage.pause(3000);  // Optional wait
            agreementPage.scrollSidebarToExportControlAndClick();
            ExtentReportListener.getExtentTest().pass("Scrolled and clicked on 'Export Control' from left navigation.");

            agreementPage.enterSearchText(positiveSearchText);
            ExtentReportListener.getExtentTest().pass("Entered '" + positiveSearchText + "' in Search by Name input");

            basePage.pause(10000);
            agreementPage.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("Clicked Search button");

            basePage.pause(10000);
            agreementPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("Clicked Clear Selections button");

            //**** Negative case ***
            agreementPage.enterSearchText(negativeSearchText);
            ExtentReportListener.getExtentTest().pass("Entered '" + negativeSearchText + "' in Search by Name input");

            basePage.pause(5000);
            agreementPage.clickSearchButton();
            ExtentReportListener.getExtentTest().pass("Clicked Search button");


            basePage.pause(5000);
            agreementPage.clickClearSelectionsButton();
            ExtentReportListener.getExtentTest().pass("Clicked Clear Selections button");

            basePage.pause(5000);  // Optional wait if needed
            agreementPage.clickAddNewLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add new' link on Export Control page");

            String dynamicName = UniqueNameGenerator.generateNextName();
            agreementPage.enterName(dynamicName);
            ExtentReportListener.getExtentTest().pass("Entered '" + dynamicName + "' into Name input field");

            basePage.pause(5000);
            agreementPage.enterDescription(formDescription);
            ExtentReportListener.getExtentTest().pass("Entered '" + formDescription + "' into Description text area");

            basePage.pause(5000);
            agreementPage.selectTypeAsExportControlRequestnew01();
            ExtentReportListener.getExtentTest().pass("Selected 'Export Control Request' from Type dropdown successfully");

            basePage.pause(5000);
            agreementPage.selectCategoryAsGeneral();
            ExtentReportListener.getExtentTest().pass("Selected 'General' from Category dropdown");

            basePage.pause(5000);
            agreementPage.enterCategorySequenceNo(formCategorySeqNo);
            ExtentReportListener.getExtentTest().pass("Entered '" + formCategorySeqNo + "' into Category Sequence No field");

            basePage.pause(5000);
            agreementPage.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("Clicked the 'Create' button");

            basePage.pause(5000);
            agreementPage.clickVersion1Link();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Version 1' link");

            basePage.pause(9000);
            formsManagementExportControlPage.clickAddRootLevelQuestionButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add root level question' button");

            basePage.pause(9000);
            formsManagementExportControlPage.clickRadioButtonGroupOption();
            ExtentReportListener.getExtentTest().pass("Clicked 'Radio button group' in Add Question Modal");

            basePage.pause(7000);
            formsManagementExportControlPage.enterRadioOption1Text(radioYes);
            ExtentReportListener.getExtentTest().pass("Entered text 'Yes' into the first radio option input");

            basePage.pause(5000);
            formsManagementExportControlPage.clickAddOptionButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add option' button to add new radio choice");

            basePage.pause(5000);
            formsManagementExportControlPage.enterRadioOption2Text(radioNo);
            ExtentReportListener.getExtentTest().pass("Entered text 'No' into the second radio option input");

            basePage.pause(8000);
            formsManagementExportControlPage.checkReadOnly();
            ExtentReportListener.getExtentTest().pass("Checked the 'Read only' checkbox");

            basePage.pause(5000);
            formsManagementExportControlPage.enterHelpText(helpText);
            ExtentReportListener.getExtentTest().pass("Entered help text as 'test01'");

            basePage.pause(5000);
            formsManagementExportControlPage.clickApplyButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Apply' button");

            basePage.pause(5000);
            formsManagementExportControlPage.clickPreviewLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Preview' link");

            basePage.pause(5000);
            formsManagementExportControlPage.clickClosePreviewLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Close preview' link");

            basePage.pause(5000);
            formsManagementExportControlPage.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button");

            //********** Negative_case ************

            basePage.pause(10000);
            formsManagementExportControlPage.clickAddChildQuestion();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add child question' button successfully");

            basePage.pause(10000);
            formsManagementExportControlPage.clickOutsidePopupByOffset();
            ExtentReportListener.getExtentTest().pass("Clicked outside popup using offset successfully");

            basePage.pause(10000);
            formsManagementExportControlPage.clickMoveButton();
            ExtentReportListener.getExtentTest().pass(" Clicked 'Move' button successfully");

            basePage.pause(10000);
            formsManagementExportControlPage.clickCancelMovingButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel moving' button successfully");

            basePage.pause(10000);
            formsManagementExportControlPage.clickEditButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit' button successfully");

            basePage.pause(8000);
            formsManagementExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");

            basePage.pause(8000);
            formsManagementExportControlPage.clickRemoveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Remove' button successfully");

            basePage.pause(9000);
            formsManagementExportControlPage.clickUndoButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Undo' button successfully");

            basePage.pause(8000);
            formsManagementExportControlPage.clickTestFormLink();
            ExtentReportListener.getExtentTest().pass("Clicked on test form link successfully");

            basePage.pause(10000);
            formsManagementExportControlPage.clickEditDescriptionButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit description' button successfully");

            basePage.pause(10000);
            formsManagementExportControlPage.clickCancelButtononversionedit01();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");

            basePage.pause(10000);
            formsManagementExportControlPage.clickEditDescriptionButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit description' button successfully");

            basePage.pause(10000);
            formsManagementExportControlPage.enterVersionText(versionDesc);
            ExtentReportListener.getExtentTest().pass("Entered version description '" + versionDesc + "' successfully");

            basePage.pause(10000);
            formsManagementExportControlPage.clickSaveButtonsmall();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");

            basePage.pause(9000);
            formsManagementExportControlPage.clickAddNewButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add new' button successfully");

            basePage.pause(9000);
            formsManagementExportControlPage.clickCancelButtonaddnewcancel();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");

            basePage.pause(5000);
            formsManagementExportControlPage.clickFormManagementLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Form Management' link successfully");
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