package com.ethan.unit.controller;

import com.ethan.GameMessage;
import com.ethan.GameMessageController;
import com.ethan.GameMessages;
import com.ethan.Notifications;
import com.ethan.unit.TestUtil;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class GameControllerTest {

    private static ResponseEntity<String> controllerThreadResponse =
            new ResponseEntity<>(HttpStatus.CREATED);

    @Test
    public void GameAddControllerReturnsCorrectBodyAndStatus() {
        GameMessage gameMessage = new GameMessage("a","b","c");
        ResponseEntity<GameMessage> r =
                GameMessageController.controllerAddMessage((gameMessage));

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        GameMessage resultMessage = r.getBody();
        //noinspection ConstantConditions
        assertEquals(gameMessage.getId(), resultMessage.getId());
    }

    @Test
    public void GameGetMessagesReturnsCorrectBodyAndStatus() {
        final String userName = "Ethan";
        GameMessage gameMessage = new GameMessage(userName,"b","c");
        GameMessages.addMessage(gameMessage);

        ResponseEntity<GameMessage[]> r =
                GameMessageController.getMessages(userName);

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        GameMessage[] resultMessage = r.getBody();
        //noinspection ConstantConditions
        assertEquals(resultMessage.length, GameMessages.getMessages(userName).length);
    }

    @Test
    public void testSetsHttpStatusToCreatedIfUserHasNotification() {
        String userName = "Ethan";
        int firstSleepMillis = 100;
        int frequencyMillis = 25;
        int timeoutMillis = 200;

        new Thread(() -> controllerThreadResponse = Notifications.pollForChanges(userName, frequencyMillis, timeoutMillis)).start();

        TestUtil.pause(firstSleepMillis);
        assertEquals(HttpStatus.CREATED, controllerThreadResponse.getStatusCode() );
        Notifications.addUserNotification(userName);

        TestUtil.pause(timeoutMillis);
        assertEquals(HttpStatus.ACCEPTED, controllerThreadResponse.getStatusCode() );
    }


}
