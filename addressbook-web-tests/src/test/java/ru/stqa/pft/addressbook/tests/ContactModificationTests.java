package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends BaseTests {

    @Test
    public void testContactModification() {
        applicationManager.getGroupHelper().groupCreationCheck(new GroupData("Test group name #1", "Test group header #1", "Test group footer #1"));
        applicationManager.getContactHelper().contactCreationCheck(new ContactData("Test first name #1", "Test last name #1", "Test mobile #1", "Test group name #1"));
        applicationManager.getContactHelper().contactModificationProcess(new ContactData("Test first name #2", "Test last name #2", "Test mobile #2", "Test group name #1"));
    }

}
