package com.ethan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class GameMessages {

    public static GameMessage addMessage(GameMessage gameMessage) {
        GamesSession.authenticateNickname(gameMessage.getFromNickname());
        GameDatabase.addMessage(gameMessage);
        return gameMessage;
    }

    public static GameMessage[] getMessages(@PathVariable String fromNickname) {
        GamesSession.authenticateNickname(fromNickname);
        GameMessage[] gameMessages = GameDatabase.getGamesFromOrTo(fromNickname);
        return gameMessages;
    }
}
