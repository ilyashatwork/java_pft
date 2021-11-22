package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends BaseTests {

    @BeforeMethod
    public void ensurePreconditions() {
        applicationManager.navigationHelper.goToGroupPage();
        applicationManager.getGroupHelper().groupCreationCheck(new GroupData("Test group name #1", "Test group header #1", "Test group footer #1"));

        applicationManager.navigationHelper.goToGroupPage();
        List<GroupData> groupsList = applicationManager.getGroupHelper().groupGetList();

        applicationManager.getNavigationHelper().goToHomePage();
        applicationManager.getContactHelper().contactCreationCheck(new ContactData("Test last name #1", "Test first name #1", "Test mobile #1",
                Integer.toString(groupsList.get(groupsList.size() - 1).getId())));
    }

    @Test
    public void testContactDeletion() {
        applicationManager.navigationHelper.goToHomePage();
        List<ContactData> contactsBefore = applicationManager.getContactHelper().contactGetList();

        int index = contactsBefore.size() - 1;

        applicationManager.getContactHelper().contactDeletion(index);

        applicationManager.navigationHelper.goToHomePage();
        List<ContactData> contactsAfter = applicationManager.getContactHelper().contactGetList();

        Assert.assertEquals(contactsAfter.size(), index);
        contactsBefore.remove(contactsBefore.size() - 1);
        Assert.assertEquals(contactsAfter, contactsBefore);
    }

}
