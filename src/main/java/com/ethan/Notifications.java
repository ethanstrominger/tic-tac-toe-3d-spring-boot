package com.ethan;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    // Note on @SneakyThrows: avoids need for adding exception to this method signature
    // and anything that calls it.  If thread.sleep somehow throws an exception, it will cause application
    // to stop with the exception.
    @SneakyThrows
    private static void pause(long millis) {
        Thread.sleep(millis);
    }

    public static ResponseEntity<String> pollForChanges(String userName, long frequencyMillis, long timeoutMillis) {
            long startTime = System.currentTimeMillis();
            String emptyMessage = null;
            while (System.currentTimeMillis() - startTime <= timeoutMillis) {
                if (Notifications.isNotificationExists(userName)) {
                    return new ResponseEntity<String>(emptyMessage, HttpStatus.ACCEPTED);
                }
                pause(frequencyMillis);
            }

            return new ResponseEntity<String>(emptyMessage, HttpStatus.REQUEST_TIMEOUT);
        }
}
