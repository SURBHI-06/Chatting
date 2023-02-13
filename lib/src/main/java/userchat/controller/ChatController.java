package userchat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import userchat.dto.ChatMessage;
import userchat.respository.UserRespository;

@Controller
public class ChatController {
	
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    
    @Autowired
    private UserRespository userRepo;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, 
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        var userDetails = userRepo.findByPhoneNumber(chatMessage.getSender());
        if(userDetails.isEmpty()) {
        	  chatMessage.setContent("user not present with phone number :"+chatMessage.getSender());
        }else {
        	headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        }
        return chatMessage;
    }
    
//    @MessageExceptionHandler
//    @SendToUser("/topic/error")
//    public String handleException(IllegalArgumentException ex) {
//        return "Got error: " + ex.getMessage();
//    }
}