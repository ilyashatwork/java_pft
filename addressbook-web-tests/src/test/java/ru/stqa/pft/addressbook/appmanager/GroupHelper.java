package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

    public void groupCheckBox(int groupIndex) {
        webDriver.findElements(By.name("selected[]")).get(groupIndex).click();
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

    public List<GroupData> groupGetList() {
        List<GroupData>  groupList = new ArrayList<GroupData>();
        List<WebElement> webElements = webDriver.findElements(By.cssSelector("span.group"));
        for (WebElement webElement : webElements) {
            String groupName = webElement.getText();
            GroupData groupData = new GroupData(groupName, null, null);
            groupList.add(groupData);
        }
        return groupList;
    }

    public void groupCreationProcess(GroupData groupData) {
        navigationHelper.goToGroupPage();
        List<GroupData> groupCountBefore = groupGetList();

        groupNewGroupButton();
        groupEditFields(groupData);
        groupEnterInformationButton();

        navigationHelper.goToGroupPage();
        List<GroupData> groupCountAfter = groupGetList();

        Assert.assertEquals(groupCountAfter.size(), groupCountBefore.size() + 1);
    }

    public void groupModificationProcess(GroupData groupData) {
        navigationHelper.goToGroupPage();
        List<GroupData> groupCountBefore = groupGetList();

        groupCheckBox(groupCountBefore.size() - 1);
        groupEditGroupButton();
        groupEditFields(groupData);
        groupUpdateButton();

        navigationHelper.goToGroupPage();
        List<GroupData> groupCountAfter = groupGetList();

        Assert.assertEquals(groupCountAfter.size(), groupCountBefore.size());
    }

    public void groupDeletionProcess() {
        navigationHelper.goToGroupPage();
        List<GroupData> groupCountBefore = groupGetList();

        groupCheckBox(groupCountBefore.size() - 1);
        groupDeleteButton();

        navigationHelper.goToGroupPage();
        List<GroupData> groupCountAfter = groupGetList();

        Assert.assertEquals(groupCountAfter.size(), groupCountBefore.size() - 1);
    }

}
