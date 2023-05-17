package com.project.majorproject;

public class ChatMessage {
    private String username;
    private String message;

    private String sender;

    private String currentTime;

    public ChatMessage(String currentTime) {}

    public ChatMessage(String username, String message, String sender,  String currentTime) {
        this.username = username;
        this.message = message;
        this.currentTime = currentTime;
        this.sender = sender;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public String getCurrentTime() {return currentTime; }
}
