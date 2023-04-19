package com.example.majorproject;

public class HelperClass3 {
    private String username;
    private String entertext;
    private String result11;
    private String result22;
    private String result33;
    private String currentDate;
    private String currentTime;

    private String language1;

    private String language2;

    private String language3;

    public HelperClass3() {}

    public HelperClass3(String username, String entertext, String language1, String language2, String language3, String result11, String result22, String result33, String currentDate, String currentTime) {
        this.username = username;
        this.entertext = entertext;
        this.language1 = language1;
        this.language2 = language2;
        this.language3 = language3;
        this.result11 = result11;
        this.result22 = result22;
        this.result33 = result33;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
    }

    public String getUsername() {
        return username;
    }

    public String getEntertext() {
        return entertext;
    }

    public String getLanguage1() {
        return language1;
    }

    public String getLanguage2() {
        return language2;
    }

    public String getLanguage3() {
        return language3;
    }
    public String getResult11() {
        return result11;
    }

    public String getResult22() {
        return result22;
    }

    public String getResult33() {
        return result33;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public String getCurrentTime() {return currentTime; }
}
