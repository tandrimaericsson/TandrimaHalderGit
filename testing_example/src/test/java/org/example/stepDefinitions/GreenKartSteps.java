package org.example.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import org.example.pageClasses.greenkarts.GreenKartPage;
import org.example.pageClasses.greenkarts.Product;
import org.example.pojoClass.ProductDetails;
import org.example.utilities.ManageWebDriver;
import org.example.utilities.TestContext;
import org.junit.Assert;
import org.testng.asserts.SoftAssert;

import java.util.*;
import java.util.stream.IntStream;

public class GreenKartSteps {

    private ManageWebDriver manageWebDriver;
    private GreenKartPage greenKart;
    private TestContext testContext;

    public GreenKartSteps(TestContext testContext) {
        this.testContext = testContext;
        manageWebDriver = testContext.getManageWebDriver();
        greenKart = new GreenKartPage(manageWebDriver);
    }

    @When("Enter {int} as quantity for {string} product")
    public void enter_as_quantity_for_product(Integer quantity, String productName) {
        Product product = greenKart.getProduct(productName);
        product.quantityInput().enterValue(String.valueOf(quantity));
    }

    @Then("Assert that quantity for {string} is changed to {int}")
    public void assert_that_quantity_for_is_changed_to(String productName, int expectedQuantity) {
        Product product = greenKart.getProduct(productName);
        Assert.assertEquals(expectedQuantity, Integer.parseInt(product.quantityInput().readValue()));
    }

    @When("Click on decrease quantity button {int} times for {string}")
    public void click_on_decrease_quantity_button_times_for(Integer decreaseQuantity, String productName) {
        Product product = greenKart.getProduct(productName);
        product.decreaseQuantity(decreaseQuantity);
    }

    @When("Click on increase quantity button {int} times for {string}")
    public void click_on_increase_quantity_button_times_for(Integer increaseQuantity, String productName) {
        Product product = greenKart.getProduct(productName);
        product.increaseQuantity(increaseQuantity);
    }

    @When("Enter {string} search text box")
    public void enter_search_text_box(String searchValue) {
        greenKart.getSearchTextBox().enterValue(searchValue);
    }

    @When("Click on Search button")
    public void click_on_search_button() {
        greenKart.clickOnSearchButton();
    }

    @Then("Products containing {string} is displaying")
    public void products_containing_is_displaying(String searchValue) {
        greenKart.getProductList().stream().map(Product::getProductName)
                .forEach(actualProductName -> Assert.assertTrue(actualProductName.contains(searchValue)));
    }

    @When("Click on Add To Cart button for {string}")
    public void click_on_add_to_cart_button_for(String productName) {
        Product product = greenKart.getProduct(productName);
        product.clickOnAddToCart();
    }

    @Then("Assert Add to cart button is displayed {string} for {string}")
    public void assert_add_to_cart_button_is_displayed_for(String expectedVisibility, String productName) {
        Product product = greenKart.getProduct(productName);
        Assert.assertEquals(Boolean.parseBoolean(expectedVisibility), product.isAddToCartButtonDisplayed());
    }

    @Then("Assert Added button is displayed {string} for {string}")
    public void assert_added_button_is_displayed_for(String expectedVisibility, String productName) {
        Product product = greenKart.getProduct(productName);
        Assert.assertEquals(Boolean.parseBoolean(expectedVisibility), product.isAddedButtonDisplayed());
    }

    @Then("Assert {string} is added to cart with {int}")
    public void assert_is_added_to_cart_with(String productName, Integer productQuantity) {
        Assert.assertEquals(String.valueOf(productQuantity), greenKart.getCartPreview().getCartItems().stream()
                .filter(product -> product.getProductName().startsWith(productName)).toList()
                .get(0).getTotalQuantity().split(" ")[0]);
    }

    @Then("Click on cart to open")
    public void click_on_cart_to_open() {
        if (!greenKart.isCartPreviewActive())
            greenKart.clickOnCartIcon();
    }

    @Then("Assert cart preview is open now {string}")
    public void assert_cart_preview_is_open_now(String expectedVisibility) {
        Assert.assertEquals(Boolean.parseBoolean(expectedVisibility), greenKart.isCartPreviewActive());
    }

    @When("Click on cart to close")
    public void click_on_cart_to_close() {
        if (greenKart.isCartPreviewActive())
            greenKart.clickOnCartIcon();
    }

    @Then("Assert product details response and product details from application")
    public void assertProductDetailsResponseAndProductDetailsFromApplication() {
        List<ProductDetails> productDetailsList = testContext.getResponse().jsonPath().getList("", ProductDetails.class);
        List<Product> actrualProductList = greenKart.getProductList();
        SoftAssert softAssert = new SoftAssert();
        IntStream.range(0, productDetailsList.size()).forEach(index -> {
            softAssert.assertEquals(productDetailsList.get(index).getName(), actrualProductList.get(index).getProductName());
            softAssert.assertEquals(String.valueOf(productDetailsList.get(index).getPrice()), actrualProductList.get(index).getPrice());
        });
        softAssert.assertAll();
    }

    @Then("Assert Number of items added in cart is {int}")
    public void assertNumberOfItemsAddedInCartIs(int expectedNumberOfItems) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(String.valueOf(expectedNumberOfItems), greenKart.getNumberOfItemsInCart());
        softAssert.assertAll();
    }

    @And("Total cart price is {int}")
    public void totalCartPriceIs(int expectedTotalPrice) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(String.valueOf(expectedTotalPrice), greenKart.getTotalPriceInCart());
        softAssert.assertAll();
    }

    @Then("Assert Number of items added in cart preview is {int}")
    public void assertNumberOfItemsAddedInCartPreviewIs(int expectedNumberOfItems) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expectedNumberOfItems, greenKart.getCartPreview().getCartItems().size());
        softAssert.assertAll();
    }

    @And("Total cart price in cart preview is {int} for {string}")
    public void totalCartPriceInCartPreviewIsFor(int expectedTotalPrice, String productName) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(String.valueOf(expectedTotalPrice),
                greenKart.getCartPreview().getCartItems()
                        .stream().filter(product -> product.getProductName().startsWith(productName)).toList()
                        .get(0).getTotalAmount());
        softAssert.assertAll();
    }

    @And("Number of quantity for {string} is {int}")
    public void numberOfQuantityForIs(String productName, int expectedQuantity) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(String.valueOf(expectedQuantity),
                greenKart.getCartPreview().getCartItems()
                        .stream().filter(product -> product.getProductName().startsWith(productName)).toList()
                        .get(0).getTotalQuantity());
        softAssert.assertAll();
    }

    @And("Enter quantity for the following products")
    public void enterQuantityForTheFollowingProducts(DataTable dataTable) {
        List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
        dataList.forEach(entry -> {
            enter_as_quantity_for_product(Integer.parseInt(entry.get("quantity")), entry.get("product"));
        });
        testContext.getHashMap().put("productQtyList", dataList);
    }

    @And("Click on Add To Cart button for the following product")
    public void clickOnAddToCartButtonForTheFollowingProduct(DataTable dataTable) {
        List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
        dataList.forEach(entry -> click_on_add_to_cart_button_for(entry.get("product")));
    }

    @Then("Assert Number of items present in cart")
    @Then("Assert Number of items added in cart")
    public void assertNumberOfItemsAddedInCart() {
        int itemsAddedInCart = ((List<String>) (testContext.getHashMap().get("productQtyList"))).size();
        int itemsInCart = testContext.getHashMap().containsKey("productToRemove") ?
                itemsAddedInCart - ((List<String>) (testContext.getHashMap().get("productToRemove"))).size()
                :itemsAddedInCart;
        assertNumberOfItemsAddedInCartIs(itemsInCart);
    }

    @And("Total cart price from json response")
    public void totalCartPriceFromJsonResponse() {
        List<ProductDetails> productDetailsList = testContext.getResponse().jsonPath().getList("", ProductDetails.class);
        List<Map<String, String>> productQtyList = (List<Map<String, String>>) testContext.getHashMap().get(
                "productQtyList");
        Integer expectedTotalPrice = 0;
        for (Map<String, String> productAndQty : productQtyList) {
            ProductDetails productDetails = productDetailsList.stream()
                    .filter(product -> product.getName().startsWith(productAndQty.get("product"))).toList().get(0);
            expectedTotalPrice += productDetails.getPrice() * Integer.parseInt(productAndQty.get("quantity"));
        }
        Assert.assertEquals(String.valueOf(expectedTotalPrice), greenKart.getTotalPriceInCart());
    }

    @Then("Assert Number of items added in cart preview")
    public void assertNumberOfItemsAddedInCartPreview() {
        List<Map<String, String>> productQtyList = (List<Map<String, String>>) testContext.getHashMap().get(
                "productQtyList");
        Assert.assertEquals(productQtyList.size(), greenKart.getCartPreview().getCartItems().size());
    }

    @And("Total cart price in cart preview for each product")
    public void totalCartPriceInCartPreviewForEachProduct() {
        List<ProductDetails> productDetailsList = testContext.getResponse().jsonPath().getList("", ProductDetails.class);
        List<Map<String, String>> productQtyList = (List<Map<String, String>>) testContext.getHashMap().get(
                "productQtyList");
        List<Product> cartItems = greenKart.getCartPreview().getCartItems();
        SoftAssert softAssert = new SoftAssert();
        for (Map<String, String> productAndQty : productQtyList) {
            ProductDetails productDetails = productDetailsList.stream()
                    .filter(product -> product.getName().startsWith(productAndQty.get("product"))).toList().get(0);
            Integer expectedTotalPrice = productDetails.getPrice() * Integer.parseInt(productAndQty.get("quantity"));
            softAssert.assertEquals(String.valueOf(expectedTotalPrice),
                    cartItems.stream().filter(item -> item.getProductName().startsWith(productAndQty.get("product")))
                            .toList().get(0).getTotalAmount());
        }
        softAssert.assertAll();
    }

    @And("Number of quantity for each product")
    public void numberOfQuantityForEachProduct() {
        List<Map<String, String>> productQtyList = (List<Map<String, String>>) testContext.getHashMap().get(
                "productQtyList");
        List<Product> cartItems = greenKart.getCartPreview().getCartItems();
        SoftAssert softAssert = new SoftAssert();
        for (Map<String, String> productAndQty : productQtyList) {
            softAssert.assertEquals(String.valueOf(productAndQty.get("quantity")),
                    cartItems.stream().filter(item -> item.getProductName().startsWith(productAndQty.get("product")))
                            .toList().get(0).getTotalQuantity());
        }
        softAssert.assertAll();
    }

    @And("Click on Add To Cart button for the products")
    public void clickOnAddToCartButtonForTheProducts() {
        List<Map<String, String>> productQtyList = (List<Map<String, String>>) testContext.getHashMap().get(
                "productQtyList");
        productQtyList.forEach(entry -> click_on_add_to_cart_button_for(entry.get("product")));
    }

    @And("Remove the following products")
    public void removeTheFollowingProducts(DataTable dataTable) {
        List<Map<String, String>> dataList = dataTable.asMaps(String.class, String.class);
        dataList.forEach(entry -> greenKart.getCartPreview().getCartItem(entry.get("productToRemove")).removeItemFromCart());
        testContext.getHashMap().put("productToRemove", dataList);
    }

    @Then("Assert removed products are not available in cart")
    public void assertRemovedProductsAreNotAvailableInCart() {
        List<Map<String, String>> productsToRemove = (List<Map<String, String>>) testContext.getHashMap().get(
                "productToRemove");
        SoftAssert softAssert = new SoftAssert();
        productsToRemove.forEach(entry -> softAssert.assertFalse(greenKart.getCartPreview().isItemPresent(entry.get("productToRemove"))));
        softAssert.assertAll();
    }
}
