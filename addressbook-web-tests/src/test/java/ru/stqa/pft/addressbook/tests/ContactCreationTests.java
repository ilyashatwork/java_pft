package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNh().goToAddNewPage();
        app.getCh().fillContactForm(new ContactData("Ilya", "Shatskiy", "89063000199"));
        app.getCh().submitContactCreation();
        app.getNh().goToHomePageLink();
    }

}
