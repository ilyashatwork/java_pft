package ru.stqa.pft.addressbook.tests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactDeletionTests extends TestBase {
    public WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        login("admin", "secret");
    }

    public void login(String username, String password) {
        wd.get("http://localhost/addressbook/");
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    @Test
    public void testContactDeletion() {
        selectContact();
        deleteSelectedContact();
        acceptAllert();
        goToHomePage();
    }

    public void goToHomePage() {
        wd.findElement(By.linkText("home")).click();
    }

    public void acceptAllert() {
        wd.switchTo().alert().accept();
    }

    public void deleteSelectedContact() {
        wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    public void selectContact() {
        wd.findElement(By.name("selected[]")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        wd.quit();
    }

}
