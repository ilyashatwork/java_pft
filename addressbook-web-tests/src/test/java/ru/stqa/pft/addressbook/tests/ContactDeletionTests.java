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
        app.goTo().groupPage();
        app.group().creationCheck(new GroupData().withName("Test group name #1").withHeader("Test group header #1").withFooter("Test group footer #1"));

        app.goTo().groupPage();
        List<GroupData> groupsList = app.group().list();

        app.goTo().homePage();
        app.contact().creationCheck(new ContactData().withLastName("Test last name #1").withFirstName("Test first name #1").withMobile("Test mobile #1").
                withGroupValue(Integer.toString(groupsList.get(groupsList.size() - 1).getId())));
    }

    @Test
    public void testContactDeletion() {
        app.goTo().homePage();
        List<ContactData> contactsBefore = app.contact().list();

        int index = contactsBefore.size() - 1;

        app.contact().delete(index);

        app.goTo().homePage();
        List<ContactData> contactsAfter = app.contact().list();

        Assert.assertEquals(contactsAfter.size(), index);
        contactsBefore.remove(contactsBefore.size() - 1);
        Assert.assertEquals(contactsAfter, contactsBefore);
    }

}
