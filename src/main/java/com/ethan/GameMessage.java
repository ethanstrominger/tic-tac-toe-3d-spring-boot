package com.ethan;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GameMessage {
    UUID id = UUID.randomUUID();
    private String fromNickname = GamesSession.getCurrentNickname();
    private String toNickname;
    private String messageText;
    long timeCreated = System.currentTimeMillis();

    public GameMessage(String toNickname, String messageText) {
        this.toNickname = toNickname;
        this.messageText = messageText;
    }
}
