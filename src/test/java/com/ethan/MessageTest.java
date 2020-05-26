package com.ethan;

import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTest {
    TestUtil m = new TestUtil();
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

    //TODO: Split into two tests
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

        TestUtil.addRepeatMessages(myNickname, user1Nickname, messageFromMe, expectedFromCount);
        TestUtil.addRepeatMessages(user1Nickname, user2Nickname, messageNotToMe, expectedNotToMeCount);
        TestUtil.addRepeatMessages(user2Nickname, myNickname, messageToMe, expectedToCount);

        GameMessage[] myMessages = GameMessages.getMessages(myNickname);
        assertEquals(expectedTotalCount, myMessages.length);

        int actualFromCount = TestUtil.getCountFromMe(myNickname, myMessages);
        assertEquals(expectedFromCount, actualFromCount);

        int actualToCount = TestUtil.getCountToMe(myNickname, myMessages);
        assertEquals(expectedToCount, actualToCount);
    }

}
