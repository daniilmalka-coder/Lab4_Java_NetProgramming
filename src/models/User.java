package models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {
    private static final DateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");

    private String name;
    private String surName;
    private String gender;
    private Date birthday;

    public User() {}

    public User(String name, String surName, String gender, String birthday) {
        this.name = name;
        this.surName = surName;
        this.gender = gender;
        try {
            this.birthday = dateFormatter.parse(birthday);
        } catch (ParseException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurName() { return surName; }
    public void setSurName(String surName) { this.surName = surName; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }

    @Override
    public String toString() {
        return name + " " + surName + " (" + gender + ") " +
                (birthday != null ? dateFormatter.format(birthday) : "Unknown date");
    }
}