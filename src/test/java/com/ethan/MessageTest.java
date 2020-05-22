package com.ethan;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class MessageTest {



    @Test
    public void testCreateGameMessage() {
        String EXPECTED_MESSAGE = "Hi Mary";
        String EXPECTED_TO_NICKNAME = "Mary";        long startTransaction = System.currentTimeMillis();
        String EXPECTED_FROM_NICKNAME = "John";

        GamesSession.setCurrentNickName(EXPECTED_FROM_NICKNAME);
        GameMessage gameMessage = new GameMessage(EXPECTED_TO_NICKNAME, EXPECTED_MESSAGE);
        long actualTimeCreated = gameMessage.getTimeCreated();
        long endTransactionTime = System.currentTimeMillis();

        assertNotNull(gameMessage.getId());
        assertTrue(gameMessage.getId() instanceof UUID);
        assertTrue(actualTimeCreated >= startTransaction & actualTimeCreated <= endTransactionTime);
        assertEquals(EXPECTED_FROM_NICKNAME, gameMessage.getFromNickname());
        assertEquals(EXPECTED_TO_NICKNAME, gameMessage.getToNickname());
        assertEquals(EXPECTED_MESSAGE, gameMessage.getGameMessage());
    }


}
