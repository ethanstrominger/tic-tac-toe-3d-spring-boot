package com.ethan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class GameSessions {
    private static List<GameSession> gameSessions = new ArrayList<>();

    public static void addSession(String userNickname) {
        GameSession gameSession = new GameSession(userNickname);
        gameSessions.add(new GameSession(userNickname));
    }

    public static GameSession getSession(String userNickname) {
        for (GameSession gameSession: gameSessions) {
            if (gameSession.getUserNickname().equals(userNickname)){
                return gameSession;
            }
        }
        return null;
    }

    public static void refreshSession(String userNickname) {
        GameSession gameSession = GameSessions.getSession(userNickname);
        gameSession.setTimeRefreshedNow();
    }

}
