package com.ethan.socket.model;

public class WebMessage {
    public WebMessage(String message, String from, String to) {
        this.message = message;
        this.from = from;
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    private String message;
    private String from;
    private String to;
}
