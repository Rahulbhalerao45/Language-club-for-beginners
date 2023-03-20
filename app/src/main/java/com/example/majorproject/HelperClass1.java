package com.example.majorproject;

public class HelperClass1 {

    String  username, entertext, selectlanguage;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEnterText() {
        return entertext;
    }

    public void setEnterText(String entertext) {
        this.entertext = entertext;
    }

    public String getSelectLanguage() {
        return selectlanguage;
    }

    public void setSelectLanguage(String selectlanguage) {
        this.selectlanguage = selectlanguage;
    }

    public HelperClass1(String entertext, String selectlanguage, String username) {

        this.username = username;
        this.entertext = entertext;
        this.selectlanguage = selectlanguage;
}
    public HelperClass1() {
    }
}

