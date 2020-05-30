package com.ethan.nonserver.unit;
import com.ethan.Notifications;
import com.ethan.nonserver.TestUtil;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;


public class ThreadMessageNotificationTest {
    // Variables set inside Thread() have to be static or get error
    private static ResponseEntity<String> threadResponse =
            new ResponseEntity<>(HttpStatus.CREATED);

    @Test
    public void testTimeout() {
        String message = "";
        String userName = "Mary";
        long firstSleepMillis = 50;
        long frequencyMillis = 25;
        long timeoutMillis = 200;

        threadResponse = new ResponseEntity<>(message, HttpStatus.CREATED);

        new Thread(() -> threadResponse = Notifications.pollForChanges(userName, frequencyMillis, timeoutMillis)).start();

        TestUtil.pause(firstSleepMillis);
        assertEquals(HttpStatus.CREATED, threadResponse.getStatusCode() );

        TestUtil.pause(timeoutMillis);
        assertEquals(HttpStatus.REQUEST_TIMEOUT, threadResponse.getStatusCode());
    }

    @Test
    public void testSetsHttpStatusToCreatedIfUserHasNotification() {
        String userName = "Ethan";
        int frequencyMillis = 25;
        int firstSleepMillis = 100;
        int timeoutMillis = 200;
        threadResponse = new ResponseEntity<>(null, HttpStatus.CREATED);

        new Thread(() -> threadResponse = Notifications.pollForChanges(userName, frequencyMillis, timeoutMillis)).start();

        TestUtil.pause(firstSleepMillis);
        assertEquals(HttpStatus.CREATED, threadResponse.getStatusCode() );
        Notifications.addUserNotification(userName);

        TestUtil.pause(timeoutMillis);
        assertEquals(HttpStatus.ACCEPTED, threadResponse.getStatusCode());
    }

}
