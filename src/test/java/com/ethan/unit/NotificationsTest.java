package com.ethan.unit;

import com.ethan.Notifications;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Component;
import static org.junit.Assert.*;

@Component
public class NotificationsTest {
    @Before public void initialize() {
        Notifications.emptyUserMessagesQueue();
    }

    @Test
    public void testCanGetUsersWithMessages() {
        String[] notifs = Notifications.getUsersWithNotifications();
        assertTrue(true);
    }

    @Test
    public void testCanAddUserMessage() {
        final String userName = "canaddusermessage";
        Notifications.addUserNotification(userName);
        assertEquals(1, Notifications.getUserNotificationCount());
    }

    @Test
    public void testCanAddTwoUserMessages() {
        final String[] userNames = {"canadduser1", "canadduser2"};
        Notifications.addUserNotification(userNames[0]);
        Notifications.addUserNotification(userNames[1]);
        assertEquals(2, Notifications.getUserNotificationCount());
    }

    @Test
    public void testCheckForUserNotification() {
        final String[] userNames = {"canadduser1", "canadduser2"};
        Notifications.addUserNotification(userNames[0]);
        Notifications.addUserNotification(userNames[0]);
        assertTrue(Notifications.isNotificationExists(userNames[0]));
        assertTrue(Notifications.isNotificationExists(userNames[0]));
        assertFalse(Notifications.isNotificationExists("Bogus user"));
    }

    @Test
    public void testUserNotAddedTwice() {
        final String userAddedTwiceName = "Twice";
        final String otherUser = "Other";
        Notifications.addUserNotification(userAddedTwiceName);
        Notifications.addUserNotification(userAddedTwiceName);
        Notifications.addUserNotification(otherUser);

        assertEquals(2, Notifications.getUserNotificationCount());
        assertTrue(Notifications.isNotificationExists(userAddedTwiceName));
        assertTrue(Notifications.isNotificationExists(otherUser));
    }

    @Test
    public void testDeleteNotification() {
        final String userNameToBeDeleted = "To Be Deleted";
        final String userName1 = "User 1";
        final String userName2 = "User2";

        Notifications.addUserNotification(userName1);
        Notifications.addUserNotification(userNameToBeDeleted);
        Notifications.addUserNotification(userName2);

        Notifications.deleteNotification(userNameToBeDeleted);

        assertFalse(Notifications.isNotificationExists(userNameToBeDeleted));
        assertTrue(Notifications.isNotificationExists(userName1));
        assertTrue(Notifications.isNotificationExists(userName2));
    }

}
