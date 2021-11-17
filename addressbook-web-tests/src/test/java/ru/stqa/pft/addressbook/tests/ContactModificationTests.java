package ru.stqa.pft.addressbook.tests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactModificationTests {
    public WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testContactModification() {
        wd.get("http://localhost/addressbook/");
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys("admin");
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys("secret");
        wd.findElement(By.xpath("//input[@value='Login']")).click();
        wd.findElement(By.xpath("//td[8]/a")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys("Ivan");
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys("Ivanov");
        wd.findElement(By.name("mobile")).clear();
        wd.findElement(By.name("mobile")).sendKeys("89053807510");
        wd.findElement(By.name("update")).click();
        wd.findElement(By.linkText("home page")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        wd.quit();
    }

}
