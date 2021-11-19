package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends BaseHelper {

    public SessionHelper(WebDriver webDriver) {
        super(webDriver);
    }

    public void login(String username, String password) {
        print(By.name("user"), username);
        print(By.name("pass"), password);
        click(By.xpath("//input[@value='Login']"));
    }

    public void logout() {
        click(By.linkText("Logout"));
    }

}
