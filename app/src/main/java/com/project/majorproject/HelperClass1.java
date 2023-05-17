package com.project.majorproject;

public class HelperClass1 {

    String  username, entertext, selectlanguage1, selectlanguage2, selectlanguage3;

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

    public String getSelectLanguage1() {
        return selectlanguage1;
    }

    public void setSelectLanguage1(String selectlanguage1) {this.selectlanguage1 = selectlanguage1; }

    public String getSelectLanguage2() {
        return selectlanguage2;
    }

    public void setSelectLanguage2(String selectlanguage2) {this.selectlanguage2 = selectlanguage2; }

    public String getSelectLanguage3() {
        return selectlanguage3;
    }

    public void setSelectLanguage3(String selectlanguage3) {this.selectlanguage3 = selectlanguage3; }

    public HelperClass1(String username, String entertext, String selectlanguage1, String selectlanguage2, String selectlanguage3) {

        this.username = username;
        this.entertext = entertext;
        this.selectlanguage1 = selectlanguage1;
        this.selectlanguage2 = selectlanguage2;
        this.selectlanguage3 = selectlanguage3;

}
    public HelperClass1() {
    }
}

