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

    /*************Login credentials*************/
    String password = System.getenv("Takealot_password");
    String username = System.getenv("Takealot_username");


    @Given("the user is on the Takealot home page")
    public void theUserOnHomePage()  {

        waitForElementToBeVisible(AddToCartPage.CookieButton, 10);
        click(AddToCartPage.CookieButton);
    }
    @And("the user clicks the Login button")
    public void theUserClicksTheLoginButton()
    {
        waitForElementToBeVisible(AddToCartPage.LoginButton, 10);
        click(AddToCartPage.LoginButton);

        waitForElementToBeVisible(AddToCartPage.AdButton, 10);
        click(AddToCartPage.AdButton);

    }

    @When("the user enters valid login credentials and clicks Sign In")
    public void theUserClicksSignIn() {

        //wait for at least the email field to show
        waitForElementToBeVisible(AddToCartPage.UserEmailInput, 10);

        login(AddToCartPage.UserEmailInput, AddToCartPage.UserPasswordInput,
                AddToCartPage.SignInButton, username, password );

        //stop the tests if login fails
        if(!isLoginSuccessful(AddToCartPage.LoggedInUserName))
            org.junit.Assert.fail("Login Failed :/ \nStopping execution.");

    }



    @And("the user searches for an existing item")
    public void theUserSearchesTheItem()
    {
        waitForElementToBeVisible(AddToCartPage.search, 10);
        searchItem(AddToCartPage.search, AddToCartPage.search_button, item_name);

    }

    @And("the user adds the item to the cart")
    public void theUserAddsTheItemToCart()
    {
        waitForElementToBeVisible(AddToCartPage.AddToCartButton, 10);
        click(AddToCartPage.AddToCartButton);
    }
    @And("the user clicks go to cart")
    public void theUserGoesToCart()
    {
        click(AddToCartPage.GoToCartButton);
    }
    @Then("the user finds the added item in the cart")
    public void verifyItemInCart()
    {
        if(!isItemInCart(AddToCartPage.Item))
        {
            org.junit.Assert.fail("404 Item Not Found.");
        }
    }

}
