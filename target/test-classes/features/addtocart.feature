Feature: Add item to cart
  As a valid takealot.com user
  I want to add an existing item to my cart
  So that I can view it before checkout

  Scenario: Sign-In and add an existing item to the cart
    Given the user is on the Takealot home page
    And the user clicks the Login button
    When the user enters valid login credentials and clicks Sign In
    And the user searches for an existing item
    And the user adds the item to the cart
    And the user clicks go to cart
    Then the user finds the added item in the cart
