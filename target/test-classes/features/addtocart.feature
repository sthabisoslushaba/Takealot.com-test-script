Feature: Login & add to cart

  Scenario: Add existing item to cart
    Given The user is on the home page
    When The user clicks on the search button
    And The user clicks on the go to cart button
    And The user should see the added items in the cart

