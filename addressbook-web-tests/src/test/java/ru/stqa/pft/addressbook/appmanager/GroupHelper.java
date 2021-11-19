package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver webDriver) {
        super(webDriver);
    }

    NavigationHelper navigationHelper = new NavigationHelper(webDriver);

    public void groupNewGroupButton() {
        click(By.name("new"));
    }

    public void groupEnterInformationButton() {
        click(By.name("submit"));
    }

    public void groupCheckBox() {
        click(By.name("selected[]"));
    }

    public void groupEditGroupButton() {
        click(By.name("edit"));
    }

    public void groupUpdateButton() {
        click(By.name("update"));
    }

    public void groupDeleteButton() {
        click(By.xpath("//input[5]"));
    }

    public void groupEditFields(GroupData groupData) {
        print(By.name("group_name"), groupData.name());
        print(By.name("group_header"), groupData.header());
        print(By.name("group_footer"), groupData.footer());
    }

    public void groupCreationCheck(GroupData groupData) {
        navigationHelper.goToGroupPage();
        if (!groupIs()) {
            groupCreationProcess(groupData);
        }
    }

    public boolean groupIs() {
        return elementPresentIs(By.name("selected[]"));
    }

    public void groupCreationProcess(GroupData groupData) {
        navigationHelper.goToGroupPage();
        groupNewGroupButton();
        groupEditFields(groupData);
        groupEnterInformationButton();
        navigationHelper.goToGroupPage();
    }

    public void groupModificationProcess(GroupData groupData) {
        navigationHelper.goToGroupPage();
        groupCheckBox();
        groupEditGroupButton();
        groupEditFields(groupData);
        groupUpdateButton();
        navigationHelper.goToGroupPage();
    }

    public void groupDeletionProcess() {
        navigationHelper.goToGroupPage();
        groupCheckBox();
        groupDeleteButton();
        navigationHelper.goToGroupPage();
    }

}
