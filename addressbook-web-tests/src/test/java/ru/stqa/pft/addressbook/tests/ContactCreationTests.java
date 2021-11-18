package ru.stqa.pft.addressbook.tests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
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
    public void testContactCreation() {
        goToAddNewPage();
        fillContactForm(new ContactData("Ilya", "Shatskiy", "89063000199"));
        submitContactCreation();
        goToHomePageLink();
    }

    public void goToHomePageLink() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void submitContactCreation() {
        wd.findElement(By.name("submit")).click();
    }

    public void fillContactForm(ContactData contactData) {
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(contactData.firstName());
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactData.lastName());
        wd.findElement(By.name("mobile")).clear();
        wd.findElement(By.name("mobile")).sendKeys(contactData.mobile());
    }

    public void goToAddNewPage() {
        wd.findElement(By.linkText("add new")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        wd.quit();
    }

}
