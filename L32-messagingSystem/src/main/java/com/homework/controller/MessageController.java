package com.homework.controller;

import com.homework.entity.Message;
import com.homework.entity.OutputMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Slf4j
public class MessageController {

//    private final UserServiceImpl userService = new UserServiceImpl();


    @MessageMapping("/chat")
    //TODO check method!
    @SendTo("/topic/messages")
    public OutputMessage sayHello(Message message) {
        log.info("got message: {}", message);
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }
}
