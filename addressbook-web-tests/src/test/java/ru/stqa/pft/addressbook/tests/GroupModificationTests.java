package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends BaseTests {

    @Test
    public void testGroupModification() {
        applicationManager.getGroupHelper().groupCreationCheck(new GroupData("Test group name #1", "Test group header #1", "Test group footer #1"));
        applicationManager.getGroupHelper().groupModificationProcess(new GroupData("Test group name #2", "Test group header #2", "Test group footer #2"));
    }

}
