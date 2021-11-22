package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;

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

    public void contactEditImageLink() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void contactUpdateButton() {
        click(By.name("update"));
    }

    public void contactDeleteButton() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void contactEditFields(ContactData contactData, boolean creationIs) {
        print(By.name("lastname"), contactData.getLastName());
        print(By.name("firstname"), contactData.getFirstName());
        print(By.name("mobile"), contactData.getMobile());
        if (creationIs) {
            new Select(webDriver.findElement(By.name("new_group"))).selectByValue(contactData.getGroupValue());
        } else {
            Assert.assertFalse(elementPresentIs(By.name("new_group")));
        }
    }

    public void contactCreationCheck(ContactData contactData) {
        if (!contactIs()) {
            contactCreation(contactData);
        }
    }

    public boolean contactIs() {
        return elementPresentIs(By.name("selected[]"));
    }

    public List<ContactData> contactGetList() {
        List<ContactData> contactListOfContactData = new ArrayList<ContactData>();
        List<WebElement> contactWebElements = webDriver.findElements(By.name("selected[]"));
        int index = 2;
        for (WebElement contactWebElement : contactWebElements) {
            String stringIndex = String.valueOf(index);
            String lastName = contactWebElement.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + stringIndex + "]/td[2]")).getText();
            String firstName = contactWebElement.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + stringIndex + "]/td[3]")).getText();
            index++;
            ContactData contactData = new ContactData(lastName, firstName, null, null);
            contactListOfContactData.add(contactData);
        }
        return contactListOfContactData;
    }

    public void contactCreation(ContactData contactData) {
        navigationHelper.goToAddNewPage();
        contactEditFields(contactData, true);
        contactEnterButton();
    }

    public void contactModification(ContactData contactData) {
        contactEditImageLink();
        contactEditFields(contactData, false);
        contactUpdateButton();
    }

    public void contactDeletion(int index) {
        contactCheckBox(index);
        contactDeleteButton();
        alertAccept();
    }

}
