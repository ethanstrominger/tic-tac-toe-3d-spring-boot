package com.ethan.nonserver.unit;

import com.ethan.GameDatabase;
import com.ethan.GameMessages;
import com.ethan.MessageCentralApplication;
import com.ethan.Notifications;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SingletonTests {
    // TODO: Decide if tests are for code coverate purposes
    // TODO: Should I make singletons follow this pattern
    @Test
    public void testMessagesClass() {
        GameMessages m1 = GameMessages.getInstance();
        GameMessages m2 = GameMessages.getInstance();
        assertEquals(m1,m2);
    }


    @Test
    public void testGameDatabase() { GameDatabase db = new GameDatabase(); }

    @Test
    public void testNotifications() { Notifications notif = new Notifications(); }

    @Test
    public void testMessageCentralApplication() { MessageCentralApplication app = new MessageCentralApplication(); }

}
