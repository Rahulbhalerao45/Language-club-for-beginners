package com.project.majorproject;

public class HelperClass2 {

        private String username;
        private String score;
        private String currentDate;
        private String currentTime;

        public HelperClass2() {}

        public HelperClass2(String username, String score, String currentDate, String currentTime) {
            this.username = username;
            this.score = score;
            this.currentDate = currentDate;
            this.currentTime = currentTime;
        }

        public String getUsername() {
            return username;
        }

        public String getScore() {
            return score;
        }

        public String getCurrentDate() {
            return currentDate;
        }

        public String getCurrentTime() {
            return currentTime;
        }
    }

