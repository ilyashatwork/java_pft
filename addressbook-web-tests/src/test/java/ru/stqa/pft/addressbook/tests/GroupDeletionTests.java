package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNh().goToGroupPage();
        app.getGh().selectGroup();
        app.getGh().deleteSelectedGroup();
        app.getNh().goToGroupPage();
    }

}
