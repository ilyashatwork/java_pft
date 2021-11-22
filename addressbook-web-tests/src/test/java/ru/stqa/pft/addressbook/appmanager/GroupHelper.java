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

    public void newGroupButton() {
        click(By.name("new"));
    }

    public void enterInformationButton() {
        click(By.name("submit"));
    }

    public void checkBox(int groupIndex) {
        webDriver.findElements(By.name("selected[]")).get(groupIndex).click();
    }

    public void editGroupButton() {
        click(By.name("edit"));
    }

    public void updateButton() {
        click(By.name("update"));
    }

    public void deleteButton() {
        click(By.xpath("//input[5]"));
    }

    public void editFields(GroupData groupData) {
        print(By.name("group_name"), groupData.getName());
        print(By.name("group_header"), groupData.getHeader());
        print(By.name("group_footer"), groupData.getFooter());
    }

    public void creationCheck(GroupData groupData) {
        if (!groupIs()) {
            create(groupData);
        }
    }

    public boolean groupIs() {
        return elementPresentIs(By.name("selected[]"));
    }

    public List<GroupData> list() {
        List<GroupData> groupListOfGroupData = new ArrayList<GroupData>();
        List<WebElement> groupWebElements = webDriver.findElements(By.cssSelector("span.group"));
        for (WebElement groupWebElement : groupWebElements) {
            int groupId = Integer.parseInt(groupWebElement.findElement(By.tagName("input")).getAttribute("value"));
            String groupName = groupWebElement.getText();
            GroupData groupData = new GroupData().withId(groupId).withName(groupName);
            groupListOfGroupData.add(groupData);
        }
        return groupListOfGroupData;
    }

    public void create(GroupData groupData) {
        newGroupButton();
        editFields(groupData);
        enterInformationButton();
    }

    public void modify(GroupData groupData, int index) {
        checkBox(index);
        editGroupButton();
        editFields(groupData);
        updateButton();
    }

    public void delete(int index) {
        checkBox(index);
        deleteButton();
    }

}
