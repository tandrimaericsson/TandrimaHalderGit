package org.example.pageClasses.greenkarts;

import org.example.pageClasses.widgets.InputElements;
import org.example.utilities.ManageWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GreenKartPage {

    private final ManageWebDriver manageWebDriver;
    private final By searchField = By.className("search-keyword");
    private final By searchButton = By.className("search-button");
    private final By products = By.xpath(".//div[@class='products']//div[@class='product']");
    private final By numberOfItemsInCart = By.xpath(".//div[@class='cart-info']//table//tr[1]//td[last()]");
    private final By totalPrice = By.xpath(".//div[@class='cart-info']//table//tr[2]//td[last()]");
    private final By cartIcon = By.xpath(".//a[@class='cart-icon']//img");
    private final By cartSection = By.xpath(".//div[starts-with(@class,'cart-preview')]");

    public GreenKartPage(ManageWebDriver manageWebDriver) {
        this.manageWebDriver = manageWebDriver;
    }

    public Product getProduct(String productName) {
        return new Product(manageWebDriver, productName);
    }

    public List<Product> getProductList() {
        List<WebElement> productList = manageWebDriver.waitForElements(products, null);
        return productList.stream().map(productElement -> new Product(manageWebDriver, productElement)).toList();
    }

    public InputElements getSearchTextBox() {
        return new InputElements(manageWebDriver.waitForElement(searchField, null));
    }

    public void clickOnSearchButton() {
        manageWebDriver.click(searchButton, null);
    }

    public String getNumberOfItemsInCart() {
        return manageWebDriver.waitForElement(numberOfItemsInCart, null).getText();
    }

    public String getTotalPriceInCart() {
        return manageWebDriver.waitForElement(totalPrice, null).getText();
    }

    public void clickOnCartIcon() {
        manageWebDriver.click(cartIcon, null);
    }

    public CartPreview getCartPreview() {
        return new CartPreview(manageWebDriver, manageWebDriver.waitForElement(cartSection, null));
    }

    public boolean isCartPreviewActive() {
        return manageWebDriver.waitForElement(cartSection, null).getAttribute("class").contains("active");
    }
}
