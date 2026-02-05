package pages.My_Profile.Security_Page;
import org.openqa.selenium.*;
import base.BasePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;


public class Record_Level_people_in_export_control_Page extends BasePage {

    public Record_Level_people_in_export_control_Page(WebDriver driver) {
        super(driver);
    }

    // Locators
    By peopleLink = By.xpath("//a[contains(@href,'/export-control') and contains(@href,'/people') and normalize-space()='People']");
    By peopleHeader = By.xpath("//header[normalize-space()='People']");
    By addNewPeopleButton = By.xpath("//button[@type='button' and normalize-space()='Add New People']");
    By userSearchInput = By.xpath("//input[contains(@id,'react-select') and contains(@id,'-input') and @role='combobox']");
    By cancelButton = By.xpath("//button[@type='button' and normalize-space()='Cancel']");
    By addButton = By.xpath("//button[@type='button' and normalize-space()='Add' and contains(@class,'-primary')]");
    By typeDropdownInput = By.xpath("//input[contains(@id,'react-select-16') and @role='combobox']");
    By roleDropdownInput = By.xpath("//input[contains(@id,'react-select-17') and @role='combobox']");
    By firstRemoveXIcon = By.xpath("(//i[contains(@class,'fi-remove')])[1]");


    //Actions

    public void clickFirstRemoveXMark() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement xIcon = wait.until(ExpectedConditions.presenceOfElementLocated(firstRemoveXIcon));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", xIcon);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", xIcon);

        pause(2000);
    }

    public void selectRoleAsProjectManager() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(roleDropdownInput));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys("Project Manager");

        By option = By.xpath("//*[contains(@id,'react-select') and (self::div or self::span) and normalize-space(.)='Project Manager']");
        WebElement pmOption = wait.until(ExpectedConditions.visibilityOfElementLocated(option));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", pmOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", pmOption);

        input.sendKeys(Keys.TAB);

        pause(2000);
    }

    public void acceptRemoveUserConfirmationAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.accept(); // Click OK

        pause(3000);
    }

    public void selectTypeAsExternal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(typeDropdownInput));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys("External");

        By option = By.xpath("//*[contains(@id,'react-select') and (self::div or self::span) and normalize-space(.)='External']");
        WebElement externalOption = wait.until(ExpectedConditions.visibilityOfElementLocated(option));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", externalOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", externalOption);

        input.sendKeys(Keys.TAB);

        pause(4000); // as you requested
    }


    public void clickAddButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement add = wait.until(ExpectedConditions.elementToBeClickable(addButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", add);

        add.click();

        pause(4000);
    }


    public void clickCancelButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement cancel = wait.until(ExpectedConditions.elementToBeClickable(cancelButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", cancel);

        cancel.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(cancelButton));
        pause(1000);
    }


    public void selectUserFromDropdownById(String userId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(userSearchInput));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(userId);

        By optionById = By.xpath("//*[contains(@id,'react-select') and (self::div or self::span) and contains(normalize-space(.),'" + userId + "')]");

        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionById));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

        input.sendKeys(Keys.TAB);

        pause(1000);
    }


    public void enterUserIdInSearchBox(String userId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(userSearchInput));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", input);

        input.click();
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(userId);

        pause(1000);
    }

    public void clickAddNewPeopleButton() {
        WebElement addNewPeopleBtn = driver.findElement(addNewPeopleButton);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", addNewPeopleBtn);

        addNewPeopleBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//header[contains(normalize-space(),'People')]")
        ));

        pause(1000);
    }


    public boolean verifyUserLandsOnPeoplePage() {
        boolean result = false;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement header = wait.until(
                ExpectedConditions.presenceOfElementLocated(peopleHeader)
        );

        String actualHeaderText = header.getText().trim();
        result = Objects.equals(actualHeaderText, "People");

        return result;
    }


    public void clickPeopleLink() {
        WebElement people = driver.findElement(peopleLink);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", people);

        people.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("/people"));

        pause(1000);
    }

    public void refreshPage() {
        driver.navigate().refresh();
        pause(3000); // wait for Insight page to reload
    }

    public void refreshPage01() {
        pause(7000);
        driver.navigate().refresh();
        pause(3000);
    }



}