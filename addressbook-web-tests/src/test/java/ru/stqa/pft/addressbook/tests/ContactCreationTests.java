package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends BaseTests {

    @Test
    public void testContactCreation() {
        applicationManager.getGroupHelper().groupCreationCheck(new GroupData("Test group name #1", "Test group header #1", "Test group footer #1"));
        applicationManager.getContactHelper().contactCreationProcess(new ContactData("Test first name #1", "Test last name #1", "Test mobile #1", "Test group name #1"));
    }

}
