package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactModificationTests extends BaseTests {

    @Test
    public void testContactModification() {
        applicationManager.getGroupHelper().groupCreationCheck(new GroupData("Test group name #1", "Test group header #1", "Test group footer #1"));
        List<GroupData> groupsList = applicationManager.getGroupHelper().groupGetList();
        applicationManager.getContactHelper().contactCreationCheck(new ContactData("Test last name #1", "Test first name #1", "Test mobile #1",
                Integer.toString(groupsList.get(groupsList.size() - 1).getId())));
        applicationManager.getContactHelper().contactModificationProcess(new ContactData("Test last name #2", "Test first name #2", "Test mobile #2",
                Integer.toString(groupsList.get(groupsList.size() - 1).getId())));
    }

}
