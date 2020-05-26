package com.ethan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameMessageController {
    @PostMapping(value="/messages")
    public static @ResponseBody  ResponseEntity<GameMessage> controllerAddMessage (@RequestBody GameMessage gameMessage) {
        GameMessages.addMessage(gameMessage);
        return new ResponseEntity<GameMessage>(gameMessage, HttpStatus.OK);
    }

    @GetMapping("/messages/user/{fromNickname}")
    public static @ResponseBody ResponseEntity<GameMessage[]>
    getMessages(@PathVariable String fromNickname) {
        GamesSession.authenticateNickname(fromNickname);
        GameMessage[] gameMessages = GameMessages.getMessages(fromNickname);
        return new ResponseEntity<GameMessage[]>(gameMessages, HttpStatus.OK);
    }
}
