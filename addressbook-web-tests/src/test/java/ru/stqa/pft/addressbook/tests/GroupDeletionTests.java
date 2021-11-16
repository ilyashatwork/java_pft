package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() throws Exception {
        app.getNh().goToGroupPage();
        app.getGh().selectGroup();
        app.getGh().deleteSelectedGroups();
        app.getNh().returnToGroupPage();
    }

}
