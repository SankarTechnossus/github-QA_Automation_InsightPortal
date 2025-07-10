package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtility;
import org.openqa.selenium.WebDriver;
import utils.TestConstants;
import utils.DriverManager;
import utils.ScreenshotUtility;
import com.aventstack.extentreports.MediaEntityBuilder;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExtentReportListener implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal <ExtentTest> test = new ThreadLocal < > ();
    private static Map< String, ExtentTest> classLevelTests = new ConcurrentHashMap< >();
    public static ExtentTest getExtentTest() {
        return test.get();
    }

    private void configureReport() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportFileName = "Esign_Agreement_PDF_Attachment_Positive_Flow_" + timeStamp + ".html";
        String reportsDirPath = System.getProperty("user.dir") + "/test_reports";

        File reportsDir = new File(reportsDirPath);
        if (!reportsDir.exists()) reportsDir.mkdirs();

        ExtentSparkReporter spark = new ExtentSparkReporter(reportsDirPath + "/" + reportFileName);
        spark.config().setDocumentTitle("Esign_Agreement_PDF_Attachment_Positive_Flow");
        spark.config().setReportName("Sprint 1 Automation");
        spark.config().setTheme(Theme.DARK); // You can change to Theme.STANDARD if needed

        extent = new ExtentReports();
        extent.attachReporter(spark);

        // You can modify or remove the below system info if needed
        extent.setSystemInfo("Host Name", "Automation Host");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Shankar");
    }


    @Override
    public synchronized void onStart(ITestContext context) {
        if (extent == null) configureReport();
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        if (extent == null) {
            System.err.println("Extent is null in onTestStart. Skipping report entry.");
            return;
        }
        String xmlName = result.getTestContext().getName();
        String className = result.getTestClass().getRealClass().getSimpleName();
        String method = result.getMethod().getMethodName();
        String key = xmlName + "::" + className;
        ExtentTest extentTest;
        if (TestConstants.ADD_CLASS_NAME) {
            String parentName = "<b>"+className+"</b>"+"::"+xmlName;
            ExtentTest parent = classLevelTests.computeIfAbsent(key, k -> extent.createTest(parentName));
            extentTest = parent.createNode(method);
        } else {
            extentTest = extent.createTest(xmlName);
        }
        test.set(extentTest);
        extentTest.log(Status.INFO, "Test Started: " + method);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest == null) return;
        String stepName = "Test Passed: " + result.getMethod().getMethodName();
        if (TestConstants.TAKE_SCREENSHOT_ON_PASS && DriverManager.getDriver() != null) {
            try {
                WebDriver driver = DriverManager.getDriver();
                String format = TestConstants.SCREENSHOT_FORMAT;
                String screenshotPath = null;
                if (TestConstants.TAKE_FULL_PAGE_SCREENSHOT) {
                    screenshotPath = "base64".equalsIgnoreCase(format)
                            ?
                            ScreenshotUtility.takeFullPageScreenshotAsBase64(driver)
                            :
                            ScreenshotUtility.takeFullPageScreenshotAsPNG(driver, "passed", result.getMethod().getMethodName());
                } else {
                    screenshotPath = "base64".equalsIgnoreCase(format)
                            ?
                            ScreenshotUtility.takeScreenshotAsBase64(driver)
                            :
                            ScreenshotUtility.takeScreenshotAsPNG(driver, "passed", result.getMethod().getMethodName());
                }

                if ("base64".equalsIgnoreCase(format)) {
                    extentTest.pass(stepName, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());
                } else if ("png".equalsIgnoreCase(format)) {
                    extentTest.pass(stepName, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } else {
                    extentTest.pass(stepName);
                    extentTest.log(Status.WARNING, "Invalid screenshot format: " + format);
                }
            } catch (Exception e) {
                extentTest.pass(stepName);
                extentTest.log(Status.WARNING, "Could not capture screenshot: " + e.getMessage());
            }
        } else {
            extentTest.log(Status.PASS, stepName);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest == null) return;
        extentTest.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        extentTest.log(Status.FAIL, result.getThrowable());
        if (TestConstants.TAKE_SCREENSHOT_ON_FAILURE && DriverManager.getDriver() != null) {
            try {
                WebDriver driver = DriverManager.getDriver();
                String format = TestConstants.SCREENSHOT_FORMAT;
                String screenshot = TestConstants.TAKE_FULL_PAGE_SCREENSHOT
                        ?
                        ("base64".equalsIgnoreCase(format)
                                ?
                                ScreenshotUtility.takeFullPageScreenshotAsBase64(driver)
                                :
                                ScreenshotUtility.takeFullPageScreenshotAsPNG(driver, "failed", result.getMethod().getMethodName()))
                        :
                        ("base64".equalsIgnoreCase(format)
                                ?
                                ScreenshotUtility.takeScreenshotAsBase64(driver)
                                :
                                ScreenshotUtility.takeScreenshotAsPNG(driver, "failed", result.getMethod().getMethodName()));
                if ("base64".equalsIgnoreCase(format)) {
                    extentTest.addScreenCaptureFromBase64String(screenshot, "Failed Screenshot");
                } else if ("png".equalsIgnoreCase(format)) {
                    extentTest.addScreenCaptureFromPath(screenshot);
                } else {
                    extentTest.log(Status.WARNING, "Invalid screenshot format: " + format);
                }
            } catch (Exception e) {
                extentTest.log(Status.WARNING, "Could not capture screenshot: " + e.getMessage());
            }
        }

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest == null) return;
        extentTest.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
        extentTest.log(Status.SKIP, result.getThrowable());
        if (TestConstants.TAKE_SCREENSHOT_ON_FAILURE && DriverManager.getDriver() != null) {
            try {
                WebDriver driver = DriverManager.getDriver();
                String format = TestConstants.SCREENSHOT_FORMAT;
                String screenshot = TestConstants.TAKE_FULL_PAGE_SCREENSHOT
                        ?
                        ("base64".equalsIgnoreCase(format)
                                ?
                                ScreenshotUtility.takeFullPageScreenshotAsBase64(driver)
                                :
                                ScreenshotUtility.takeFullPageScreenshotAsPNG(driver, "skipped", result.getMethod().getMethodName()))
                        :
                        ("base64".equalsIgnoreCase(format)
                                ?
                                ScreenshotUtility.takeScreenshotAsBase64(driver)
                                :
                                ScreenshotUtility.takeScreenshotAsPNG(driver, "skipped", result.getMethod().getMethodName()));
                if ("base64".equalsIgnoreCase(format)) {
                    extentTest.addScreenCaptureFromBase64String(screenshot, "Skipped Screenshot");
                } else if ("png".equalsIgnoreCase(format)) {
                    extentTest.addScreenCaptureFromPath(screenshot);
                } else {
                    extentTest.log(Status.WARNING, "Invalid screenshot format: " + format);
                }
            } catch (Exception e) {
                extentTest.log(Status.WARNING, "Could not capture screenshot for skipped test: " + e.getMessage());
            }
        }
    }

    // ####################################################################################################

    public static void logPassWithScreenshot(String logMessage) {
        try {
            String screenshotFormat = TestConstants.SCREENSHOT_FORMAT;
            if ("base64".equalsIgnoreCase(screenshotFormat)) {
                String base64Screenshot = ScreenshotUtility.takeScreenshotAsBase64(DriverManager.getDriver());
                getExtentTest().pass(logMessage,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            } else if ("png".equalsIgnoreCase(screenshotFormat)) {
                String screenshotPath = ScreenshotUtility.takeScreenshotAsPNG(DriverManager.getDriver(), "passed", logMessage);
                getExtentTest().pass(logMessage,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                getExtentTest().pass(logMessage);
                getExtentTest().log(Status.WARNING, "Invalid screenshot format in config: " + screenshotFormat);
            }
        } catch (Exception e) {
            getExtentTest().pass(logMessage);
            getExtentTest().log(Status.WARNING, "Could not capture screenshot: " + e.getMessage());
        }
    }
    public static void logFailWithScreenshot(String logMessage) {
        try {
            String screenshotFormat = TestConstants.SCREENSHOT_FORMAT;
            if ("base64".equalsIgnoreCase(screenshotFormat)) {
                String base64Screenshot = ScreenshotUtility.takeScreenshotAsBase64(DriverManager.getDriver());
                getExtentTest().fail(logMessage,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            } else if ("png".equalsIgnoreCase(screenshotFormat)) {
                String screenshotPath = ScreenshotUtility.takeScreenshotAsPNG(DriverManager.getDriver(), "passed", logMessage);
                getExtentTest().fail(logMessage,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                getExtentTest().fail(logMessage);
                getExtentTest().log(Status.WARNING, "Invalid screenshot format in config: " + screenshotFormat);
            }
        } catch (Exception e) {
            getExtentTest().fail(logMessage);
            getExtentTest().log(Status.WARNING, "Could not capture screenshot: " + e.getMessage());
        }
    }
    public static void logPassWithElementScreenshot(WebElement element, String stepName) {
        try {
            String screenshotFormat = TestConstants.SCREENSHOT_FORMAT;
            if ("base64".equalsIgnoreCase(screenshotFormat)) {
                String base64Screenshot = ScreenshotUtility.takeElementScreenshotAsBase64(element);
                getExtentTest().pass(stepName,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            } else if ("png".equalsIgnoreCase(screenshotFormat)) {
                String screenshotPath = ScreenshotUtility.takeElementScreenshotAsPNG(element, "passed", stepName);
                getExtentTest().pass(stepName,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                getExtentTest().pass(stepName);
                getExtentTest().log(Status.WARNING, "Invalid screenshot format in config: " + screenshotFormat);
            }
        } catch (Exception e) {
            getExtentTest().pass(stepName);
            getExtentTest().log(Status.WARNING, "Could not capture element screenshot: " + e.getMessage());
        }
    }
    public static void logStepPassWithFullElementScreenshot(WebElement element,String stepName) {
        try {
            String screenshotFormat = TestConstants.SCREENSHOT_FORMAT;
            if ("base64".equalsIgnoreCase(screenshotFormat)) {
                String base64Screenshot = ScreenshotUtility.takeFullElementScreenshotAsBase64(DriverManager.getDriver(),element);
                getExtentTest().pass(stepName,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            } else if ("png".equalsIgnoreCase(screenshotFormat)) {
                String screenshotPath = ScreenshotUtility.takeFullElementScreenshotAsPNG(DriverManager.getDriver(),element, "passed", stepName);
                getExtentTest().pass(stepName,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                getExtentTest().pass(stepName);
                getExtentTest().log(Status.WARNING, "Invalid screenshot format in config: " + screenshotFormat);
            }
        } catch (Exception e) {
            getExtentTest().pass(stepName);
            getExtentTest().log(Status.WARNING, "Could not capture element screenshot: " + e.getMessage());
        }
    }
    public static void logStepPassWithFullPageScreenshot(String stepName) {
        try {
            String screenshotFormat = TestConstants.SCREENSHOT_FORMAT;

            if ("base64".equalsIgnoreCase(screenshotFormat)) {
                String base64Screenshot = ScreenshotUtility.takeFullPageScreenshotAsBase64(DriverManager.getDriver());
                getExtentTest().pass(stepName,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            } else if ("png".equalsIgnoreCase(screenshotFormat)) {
                String screenshotPath = ScreenshotUtility.takeFullPageScreenshotAsPNG(DriverManager.getDriver(), "passed", stepName);
                getExtentTest().pass(stepName,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                getExtentTest().pass(stepName);
                getExtentTest().log(Status.WARNING, "Invalid screenshot format in config: " + screenshotFormat);
            }
        } catch (Exception e) {
            getExtentTest().pass(stepName);
            getExtentTest().log(Status.WARNING, "Could not capture full page screenshot: " + e.getMessage());
        }
    }
    public static void logStepPassWithFullPageScreenshot() {
        String defaultStepMessage = "Full page screenshot captured";

        try {
            String screenshotFormat = TestConstants.SCREENSHOT_FORMAT;

            if ("base64".equalsIgnoreCase(screenshotFormat)) {
                String base64Screenshot = ScreenshotUtility.takeFullPageScreenshotAsBase64(DriverManager.getDriver());
                getExtentTest().pass(defaultStepMessage,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
            } else if ("png".equalsIgnoreCase(screenshotFormat)) {
                String screenshotPath = ScreenshotUtility.takeFullPageScreenshotAsPNG(DriverManager.getDriver(), "passed", "full_page");
                getExtentTest().pass(defaultStepMessage,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else {
                getExtentTest().pass(defaultStepMessage);
                getExtentTest().log(Status.WARNING, "Invalid screenshot format in config: " + screenshotFormat);
            }
        } catch (Exception e) {
            getExtentTest().pass(defaultStepMessage);
            getExtentTest().log(Status.WARNING, "Could not capture full page screenshot: " + e.getMessage());
        }
    }

}