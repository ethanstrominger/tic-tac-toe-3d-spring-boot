package com.ethan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public class GameMessages {

    private static GameMessages instance = new GameMessages();

    public static void addMessage(GameMessage gameMessage) {
        Notifications.addUserNotification(gameMessage.getFromNickname());
        Notifications.addUserNotification(gameMessage.getToNickname());
        GameDatabase.addMessage(gameMessage);
    }

    public static GameMessage[] getMessages(@PathVariable String fromNickname) {
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
