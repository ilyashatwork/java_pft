package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends BaseTests {

    @BeforeMethod
    public void ensurePreconditions() {
        applicationManager.navigationHelper.goToGroupPage();
        applicationManager.getGroupHelper().groupCreationCheck(new GroupData("Test group name #1", "Test group header #1", "Test group footer #1"));
    }

    @Test
    public void testGroupDeletion() {
        applicationManager.navigationHelper.goToGroupPage();
        List<GroupData> groupsBefore = applicationManager.getGroupHelper().groupGetList();

        int index = groupsBefore.size() - 1;

        applicationManager.getGroupHelper().groupDeletion(index);

        applicationManager.navigationHelper.goToGroupPage();
        List<GroupData> groupsAfter = applicationManager.getGroupHelper().groupGetList();

        Assert.assertEquals(groupsAfter.size(), index);
        groupsBefore.remove(index);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        groupsBefore.sort(byId);
        groupsAfter.sort(byId);
        Assert.assertEquals(groupsAfter, groupsBefore);
    }

}
