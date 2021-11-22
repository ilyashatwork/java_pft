package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends BaseTests {

    @BeforeMethod
    public void ensurePreconditions() {
        applicationManager.navigationHelper.goToGroupPage();
        applicationManager.getGroupHelper().groupCreationCheck(new GroupData("Test group name #1", "Test group header #1", "Test group footer #1"));
    }

    @Test
    public void testContactCreation() {
        applicationManager.navigationHelper.goToGroupPage();
        List<GroupData> groupsList = applicationManager.getGroupHelper().groupGetList();

        ContactData contactExpected = new ContactData("Test last name #1", "Test first name #1", "Test mobile #1",
                Integer.toString(groupsList.get(groupsList.size() - 1).getId()));

        applicationManager.navigationHelper.goToHomePage();
        List<ContactData> contactsBefore = applicationManager.getContactHelper().contactGetList();

        applicationManager.getContactHelper().contactCreation(new ContactData("Test last name #1", "Test first name #1", "Test mobile #1",
                Integer.toString(groupsList.get(groupsList.size() - 1).getId())));

        applicationManager.navigationHelper.goToHomePage();
        List<ContactData> contactsAfter = applicationManager.getContactHelper().contactGetList();

        Assert.assertEquals(contactsAfter.size(), contactsBefore.size() + 1);
        contactsBefore.add(contactExpected);
        Comparator<? super ContactData> byLastName = (c1, c2) -> c1.getLastName().compareTo(c2.getLastName());
        contactsBefore.sort(byLastName);
        contactsAfter.sort(byLastName);
        Assert.assertEquals(contactsAfter, contactsBefore);
    }

}
