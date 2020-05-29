package com.ethan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Notifications {
    private static Set<String> notifications = new HashSet<>();

    public static String[] getUsersWithNotifications() {
        return null;
    }

    public static void emptyUserMessagesQueue() {
        notifications = new HashSet<>();
    }

    public static void addUserNotification(String userName) {
        notifications.add(userName);
    }

    public static int getUserNotificationCount() {
        return notifications.size();
    }

    public static boolean isNotificationExists(String userName) {
        return notifications.contains(userName);
    }

    public static void deleteNotification(String userNameToBeDeleted) {
        notifications.remove(userNameToBeDeleted);
    }
}
