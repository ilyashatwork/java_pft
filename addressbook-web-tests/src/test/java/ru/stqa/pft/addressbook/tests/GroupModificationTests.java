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
        app.goTo().groupPage();
        app.group().creationCheck(new GroupData().withName("Test group name #1").withHeader("Test group header #1").withFooter("Test group footer #1"));
    }

    @Test
    public void testGroupModification() {
        app.goTo().groupPage();
        List<GroupData> groupsBefore = app.groupHelper.list();

        int index = groupsBefore.size() - 1;
        GroupData groupExpected = new GroupData().withId(groupsBefore.get(index).getId()).withName("Test group name #0").withHeader("Test group header #2").withFooter("Test group footer #2");

        app.groupHelper.modify(new GroupData().withName("Test group name #0").withHeader("Test group header #2").withFooter("Test group footer #2"), index);

        app.goTo().groupPage();
        List<GroupData> groupsAfter = app.groupHelper.list();

        Assert.assertEquals(groupsAfter.size(), groupsBefore.size());
        groupsBefore.set(index, groupExpected);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        groupsBefore.sort(byId);
        groupsAfter.sort(byId);
        Assert.assertEquals(groupsAfter, groupsBefore);
    }

}
