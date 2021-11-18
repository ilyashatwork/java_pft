package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNh().goToHomePage();
        app.getCh().selectContact();
        app.getCh().deleteSelectedContact();
        app.getCh().acceptAllert();
        app.getNh().goToHomePage();
    }

}
