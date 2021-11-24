package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends BaseTests {

    @BeforeMethod
    public void ensurePreconditions() {
        app.groups().creationCheck();
        app.contacts().creationCheck();
    }

    @Test
    public void testContactDeletion() {
        Contacts before = app.contacts().all();
        ContactData toDelete = before.iterator().next();
        app.contacts().delete(toDelete);
        assertThat(app.contacts().count(), equalTo(before.size() - 1));
        Contacts after = app.contacts().all();
        assertThat(after, equalTo(before.without(toDelete)));
    }

}
