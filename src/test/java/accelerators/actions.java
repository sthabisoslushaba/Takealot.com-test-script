package accelerators;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

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
    public static String getText(By locator) {
        waitForElementToBeVisible(locator, 10);
        return driver.findElement(locator).getText();
    }
    public static boolean isElementDisplayed(By locator) {
        try {
            waitForElementToBeVisible(locator, 1);
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
    public static boolean isItemInCart(By CartLocator, String item){
        waitForElementToBeVisible(CartLocator, 10);
        WebElement cartContainer = driver.findElement(CartLocator);
//        System.out.print(cartContainer.getText());
        return cartContainer.getText().contains(item);

    }
    public static void takeScreenshot(String fileName) {
        try {
            if (driver == null) {
                System.out.println("Driver is null — cannot take screenshot.");
                return;
            }
            // Take the screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //timestamp to make each file unique
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File dest = new File("Screenshots/" + fileName + "_" + timestamp + ".png");
            FileUtils.copyFile(src, dest);

        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }
    public static void clickAddToCart(String productName) throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Scroll the page down gradually to load all products
        long lastHeight = (long) js.executeScript("return document.body.scrollHeight");

        while (true) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(800); // wait for lazy load

            long newHeight = (long) js.executeScript("return document.body.scrollHeight");
            if (newHeight == lastHeight) {
                break; // reached bottom
            }
            lastHeight = newHeight;
        }

        // 2. Now all product cards are visible in DOM
        List<WebElement> productCards = driver.findElements(
                By.xpath("//article[contains(@class,'product-card')]")
        );

        WebElement targetCard = null;

        // 3. Find the card containing the product title
        for (WebElement card : productCards) {
            if (card.getText().contains(productName)) {
                targetCard = card;
                break;
            }
        }

        if (targetCard == null) {
            throw new NoSuchElementException("Product not found: " + productName);
        }

        // 4. Scroll the target card into view
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", targetCard);

        // 5. Now click the Add to Cart button
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(
                targetCard.findElement(By.xpath(".//button[contains(., 'Add')]"))
        ));

        js.executeScript("arguments[0].click();", addBtn);
    }







}
