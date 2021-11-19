package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver webDriver) {
        super(webDriver);
    }

    NavigationHelper navigationHelper = new NavigationHelper(webDriver);

    public void contactEnterButton() {
        click(By.name("submit"));
    }

    public void contactCheckBox(int contactIndex) {
        webDriver.findElements(By.name("selected[]")).get(contactIndex).click();
    }

    public void contactEditImageLink() { click(By.xpath("//td[8]/a")); }

    public void contactUpdateButton() {
        click(By.name("update"));
    }

    public void contactDeleteButton() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void contactEditFields(ContactData contactData, boolean creationIs) {
        print(By.name("firstname"), contactData.firstName());
        print(By.name("lastname"), contactData.lastName());
        print(By.name("mobile"), contactData.mobile());
        if (creationIs) {
            new Select(webDriver.findElement(By.name("new_group"))).selectByVisibleText(contactData.group());
        } else {
            Assert.assertFalse(elementPresentIs(By.name("new_group")));
        }
    }

    public void contactCreationCheck(ContactData contactData) {
        navigationHelper.goToHomePage();
        if (!contactIs()) {
            contactCreationProcess(contactData);
        }
    }

    public boolean contactIs() {
        return elementPresentIs(By.name("selected[]"));
    }

    public int contactGetCount() {
        return webDriver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> contactGetList() {
        List<ContactData>  contactList = new ArrayList<ContactData>();
        List<WebElement> webElements = webDriver.findElements(By.name("selected[]"));
        for (WebElement webElement : webElements) {
            String contactName = webElement.getText();
            ContactData contactData = new ContactData(contactName, null, null, null);
            contactList.add(contactData);
        }
        return contactList;
    }

    public void contactCreationProcess(ContactData contactData) {
        navigationHelper.goToHomePage();
        List<ContactData> contactCountBefore = contactGetList();

        navigationHelper.goToAddNewPage();
        contactEditFields(contactData, true);
        contactEnterButton();

        navigationHelper.goToHomePage();
        List<ContactData> contactCountAfter = contactGetList();

        Assert.assertEquals(contactCountAfter.size(), contactCountBefore.size() + 1);
    }

    public void contactModificationProcess(ContactData contactData) {
        navigationHelper.goToHomePage();
        List<ContactData> contactCountBefore = contactGetList();

        contactEditImageLink();
        contactEditFields(contactData, false);
        contactUpdateButton();

        navigationHelper.goToHomePage();
        List<ContactData> contactCountAfter = contactGetList();

        Assert.assertEquals(contactCountAfter.size(), contactCountBefore.size());
    }

    public void contactDeletionProcess() {
        navigationHelper.goToHomePage();
        List<ContactData> contactCountBefore = contactGetList();

        contactCheckBox(contactCountBefore.size() - 1);
        contactDeleteButton();
        alertAccept();

        navigationHelper.goToHomePage();
        List<ContactData> contactCountAfter = contactGetList();;

        Assert.assertEquals(contactCountAfter.size(), contactCountBefore.size() - 1);
    }

}
