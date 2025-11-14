package stepDefinitions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.AddToCartPage;
import utility.Utils;

import static accelerators.actions.*;

public class AddToCart {

    String item_name = Utils.ConfigReader.getProperty("item_name");
    private final int timer = 10; //set this for exception wait time

    /************* Login credentials *************/
    String password = System.getenv("Takealot_password");
    String username = System.getenv("Takealot_username");

    @Given("the user is on the Takealot home page")
    public void theUserOnHomePage() {

        waitForElementToBeVisible(AddToCartPage.CookieButton, 10);
        click(AddToCartPage.CookieButton);
        takeScreenshot("HomePage");
    }

    @And("the user clicks the Login button")
    public void theUserClicksTheLoginButton() {
        waitForElementToBeVisible(AddToCartPage.LoginButton, 10);
        click(AddToCartPage.LoginButton);
        takeScreenshot("LoginPage");

        waitForElementToBeVisible(AddToCartPage.AdButton, timer);
        click(AddToCartPage.AdButton);
    }

    @When("the user enters valid login credentials and clicks Sign In")
    public void theUserClicksSignIn() {

        login(AddToCartPage.UserEmailInput, AddToCartPage.UserPasswordInput,
                AddToCartPage.SignInButton, username, password);

        // stop the tests if login fails
        if (!isLoginSuccessful(AddToCartPage.LoggedInUserName))
            org.junit.Assert.fail("Login Failed :/ \nStopping execution.");

        takeScreenshot("UserHasLoggedIn");
    }

    @And("^the user searches for \"(.*)\"$")
    public void theUserSearchesTheItem(String item_name) {

        waitForElementToBeVisible(AddToCartPage.search, timer);
        takeScreenshot("SearchItem");
        searchItem(AddToCartPage.search, AddToCartPage.search_button, item_name, timer);
    }
    @And("the user adds the item to the cart")
    public void theUserAddsTheItemToCart() throws InterruptedException {
        clickAddToCart(item_name);
    }

    @And("the user clicks go to cart")
    public void theUserGoesToCart() {
        click(AddToCartPage.GoToCartButton);
        takeScreenshot("CartItem");
    }

    @Then("the user finds the added item in the cart")
    public void verifyItemInCart() {
        takeScreenshot("itemsInTheCart");
        if (!isItemInCart(AddToCartPage.cartContainer, item_name)) {
            org.junit.Assert.fail("Item Not Found.");
        }
    }
}
