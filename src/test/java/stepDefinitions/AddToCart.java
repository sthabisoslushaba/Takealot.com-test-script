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


    @Given("The user is on the home page")
    public void theUserHasFoundTheItem() throws InterruptedException {
        actions.setUpDriver(); // Initialize driver
        driver = actions.driver;

        String home_url = Utils.ConfigReader.getProperty("home_url");
        String item_name = Utils.ConfigReader.getProperty("item_name");

        driver.get(home_url);
        WebElement cookieButton = driver.findElement(By.cssSelector(
                "#shopfront-app > div.header-module_bottom-banners-container_3F8RC > div > div > button"
        ));

        if(cookieButton.isDisplayed()){cookieButton.click();}

        actions.waitForElementToBeVisible(AddToCartPage.search, 10);

        actions.sendKeys(AddToCartPage.search, item_name);
        actions.click(AddToCartPage.search_button);


        actions.waitForElementToBeVisible(AddToCartPage.AddToCartButton, 30);


    }

    @When("The user clicks on the search button")
    public void theUserClicksOnAddToCartButton() {

        actions.click(AddToCartPage.AddToCartButton);
    }

    @When("The user clicks on the go to cart button")
    public void theUserClicksOnAddGoCartButton() {
        actions.click(AddToCartPage.GoToCartButton);
    }
    @Then("The user should see the added items in the cart")
    public void theUserShouldFindItemsInTheCart() {
        WebElement element = driver.findElement(By.xpath("//*[@id='54864197']"));
        String ItemName = element.getText();

        if(ItemName.equals("OZtrail Genesis 3 Person Dome Tent"))
        {
            System.out.println("The item has been added successfully");

        }
        else {
            System.out.println("The item has not been added successfully");
        }
    }
}
