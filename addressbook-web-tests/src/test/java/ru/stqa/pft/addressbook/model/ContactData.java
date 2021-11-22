package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private final String lastName;
    private final String firstName;
    private final String mobile;
    private final String groupValue;

    public ContactData(String lastName, String firstName, String mobile, String groupValue) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.mobile = mobile;
        this.groupValue = groupValue;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getGroupValue() {
        return groupValue;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName);
    }

}
