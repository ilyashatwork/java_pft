package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver webDriver;
    public String browser;
    public SessionHelper session;
    public NavigationHelper goTo;
    public GroupHelper groups;
    public ContactHelper contacts;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void start() {
        switch (browser) {
            case BrowserType.CHROME -> webDriver = new ChromeDriver();
            case BrowserType.FIREFOX -> webDriver = new FirefoxDriver();
        }
        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        webDriver.get("http://localhost/addressbook/");

        session = new SessionHelper(webDriver);
        goTo = new NavigationHelper(webDriver);
        groups = new GroupHelper(webDriver);
        contacts = new ContactHelper(webDriver);

        session.login("admin", "secret");
    }

    public void stop() {
        session.logout();
        webDriver.quit();
    }

    public NavigationHelper goTo() {
        return goTo;
    }

    public GroupHelper groups() {
        return groups;
    }

    public ContactHelper contacts() {
        return contacts;
    }

}
