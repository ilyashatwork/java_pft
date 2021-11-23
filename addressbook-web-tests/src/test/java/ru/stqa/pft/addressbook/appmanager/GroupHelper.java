package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void checkBox(int id) { webDriver.findElement(By.cssSelector("input[value='" + id + "']")).click(); }

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

    public Set<GroupData> set() {
        Set<GroupData> set = new HashSet<>();
        List<WebElement> webElements = webDriver.findElements(By.cssSelector("span.group"));
        for (WebElement webElement : webElements) {
            int id = Integer.parseInt(webElement.findElement(By.tagName("input")).getAttribute("value"));
            String name = webElement.getText();
            GroupData data = new GroupData().withId(id).withName(name);
            set.add(data);
        }
        return set;
    }

    public void create(GroupData data) {
        newGroupButton();
        editFields(data);
        enterInformationButton();
    }

    public void modify(GroupData data) {
        checkBox(data.getId());
        editGroupButton();
        editFields(data);
        updateButton();
    }

    public void delete(GroupData data) {
        checkBox(data.getId());
        deleteButton();
    }
}
