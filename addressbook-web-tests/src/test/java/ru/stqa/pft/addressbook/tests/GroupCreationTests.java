package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends BaseTests {

    @Test
    public void testGroupCreation() {
        GroupData groupExpected = new GroupData().withName("Test group name #777");

        app.goTo().groupPage();
        List<GroupData> groupsBefore = app.group().list();

        app.group().create(new GroupData().withName("Test group name #777").withHeader("Test group header #1").withFooter("Test group footer #1"));

        app.goTo().groupPage();
        List<GroupData> groupsAfter = app.group().list();

        Assert.assertEquals(groupsAfter.size(), groupsBefore.size() + 1);
        groupsBefore.add(groupExpected);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        groupsBefore.sort(byId);
        groupsAfter.sort(byId);
        Assert.assertEquals(groupsAfter, groupsBefore);
    }

}
