package com.ethan.nonserver.controller;

import com.ethan.GameMessage;
import com.ethan.GameMessageController;
import com.ethan.GameMessages;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class GameControllerTest {
    @Test
    public void GameAddControllerReturnsCorrectBodyAndStatus() {
        GameMessage gameMessage = new GameMessage("a","b","c");
        ResponseEntity<GameMessage> r =
                GameMessageController.controllerAddMessage((gameMessage));

        assertEquals(r.getStatusCode(), HttpStatus.OK);
        GameMessage resultMessage = r.getBody();
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
        assertEquals(resultMessage.length, GameMessages.getMessages(userName).length);
    }
}
