package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

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

    public int groupGetCount() {;
        return webDriver.findElements(By.name("selected[]")).size();
    }

    public void groupCreationProcess(GroupData groupData) {
        navigationHelper.goToGroupPage();
        int groupCountBefore = groupGetCount();
        groupNewGroupButton();
        groupEditFields(groupData);
        groupEnterInformationButton();
        navigationHelper.goToGroupPage();
        int groupCountAfter = groupGetCount();
        Assert.assertEquals(groupCountAfter, groupCountBefore + 1);
    }

    public void groupModificationProcess(GroupData groupData) {
        navigationHelper.goToGroupPage();
        int groupCountBefore = groupGetCount();
        groupCheckBox();
        groupEditGroupButton();
        groupEditFields(groupData);
        groupUpdateButton();
        navigationHelper.goToGroupPage();
        int groupCountAfter = groupGetCount();
        Assert.assertEquals(groupCountAfter, groupCountBefore);
    }

    public void groupDeletionProcess() {
        navigationHelper.goToGroupPage();
        int groupCountBefore = groupGetCount();
        groupCheckBox();
        groupDeleteButton();
        navigationHelper.goToGroupPage();
        int groupCountAfter = groupGetCount();
        Assert.assertEquals(groupCountAfter, groupCountBefore - 1);
    }

}
