package com.example.majorproject;

public class ChatMessage {
    private String username;
    private String message;

    private String sender;

    public ChatMessage() {}

    public ChatMessage(String username, String message) {
        this.username = username;
        this.message = message;
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
}
