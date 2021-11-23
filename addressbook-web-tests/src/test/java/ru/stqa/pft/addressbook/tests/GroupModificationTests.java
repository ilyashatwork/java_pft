package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTests extends BaseTests {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groups().set().size() == 0) { app.groups().create(new GroupData().withName("Test group name #1")); }
    }

    @Test
    public void testGroupModification() {
        app.goTo().groupPage();
        Set<GroupData> before = app.groups().set();

        GroupData oldData = before.iterator().next();
        GroupData newData = new GroupData().withId(oldData.getId()).withName("Test group name #2");
        app.groups().modify(newData);

        app.goTo().groupPage();
        Set<GroupData> after = app.groups().set();

        Assert.assertEquals(after.size(), before.size());
        before.remove(oldData);
        before.add(newData);
        Assert.assertEquals(after, before);
    }

}
