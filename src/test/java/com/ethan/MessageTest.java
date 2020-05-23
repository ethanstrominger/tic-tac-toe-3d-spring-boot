package com.ethan;

import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testCreateGameMessage() {
        String EXPECTED_MESSAGE = "Hi Mary";
        String EXPECTED_TO_NICKNAME = "Mary";        long startTransaction = System.currentTimeMillis();
        String EXPECTED_FROM_NICKNAME = "John";

        GamesSession.setCurrentNickname(EXPECTED_FROM_NICKNAME);
        GameMessage gameMessage = GameMessages.addMessage(EXPECTED_TO_NICKNAME, EXPECTED_MESSAGE);
        long actualTimeCreated = gameMessage.getTimeCreated();
        long endTransactionTime = System.currentTimeMillis();

        assertNotNull(gameMessage.getId());
        assertTrue(gameMessage.getId() instanceof UUID);
        assertTrue(actualTimeCreated >= startTransaction & actualTimeCreated <= endTransactionTime);
        assertEquals(EXPECTED_FROM_NICKNAME, gameMessage.getFromNickname());
        assertEquals(EXPECTED_TO_NICKNAME, gameMessage.getToNickname());
        assertEquals(EXPECTED_MESSAGE, gameMessage.getMessageText());
    }

    @Test
    public void testGetOnlyMyMessages() {
        String myNickname = "Ethan";
        String user1Nickname = "Amanda";
        String user2Nickname = "Prabha";
        String messageFromMe = "Hi, it's "+myNickname;
        String messageNotToMe = "This is not "+myNickname;
        String messageToMe = "This is a message to "+myNickname;
        int expectedFromCount = 4;
        int expectedNotToMeCount = 2;
        int expectedToCount = 3;
        int expectedTotalCount = expectedFromCount + expectedToCount;

        addMessages(myNickname, user1Nickname, messageToMe, expectedFromCount);
        addMessages(user1Nickname, user2Nickname, messageNotToMe, expectedNotToMeCount);
        addMessages(user2Nickname, myNickname, messageToMe, expectedToCount);

        GamesSession.setCurrentNickname(myNickname);
        GameMessage myMessages[] = GameMessages.getMessages();
        assertEquals(expectedTotalCount, myMessages.length);

        int actualFromCount = getCountFromMe(myNickname, myMessages);
        assertEquals(expectedFromCount, actualFromCount);

        int actualToCount = getCountToMe(myNickname, myMessages);
        assertEquals(expectedToCount, actualToCount);
    }

    private static int getCountFromMe(String myNickname, GameMessage[] myMessages) {
        int actualFromMe = 0;
        for (GameMessage message: myMessages) {
            if (message.getFromNickname() == myNickname) {
                actualFromMe++;
            }
        }
        return actualFromMe;
    }

    private static int getCountToMe(String myNickname, GameMessage[] myMessages) {
        int actualToMe = 0;
        for (GameMessage message: myMessages) {
            if (message.getToNickname() == myNickname) {
                actualToMe++;
            }
        }
        return actualToMe;
    }
    private static void addMessages(
            String user1Nickname,
            String user2Nickname,
            String messageText,
            int numberToAdd) {
        GamesSession.setCurrentNickname(user1Nickname);
        for (int i = 0; i < numberToAdd; i++) {
            GameMessages.addMessage(user2Nickname, messageText + " " + Integer.toString(i));
        }
    }


}
