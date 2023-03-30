package com.example.majorproject;

public class HelperClass {

    String name, email, language, username, password;

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

    public String getLanguage() {return language;}

    public void setLanguage(String language) {this.language = language;}


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



    public HelperClass(String name, String email,String language, String username, String password) {
        this.name = name;
        this.email = email;
        this.language = language;
        this.username = username;
        this.password = password;

    }

    public HelperClass() {
    }
}
