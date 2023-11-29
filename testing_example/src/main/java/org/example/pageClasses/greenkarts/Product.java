package org.example.pageClasses.greenkarts;

import org.example.pageClasses.widgets.InputElements;
import org.example.utilities.ManageWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Product {

    private ManageWebDriver manageWebDriver;
    private WebElement product;
    private String productName;
    private final String productDiv = ".//h4[@class='product-name' and starts-with(text(),'%s')]/ancestor::div[@class='product']";
    private final By productNameLocator = By.className("product-name");
    private final By price = By.className("product-price");
    private final By decreaseQty = By.className("decrement");
    private final By increaseQty = By.className("increment");
    private final By quantity = By.className("quantity");
    private final By addToCartButton = By.xpath(".//button[text()='ADD TO CART']");
    private final By addedButton = By.xpath(".//button[contains(text(),'ADDED')]");
    private final By totalQuantity = By.xpath(".//div[@class='product-total']//p[@class='quantity']");
    private final By totalAmount = By.xpath(".//div[@class='product-total']//p[@class='amount']");
    private final By removeProductFromCart = By.xpath(".//a[@class='product-remove']");

    public Product(ManageWebDriver manageWebDriver, String productName) {
        this.manageWebDriver = manageWebDriver;
        this.productName = productName;
    }

    public Product(ManageWebDriver manageWebDriver, WebElement product) {
        this.manageWebDriver = manageWebDriver;
        this.product = product;
    }

    private WebElement getProductDiv() {
        return manageWebDriver.waitForElement(By.xpath(String.format(productDiv, productName)), null);
    }

    private WebElement getDecreaseQuantity() {
        return manageWebDriver.waitForElement(decreaseQty, getProductDiv());
    }

    private WebElement getIncreaseQuantity() {
        return manageWebDriver.waitForElement(increaseQty, getProductDiv());
    }

    public String getPrice() {
        return manageWebDriver.waitForElement(price, product!=null ? product:getProductDiv()).getText();
    }

    public void decreaseQuantity(int count) {
        for (int i = 0; i < count; i++)
            manageWebDriver.click(getDecreaseQuantity());
    }

    public void increaseQuantity(int count) {
        for (int i = 0; i < count; i++)
            manageWebDriver.click(getIncreaseQuantity());
    }

    public InputElements quantityInput() {
        return new InputElements(manageWebDriver.waitForElement(quantity, getProductDiv()));
    }

    public String getProductName() {
        return manageWebDriver.waitForElement(productNameLocator, product!=null ? product:getProductDiv()).getText();
    }

    public void clickOnAddToCart() {
        manageWebDriver.click(addToCartButton, product!=null ? product:getProductDiv());
    }

    public boolean isAddToCartButtonDisplayed() {
        return manageWebDriver.waitForElement(addToCartButton, product!=null ? product:getProductDiv()).isDisplayed();
    }

    public boolean isAddedButtonDisplayed() {
        return manageWebDriver.waitForElement(addedButton, product!=null ? product:getProductDiv()).isDisplayed();
    }

    public String getTotalQuantity() {
        return manageWebDriver.waitForElement(totalQuantity, product!=null ? product:getProductDiv()).getText().split(" ")[0];
    }

    public String getTotalAmount() {
        return manageWebDriver.waitForElement(totalAmount, product!=null ? product:getProductDiv()).getText();
    }

    public void removeItemFromCart() {
        manageWebDriver.click(removeProductFromCart, product!=null ? product:getProductDiv());
    }
}
