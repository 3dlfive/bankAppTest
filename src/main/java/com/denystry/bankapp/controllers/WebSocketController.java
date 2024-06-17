package com.denystry.bankapp.controllers;

import com.denystry.bankapp.controllers.wsdata.Greeting;
import com.denystry.bankapp.controllers.wsdata.HelloMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
@Slf4j
@RequiredArgsConstructor
public class WebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greetingAll(HelloMessage message, Principal principal) throws Exception {
        log.info("Incoming message: " + message);
        Thread.sleep(1000); // simulated delay
        //simpMessagingTemplate.convertAndSend("/topic/greetings", new Greeting("hello"));
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }


    @MessageMapping("/app/sendMessage")
    @SendTo("/topic/accountUpdates")
    public String sendMessage(String message) {
        return message;
    }

}