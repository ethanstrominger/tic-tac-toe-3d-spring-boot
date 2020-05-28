package com.ethan;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GameSession {
    private static String currentNickname;
    private static int guestCount = 0;
    private final String userNickname;
    private final long timeCreated = System.currentTimeMillis();
    private long timeMessageUpdated;

    private long timeRefreshed = System.currentTimeMillis();

    public GameSession(String userNickname) {
        this.userNickname = userNickname;
    }

    public static ResponseEntity<GameMessage[]> pollForChanges(
            String userName,
            long frequencyMillis,
            long timeoutMillis) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime <= timeoutMillis) {
            Thread.sleep(frequencyMillis);
        }
        GameMessage[] emptyMessage = {};
        return new ResponseEntity<GameMessage[]>(emptyMessage, HttpStatus.REQUEST_TIMEOUT);
    }

    public void setTimeRefreshedNow() {
        this.timeRefreshed = System.currentTimeMillis();
    }

    public static void authenticateNickname(String currentNickname) {
        GameSession.currentNickname = currentNickname;
    }

    public static String getCurrentNickname() {
        return GameSession.currentNickname;
    }

    public static int getGuestCount() {
        return GameSession.guestCount;
    }

    public static void setGuestNickname(String beforeNickname) {
        GameSession.guestCount = GameSession.guestCount + 1;
        GameSession.currentNickname =
                "Guest"+ guestCount + "-" + beforeNickname;
    }

    public void setMessageUpdated(String userNickname) {
    }
}
