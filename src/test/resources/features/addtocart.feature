Feature: Login & add to cart

  Scenario: Add existing item to cart
    Given The user logged in and on the home page
    When The user searches and adds an item to cart
    And The user clicks on the go to cart button
    And The user should see the added items in the cart

