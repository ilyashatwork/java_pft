package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class ContactModificationTests extends BaseTests {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groups().set().size() == 0) { app.groups().create(new GroupData().withName("Test group name #1")); }
    }

    @Test
    public void testContactModification() {
        app.goTo().groupPage();
        String value = Integer.toString(app.groups().set().iterator().next().getId());
        app.goTo().homePage();
        if (app.contacts().set().size() == 0) { app.contacts().create(new ContactData().
                withLastName("Test last name #1").withFirstName("Test first name #1").withGroupValue(value)); }

        app.goTo().homePage();
        Set<ContactData> before = app.contacts().set();

        ContactData oldData = before.iterator().next();
        ContactData newData = new ContactData().withId(oldData.getId()).withLastName("Test last name #2")
                .withFirstName("Test first name #2");

        app.contacts().modify(newData, app.contacts().getTr(oldData.getId()));

        app.goTo().homePage();
        Set<ContactData> after = app.contacts().set();

        Assert.assertEquals(after.size(), before.size());
        before.remove(oldData);
        before.add(newData);
        Assert.assertEquals(after, before);
    }

}
