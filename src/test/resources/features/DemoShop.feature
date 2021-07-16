
Feature: This feature Develop a Web test solution that automates below simple test scenario, composed as BDD scenarios.


@DemoShopTest
Scenario Outline: Verify Demo Shop Website
    Given  I add "<noOfItems>" different products to my wish list
    When  I view my wishlist table
    Then  I find total "<noOfItems>" selected items in my Wishlist
    When  I search for lowest price product
    And  I am able to add the lowest price item to my cart
    Then  I am able to verify the item in my cart
  Examples:
    | noOfItems   |
    | 4       |
