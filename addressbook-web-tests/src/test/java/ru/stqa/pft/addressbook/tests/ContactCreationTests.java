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
        app.goTo().groupPage();
        app.group().creationCheck(new GroupData().withName("Test group name #1").withHeader("Test group header #1").withFooter("Test group footer #1"));
    }

    @Test
    public void testContactCreation() {
        app.goTo().groupPage();
        List<GroupData> groupsList = app.group().list();

        ContactData contactExpected = new ContactData().withLastName("Test last name #1").withFirstName("Test first name #1").withMobile("Test mobile #1").
                withGroupValue(Integer.toString(groupsList.get(groupsList.size() - 1).getId()));

        app.goTo().homePage();
        List<ContactData> contactsBefore = app.contact().list();

        app.contact().create(new ContactData().withLastName("Test last name #1").withFirstName("Test first name #1").withMobile("Test mobile #1").
                withGroupValue(Integer.toString(groupsList.get(groupsList.size() - 1).getId())));

        app.goTo().homePage();
        List<ContactData> contactsAfter = app.contact().list();

        Assert.assertEquals(contactsAfter.size(), contactsBefore.size() + 1);
        contactsBefore.add(contactExpected);
        Comparator<? super ContactData> byLastName = (c1, c2) -> c1.getLastName().compareTo(c2.getLastName());
        contactsBefore.sort(byLastName);
        contactsAfter.sort(byLastName);
        Assert.assertEquals(contactsAfter, contactsBefore);
    }

}
