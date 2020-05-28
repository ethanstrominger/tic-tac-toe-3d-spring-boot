package com.ethan;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionTests {
    static ResponseEntity<GameMessage[]> response = new ResponseEntity<GameMessage[]>(HttpStatus.REQUEST_TIMEOUT);

    @Test
    public void testSetSessionUser() {
        String currentNickname="John";
        GameSession.authenticateNickname(currentNickname);
        assertEquals(currentNickname, GameSession.getCurrentNickname());
    }

    @Test
    public void testSetGuestUser() {
        String beforeNickname="Fred";
        int guestCountBefore = GameSession.getGuestCount();
        String expectedNickname = "Guest" +
                (guestCountBefore + 1) +
                "-" + beforeNickname;
        GameSession.setGuestNickname(beforeNickname);
        assertEquals(expectedNickname, GameSession.getCurrentNickname());
        assertEquals(guestCountBefore + 1, GameSession.getGuestCount());
    }

    @Test
    public void testCreateGameSession() {
        String testName = "name of session user";
        long currentTime = System.currentTimeMillis();
        GameSession gameSession = new GameSession(testName);
        assertEquals(testName,gameSession.getUserNickname());
        assertTrue(gameSession.getTimeCreated() >= currentTime);
    }

    @Test
    public void testAddGetUserSession() {
        String testName = "name of session user";
        long currentTime = System.currentTimeMillis();
        GameSessions.addSession(testName);
        assertEquals(testName,GameSessions.getSession(testName).getUserNickname());
    }

    @Test
    public void testSetTimeRefreshedUserSession() throws InterruptedException {
        String testName = "Set time refreshed";
        GameSessions.addSession(testName);
        Thread.sleep(50);
        GameSessions.refreshSession(testName);
        long afterSomeTime = System.currentTimeMillis();
        assertTrue(GameSessions.getSession(testName).getTimeRefreshed() >= afterSomeTime);

    }

    @Test
    public void testPollingUser() throws InterruptedException {
        String userName = "Ethan";
        int frequencyMillis = 25;
        int firstSleepMillis = 100;
        int timeoutMillis = 200;
        GameMessage[] emptyMessages = {};
        response = new ResponseEntity<GameMessage[]>(emptyMessages, HttpStatus.CREATED);
        new Thread(() -> {
          try {
              response =
                  GameSession.pollForChanges(userName, frequencyMillis, timeoutMillis);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }).start();

        Thread.sleep(firstSleepMillis);
        assertEquals(HttpStatus.CREATED, response.getStatusCode() );

        Thread.sleep(timeoutMillis);
        assertEquals(HttpStatus.REQUEST_TIMEOUT, response.getStatusCode());

    }
}


