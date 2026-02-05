package pages.My_Profile.Security_Page;
import org.openqa.selenium.*;
import base.BasePage;
import org.openqa.selenium.interactions.Actions;
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
    By firstRemoveXIcon = By.xpath("(//i[contains(@class,'fi-remove')])[1]");
    By typeControl_Alam = By.xpath("//tr[.//td[@data-column='name' and normalize-space()='Alam, Md']]//td[@data-column='type']//div[contains(@class,'select-control')]");
    By typeInput_Alam = By.xpath("//tr[.//td[@data-column='name' and normalize-space()='Alam, Md']]//td[@data-column='type']//input[contains(@id,'react-select') and contains(@id,'-input')]");
    By addExternalPeopleButton = By.xpath("//button[.//i[contains(@class,'fi-add')] and contains(normalize-space(),'Add External People')]");
    By userDropdownControl = By.xpath("//*[normalize-space()='User']/following::div[contains(@class,'Select-control')][1]");
    By userSearchInput01     = By.xpath("//*[normalize-space()='User']/following::input[@role='combobox'][1]");


    //Actions

    public void selectUserFromDropdownById01(String userId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement control = wait.until(ExpectedConditions.elementToBeClickable(userDropdownControl));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);

        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(userSearchInput01));
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(userId);

        By optionById = By.xpath("//div[contains(@id,'react-select') and @role='option' and contains(normalize-space(.),'" + userId + "')]");
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionById));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

        pause(1000);
    }

    public void clickAddExternalPeopleButton() {
        WebElement addBtn = driver.findElement(addExternalPeopleButton);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", addBtn);

        addBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(normalize-space(),'Cancel')]")
        ));

        pause(1000);
    }


    public void selectValueFromPeopleGridDropdown(String personName, String columnData, String valueToSelect) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        //  Skip if already selected
        try {
            WebElement selected = driver.findElement(gridCellSelectedValue(personName, columnData));
            String already = selected.getText().trim();
            if (already.equalsIgnoreCase(valueToSelect)) {
                return;
            }
        } catch (Exception ignored) {
            // no selected value -> continue
        }

        WebElement control = wait.until(ExpectedConditions.elementToBeClickable(gridCellControl(personName, columnData)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);

        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(gridCellInput(personName, columnData)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", input);

        //  Actions typing is more reliable than input.sendKeys for react-select
        Actions actions = new Actions(driver);
        actions.click(input)
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(valueToSelect)
                .perform();

        By optionLocator = By.xpath(
                "//*[contains(@id,'react-select') and (@role='option' or contains(@id,'-option')) and " +
                        "(self::div or self::span or self::li) and contains(normalize-space(.),'" + valueToSelect + "')]"
        );

        WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(optionLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

        pause(5000);
    }


    By gridCellSelectedValue(String personName, String columnData) {
        return By.xpath(
                "//tr[.//td[@data-column='name' and normalize-space()='" + personName + "']]" +
                        "//td[@data-column='" + columnData + "']//div[contains(@class,'singleValue')]"

        );

    }

    By gridCellControl(String personName, String columnData) {
        return By.xpath(
                "//tr[.//td[@data-column='name' and normalize-space()='" + personName + "']]" +
                        "//td[@data-column='" + columnData + "']//div[contains(@class,'select-control')]"
        );
    }

    By gridCellInput(String personName, String columnData) {
        return By.xpath(
                "//tr[.//td[@data-column='name' and normalize-space()='" + personName + "']]" +
                        "//td[@data-column='" + columnData + "']//input[contains(@id,'react-select') and contains(@id,'-input')]"
        );
    }

    public void selectTypeAsExternal_ForAlam() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        WebElement control = wait.until(ExpectedConditions.elementToBeClickable(typeControl_Alam));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", control);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", control);

        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(typeInput_Alam));

        //  make input interactable by focusing through JS
        ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", input);

        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys("External");

        By externalOption = By.xpath(
                "//*[contains(@id,'react-select') and (@role='option' or contains(@id,'-option')) and " +
                        "(self::div or self::span or self::li) and contains(normalize-space(.),'External')]"
        );

        WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(externalOption));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

        pause(5000);
    }



    public void clickFirstRemoveXMark() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement xIcon = wait.until(ExpectedConditions.presenceOfElementLocated(firstRemoveXIcon));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", xIcon);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", xIcon);

        pause(2000);
    }


    public void acceptRemoveUserConfirmationAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        alert.accept(); // Click OK

        pause(4000);
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

        pause(3000);
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


    public void refreshPage01() {
        pause(7000);
        driver.navigate().refresh();
        pause(3000);
    }



}