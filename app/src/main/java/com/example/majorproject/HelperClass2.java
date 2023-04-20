package com.example.majorproject;

public class HelperClass2 {

        private String username;
        private String Score;
        private String currentDate;
        private String currentTime;

        public HelperClass2() {}

        public HelperClass2(String username, String Score, String currentDate, String currentTime) {
            this.username = username;
            this.Score = Score;
            this.currentDate = currentDate;
            this.currentTime = currentTime;
        }

        public String getUsername() {
            return username;
        }

        public String getScore() {
            return Score;
        }

        public String getCurrentDate() {
            return currentDate;
        }

        public String getCurrentTime() {
            return currentTime;
        }
    }

