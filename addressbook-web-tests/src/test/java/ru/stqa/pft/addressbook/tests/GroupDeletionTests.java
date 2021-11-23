package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends BaseTests {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.groups().set().size() == 0) { app.groups().create(new GroupData().withName("Test group name #1")); }
    }

    @Test
    public void testGroupDeletion() {
        app.goTo().groupPage();
        Set<GroupData> before = app.groups().set();

        GroupData toDelete = before.iterator().next();
        app.groups().delete(toDelete);

        app.goTo().groupPage();
        Set<GroupData> after = app.groups().set();

        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(toDelete);
        Assert.assertEquals(after, before);
    }

}
