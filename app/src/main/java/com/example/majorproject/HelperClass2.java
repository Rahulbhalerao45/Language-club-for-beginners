package com.example.majorproject;

public class HelperClass2 {

        private String username;
        private String entertext;
        private String selectedLanguage1;
        private String selectedLanguage2;
        private String selectedLanguage3;
        private String currentDate;
        private String currentTime;

        public HelperClass2() {}

        public HelperClass2(String username, String entertext, String selectedLanguage1, String selectedLanguage2, String selectedLanguage3, String currentDate, String currentTime) {
            this.username = username;
            this.entertext = entertext;
            this.selectedLanguage1 = selectedLanguage1;
            this.selectedLanguage2 = selectedLanguage2;
            this.selectedLanguage3 = selectedLanguage3;
            this.currentDate = currentDate;
            this.currentTime = currentTime;
        }

        public String getUsername() {
            return username;
        }

        public String getEntertext() {
            return entertext;
        }

        public String getSelectedLanguage1() {
            return selectedLanguage1;
        }

        public String getSelectedLanguage2() {
            return selectedLanguage2;
        }

        public String getSelectedLanguage3() {
            return selectedLanguage3;
        }

        public String getCurrentDate() {
            return currentDate;
        }

        public String getCurrentTime() {
            return currentTime;
        }
    }

