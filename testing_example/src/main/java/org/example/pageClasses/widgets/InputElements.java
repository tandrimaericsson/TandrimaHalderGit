package org.example.pageClasses.widgets;

import org.openqa.selenium.WebElement;

public class InputElements {

    private final WebElement element;

    public InputElements(WebElement element) {
        this.element = element;
    }

    public String readValue() {
        return element.getAttribute("value");
    }

    public void enterValue(String value) {
        element.clear();
        element.sendKeys(value);
    }

    public void enterWithoutClear(String value) {
        element.sendKeys(value);
    }
}
