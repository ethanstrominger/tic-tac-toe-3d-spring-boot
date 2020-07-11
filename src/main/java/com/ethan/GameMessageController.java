package com.ethan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GameMessageController {
//    @CrossOrigin
//    @MessageMapping("/messages/send/user/{toNickname}")
//    @SendTo("/messages/deliver/user/{toNickname}")
//    public static @ResponseBody  ResponseEntity<GameMessage> controllerAddMessage (@RequestBody GameMessage gameMessage) {
//        GameMessages.addMessage(gameMessage);
//        return new ResponseEntity<>(gameMessage, HttpStatus.OK);
//    }

    @CrossOrigin
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public MessageToClient greeting(MessageFromClient message) throws Exception {
        System.out.println("ABCABC **************");
        Thread.sleep(1000); // simulated delay
        return new MessageToClient("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @CrossOrigin
    @PostMapping(value="/messages/create/{toNickname}")
    @SendTo("/topic/greeting")
//    @SendTo("/socket-subscribe/user/{toNickname}")
    public static @ResponseBody  ResponseEntity<GameMessage> controllerAddMessage (@RequestBody GameMessage gameMessage) {
         System.out.println("Here");
         GameMessages.addMessage(gameMessage);
        return new ResponseEntity<>(gameMessage, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/messages/getbyuser/{fromNickname}")
    public static @ResponseBody ResponseEntity<GameMessage[]>
    getMessages(@PathVariable String fromNickname) {
        GameMessage[] gameMessages = GameMessages.getMessages(fromNickname);
        return new ResponseEntity<>(gameMessages, HttpStatus.OK);
    }

//     TODO: remove
//    @CrossOrigin
//    @GetMapping("/messages/listen/{userName}")
//    public static ResponseEntity<String> getNotifications(@PathVariable String userName) {
//        int frequencyMillis = 25;
//        int timeoutMillis = 200;
////        return Notifications.pollForChanges(userName, frequencyMillis, timeoutMillis);
//    }


}
