package com.ethan.unit;
import com.ethan.Notifications;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;


public class ThreadMessageNotificationTest {
    // Variables set inside Thread() have to be static or get error
    private static ResponseEntity<String> threadResponse =
            new ResponseEntity<String>(HttpStatus.CREATED);
    String message = null;

    @Test
    public void testTimeout() {
        String message = "";
        String userName = "Mary";
        long firstSleepMillis = 50;
        long frequencyMillis = 25;
        long timeoutMillis = 200;
        threadResponse = new ResponseEntity<String>(message, HttpStatus.CREATED);
        new Thread(() -> {
            threadResponse = Notifications.pollForChanges(userName, frequencyMillis, timeoutMillis);
        }).start();

        pause(firstSleepMillis);
        assertEquals(HttpStatus.CREATED, threadResponse.getStatusCode() );

        pause(timeoutMillis);
        assertEquals(HttpStatus.REQUEST_TIMEOUT, threadResponse.getStatusCode());
    }

    @Test
    public void testPollSetsStatusToCreatedIfUserHasNotification() {
        String userName = "Ethan";
        int frequencyMillis = 25;
        int firstSleepMillis = 100;
        int timeoutMillis = 200;
        String emptyMessage = null;
        threadResponse = new ResponseEntity<String>(emptyMessage, HttpStatus.CREATED);

        new Thread(() -> {
            threadResponse = Notifications.pollForChanges(userName, frequencyMillis, timeoutMillis);
        }).start();

        pause(firstSleepMillis);
        assertEquals(HttpStatus.CREATED, threadResponse.getStatusCode() );
        Notifications.addUserNotification(userName);

        pause(timeoutMillis);
        assertEquals(HttpStatus.ACCEPTED, threadResponse.getStatusCode());
    }

    private void pause(long firstSleepMillis) {
        try {
            Thread.sleep(firstSleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
