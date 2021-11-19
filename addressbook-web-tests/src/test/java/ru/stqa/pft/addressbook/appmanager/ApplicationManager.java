package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver webDriver;
    public String browser;
    public GroupHelper groupHelper;
    public ContactHelper contactHelper;
    public NavigationHelper navigationHelper;
    public SessionHelper sessionHelper;

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

        groupHelper = new GroupHelper(webDriver);
        contactHelper = new ContactHelper(webDriver);
        navigationHelper = new NavigationHelper(webDriver);
        sessionHelper = new SessionHelper(webDriver);

        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        sessionHelper.logout();
        webDriver.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

}
