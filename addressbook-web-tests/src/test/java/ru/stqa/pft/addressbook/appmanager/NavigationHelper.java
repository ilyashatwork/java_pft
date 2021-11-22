package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver webDriver) {
        super(webDriver);
    }

    public void homePage() {
        if (elementPresentIs(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void groupPage() {
        if (elementPresentIs(By.tagName("h1"))
                && webDriver.findElement(By.tagName("h1")).getText().equals("Groups")
                && elementPresentIs(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void addNewPage() {
        if (elementPresentIs(By.tagName("h1"))
                && webDriver.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
                && elementPresentIs(By.name("submit"))) {
            return;
        }
        click(By.linkText("add new"));
    }

}
