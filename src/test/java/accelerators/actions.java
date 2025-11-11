package accelerators;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
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

    public static void searchItem(By searchFieldLocator, By searchButtonLocator, String itemName, int timer) {
        try {
            // Wait for the search field to be visible
            WebElement searchField = new WebDriverWait(driver, Duration.ofSeconds(timer))
                    .until(ExpectedConditions.visibilityOfElementLocated(searchFieldLocator));

            // Clear the field before data entry
            searchField.clear();
            searchField.sendKeys(itemName);

            // Wait for the search button to be clickable and click it
            WebElement searchButton = new WebDriverWait(driver, Duration.ofSeconds(timer))
                    .until(ExpectedConditions.elementToBeClickable(searchButtonLocator));
            searchButton.click();

        } catch (TimeoutException e) {
            System.err.println("Timeout... " + e.getMessage());
        }
    }
        public static void takeScreenshot(WebDriver driver, String sTestCaseName, String path) throws Exception {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(path + sTestCaseName + ".jpg"));
        } catch (Exception e) {
            throw new Exception();
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
    public static void clickOnProductByText(String productName) {
        try {
            // Wait for product link that contains the text
            By productLocator = By.xpath(
                    "//a[contains(@href, '/')]//*[contains(., '" + productName + "')]/ancestor::a"
            );

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(productLocator));

            // Scroll to the element and click via JS to avoid overlay issues
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productLink);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", productLink);

            System.out.println("Clicked on product: " + productName);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
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

    public static boolean isItemInCart(By CartLocator, String item)
    {
        waitForElementToBeVisible(CartLocator, 10);
        WebElement cartContainer = driver.findElement(CartLocator);
        //System.out.print(cartContainer.getText());
        return cartContainer.getText().contains(item);

    }
}
