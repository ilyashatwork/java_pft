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

    public void updateButton() {
        click(By.name("update"));
    }

    public void deleteButton() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void editFields(ContactData data, boolean creation) {
        print(By.name("lastname"), data.getLastName());
        print(By.name("firstname"), data.getFirstName());
        print(By.name("home"), data.getHomePhone());
        print(By.name("mobile"), data.getMobilePhone());
        print(By.name("work"), data.getWorkPhone());
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
                    withFirstName("Test first name #1").withHomePhone("503654").withMobilePhone("89053807510")
                    .withWorkPhone("89063000199").withGroupValue(groups.getAnyGroupValue()));
        }
    }

    public Contacts contactsCache = null;

    public Contacts all() {
        goTo.homePage();
        if (contactsCache != null) {
            return new Contacts(contactsCache);
        }
        contactsCache = new Contacts();
        List<WebElement> rows = webDriver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String[] phones = cells.get(5).getText().split("\n");
            ContactData data = new ContactData().withId(id).withLastName(lastName).withFirstName(firstName)
                    .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]);
            contactsCache.add(data);
        }
        return new Contacts(contactsCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String lastName = webDriver.findElement(By.name("lastname")).getAttribute("value");
        String firstName = webDriver.findElement(By.name("firstname")).getAttribute("value");
        String home = webDriver.findElement(By.name("home")).getAttribute("value");
        String mobile = webDriver.findElement(By.name("mobile")).getAttribute("value");
        String work = webDriver.findElement(By.name("work")).getAttribute("value");
        webDriver.navigate().back();
        return new ContactData().withId(contact.getId()).withLastName(lastName).withFirstName(firstName)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    public void initContactModificationById(int id) {
        webDriver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
        // Or
        //webDriver.findElement(By.xpath(String.format("//input[@value='%s']/../../d[8]a", id))).click();
        // Or
        /*WebElement checkBox = webDriver.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkBox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();*/
    }

    public int count() {
        return webDriver.findElements(By.name("selected[]")).size();
    }

    public void create(ContactData data) {
        goTo.addNewPage();
        editFields(data, true);
        enterButton();
        contactsCache = null;
        goTo.homePage();
    }

    public void modify(ContactData data, int id) {
        goTo.homePage();
        initContactModificationById(id); // или editImageLink(id);
        editFields(data, false);
        updateButton();
        contactsCache = null;
        goTo.homePage();
    }

    public void delete(ContactData data) {
        goTo.homePage();
        checkBox(data.getId());
        deleteButton();
        alertAccept();
        contactsCache = null;
        goTo.homePage();
    }

    /*public Contacts all() {
        goTo.homePage();
        if (contactsCache != null) {
            return new Contacts(contactsCache);
        }
        contactsCache = new Contacts();
        List<WebElement> webElements = webDriver.findElements(By.name("selected[]"));
        int index = 2;
        for (WebElement webElement : webElements) {
            int id = Integer.parseInt(webElement.getAttribute("value"));
            String lastName = webElement.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + index +
                    "]/td[2]")).getText();
            String firstName = webElement.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + index +
                    "]/td[3]")).getText();
            String allPhones
            index++;
            ContactData data = new ContactData().withId(id).withLastName(lastName).withFirstName(firstName);
            contactsCache.add(data);
        }
        return new Contacts(contactsCache);
    }*/

    /*public int getTableRow(int idExpected) {
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
    }*/

    /*public void editImageLink(int id) {
        click(By.xpath("//table[@id='maintable']/tbody/tr[" + id + "]/td[8]/a/img"));
    }*/

}
