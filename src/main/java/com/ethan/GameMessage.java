package com.ethan;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GameMessage {
    UUID id = UUID.randomUUID();
    private String fromNickname = GamesSession.getCurrentNickName();
    private String toNickname;
    private String gameMessage;
    long timeCreated = System.currentTimeMillis();

    public GameMessage(String toNickname, String gameMessage) {
        this.toNickname = toNickname;
        this.gameMessage = gameMessage;
    }
}
