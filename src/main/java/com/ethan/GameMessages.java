package com.ethan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public class GameMessages {

    public static void addMessage(GameMessage gameMessage) {
        
        GameDatabase.addMessage(gameMessage);
        String fromNickname = gameMessage.getFromNickname();
        String toNickname = gameMessage.getToNickname();
        GameSessions.getSession(fromNickname).setMessageUpdated(fromNickname);
        GameSessions.getSession(toNickname).setMessageUpdated(fromNickname);
    }

    public static GameMessage[] getMessages(@PathVariable String fromNickname) {
        return GameDatabase.getGamesFromOrTo(fromNickname);
    }

    public static GameMessage getById(UUID id) {
        return GameDatabase.getById(id);
    }

}
