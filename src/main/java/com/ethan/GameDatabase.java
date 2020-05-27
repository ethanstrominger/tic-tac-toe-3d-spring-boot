package com.ethan;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
// TODO: Replace with ENUM
public class GameDatabase {

    public static final List<GameMessage> dbGameMessages = new ArrayList<>();

    public static void addMessage(GameMessage gameMessage) {
        dbGameMessages.add(gameMessage);
    }

    public static GameMessage[] getGamesFromOrTo(String userNickname) {
        List<GameMessage> listMessages = new ArrayList<>();
        // TODO: Refactor into method and turn condition into a message
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

    public static GameMessage getById(UUID id) {
        for (GameMessage gameMessage:dbGameMessages) {
            if (gameMessage.getId().equals(id)) {
                return gameMessage;
            }
        }
        return null;
    }
}
