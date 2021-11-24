package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import org.openqa.selenium.remote.BrowserType;

import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class BaseTests {

    protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite
    public void start() {
        app.start();
    }

    @AfterSuite
    public void stop() {
        app.stop();
    }

}
