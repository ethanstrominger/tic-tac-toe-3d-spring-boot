package com.ethan;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameDatabase {

    public static List<GameMessage> dbGameMessages = new ArrayList<GameMessage>();

    public static void addMessage(GameMessage gameMessage) {
        dbGameMessages.add(gameMessage);
    }

    public static GameMessage[] getGamesFromOrTo(String userNickname) {
        List<GameMessage> listMessages = new ArrayList<GameMessage>();
        for (GameMessage gameMessage:dbGameMessages) {
            if (gameMessage.getFromNickname().equals(userNickname) ||
                    gameMessage.getToNickname().equals(userNickname)) {
                listMessages.add(gameMessage);
            }
        }
        GameMessage[] arrayMessages = new GameMessage[listMessages.size()];
        arrayMessages = listMessages.toArray(arrayMessages);
        return arrayMessages;
    }
}
