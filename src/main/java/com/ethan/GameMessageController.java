package com.ethan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class GameMessageController {
    @PostMapping(value="/messages/get")
    public static @ResponseBody  ResponseEntity<GameMessage> controllerAddMessage (@RequestBody GameMessage gameMessage) {
        GameMessages.addMessage(gameMessage);
        return new ResponseEntity<>(gameMessage, HttpStatus.OK);
    }

    @GetMapping("/messages/user/{fromNickname}")
    public static @ResponseBody ResponseEntity<GameMessage[]>
    getMessages(@PathVariable String fromNickname) {
        GameMessage[] gameMessages = GameMessages.getMessages(fromNickname);
        return new ResponseEntity<>(gameMessages, HttpStatus.OK);
    }

    @GetMapping("/messages/id/{fromNickname}")
    public static @ResponseBody ResponseEntity<GameMessage>
    getMessageById(@PathVariable String fromNickname, @RequestBody MultiValueMap<String,String> values) {
        String s = values.getFirst("id");
        UUID id = UUID.fromString(s);
        GameMessage gameMessage = GameMessages.getById(fromNickname, id);
        return new ResponseEntity<>(gameMessage, HttpStatus.OK);
    }

}
