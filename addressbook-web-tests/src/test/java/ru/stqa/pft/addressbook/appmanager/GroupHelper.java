package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
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
        print(By.name("group_name"), groupData.getName());
        print(By.name("group_header"), groupData.getHeader());
        print(By.name("group_footer"), groupData.getFooter());
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
            int groupId = Integer.parseInt(webElement.findElement(By.tagName("input")).getAttribute("value"));
            String groupName = webElement.getText();
            GroupData groupData = new GroupData(groupId, groupName, null, null);
            groupList.add(groupData);
        }
        return groupList;
    }

    public void groupCreationProcess(GroupData groupData) {
        navigationHelper.goToGroupPage();
        List<GroupData> groupsBefore = groupGetList();

        groupNewGroupButton();
        groupEditFields(groupData);
        groupEnterInformationButton();

        navigationHelper.goToGroupPage();
        List<GroupData> groupsAfter = groupGetList();

        Assert.assertEquals(groupsAfter.size(), groupsBefore.size() + 1);

        GroupData groupExpected = new GroupData(groupData.getName(), null, null);

        groupsBefore.add(groupExpected);

        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        groupsBefore.sort(byId);
        groupsAfter.sort(byId);
        Assert.assertEquals(groupsAfter, groupsBefore);
    }

    public void groupModificationProcess(GroupData groupData) {
        navigationHelper.goToGroupPage();
        List<GroupData> groupsBefore = groupGetList();

        groupCheckBox(groupsBefore.size() - 1);
        groupEditGroupButton();
        groupEditFields(groupData);
        groupUpdateButton();

        navigationHelper.goToGroupPage();
        List<GroupData> groupsAfter = groupGetList();

        Assert.assertEquals(groupsAfter.size(), groupsBefore.size());
        GroupData groupExpected = new GroupData(groupsBefore.get(groupsBefore.size() - 1).getId(), groupData.getName(), groupData.getFooter(), groupData.getHeader());
        groupsBefore.remove(groupsBefore.size() - 1);
        groupsBefore.add(groupExpected);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        groupsBefore.sort(byId);
        groupsAfter.sort(byId);
        Assert.assertEquals(groupsAfter, groupsBefore);
    }

    public void groupDeletionProcess() {
        navigationHelper.goToGroupPage();
        List<GroupData> groupsBefore = groupGetList();

        groupCheckBox(groupsBefore.size() - 1);
        groupDeleteButton();

        navigationHelper.goToGroupPage();
        List<GroupData> groupsAfter = groupGetList();

        Assert.assertEquals(groupsAfter.size(), groupsBefore.size() - 1);
        groupsBefore.remove(groupsBefore.size() - 1);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        groupsBefore.sort(byId);
        groupsAfter.sort(byId);
        Assert.assertEquals(groupsAfter, groupsBefore);
    }

}
