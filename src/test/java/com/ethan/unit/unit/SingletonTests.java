package com.ethan.unit.unit;

import com.ethan.GameDatabase;
import com.ethan.GameMessages;
import com.ethan.MessageCentralApplication;
import com.ethan.Notifications;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings({"unused", "InstantiationOfUtilityClass"})
public class SingletonTests {
    // TODO: Decide if tests are for code coverage purposes
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
    public void testNotifications() { Notifications notification = new Notifications(); }

    @SuppressWarnings("unused")
    @Test
    public void testMessageCentralApplication() { MessageCentralApplication app = new MessageCentralApplication(); }

}
