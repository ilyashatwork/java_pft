package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    ChromeDriver wd;
    public GroupHelper gh;
    public ContactHelper ch;
    public NavigationHelper nh;
    public SessionHelper sh;

    public void start() {
        wd = new ChromeDriver();
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
