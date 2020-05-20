package com.ethan;

public class MessageObject {

    private String fromValue;
    private String toValue;
    private String messageValue;

    public MessageObject(String fromValue, String toValue, String messageValue) {
        this.fromValue = fromValue;
        this.toValue = toValue;
        this.messageValue = messageValue;
    }

    public String getJsonStringMessage() {
        String fromJsonAttrib = getJsonAttributString("from", fromValue);
        String toJsonAttrib = getJsonAttributString("to", toValue);
        String messageJsonAttrib = getJsonAttributString("sendMessage", messageValue);
        return "{"+fromJsonAttrib+","+toJsonAttrib+","+messageJsonAttrib+"}";
    }


    private static String getJsonAttributString(String prompt, String value) {
        String q = "\"";
        return q+prompt+q+":"+q+value+q;
    }
}
