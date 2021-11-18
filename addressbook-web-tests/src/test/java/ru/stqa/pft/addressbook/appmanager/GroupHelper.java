package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void startGroupCreation() {
        click(By.name("new"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.name());
        type(By.name("group_header"), groupData.header());
        type(By.name("group_footer"), groupData.footer());
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedGroup() {
        click(By.xpath("//input[5]"));
    }

    public void startGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }
}
