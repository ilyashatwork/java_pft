package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends BaseTests {

    @BeforeMethod
    public void ensurePreconditions() {
        app.groups().creationCheck();
    }

    @Test
    public void testGroupModification() {
        Groups before = app.groups().all();
        GroupData oldData = before.iterator().next();
        GroupData newData = new GroupData().withId(oldData.getId()).withName("Test group name #2");
        app.groups().modify(newData);
        assertThat(app.groups().count(), equalTo(before.size()));
        Groups after = app.groups().all();
        assertThat(after, equalTo(before.without(oldData).withAdded(newData)));
    }

}
