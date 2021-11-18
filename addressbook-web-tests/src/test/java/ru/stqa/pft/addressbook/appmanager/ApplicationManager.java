package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
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
        if (browser == BrowserType.CHROME) {
            wd = new ChromeDriver();
        }
        else if (browser == BrowserType.FIREFOX) {
            wd = new FirefoxDriver();
        }
        else if (browser == BrowserType.IE) {
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
