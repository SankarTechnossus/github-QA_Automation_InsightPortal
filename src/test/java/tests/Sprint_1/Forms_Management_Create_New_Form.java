package tests.Sprint_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Forms_Management_Create_New_Form {
    public static void main(String[] args) {
        // 'Initialize Chrome WebDriver'
        WebDriver driver = new ChromeDriver();

        // 'Maximize browser window'
        driver.manage().window().maximize();

        // 'Navigate to the dashboard URL'
        driver.get("https://your-dashboard-url.com"); // replace with actual URL

        // 'Initialize WebDriverWait'
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Step 1: 'Click on “Administration”'
            By administrationLink = By.xpath("//a[text()='Administration']");
            wait.until(ExpectedConditions.elementToBeClickable(administrationLink)).click();

            // Step 2: 'Add wait time'
            Thread.sleep(2000);

            // Step 3: 'Click on “Forms management” drop down button'
            By formsManagementToggle = By.xpath("//button[@aria-label='Expand Forms Management']");
            wait.until(ExpectedConditions.elementToBeClickable(formsManagementToggle)).click();

            // Step 4: 'Add wait time'
            Thread.sleep(2000);

            // Step 5: 'Click on “Agreements” link'
            By agreementsLink = By.xpath("//span[text()='Agreements']");
            wait.until(ExpectedConditions.elementToBeClickable(agreementsLink)).click();

            // Step 6: 'Add wait time'
            Thread.sleep(2000);

            // Step 7: 'Click on “Add new” button'
            By addNewLink = By.xpath("//a[text()='Add new']");
            wait.until(ExpectedConditions.elementToBeClickable(addNewLink)).click();

            // Step 8: 'Add wait time'
            Thread.sleep(2000);

            // Step 9: 'Enter “TestsankarQAautomation1” in Name text box'
            By nameInput = By.xpath("//input[@id='name']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys("TestsankarQAautomation1");

            // Step 10: 'Add wait time'
            Thread.sleep(1000);

            // Step 11: 'Enter “TestAutomationQA” in Description box'
            By descriptionTextarea = By.xpath("//textarea[@id='description']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionTextarea)).sendKeys("TestAutomationQA");

            // Step 12: 'Add wait time'
            Thread.sleep(1000);

            // Step 13: 'Click on “Type” drop down'
            By dropdownArrow = By.xpath("//div[contains(@class, '_dropdownArrow_1y8qt_214')]");
            wait.until(ExpectedConditions.elementToBeClickable(dropdownArrow)).click();

            // Step 14: 'Add wait time'
            Thread.sleep(1000);

            // Step 15: 'Select “FM Billable” (04) in the list'
            By fmBillableOption = By.xpath("//div[contains(text(), 'FM Billable')]");
            wait.until(ExpectedConditions.elementToBeClickable(fmBillableOption)).click();

            // Step 16: 'Add wait time'
            Thread.sleep(1000);

            // Step 17: 'Click on Category drop down and select “General” (3rd option)'
            By selectPlaceholder = By.xpath("//div[@id='react-select-9-placeholder']");
            wait.until(ExpectedConditions.elementToBeClickable(selectPlaceholder)).click();

            By generalOption = By.xpath("//div[contains(text(), 'General')]");
            wait.until(ExpectedConditions.elementToBeClickable(generalOption)).click();

            // Step 18: 'Add wait time'
            Thread.sleep(1000);

            // Step 19: 'Enter 1 in “Category Sequence Number”'
            By categorySeqInput = By.xpath("//input[@id='categorySequenceNo']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(categorySeqInput)).sendKeys("1");

            // Step 20: 'Add wait time'
            Thread.sleep(1000);

            // Step 21: 'Click on “Create” button'
            By createButton = By.xpath("//button[text()='Create']");
            wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();

            // Optional: Confirmation log
            System.out.println("Form created successfully.");

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        } finally {
            // 'Close the browser'
            driver.quit();
        }
    }
}
