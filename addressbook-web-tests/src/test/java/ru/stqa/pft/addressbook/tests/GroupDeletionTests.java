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
        app.goTo().groupPage();
        app.group().creationCheck(new GroupData().withName("Test group name #1").withHeader("Test group header #1").withFooter("Test group footer #1"));
    }

    @Test
    public void testGroupDeletion() {
        app.goTo().groupPage();
        List<GroupData> groupsBefore = app.group().list();

        int index = groupsBefore.size() - 1;

        app.group().delete(index);

        app.goTo().groupPage();
        List<GroupData> groupsAfter = app.group().list();

        Assert.assertEquals(groupsAfter.size(), index);
        groupsBefore.remove(index);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        groupsBefore.sort(byId);
        groupsAfter.sort(byId);
        Assert.assertEquals(groupsAfter, groupsBefore);
    }

}
