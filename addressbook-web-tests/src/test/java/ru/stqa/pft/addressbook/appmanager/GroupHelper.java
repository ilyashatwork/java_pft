package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver webDriver) {
        super(webDriver);
    }

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
        if (!groupIs()) {
            groupCreation(groupData);
        }
    }

    public boolean groupIs() {
        return elementPresentIs(By.name("selected[]"));
    }

    public List<GroupData> groupGetList() {
        List<GroupData> groupListOfGroupData = new ArrayList<GroupData>();
        List<WebElement> groupWebElements = webDriver.findElements(By.cssSelector("span.group"));
        for (WebElement groupWebElement : groupWebElements) {
            int groupId = Integer.parseInt(groupWebElement.findElement(By.tagName("input")).getAttribute("value"));
            String groupName = groupWebElement.getText();
            GroupData groupData = new GroupData(groupId, groupName, null, null);
            groupListOfGroupData.add(groupData);
        }
        return groupListOfGroupData;
    }

    public void groupCreation(GroupData groupData) {
        groupNewGroupButton();
        groupEditFields(groupData);
        groupEnterInformationButton();
    }

    public void groupModification(GroupData groupData, int index) {
        groupCheckBox(index);
        groupEditGroupButton();
        groupEditFields(groupData);
        groupUpdateButton();
    }

    public void groupDeletion(int index) {
        groupCheckBox(index);
        groupDeleteButton();
    }

}
