package com.ethan;

public class GameMessages {
    public static GameMessage addMessage(String toNickname, String messageText) {
        GameMessage gameMessage = new GameMessage(toNickname, messageText);
        GameDatabase.addMessage(gameMessage);
        return gameMessage;
    }

    public static GameMessage[] getMessages() {
        GameMessage[] gameMessages = GameDatabase.getGamesFromOrTo(GamesSession.getCurrentNickname());
        return gameMessages;
    }
}
