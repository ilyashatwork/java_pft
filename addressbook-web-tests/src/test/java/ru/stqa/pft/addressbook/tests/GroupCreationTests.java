package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends BaseTests {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Set<GroupData> before = app.groups().set();

        app.groups().create(new GroupData().withName("Test group name #1"));

        app.goTo().groupPage();
        Set<GroupData> after = app.groups().set();

        Assert.assertEquals(after.size(), before.size() + 1);
        GroupData newG = new GroupData().withName("Test group name #1");
        newG.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(newG);
        Assert.assertEquals(after, before);
    }

}
