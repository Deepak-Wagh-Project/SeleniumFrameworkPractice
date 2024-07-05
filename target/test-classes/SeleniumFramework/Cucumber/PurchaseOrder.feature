
@tag
Feature: Purchase the order from ecommerce website
  I want to use this template for my feature file

  Background:
  Given I landed on ecommerce page

  @tag2
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to the cart
    And Checkout <productName> and submit the order
    Then "THANK YOU FOR THE ORDER" message is displayed on the Confirmation Page.

    Examples: 
      | name                    | password      | productName  |
      | deeps2398wagh@gmail.com |    Deepak@123 | ZARA COAT 3  |
    
