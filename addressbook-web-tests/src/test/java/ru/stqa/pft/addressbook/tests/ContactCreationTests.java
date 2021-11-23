package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class ContactCreationTests extends BaseTests {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groups().set().size() == 0) { app.groups().create(new GroupData().withName("Test group name #1")); }
    }

    @Test
    public void testContactCreation() {
        app.goTo().groupPage();
        String value = Integer.toString(app.groups().set().iterator().next().getId());
        String lastName = "Test last name #1";
        String firstName = "Test first name #1";

        app.goTo().homePage();
        Set<ContactData> before = app.contacts().set();

        app.contacts().create(new ContactData().withLastName(lastName).withFirstName(firstName).withGroupValue(value));

        app.goTo().homePage();
        Set<ContactData> after = app.contacts().set();

        Assert.assertEquals(after.size(), before.size() + 1);
        ContactData newC = new ContactData().withLastName(lastName).withFirstName(firstName).withGroupValue(value);
        newC.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(newC);
        Assert.assertEquals(after, before);
    }

}
