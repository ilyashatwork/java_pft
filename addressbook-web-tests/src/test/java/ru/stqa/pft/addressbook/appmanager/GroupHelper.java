package ru.stqa.pft.addressbook.appmanager;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver webDriver) {
        super(webDriver);
    }

    NavigationHelper goTo = new NavigationHelper(webDriver);

    public void newGroupButton() {
        click(By.name("new"));
    }

    public void enterInformationButton() {
        click(By.name("submit"));
    }

    public void checkBox(int id) {
        webDriver.findElement(By.cssSelector("input[value='" + id + "']")).click();
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

    public void editFields(GroupData data) {
        print(By.name("group_name"), data.getName());
    }

    public void creationCheck() {
        goTo.groupPage();
        if (all().size() == 0) {
            create(new GroupData().withName("Test group name #1"));
        }
    }

    public Groups groupCache = null;

    public Groups all() {
        goTo.groupPage();
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> webElements = webDriver.findElements(By.cssSelector("span.group"));
        for (WebElement webElement : webElements) {
            int id = Integer.parseInt(webElement.findElement(By.tagName("input")).getAttribute("value"));
            String name = webElement.getText();
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCache);
    }

    public String getAnyGroupValue() {
        goTo.groupPage();
        return Integer.toString(all().iterator().next().getId());
    }

    public int count() {
        return webDriver.findElements(By.name("selected[]")).size();
    }

    public void create(GroupData data) {
        goTo.groupPage();
        newGroupButton();
        editFields(data);
        enterInformationButton();
        groupCache = null;
        goTo.groupPage();
    }

    public void modify(GroupData data) {
        goTo.groupPage();
        checkBox(data.getId());
        editGroupButton();
        editFields(data);
        updateButton();
        groupCache = null;
        goTo.groupPage();
    }

    public void delete(GroupData data) {
        goTo.groupPage();
        checkBox(data.getId());
        deleteButton();
        groupCache = null;
        goTo.groupPage();
    }

}
