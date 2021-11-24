package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends BaseTests {

    @BeforeMethod
    public void ensurePreconditions() {
        app.groups().creationCheck();
    }

    @Test
    public void testContactCreation() {
        Contacts before = app.contacts().all();
        ContactData newC = new ContactData().withLastName("Test last name #1").withFirstName("Test first name #1")
                .withGroupValue(app.groups().getAnyGroupValue());
        app.contacts().create(newC);
        Contacts after = app.contacts().all();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(newC.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
