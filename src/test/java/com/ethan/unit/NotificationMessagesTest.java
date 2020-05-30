package com.ethan.unit;

import com.ethan.GameMessage;
import com.ethan.GameMessages;
import com.ethan.Notifications;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Component;

import static org.junit.Assert.*;

@Component
public class NotificationMessagesTest {
    @Before public void initialize() {
        Notifications.emptyUserMessagesQueue();
    }

    @Test
    public void testNotificationAddedWhenMessageAdded() {
        final String fromName = "Guinea Pig 1";
        final String toName = "Guniea Pig 2";
        GameMessage gameMessage = new GameMessage(fromName, toName, "A message");
        GameMessages.addMessage(gameMessage);

        assertFalse(Notifications.isNotificationExists(fromName));
        assertTrue(Notifications.isNotificationExists(toName));

    }

    @Test
    public void testNotificationDeletedWhenGetMessages() {
        final String fromName = "Guinea Pig 1";
        final String toName = "Guniea Pig 2";
        GameMessage gameMessage = new GameMessage(fromName, toName, "A message");
        GameMessages.addMessage(gameMessage);
        GameMessage[] gameMessages = GameMessages.getMessages(toName);
        assertFalse(Notifications.isNotificationExists(toName));

    }
}


