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
        agreementPage = new AgreementPage(driver);
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

            basePage.pause(10000);
            agreementPage.clickAdministrationLink();
            Assert.assertTrue(agreementPage.isDashboardNotificationsSummaryDisplayed(), "Dashboard Notifications - Summary page is NOT displayed after clicking Administration link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Dashboard Notifications - Summary page.");

            basePage.pause(10000);
            agreementPage.clickFormsManagementLink();
            Assert.assertTrue(agreementPage.isFormsManagementPageDisplayed(), "Forms Management page is not displayed after clicking Forms Management link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Forms Management page.");

            basePage.pause(3000);
            agreementPage.scrollSidebarToExportControlAndClick();
            ExtentReportListener.getExtentTest().info("Scrolled and clicked on 'Export Control' from left navigation.");

            agreementPage.enterSearchText(positiveSearchText);
            ExtentReportListener.getExtentTest().pass("Entered '" + positiveSearchText + "' in Search by Name input");
            Assert.assertTrue(agreementPage.isSearchResultDisplayed(), "Search result is NOT displayed for the entered search text: " + positiveSearchText);
            ExtentReportListener.getExtentTest().pass("Search result is displayed successfully for: " + positiveSearchText);

            basePage.pause(10000);
            agreementPage.clickSearchButton();
            Assert.assertTrue(agreementPage.isSearchResultDisplayed(), "Search result is found: " + positiveSearchText);
            ExtentReportListener.getExtentTest().pass("Clicked Search button");

            basePage.pause(10000);
            agreementPage.clickClearSelectionsButton();
            Assert.assertTrue(agreementPage.isSearchResultDisplayed(), "Search result is found: " + positiveSearchText);
            ExtentReportListener.getExtentTest().pass("Clicked Clear Selections button");

            //**** Negative case ***
            agreementPage.enterSearchText(negativeSearchText);
            Assert.assertTrue(agreementPage.isSearchResultDisplayed(), "Search result is found: " + positiveSearchText);
            ExtentReportListener.getExtentTest().pass("Entered '" + negativeSearchText + "' in Search by Name input");

            basePage.pause(5000);
            agreementPage.clickSearchButton();
            Assert.assertTrue(agreementPage.isSearchResultDisplayed(), "Search result is found: " + positiveSearchText);
            ExtentReportListener.getExtentTest().pass("Clicked Search button");


            basePage.pause(5000);
            agreementPage.clickClearSelectionsButton();
            Assert.assertTrue(agreementPage.isSearchResultDisplayed(), "Search result is found: " + positiveSearchText);
            ExtentReportListener.getExtentTest().pass("Clicked Clear Selections button");

            basePage.pause(5000);  // Optional wait if needed
            agreementPage.clickAddNewLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add new' link on Export Control page");
            Assert.assertTrue(agreementPage.isNewFormPageDisplayed(), "New Form page is NOT displayed after clicking 'Add new' link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to New Form page after clicking 'Add new' link.");


            String dynamicName = UniqueNameGenerator.generateNextName();
            agreementPage.enterName(dynamicName);
            ExtentReportListener.getExtentTest().info("Entered '" + dynamicName + "' into Name input field");

            basePage.pause(5000);
            agreementPage.enterDescription(formDescription);
            ExtentReportListener.getExtentTest().info("Entered '" + formDescription + "' into Description text area");

            basePage.pause(5000);
            agreementPage.selectTypeAsExportControlRequestnew01();
            ExtentReportListener.getExtentTest().info("Selected 'Export Control Request' from Type dropdown successfully");

            basePage.pause(5000);
            agreementPage.selectCategoryAsGeneral();
            ExtentReportListener.getExtentTest().info("Selected 'General' from Category dropdown");

            basePage.pause(5000);
            agreementPage.enterCategorySequenceNo(formCategorySeqNo);
            ExtentReportListener.getExtentTest().info("Entered '" + formCategorySeqNo + "' into Category Sequence No field");

            basePage.pause(5000);
            agreementPage.clickCreateButton();
            ExtentReportListener.getExtentTest().pass("Clicked the 'Create' button");
            Assert.assertTrue(agreementPage.isFormVersionsPageDisplayed(), "Form Versions page is NOT displayed after clicking the 'Create' button");
            ExtentReportListener.getExtentTest().pass("Form created successfully and Form Versions page is displayed.");

            basePage.pause(5000);
            agreementPage.clickVersion1Link();
            ExtentReportListener.getExtentTest().pass("Clicked on 'Version 1' link");
            Assert.assertTrue(agreementPage.isVersion1PageDisplayed(), "'Version 1' heading is NOT displayed after clicking on Version 1 link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Version 1 page and heading is displayed.");

            basePage.pause(9000);
            formsManagementExportControlPage.clickAddRootLevelQuestionButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add root level question' button");

            basePage.pause(9000);
            formsManagementExportControlPage.clickRadioButtonGroupOption();
            ExtentReportListener.getExtentTest().pass("Clicked 'Radio button group' in Add Question Modal");
            Assert.assertTrue(agreementPage.isVersion1PageDisplayed(), "'Version 1' heading is NOT displayed after clicking on Version 1 link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Version 1 page and heading is displayed.");

            basePage.pause(7000);
            formsManagementExportControlPage.enterRadioOption1Text(radioYes);
            ExtentReportListener.getExtentTest().info("Entered text 'Yes' into the first radio option input");

            basePage.pause(5000);
            formsManagementExportControlPage.clickAddOptionButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Add option' button to add new radio choice");

            basePage.pause(5000);
            formsManagementExportControlPage.enterRadioOption2Text(radioNo);
            ExtentReportListener.getExtentTest().info("Entered text 'No' into the second radio option input");

            basePage.pause(8000);
            formsManagementExportControlPage.checkReadOnly();
            ExtentReportListener.getExtentTest().info("Checked the 'Read only' checkbox");

            basePage.pause(5000);
            formsManagementExportControlPage.enterHelpText(helpText);
            ExtentReportListener.getExtentTest().info("Entered help text as 'test01'");

            basePage.pause(5000);
            formsManagementExportControlPage.clickApplyButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Apply' button");
            Assert.assertTrue(formsManagementExportControlPage.isSaveButtonDisplayed(), "'Save' button is NOT displayed after clicking the 'Apply' button");
            ExtentReportListener.getExtentTest().pass("'Save' button is displayed successfully after clicking 'Apply'.");

            basePage.pause(5000);
            formsManagementExportControlPage.clickPreviewLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Preview' link");
            Assert.assertTrue(formsManagementExportControlPage.isPreviewPageDisplayed(), "'Preview' heading is NOT displayed after clicking the Preview link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Preview page and heading is displayed.");

            basePage.pause(5000);
            formsManagementExportControlPage.clickClosePreviewLink();
            ExtentReportListener.getExtentTest().pass("Clicked 'Close preview' link");
            Assert.assertTrue(agreementPage.isVersion1PageDisplayed(), "'Version 1' heading is NOT displayed after clicking on Version 1 link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Version 1 page and heading is displayed.");

            basePage.pause(5000);
            formsManagementExportControlPage.clickSaveButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button");
            Assert.assertTrue(agreementPage.isVersion1PageDisplayed(), "'Version 1' heading is NOT displayed after clicking on Version 1 link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Version 1 page and heading is displayed.");

            //********** Negative_case ************

            basePage.pause(10000);
            formsManagementExportControlPage.clickAddChildQuestion();
            ExtentReportListener.getExtentTest().info("Clicked 'Add child question' button successfully");

            basePage.pause(10000);
            formsManagementExportControlPage.clickOutsidePopupByOffset();
            ExtentReportListener.getExtentTest().info("Clicked outside popup using offset successfully");

            basePage.pause(10000);
            formsManagementExportControlPage.clickMoveButton();
            ExtentReportListener.getExtentTest().info(" Clicked 'Move' button successfully");

            basePage.pause(10000);
            formsManagementExportControlPage.clickCancelMovingButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel moving' button successfully");

            basePage.pause(10000);
            formsManagementExportControlPage.clickEditButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit' button successfully");
            Assert.assertTrue(agreementPage.isVersion1PageDisplayed(), "'Version 1' heading is NOT displayed after clicking on Version 1 link");
            ExtentReportListener.getExtentTest().pass("User successfully navigated to Version 1 page and heading is displayed.");

            basePage.pause(8000);
            formsManagementExportControlPage.clickCancelButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' button successfully");

            basePage.pause(8000);
            formsManagementExportControlPage.clickRemoveButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Remove' button successfully");

            basePage.pause(9000);
            formsManagementExportControlPage.clickUndoButton();
            ExtentReportListener.getExtentTest().info("Clicked 'Undo' button successfully");

            basePage.pause(8000);
            formsManagementExportControlPage.clickTestFormLink();
            ExtentReportListener.getExtentTest().pass("Clicked on test form link successfully");
            Assert.assertTrue(agreementPage.isFormVersionsPageDisplayed(), "Form Versions page is NOT displayed after clicking the 'Create' button");
            ExtentReportListener.getExtentTest().pass("Form created successfully and Form Versions page is displayed.");

            basePage.pause(10000);
            formsManagementExportControlPage.clickEditDescriptionButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit description' button successfully");
            Assert.assertTrue(agreementPage.isFormVersionsPageDisplayed(), "Form Versions page is NOT displayed after clicking the 'Create' button");
            ExtentReportListener.getExtentTest().pass("Form created successfully and Form Versions page is displayed.");

            basePage.pause(10000);
            formsManagementExportControlPage.clickCancelButtononversionedit01();
            ExtentReportListener.getExtentTest().pass("Clicked 'Cancel' button successfully");
            Assert.assertTrue(agreementPage.isFormVersionsPageDisplayed(), "Form Versions page is NOT displayed after clicking the 'Create' button");
            ExtentReportListener.getExtentTest().pass("Form created successfully and Form Versions page is displayed.");

            basePage.pause(10000);
            formsManagementExportControlPage.clickEditDescriptionButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Edit description' button successfully");
            Assert.assertTrue(agreementPage.isFormVersionsPageDisplayed(), "Form Versions page is NOT displayed after clicking the 'Create' button");
            ExtentReportListener.getExtentTest().pass("Form created successfully and Form Versions page is displayed.");

            basePage.pause(10000);
            formsManagementExportControlPage.enterVersionText(versionDesc);
            ExtentReportListener.getExtentTest().info("Entered version description '" + versionDesc + "' successfully");

            basePage.pause(10000);
            formsManagementExportControlPage.clickSaveButtonsmall();
            ExtentReportListener.getExtentTest().pass("Clicked 'Save' button successfully");
            Assert.assertTrue(agreementPage.isFormVersionsPageDisplayed(), "Form Versions page is NOT displayed after clicking the 'Create' button");
            ExtentReportListener.getExtentTest().pass("Form created successfully and Form Versions page is displayed.");

            basePage.pause(9000);
            formsManagementExportControlPage.clickAddNewButton();
            ExtentReportListener.getExtentTest().pass("Clicked 'Add new' button successfully");
            Assert.assertTrue(agreementPage.isFormVersionsPageDisplayed(), "Form Versions page is NOT displayed after clicking the 'Create' button");
            ExtentReportListener.getExtentTest().pass("Form created successfully and Form Versions page is displayed.");

            basePage.pause(9000);
            formsManagementExportControlPage.clickCancelButtonaddnewcancel();
            ExtentReportListener.getExtentTest().info("Clicked 'Cancel' button successfully");

            basePage.pause(5000);
            formsManagementExportControlPage.clickFormManagementLink();
            ExtentReportListener.getExtentTest().info("Clicked 'Form Management' link successfully");

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