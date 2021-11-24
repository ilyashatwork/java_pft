package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends BaseTests {

    @BeforeMethod
    public void ensurePreconditions() {
        app.groups().creationCheck();
    }

    @Test
    public void testGroupDeletion() {
        Groups before = app.groups().all();
        GroupData toDelete = before.iterator().next();
        app.groups().delete(toDelete);
        Groups after = app.groups().all();

        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(toDelete)));
    }

}
