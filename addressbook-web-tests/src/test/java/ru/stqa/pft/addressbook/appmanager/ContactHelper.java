package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver webDriver) {
        super(webDriver);
    }

    NavigationHelper navigationHelper = new NavigationHelper(webDriver);

    public void contactEnterButton() {
        click(By.name("submit"));
    }

    public void contactCheckBox() {
        click(By.name("selected[]"));
    }

    public void contactEditImageLink() {
        click(By.xpath("//td[8]/a"));
    }

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

    public void contactCreationProcess(ContactData contactData) {
        navigationHelper.goToAddNewPage();
        contactEditFields(contactData, true);
        contactEnterButton();
        navigationHelper.goToHomePage();
    }

    public void contactModificationProcess(ContactData contactData) {
        navigationHelper.goToHomePage();
        contactEditImageLink();
        contactEditFields(contactData, false);
        contactUpdateButton();
        navigationHelper.goToHomePage();
    }

    public void contactDeletionProcess() {
        navigationHelper.goToHomePage();
        contactCheckBox();
        contactDeleteButton();
        alertAccept();
        navigationHelper.goToHomePage();
    }

}
