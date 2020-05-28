package com.ethan.game;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GameMessage {
    private final UUID id = UUID.randomUUID();
    private final String fromNickname;
    private final String toNickname;
    private final String messageText;
    private final long timeCreated = System.currentTimeMillis();

    public GameMessage(String fromNickname, String toNickname, String messageText) {
        this.fromNickname = fromNickname+"";
        this.toNickname = toNickname+"";
        this.messageText = messageText+"";
    }
}
