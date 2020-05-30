package com.ethan;

import java.util.UUID;

public class GameMessages {

    private static final GameMessages instance = new GameMessages();

    public static void addMessage(GameMessage gameMessage) {
        Notifications.addUserNotification(gameMessage.getToNickname());
        GameDatabase.addMessage(gameMessage);
    }

    public static GameMessage[] getMessages(String fromNickname) {
        Notifications.deleteNotification(fromNickname);
        return GameDatabase.getGamesFromOrTo(fromNickname);
    }

    // This makes it a singleton
    private GameMessages() {}

    public static GameMessages getInstance(){
        return instance;
    }

    public static GameMessage getById(UUID id) {
        return GameDatabase.getById(id);
    }

}
