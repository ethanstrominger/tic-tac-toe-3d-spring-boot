package com.ethan;

import org.springframework.web.bind.annotation.PathVariable;

public class GameMessages {

    public static void addMessage(GameMessage gameMessage) {
        GamesSession.authenticateNickname(gameMessage.getFromNickname());
        GameDatabase.addMessage(gameMessage);
    }

    public static GameMessage[] getMessages(@PathVariable String fromNickname) {
        GamesSession.authenticateNickname(fromNickname);
        return GameDatabase.getGamesFromOrTo(fromNickname);
    }
}
