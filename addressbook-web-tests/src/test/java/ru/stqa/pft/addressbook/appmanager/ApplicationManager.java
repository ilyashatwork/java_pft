package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    public String browser;
    public GroupHelper gh;
    public ContactHelper ch;
    public NavigationHelper nh;
    public SessionHelper sh;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void start() {
        switch (browser) {
            case BrowserType.CHROME -> wd = new ChromeDriver();
            case BrowserType.FIREFOX -> wd = new FirefoxDriver();
        }
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");

        gh = new GroupHelper(wd);
        ch = new ContactHelper(wd);
        nh = new NavigationHelper(wd);
        sh = new SessionHelper(wd);

        sh.login("admin", "secret");
    }

    public void stop() {
        sh.logout();
        wd.quit();
    }

    public GroupHelper getGh() {
        return gh;
    }

    public NavigationHelper getNh() {
        return nh;
    }

    public ContactHelper getCh() {
        return ch;
    }

}
