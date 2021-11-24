package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;

public class BaseHelper {
    WebDriver webDriver;

    public BaseHelper(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void click(By locator) {
        webDriver.findElement(locator).click();
    }

    public void print(By locator, String text) {
        if (text != null) {
            String existingText = webDriver.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                webDriver.findElement(locator).clear();
                webDriver.findElement(locator).sendKeys(text);
            }
        }
    }

    public void alertAccept() {
        webDriver.switchTo().alert().accept();
    }

    public boolean elementPresentIs(By locator) {
        try {
            webDriver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

}
