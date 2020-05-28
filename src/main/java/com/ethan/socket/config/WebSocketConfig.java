package com.ethan.socket.config;

import com.ethan.game.GameMessage;
import com.ethan.game.GameMessages;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.UUID;


@Configuration
@EnableWebSocketMessageBroker
@RestController
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/com/ethan/socket")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        System.out.println("****** Configuring");
        registry.setApplicationDestinationPrefixes("/socket-subscriber")
                .enableSimpleBroker("/socket-publisher");
    }



        @CrossOrigin
        @PostMapping(value="/messages/create")
        public static @ResponseBody
        ResponseEntity<GameMessage> controllerAddMessage (@RequestBody GameMessage gameMessage) {
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
        @GetMapping("/messages/getbyid/{id}")
        public static @ResponseBody ResponseEntity<GameMessage>
        getMessageById(@PathVariable String fromNickname, @RequestBody MultiValueMap<String,String> values) {
            String s = values.getFirst("id");
            UUID id = UUID.fromString(s);
            GameMessage gameMessage = GameMessages.getById(id);
            return new ResponseEntity<>(gameMessage, HttpStatus.OK);
        }

}