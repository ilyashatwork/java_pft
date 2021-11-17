package ru.stqa.pft.addressbook.tests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactDeletionTests {
    public WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testContactDeletion() {
        wd.get("http://localhost/addressbook/");
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys("admin");
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys("secret");
        wd.findElement(By.xpath("//input[@value='Login']")).click();
        wd.findElement(By.name("selected[]")).click();
        wd.findElement(By.xpath("//input[@value='Delete']")).click();
        wd.switchTo().alert().accept();
        wd.findElement(By.linkText("home")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        wd.quit();
    }

}
