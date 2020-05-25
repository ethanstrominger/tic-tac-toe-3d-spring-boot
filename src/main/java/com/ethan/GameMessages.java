package com.ethan;

public class GameMessages {
    public static GameMessage addMessage(String fromNickname, String toNickname, String messageText) {
        GamesSession.setCurrentNickname(fromNickname);
        GameMessage gameMessage = new GameMessage(fromNickname, toNickname, messageText);
        GameDatabase.addMessage(gameMessage);
        return gameMessage;
    }

    public static GameMessage[] getMessages(String fromNickname) {
        GameMessage[] gameMessages = GameDatabase.getGamesFromOrTo(fromNickname);
        return gameMessages;
    }
}
