package com.ethan.data;
import com.ethan.GameMessage;
import com.ethan.GameMessages;
import com.ethan.Notifications;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;


public class PollNotificationTest {
    private static ResponseEntity<String> response =
            new ResponseEntity<String>(HttpStatus.CREATED);
    String message = null;
    @Test
    public void testTimeout() throws InterruptedException {
        String message = "";
        String userName = "Mary";
        long firstSleepMillis = 50;
        long frequencyMillis = 25;
        long timeoutMillis = 200;
        response = new ResponseEntity<String>(message, HttpStatus.CREATED);
        new Thread(() -> {
            response = Notifications.pollForChanges(userName, frequencyMillis, timeoutMillis);
        }).start();

        pause(firstSleepMillis);
        assertEquals(HttpStatus.CREATED, response.getStatusCode() );

        pause(timeoutMillis);
        assertEquals(HttpStatus.REQUEST_TIMEOUT, response.getStatusCode());
    }

    @Test
    public void testUpdateWhenPolling() {
        String userName = "Ethan";
        int frequencyMillis = 25;
        int firstSleepMillis = 100;
        int timeoutMillis = 200;
        String emptyMessage = null;
        response = new ResponseEntity<String>(emptyMessage, HttpStatus.CREATED);

        new Thread(() -> {
            response = Notifications.pollForChanges(userName, frequencyMillis, timeoutMillis);
        }).start();



        pause(firstSleepMillis);
        assertEquals(HttpStatus.CREATED, response.getStatusCode() );
        Notifications.addUserNotification(userName);

        pause(timeoutMillis);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    private void pause(long firstSleepMillis) {
        try {
            Thread.sleep(firstSleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
