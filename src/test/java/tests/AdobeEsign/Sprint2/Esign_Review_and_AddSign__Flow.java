package tests.AdobeEsign.Sprint2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.time.Duration;

public class Esign_Review_and_AddSign__Flow {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void setupExtentReport() {
        // The user creates a directory to store the HTML test report
        new File("test_reports").mkdirs();

        // The user initializes the ExtentSparkReporter to generate the HTML report
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test_reports/Esign_Review_and_Sign_Flow.html");

        // The user sets the document title and report name for the test
        htmlReporter.config().setDocumentTitle("Esign_Review_and_Sign_Flow");
        htmlReporter.config().setReportName("Sprint 1 Automation");

        // The user attaches the reporter to the ExtentReports instance
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setupBrowser() {
        // The user sets up and configures the Chrome WebDriver
        WebDriverManager.chromedriver().setup();

        // The user launches a new Chrome browser instance
        driver = new ChromeDriver();

        // The user maximizes the browser window
        driver.manage().window().maximize();

        // The user initializes explicit wait for element handling
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void createFormFlow() {
        // The user creates a new test entry in the Extent Report
        test = extent.createTest("Adding_Esign_Flow_From_Email");

        try {
            // The user opens the login page URL from the email
            driver.get("https://nam02.safelinks.protection.outlook.com/?url=https%3A%2F%2Fmassgen.na1.echosign.com%2Fpublic%2Fesign%3Ftsid%3DCBFCIBAACBSCTBABDUAAABACAABAAYAhWp28xI55H_QEKwTye7GfJYtqTRk4lh0tybV5Dec8Dk9SXbxZ4RTRYHhh9ywYMYTataorCkgk-pjtTiWYdVoL7ybAwDyUkn3GmqkgSHdraPMN7_jCy0PCz-qEWWO_3%26&data=05%7C02%7Csankar.venkatesan%40technossus.com%7C5fb2f2465aaa419f810e08dde60d0dbb%7C7284c0574078417092c01bcb11c55269%7C0%7C0%7C638919665919384935%7CUnknown%7CTWFpbGZsb3d8eyJFbXB0eU1hcGkiOnRydWUsIlYiOiIwLjAuMDAwMCIsIlAiOiJXaW4zMiIsIkFOIjoiTWFpbCIsIldUIjoyfQ%3D%3D%7C0%7C%7C%7C&sdata=F8vEoMCSILpvNAh5x2LvgMszyzffK%2BxnJmJno6mVN5U%3D&reserved=0");
//     1    driver.get("https://nam02.safelinks.protection.outlook.com/?url=https%3A%2F%2Fmassgen.na1.echosign.com%2Fpublic%2Fesign%3Ftsid%3DCBFCIBAACBSCTBABDUAAABACAABAA4oa9wrofj6c-DvzUhdnDJrrdcSVIUZ9e2sOdjhNWlGdSwVz_89UWeSPknpJ_zVzW2B5K7tc4v2y6pqYJ7Io44KG2iwgV2HETYZ2NQnaHYl44AqtcLFr5CcwNd4QBfI5r%26&data=05%7C02%7Csankar.venkatesan%40technossus.com%7C41144f98381a4da861f208dde60cc162%7C7284c0574078417092c01bcb11c55269%7C0%7C0%7C638919664642416839%7CUnknown%7CTWFpbGZsb3d8eyJFbXB0eU1hcGkiOnRydWUsIlYiOiIwLjAuMDAwMCIsIlAiOiJXaW4zMiIsIkFOIjoiTWFpbCIsIldUIjoyfQ%3D%3D%7C0%7C%7C%7C&sdata=zQFHMKCk3009GeX7xBLq4cFi2HpW%2B99BVdzNW1UvGiI%3D&reserved=0");
//     2     driver.get("https://nam02.safelinks.protection.outlook.com/?url=https%3A%2F%2Fmassgen.na1.echosign.com%2Fpublic%2Fesign%3Ftsid%3DCBFCIBAACBSCTBABDUAAABACAABAAeOESxF8dZ6rH-5Y3HhSzHta2-fONqDRbzzpm5ydC7FCOxCXB9LqaE1fn6fWVGhKAn4_KHzyJgt-0P1RQ_XeYKCXH_EYAVoAYqvx6hNpzU5RT70-GJmZ47tXFCxPIEn8T%26&data=05%7C02%7Csankar.venkatesan%40technossus.com%7C85d04032bb3242de3a4808dde60d3fbb%7C7284c0574078417092c01bcb11c55269%7C0%7C0%7C638919666756315947%7CUnknown%7CTWFpbGZsb3d8eyJFbXB0eU1hcGkiOnRydWUsIlYiOiIwLjAuMDAwMCIsIlAiOiJXaW4zMiIsIkFOIjoiTWFpbCIsIldUIjoyfQ%3D%3D%7C0%7C%7C%7C&sdata=egxz72qncXDasqqIZstgqyOHGP4R7kO1jtL7mn1ZNMM%3D&reserved=0");
//     3    driver.get("https://nam02.safelinks.protection.outlook.com/?url=https%3A%2F%2Fmassgen.na1.echosign.com%2Fpublic%2Fesign%3Ftsid%3DCBFCIBAACBSCTBABDUAAABACAABAA-qcdRWHMEnuq8A2G77m_BRSHadJqCkAjW-wuSueTyNbrSxA4yhniXBszd-_kx8M-TTO-7bctRGp89APsuTv71AHFo1ZDGHeTIKfrWiBYkabRariOrgmfxyXflkWXAyTN%26&data=05%7C02%7Csankar.venkatesan%40technossus.com%7C3762add9fb7c405fa97008dde60d7307%7C7284c0574078417092c01bcb11c55269%7C0%7C0%7C638919667625663794%7CUnknown%7CTWFpbGZsb3d8eyJFbXB0eU1hcGkiOnRydWUsIlYiOiIwLjAuMDAwMCIsIlAiOiJXaW4zMiIsIkFOIjoiTWFpbCIsIldUIjoyfQ%3D%3D%7C0%7C%7C%7C&sdata=A6s6%2F1or7n3Up4RPqL6xXTr29yf%2FBWDlRiUir4pc5Dw%3D&reserved=0");
//     4     driver.get("https://nam02.safelinks.protection.outlook.com/?url=https%3A%2F%2Fmassgen.na1.echosign.com%2Fpublic%2Fesign%3Ftsid%3DCBFCIBAACBSCTBABDUAAABACAABAATqHk9L9PMmhkyvjhoR1zTVF18tYg8XmxWW8ZI8B8m3oBfGQ9UjhN6cPb0F6w7YSvsE_bxpShcmg8J1Q_gyOe0CVpQW1Psl3L1R2DMduYfV5W2RmeFZ-LcXO3UKZ3GjZD%26&data=05%7C02%7Csankar.venkatesan%40technossus.com%7C2f1f705d643145a0165608dde60dd3ed%7C7284c0574078417092c01bcb11c55269%7C0%7C0%7C638919669245892211%7CUnknown%7CTWFpbGZsb3d8eyJFbXB0eU1hcGkiOnRydWUsIlYiOiIwLjAuMDAwMCIsIlAiOiJXaW4zMiIsIkFOIjoiTWFpbCIsIldUIjoyfQ%3D%3D%7C0%7C%7C%7C&sdata=qGnWDe%2BqJqkzPKTygLS2jpgUC1ZtAWxM%2FPldHO9Duo4%3D&reserved=0");
//     5     driver.get("https://nam02.safelinks.protection.outlook.com/?url=https%3A%2F%2Fmassgen.na1.echosign.com%2Fpublic%2Fesign%3Ftsid%3DCBFCIBAACBSCTBABDUAAABACAABAA4CJs6Yz-ZAAiJNhK6USHhFSRHAsJbEvDe06eHvtNMwkuulIy0vndN_FIw6RfQyldUF9AW_JXfmb0AQZ2GfdIin6yz0B22OpNeNLDBtPSGoh5PEzDzvVuvK50rt2MImuS%26&data=05%7C02%7Csankar.venkatesan%40technossus.com%7C698976e072b94f37db7408dde60dfd8c%7C7284c0574078417092c01bcb11c55269%7C0%7C0%7C638919669956560997%7CUnknown%7CTWFpbGZsb3d8eyJFbXB0eU1hcGkiOnRydWUsIlYiOiIwLjAuMDAwMCIsIlAiOiJXaW4zMiIsIkFOIjoiTWFpbCIsIldUIjoyfQ%3D%3D%7C0%7C%7C%7C&sdata=%2FPO5HrLrL%2FxPxjO8G8cB8qiKXFP62w1IRnuKL2YSmhQ%3D&reserved=0");
//     6      driver.get("https://nam02.safelinks.protection.outlook.com/?url=https%3A%2F%2Fmassgen.na1.echosign.com%2Fpublic%2Fesign%3Ftsid%3DCBFCIBAACBSCTBABDUAAABACAABAA5uvl-kHCxwRzJfF1KNpCMj53EzmAs57M-s5XCUB9TSqd5g-6F1o2PaWgEQWlsrcjHv8tqtD7yNuEax7e8PEll9SVpmA_shQXDj4gTAVhI96szP5E5FrooP0Od7WxMP8D%26&data=05%7C02%7Csankar.venkatesan%40technossus.com%7Ce6adda816cd34729d47908dde60eb562%7C7284c0574078417092c01bcb11c55269%7C0%7C0%7C638919673032336536%7CUnknown%7CTWFpbGZsb3d8eyJFbXB0eU1hcGkiOnRydWUsIlYiOiIwLjAuMDAwMCIsIlAiOiJXaW4zMiIsIkFOIjoiTWFpbCIsIldUIjoyfQ%3D%3D%7C0%7C%7C%7C&sdata=YRKCvM8MiGC3FBvywPVkUlLAukBZiRiG2w8UbHlkD1I%3D&reserved=0");
//     7       driver.get("https://nam02.safelinks.protection.outlook.com/?url=https%3A%2F%2Fmassgen.na1.echosign.com%2Fpublic%2Fesign%3Ftsid%3DCBFCIBAACBSCTBABDUAAABACAABAAp2SWKBuJyhDcj8in4LD6qa4BAO0YL81Cc9eu7t_U-Y4cshdsRE67U2_-DLjDwTgy3qx2W2sVZSaEsj432GY_FoaqvpoJKRmvcPh2IXbR0RVx6DReKaklnY7DNnAY8ESm%26&data=05%7C02%7Csankar.venkatesan%40technossus.com%7Cb08d977f33a44b49683808dde6107881%7C7284c0574078417092c01bcb11c55269%7C0%7C0%7C638919680598993710%7CUnknown%7CTWFpbGZsb3d8eyJFbXB0eU1hcGkiOnRydWUsIlYiOiIwLjAuMDAwMCIsIlAiOiJXaW4zMiIsIkFOIjoiTWFpbCIsIldUIjoyfQ%3D%3D%7C0%7C%7C%7C&sdata=4CdkWxgSC5pV9Q%2FG2OngGZuD%2Bis5fVixuuix2EVvYR4%3D&reserved=0");
//     8       driver.get("https://nam02.safelinks.protection.outlook.com/?url=https%3A%2F%2Fmassgen.na1.echosign.com%2Fpublic%2Fesign%3Ftsid%3DCBFCIBAACBSCTBABDUAAABACAABAApFM7fdL9Dmhp3k_HFSVkim8iSlMEaxTVcvVyLZqs3MISEyFtSquBtqmgH_UgOPwGXRzgZQuoVhj-yLOQOly8_PHijk1zYsgxDjnL3kW2u39lspBqxCbkBQaIESxpzdFL%26&data=05%7C02%7Csankar.venkatesan%40technossus.com%7Cb12a54df539c442b581208dde610f505%7C7284c0574078417092c01bcb11c55269%7C0%7C0%7C638919682694315051%7CUnknown%7CTWFpbGZsb3d8eyJFbXB0eU1hcGkiOnRydWUsIlYiOiIwLjAuMDAwMCIsIlAiOiJXaW4zMiIsIkFOIjoiTWFpbCIsIldUIjoyfQ%3D%3D%7C0%7C%7C%7C&sdata=G%2FeUx746LopMzTHIYqQzCHXfi%2FqRrgjl7bluVEOg3KE%3D&reserved=0");
//     9       driver.get("https://nam02.safelinks.protection.outlook.com/?url=https%3A%2F%2Fmassgen.na1.echosign.com%2Fpublic%2Fesign%3Ftsid%3DCBFCIBAACBSCTBABDUAAABACAABAAM5pqjKe5b__M6mwUm3ShT1afoOl4Vhb598uiRQ53nIOdtSrHU1eKczz8rWnYMDkWkUsX38Ba0YprpL9MoKR03hzr9yNdDKkE7Nra9LgN9dZEZKgzGT2sfW-2pRIB0UHU%26&data=05%7C02%7Csankar.venkatesan%40technossus.com%7C1f39e44acb0a478aa47008dde6153864%7C7284c0574078417092c01bcb11c55269%7C0%7C0%7C638919701013231146%7CUnknown%7CTWFpbGZsb3d8eyJFbXB0eU1hcGkiOnRydWUsIlYiOiIwLjAuMDAwMCIsIlAiOiJXaW4zMiIsIkFOIjoiTWFpbCIsIldUIjoyfQ%3D%3D%7C0%7C%7C%7C&sdata=AtgMYb7AD8AjEGWhTGBELfKCRIEESaAOwGk7yQLTnBM%3D&reserved=0");
//    10       driver.get("https://nam02.safelinks.protection.outlook.com/?url=https%3A%2F%2Fmassgen.na1.echosign.com%2Fpublic%2Fesign%3Ftsid%3DCBFCIBAACBSCTBABDUAAABACAABAAPJs_aWvQy2pBU5a7ajFx45lzhbipsSkBV7ljG1vmiVxu3x6VeV7n8WI8GUjEupkt4W3oI2MCGMapQqiMvRgPjqjP0f2NHDUYZbmchHWzkouWxqx6GNiJ5btITXR3lfIO%26&data=05%7C02%7Csankar.venkatesan%40technossus.com%7Cdf491cf6701d4c02cc6508dde61dcb72%7C7284c0574078417092c01bcb11c55269%7C0%7C0%7C638919737844647740%7CUnknown%7CTWFpbGZsb3d8eyJFbXB0eU1hcGkiOnRydWUsIlYiOiIwLjAuMDAwMCIsIlAiOiJXaW4zMiIsIkFOIjoiTWFpbCIsIldUIjoyfQ%3D%3D%7C0%7C%7C%7C&sdata=tw%2Fim0ADrk1ePz1Ft%2FGDECzlYlxZk0Rd3jTkIr8aL3s%3D&reserved=0");
//


            test.info("Opened dashboard URL");

            // The user waits for the login page to load
            Thread.sleep(10000);

            // The user clicks the 'Continue' button to proceed
            WebElement continueButton = driver.findElement(By.xpath("//button[contains(@class, 'click-to-accept-tou') and text()='Continue']"));
            continueButton.click();
            test.pass("User clicked the 'Continue' button");

            // The user waits for the next screen to load
            Thread.sleep(10000);

            // The user uses JavaScript to click on the signature placeholder
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.querySelector(\"div[aria-label='Click to sign required']\").click();");
            test.pass("User clicked on the signature placeholder");

            // The user waits for the signature input field to become active and enters their name
            WebElement signatureInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type your signature here' and contains(@class, 'signature-type-name')]")));
            signatureInput.sendKeys("sankar Venkatesan");
            test.pass("User entered signature text 'sankar Venkatesan'");

            // The user waits before proceeding
            Thread.sleep(10000);

            // The user clicks the 'Apply' button to confirm the signature
            WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary apply' and text()='Apply']")));
            applyButton.click();
            test.pass("User clicked the 'Apply' button");

            // The user waits before interacting with the dropdown
            Thread.sleep(10000);

            // The user clicks on the 'Sign Reason' dropdown
            WebElement signReasonDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='signReasonFormControlDropdown']")));
            signReasonDropdown.click();
            test.pass("User clicked on the 'Sign Reason' dropdown");

            // The user waits before selecting the dropdown option
            Thread.sleep(10000);

            // The user selects the option 'I approve this document' from the dropdown
            WebElement approveOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='signReasonFormControlDropdown']/option[text()=' I approve this document']")));
            approveOption.click();
            test.pass("User selected 'I approve this document' from the dropdown");

            // The user waits before clicking 'OK'
            Thread.sleep(10000);

            // The user clicks the 'OK' button to confirm signing reason
            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='btn btn-primary state-next' and text()='OK']")));
            okButton.click();
            test.pass("User clicked the 'OK' button");

            // The user clicks the 'Click to Sign' button to finalize signing
            WebElement clickToSignButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'btn btn-primary') and contains(text(),'Click to Sign')]")));
            clickToSignButton.click();
            test.pass("User clicked the 'Click to Sign' button");

            // The user waits for the signing process to complete
            Thread.sleep(20000);

            // The user clicks the 'Download' button to download the signed document
            WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'spectrum-Button') and normalize-space(text())='Download']")));
            downloadButton.click();
            test.pass("User clicked the 'Download' button after signing the document");

            // The user waits before ending the test
            Thread.sleep(10000);

        } catch (Exception e) {
            // The user logs any exceptions that occur during the test
            test.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        // The user closes the browser window after test execution
        if (driver != null) {
            // driver.quit();
        }

        // The user logs browser closure
        test.info("Browser was successfully closed.");
    }

    @AfterSuite
    public void flushExtentReport() {
        // The user flushes the Extent report to generate the final HTML output
        extent.flush();
    }
}
