package accelerators;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static accelerators.Base.driver;


public class actions {

    
    public static void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void click(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public static void searchItem(By searchFieldLocator, By searchButtonLocator, String itemName) {
        try {
            // Wait for the search field to be visible
            WebElement searchField = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(searchFieldLocator));

            // Clear the field before data entry
            searchField.clear();
            searchField.sendKeys(itemName);

            // Trigger blur/focusout events to force JS validation
            searchField.sendKeys(Keys.TAB);

            // Wait for the search button to be clickable and click it
            WebElement searchButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
            searchButton.click();

        } catch (TimeoutException e) {
            System.err.println("Timeout... " + e.getMessage());
        }
    }


    public static String getText(By locator) {
        waitForElementToBeVisible(locator, 10);
        return driver.findElement(locator).getText();
    }


    public static boolean isElementDisplayed(By locator) {
        try {
            waitForElementToBeVisible(locator, 5);
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static void login(By usernameField, By passwordField, By submitButton, String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Wait for the username field, clear it, and enter the username
            WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
            email.clear();
            email.sendKeys(username);

            // Wait for the password field, clear it, and enter the password
            WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
            passwordInput.clear();
            passwordInput.sendKeys(password);

            // Wait until the submit button becomes clickable and click it
            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
            submitBtn.click();

        } catch (TimeoutException e) {
            System.err.println("Timeout..." + e.getMessage());
        }
    }



    public static boolean isLoginSuccessful(By LoggedInUserName) {
        return isElementDisplayed(LoggedInUserName) && getText(LoggedInUserName).equals("Hi S'thabiso");
    }

    public static boolean isItemInCart(By CartLocator)
    {
        waitForElementToBeVisible(CartLocator, 10);
        WebElement element = driver.findElement(CartLocator);
        return element.isDisplayed();

    }
}
