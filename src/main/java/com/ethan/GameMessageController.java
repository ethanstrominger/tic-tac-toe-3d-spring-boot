package com.ethan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameMessageController {
    @CrossOrigin
    @PostMapping(value="/messages/create")
    public static @ResponseBody  ResponseEntity<GameMessage> controllerAddMessage (@RequestBody GameMessage gameMessage) {
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

    @CrossOrigin
    @GetMapping("/messages/listen/{userName}")
    public static ResponseEntity<String> getNotifications(@PathVariable String userName) {
        int frequencyMillis = 25;
        int timeoutMillis = 200;
        return Notifications.pollForChanges(userName, frequencyMillis, timeoutMillis);
    }


//    @GetMapping("/test")
//    DeferredResult<GameMessage[]> test(String userName, int pollingTime){
//        Long timeOutInMilliSec = 100000L;
//        String timeOutResp = "Time Out.";
//        DeferredResult<String> deferredResult = new DeferredResult<>(timeOutInMilliSec,timeOutResp);
//        CompletableFuture.runAsync(()->{
//            try {
//                //Long pooling task;If task is not completed within 100 sec timeout response return for this request
//                TimeUnit.MILLISECONDS.sleep(200);
//                //set result after completing task to return response to client
//                deferredResult.setResult("ok");
//            }catch (Exception ex){
//            }
//        });
//        GameMessage[] emptyMessages = {};
//        return new DeferredResult<GameMessage[]>(emptyMessages);
//    }
}
