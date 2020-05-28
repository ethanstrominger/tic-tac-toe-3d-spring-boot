package com.ethan;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class MessageTest {

    static int getCountFromMe(String myNickname, GameMessage[] myMessages) {
        int actualFromMe = 0;
        for (GameMessage message: myMessages) {
            if (message.getFromNickname().equals(myNickname)) {
                actualFromMe++;
            }
        }
        return actualFromMe;
    }

    static int getCountToMe(String myNickname, GameMessage[] myMessages) {
        int actualToMe = 0;
        for (GameMessage message: myMessages) {
            if (message.getToNickname().equals(myNickname)) {
                actualToMe++;
            }
        }
        return actualToMe;
    }

    static void addRepeatMessages(
            String fromNickname,
            String toNickname,
            String messageText,
            int numberToRepeat) {
        for (int i = 0; i < numberToRepeat; i++) {
            GameMessage gameMessage = new GameMessage(fromNickname, toNickname, messageText + " " + i);
            GameMessages.addMessage(gameMessage);
        }
    }

    // TODO: Add test for adding a message to the database and for getting a message
    @Test
    public void testCreateGameMessage() {
        String EXPECTED_MESSAGE = "Hi Mary";
        String EXPECTED_TO_NICKNAME = "Mary";
        long startTransaction = System.currentTimeMillis();
        String EXPECTED_FROM_NICKNAME = "John";

        GameMessage gameMessage = new GameMessage(EXPECTED_FROM_NICKNAME, EXPECTED_TO_NICKNAME, EXPECTED_MESSAGE);
        long actualTimeCreated = gameMessage.getTimeCreated();
        long endTransactionTime = System.currentTimeMillis();

        assertNotNull(gameMessage.getId());
        assertTrue(actualTimeCreated >= startTransaction & actualTimeCreated <= endTransactionTime);
        assertEquals(EXPECTED_FROM_NICKNAME, gameMessage.getFromNickname());
        assertEquals(EXPECTED_TO_NICKNAME, gameMessage.getToNickname());
        assertEquals(EXPECTED_MESSAGE, gameMessage.getMessageText());
    }

    @Test
    public void testMessagesClass() {
        GameMessages m = new GameMessages();
    }

    @Test
    public void testGetMessageById() {
        GameMessage message1 = new GameMessage("Ethan", "Fred", "Hello");
        GameMessage message2 = new GameMessage("Ethan", "Louisa", "Hello 2");
        GameMessage message3 = new GameMessage("Ethan", "Fred", "Hello");
        GameMessages.addMessage(message1);
        GameMessages.addMessage(message2);
        GameMessages.addMessage(message3);

        GameMessage resultMessage = GameMessages.getById(message2.getId());
        assertEquals(message2.getId(), resultMessage.getId());
        assertEquals(message2.getToNickname(), resultMessage.getToNickname());
        assertEquals(message2.getMessageText(), resultMessage.getMessageText());
    }

    @Test
    public void testGetMessageByInvalidId() {
        GameMessage message1 = new GameMessage("Ethan", "Fred", "Hello");
        GameMessage message2 = new GameMessage("Ethan", "Louisa", "Hello 2");
        GameMessages.addMessage(message1);
        GameMessages.addMessage(message2);
        UUID invalidId = UUID.randomUUID();
        assertNull(GameMessages.getById(invalidId));
    }

        //TODO: Split into two tests
    @Test
    public void testGetOnlyMyMessages() {
        String myNickname = "TestGetMyMessages";
        String user1Nickname = "Amanda";
        String user2Nickname = "Prabha";
        String messageFromMe = "Hi, it's "+myNickname;
        String messageNotToMe = "This is not "+myNickname;
        String messageToMe = "This is a message to "+myNickname;
        int expectedFromCount = 4;
        int expectedNotToMeCount = 2;
        int expectedToCount = 3;
        int expectedTotalCount = expectedFromCount + expectedToCount;

        addRepeatMessages(myNickname, user1Nickname, messageFromMe, expectedFromCount);
        addRepeatMessages(user1Nickname, user2Nickname, messageNotToMe, expectedNotToMeCount);
        addRepeatMessages(user2Nickname, myNickname, messageToMe, expectedToCount);

        GameMessage[] myMessages = GameMessages.getMessages(myNickname);
        assertEquals(expectedTotalCount, myMessages.length);

        int actualFromCount = getCountFromMe(myNickname, myMessages);
        assertEquals(expectedFromCount, actualFromCount);

        int actualToCount = getCountToMe(myNickname, myMessages);
        assertEquals(expectedToCount, actualToCount);
    }

    @Test
    public void seesionUpdatedWhenMessageCreated() {
        final String USER1_NICKNAME = "Session Update User1";
        final String USER2_NICKNAME = "Session Update User2";
        final long nowMillis = System.currentTimeMillis();
        GameMessage message = new GameMessage(USER1_NICKNAME, USER2_NICKNAME, "Message");
        GameMessages.addMessage(message);
        long timeUser1Millis = GameSessions.getSession(USER1_NICKNAME).getTimeMessageUpdated();
        assertTrue(timeUser1Millis >= nowMillis);
        assertTrue(timeUser1Millis <= System.currentTimeMillis());
        long timeUser2Millis = GameSessions.getSession(USER2_NICKNAME).getTimeMessageUpdated();
        assertTrue(timeUser2Millis >= nowMillis);
        assertTrue(timeUser2Millis <= System.currentTimeMillis());
    }
}
