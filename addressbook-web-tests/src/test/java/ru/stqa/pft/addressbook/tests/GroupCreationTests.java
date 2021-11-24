package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends BaseTests {

    @Test
    public void testGroupCreation() {
        Groups before = app.groups().all();
        GroupData newG = new GroupData().withName("Test group name #1");
        app.groups().create(newG);
        Groups after = app.groups().all();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(newG.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
