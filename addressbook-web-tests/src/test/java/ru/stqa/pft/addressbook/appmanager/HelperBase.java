package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelperBase {
    ChromeDriver wd;

    public HelperBase(ChromeDriver wd) {
        this.wd = wd;
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void type(By locator, String text) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }
}
