package ru.stqa.pft.addressbook.appmanager;

import java.util.List;

import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver webDriver) {
        super(webDriver);
    }

    NavigationHelper goTo = new NavigationHelper(webDriver);
    GroupHelper groups = new GroupHelper(webDriver);

    public void enterButton() {
        click(By.name("submit"));
    }

    public void checkBox(int id) {
        webDriver.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void editImageLink(int id) {
        click(By.xpath("//table[@id='maintable']/tbody/tr[" + id + "]/td[8]/a/img"));
    }

    public void updateButton() {
        click(By.name("update"));
    }

    public void deleteButton() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void editFields(ContactData data, boolean creation) {
        print(By.name("lastname"), data.getLastName());
        print(By.name("firstname"), data.getFirstName());
        if (creation) {
            new Select(webDriver.findElement(By.name("new_group"))).selectByValue(data.getGroupValue());
        } else {
            Assert.assertFalse(elementPresentIs(By.name("new_group")));
        }
    }

    public void creationCheck() {
        goTo.homePage();
        if (all().size() == 0) {
            create(new ContactData().withLastName("Test last name #1").
                    withFirstName("Test first name #1").withGroupValue(groups.getAnyGroupValue()));
        }
    }

    public Contacts all() {
        goTo.homePage();
        Contacts contacts = new Contacts();
        List<WebElement> webElements = webDriver.findElements(By.name("selected[]"));
        int index = 2;
        for (WebElement webElement : webElements) {
            int id = Integer.parseInt(webElement.getAttribute("value"));
            String lastName = webElement.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + index +
                    "]/td[2]")).getText();
            String firstName = webElement.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + index +
                    "]/td[3]")).getText();
            index++;
            ContactData data = new ContactData().withId(id).withLastName(lastName).withFirstName(firstName);
            contacts.add(data);
        }
        return contacts;
    }

    public int getTableRow(int idExpected) {
        List<WebElement> webElements = webDriver.findElements(By.name("selected[]"));
        int index = 2;
        for (WebElement webElement : webElements) {
            int idReceived = Integer.parseInt(webElement.getAttribute("value"));
            if (idReceived == idExpected) {
                break;
            }
            index++;
        }
        return index;
    }

    public void create(ContactData data) {
        goTo.addNewPage();
        editFields(data, true);
        enterButton();
        goTo.homePage();
    }

    public void modify(ContactData data, int id) {
        goTo.homePage();
        editImageLink(id);
        editFields(data, false);
        updateButton();
        goTo.homePage();
    }

    public void delete(ContactData data) {
        goTo.homePage();
        checkBox(data.getId());
        deleteButton();
        alertAccept();
        goTo.homePage();
    }

}
