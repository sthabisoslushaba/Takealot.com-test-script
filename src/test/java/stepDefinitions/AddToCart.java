package stepDefinitions;

import accelerators.actions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.AddToCartPage;
import utility.Utils;


public class AddToCart {

    WebDriver driver;
    String home_url = Utils.ConfigReader.getProperty("home_url");
    String item_name = Utils.ConfigReader.getProperty("item_name");

    /*************Login credentials*************/
    String password = System.getenv("Takealot_password");
    String username = System.getenv("Takealot_username");


    @Given("The user logged in and on the home page")
    public void theUserOnHomePage() throws InterruptedException {
        actions.setUpDriver(); // Initialize driver
        driver = actions.driver;

        driver.get(home_url);
        WebElement cookieButton = driver.findElement(By.cssSelector(
                "#shopfront-app > div.header-module_bottom-banners-container_3F8RC > div > div > button"
        ));

        if (cookieButton.isDisplayed()) {
            cookieButton.click();
        }

        WebElement login_button = driver.findElement(By.cssSelector(
                "#shopfront-app > div.top-nav.top-nav-module_top-nav_2cmJW > div > div > div.auto.cell > div > div.auto.cell > ul > li:nth-child(1) > a"
        ));
        if(login_button.isDisplayed()){login_button.click();}

        WebElement user_email_input = driver.findElement(By.cssSelector(
                "#customer_login_email"
        ));
        user_email_input.sendKeys(username);

        WebElement user_password_input = driver.findElement(By.cssSelector(
                "#customer_login_password"
        ));
        user_password_input.sendKeys(password);

        WebElement sign_in_button = driver.findElement(By.xpath(
                "/html/body/div[5]/div/div/div/div/div/div/div[1]/div/div/div[1]/form/div[6]/div/button"
        ));
        sign_in_button.click();

        WebElement logged_In_User = driver.findElement(By.cssSelector(
                "#shopfront-app > div.top-nav.top-nav-module_top-nav_2cmJW > div > div > div.auto.cell > div > div.auto.cell > ul > li.top-nav-module_name-item_3ROu0"
        ));

        if(logged_In_User.getText().equals("Hi S'thabiso"))
        {
            System.out.println("S'thabiso Logged In Successfully");
        }
        else
            System.out.println("S'thabiso Logged In Failed");


    }

    @When("The user searches and adds an item to cart")
    public void theUserClicksOnAddToCartButton() {

        //should be signed in now
        actions.waitForElementToBeVisible(AddToCartPage.search, 10);

        actions.sendKeys(AddToCartPage.search, item_name);
        actions.click(AddToCartPage.search_button);


        actions.waitForElementToBeVisible(AddToCartPage.AddToCartButton, 30);

        actions.click(AddToCartPage.AddToCartButton);
    }

    @When("The user clicks on the go to cart button")
    public void theUserClicksOnAddGoCartButton()
    {
        actions.click(AddToCartPage.GoToCartButton);
    }


    @Then("The user should see the added items in the cart")
    public void theUserShouldFindItemsInTheCart()
    {
        WebElement item_in_cart = driver.findElement(By.xpath("//*[@id='54864197']"));
        String ItemName = item_in_cart.getText();

        if(ItemName.equals(item_name))
        {
            System.out.println("The item has been added successfully");
        }
        else {
            System.out.println("The item has not been added successfully");
        }
    }
}
