package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends BaseTests {

    @Test
    public void testGroupCreation() {
        applicationManager.getGroupHelper().groupCreationProcess(new GroupData("Test group name #777", "Test group header #1", "Test group footer #1"));
    }

}
