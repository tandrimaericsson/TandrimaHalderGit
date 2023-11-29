package org.example.pageClasses.greenkarts;

import org.example.utilities.ManageWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPreview {

    private ManageWebDriver manageWebDriver;
    private WebElement parent;
    private final By cartItem = By.className("cart-item");
    private final String cartItemLocator = ".//p[@class='product-name' and starts-with(text(),'%s')]/ancestor::li";

    public CartPreview(ManageWebDriver manageWebDriver, WebElement parent) {
        this.manageWebDriver = manageWebDriver;
        this.parent = parent;
    }

    public Product getCartItem(String productName) {
        return new Product(manageWebDriver, manageWebDriver.waitForElement(
                By.xpath(String.format(cartItemLocator, productName)), parent));
    }

    public List<Product> getCartItems() {
        return manageWebDriver.waitForElements(cartItem, parent).stream()
                .map(webElement -> new Product(manageWebDriver, webElement)).toList();
    }

    public boolean isItemPresent(String productName) {
        return manageWebDriver.waitForElement(
                By.xpath(String.format(cartItemLocator, productName)), parent)!=null;
    }
}
