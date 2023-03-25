package com.example.majorproject;

public class HelperClass {

    String name, email, selectlanguage, username, password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSelectlanguage() {return selectlanguage;}

    public void setSelectlanguage(String language) {this.selectlanguage = selectlanguage;}


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public HelperClass(String name, String email,String selectlanguage, String username, String password) {
        this.name = name;
        this.email = email;
        this.selectlanguage = selectlanguage;
        this.username = username;
        this.password = password;

    }

    public HelperClass() {
    }
}
