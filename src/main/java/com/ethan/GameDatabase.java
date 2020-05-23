package com.ethan;

import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.List;

public class GameDatabase {
    private static List<GameMessage> dbGameMessages = new ArrayList<GameMessage>();

    public static void addMessage(GameMessage gameMessage) {
        dbGameMessages.add(gameMessage);
    }

    public static GameMessage[] getGamesFromOrTo(String userNickname) {
        List<GameMessage> listMessages = new ArrayList<GameMessage>();
        for (GameMessage gameMessage:dbGameMessages) {
            if (gameMessage.getFromNickname() == userNickname ||
                gameMessage.getToNickname() == userNickname) {
                listMessages.add(gameMessage);
            }
        }
        GameMessage[] arrayMessages = new GameMessage[listMessages.size()];
        arrayMessages = listMessages.toArray(arrayMessages);
        return arrayMessages;
    }
}
