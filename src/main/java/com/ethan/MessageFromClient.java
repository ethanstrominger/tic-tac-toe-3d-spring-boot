package com.ethan;

// Used as paramater GreetingController when receiving message from client
public class MessageFromClient {

  private String name;

  public MessageFromClient() {
  }

  public MessageFromClient(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}