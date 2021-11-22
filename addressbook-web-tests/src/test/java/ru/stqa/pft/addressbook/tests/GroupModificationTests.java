package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends BaseTests {

    @BeforeMethod
    public void ensurePreconditions() {
        applicationManager.navigationHelper.goToGroupPage();
        applicationManager.getGroupHelper().groupCreationCheck(new GroupData("Test group name #1", "Test group header #1", "Test group footer #1"));
    }

    @Test
    public void testGroupModification() {
        applicationManager.navigationHelper.goToGroupPage();
        List<GroupData> groupsBefore = applicationManager.groupHelper.groupGetList();

        int index = groupsBefore.size() - 1;
        GroupData groupExpected = new GroupData(groupsBefore.get(index).getId(), "Test group name #0", "Test group header #2", "Test group footer #2");

        applicationManager.groupHelper.groupModification(new GroupData("Test group name #0", "Test group header #2", "Test group footer #2"), index);

        applicationManager.navigationHelper.goToGroupPage();
        List<GroupData> groupsAfter = applicationManager.groupHelper.groupGetList();

        Assert.assertEquals(groupsAfter.size(), groupsBefore.size());
        groupsBefore.set(index, groupExpected);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        groupsBefore.sort(byId);
        groupsAfter.sort(byId);
        Assert.assertEquals(groupsAfter, groupsBefore);
    }

}
