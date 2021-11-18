package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNh().goToHomePage();
        app.getCh().editContact();
        app.getCh().fillContactForm(new ContactData("Ivan", "Ivanov", "89053907510"));
        app.getCh().updateContact();
        app.getNh().goToHomePageLink();
    }

}
