package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class BaseTests {

    protected static final ApplicationManager applicationManager = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite
    public void start() { applicationManager.start(); }

    @AfterSuite
    public void stop() {
        applicationManager.stop();
    }

}
