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
                .withHomePhone("503654").withMobilePhone("89053807510").withWorkPhone("89063000199")
                .withGroupValue(app.groups().getAnyGroupValue()).withAddress("410056").withEmail("info@mail.com");
        app.contacts().create(newC);
        assertThat(app.contacts().count(), equalTo(before.size() + 1));
        Contacts after = app.contacts().all();
        assertThat(after, equalTo(
                before.withAdded(newC.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
