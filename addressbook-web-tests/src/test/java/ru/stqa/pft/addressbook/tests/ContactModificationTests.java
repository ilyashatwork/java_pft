package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends BaseTests {

    @BeforeMethod
    public void ensurePreconditions() {
        app.groups().creationCheck();
        app.contacts().creationCheck();
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contacts().all();
        ContactData oldData = before.iterator().next();
        ContactData newData = new ContactData().withId(oldData.getId()).withLastName("Test last name #2")
                .withFirstName("Test first name #2");
        app.contacts().modify(newData, app.contacts().getTableRow(oldData.getId()));
        assertThat(app.contacts().count(), equalTo(before.size()));
        Contacts after = app.contacts().all();
        assertThat(after, equalTo(before.without(oldData).withAdded(newData)));
    }

}
