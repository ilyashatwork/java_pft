package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class BaseTests {

    protected final ApplicationManager applicationManager = new ApplicationManager(BrowserType.CHROME);

    @BeforeMethod(alwaysRun = true)
    public void start() {
        applicationManager.start();
    }

    @AfterMethod(alwaysRun = true)
    public void stop() {
        applicationManager.stop();
    }

}
