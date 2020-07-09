package com.ethan;

// Used by GreetingController as control type when sending to client
public class MessageToClient {

    private String content;
  
    public MessageToClient() {
    }
  
    public MessageToClient(String content) {
      this.content = content;
    }
  
    public String getContent() {
      return content;
    }
  
  }