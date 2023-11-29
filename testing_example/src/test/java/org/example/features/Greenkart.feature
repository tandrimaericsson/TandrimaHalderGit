@UiScenarios
@greenKartUrl
Feature: test the GreenKart application

  Background: Open Green kart website
    Given Open 'greenKartUrl' website
    And Wait for 'GreenKart - veg and fruits kart' to come as page title

  Scenario Outline: verify the ability to search product
    When Enter '<searchValue>' search text box
    And Click on Search button
    Then Products containing '<searchValue>' is displaying
    Examples:
      | searchValue |
      | berry       |
      | oo          |
      | rr          |
      | ber         |

  Scenario Outline: Verify the ability to change quantity in greenKart application
    When Enter <productQuantity> as quantity for 'Beetroot' product
    Then Assert that quantity for 'Beetroot' is changed to <productQuantity>
    Examples:
      | productQuantity |
      | 10              |
      | 15              |
      | 05              |
      | 00500           |


  Scenario Outline: Verify the ability to decrease quantity by minus button in greenKart application
    When Enter <productQuantity> as quantity for 'Beetroot' product
    Then Assert that quantity for 'Beetroot' is changed to <productQuantity>
    When Click on decrease quantity button <decreaseQuantity> times for 'Beetroot'
    Then Assert that quantity for 'Beetroot' is changed to <decreasedQuantity>
    Examples:
      | productQuantity | decreaseQuantity | decreasedQuantity |
      | 10              | 1                | 9                 |
      | 15              | 5                | 10                |
      | 2               | 5                | 1                 |


  Scenario Outline: Verify the ability to increase quantity by plus button in greenKart application
    When Enter <productQuantity> as quantity for 'Beetroot' product
    Then Assert that quantity for 'Beetroot' is changed to <productQuantity>
    When Click on increase quantity button <increaseQuantity> times for 'Beetroot'
    Then Assert that quantity for 'Beetroot' is changed to <increasedQuantity>
    Examples:
      | productQuantity | increaseQuantity | increasedQuantity |
      | 10              | 1                | 11                |
      | 15              | 5                | 20                |

  Scenario Outline: Verify the functionality of Add To Cart button
    When Enter <productQuantity> as quantity for '<productName>' product
    Then Assert that quantity for '<productName>' is changed to <productQuantity>
    When Click on Add To Cart button for '<productName>'
    And Click on cart to open
    Then Assert '<productName>' is added to cart with <productQuantity>
    Examples:
      | productName | productQuantity |
      | Beetroot    | 10              |
      | Cucumber    | 15              |


  Scenario: Verify the ability to open/close cart
    When Click on cart to open
    Then Assert cart preview is open now 'true'
    When Click on cart to close
    Then Assert cart preview is open now 'false'


  Scenario: Verify all the products are displaying with product details
    Given Base url is "https://rahulshettyacademy.com"
    When Execute "/seleniumPractise/data/products.json" resource with "get" method
    Then Assert product details response and product details from application

  Scenario: Verify single product price in cart
    When Enter 10 as quantity for 'Beetroot' product
    And Click on Add To Cart button for 'Beetroot'
    Then Assert Number of items added in cart is 1
    And Total cart price is 320
    And Click on cart to open
    Then Assert Number of items added in cart preview is 1
    And Total cart price in cart preview is 320 for 'Beetroot'
    And Number of quantity for 'Beetroot' is 10

    #This is a bug, last entered entity is taken for all the product while adding to cart
  Scenario: Verify multiple product price in cart
    Given Base url is "https://rahulshettyacademy.com"
    And Execute "/seleniumPractise/data/products.json" resource with "get" method
    When Enter quantity for the following products
      | product     | quantity |
      | Beetroot    | 10       |
      | Walnuts     | 5        |
      | Nuts        | 4        |
      | Water Melon | 2        |
    And Click on Add To Cart button for the following product
      | product     |
      | Beetroot    |
      | Walnuts     |
      | Nuts        |
      | Water Melon |
    Then Assert Number of items added in cart
    And Total cart price from json response
    When Click on cart to open
    Then Assert Number of items added in cart preview
    And Total cart price in cart preview for each product
    And Number of quantity for each product
  @test
  Scenario: Verify the ability to delete item from cart
    When Enter quantity for the following products
      | product     | quantity |
      | Beetroot    | 10       |
      | Walnuts     | 5        |
      | Nuts        | 4        |
      | Water Melon | 2        |
    And Click on Add To Cart button for the products
    Then Assert Number of items added in cart
    When Click on cart to open
    And Remove the following products
      | productToRemove |
      | Beetroot        |
      | Walnuts         |
    Then Assert removed products are not available in cart
    When Click on cart to close
    Then Assert Number of items present in cart
  #Scenario: Verify the ability to increase quantity after adding item to cart
  #Scenario: Verify the ability to add item in big quantity
  #Scenario: Verify the ability to checkout
  #Scenario: Verify today's top deals
  #


