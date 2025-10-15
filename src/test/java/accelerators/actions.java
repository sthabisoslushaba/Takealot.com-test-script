package accelerators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class actions {

    public static WebDriver driver;

    // ***********************************************************
    // Initial set up for drivers
    // ***********************************************************
    public static void setUpDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-infobars");
            options.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    // ****************************************************************
    // actions
    // ********************************************************************
    public static void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void click(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public static void sendKeys(By locator, String text) {
        waitForElementToBeVisible(locator, 10);
        WebElement element = driver.findElement(locator);
        element.clear();
        for (char c : text.toCharArray()) {
            element.sendKeys(Character.toString(c));
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
        // Trigger blur/focusout events (forces JS to validate)
        element.sendKeys("\t");
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

    // **************************************************************************
    // Login actions
    // ******************************************************************************
    public static void login(By usernameField, By passwordField, By submitButton, String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        email.clear();
        for (char c : username.toCharArray()) {
            email.sendKeys(Character.toString(c));
            try { Thread.sleep(100); } catch (InterruptedException e) { }
        }

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordInput.clear();
        for (char c : password.toCharArray()) {
            passwordInput.sendKeys(Character.toString(c));
            try { Thread.sleep(100); } catch (InterruptedException e) { }
        }

        // Wait until the button becomes clickable (disabled removed)
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }


    public static boolean isLoginSuccessful(By logoutButtonOrUserIcon) {
        return isElementDisplayed(logoutButtonOrUserIcon);
    }
}
