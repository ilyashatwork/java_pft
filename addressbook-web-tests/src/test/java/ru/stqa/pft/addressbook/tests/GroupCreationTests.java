package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends BaseTests {

    @Test
    public void testGroupCreation() {
        GroupData groupExpected = new GroupData("Test group name #777", null, null);

        applicationManager.navigationHelper.goToGroupPage();
        List<GroupData> groupsBefore = applicationManager.getGroupHelper().groupGetList();

        applicationManager.getGroupHelper().groupCreation(new GroupData("Test group name #777", "Test group header #1", "Test group footer #1"));

        applicationManager.navigationHelper.goToGroupPage();
        List<GroupData> groupsAfter = applicationManager.getGroupHelper().groupGetList();

        Assert.assertEquals(groupsAfter.size(), groupsBefore.size() + 1);
        groupsBefore.add(groupExpected);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        groupsBefore.sort(byId);
        groupsAfter.sort(byId);
        Assert.assertEquals(groupsAfter, groupsBefore);
    }

}
