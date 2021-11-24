package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactParametersTests extends BaseTests {

    @BeforeMethod
    public void ensurePreconditions() {
        app.groups().creationCheck();
        app.contacts().creationCheck();
    }

    @Test
    public void testContactPhones() {
        ContactData contact = app.contacts().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);
        /*assertThat(contact.getHomePhone(), equalTo(cleanPhones(contactInfoFromEditForm.getHomePhone())));
        assertThat(contact.getMobilePhone(), equalTo(cleanPhones(contactInfoFromEditForm.getMobilePhone())));
        assertThat(contact.getWorkPhone(), equalTo(cleanPhones(contactInfoFromEditForm.getWorkPhone())));*/
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactParametersTests::cleanPhones)
                .collect(Collectors.joining("\n"));
    }

    public static String cleanPhones(String phone) {
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }

    @Test
    public void testContactAddress() {
        ContactData contact = app.contacts().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

    @Test
    public void testContactEmail() {
        ContactData contact = app.contacts().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);
        assertThat(contact.getEmail(), equalTo(contactInfoFromEditForm.getEmail()));
    }

}
