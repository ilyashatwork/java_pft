package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        app.getNh().goToGroupPage();
        app.getGh().selectGroup();
        app.getGh().startGroupModification();
        app.getGh().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGh().submitGroupModification();
        app.getNh().returnToGroupPage();
    }
}
