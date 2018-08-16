/*package com.blog.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.blog.domain.Chat;

@Controller
public class SockController {

	//private static final Log logger=LogFactory.getLog(SockController.class);
	private final SimpMessagingTemplate messagingTemplate;
    private List<String> users=new ArrayList<String>();
    
    @Autowired
    public SockController(SimpMessagingTemplate messagingTemplate)
    {
    	this.messagingTemplate=messagingTemplate;
    }
    
    @SubscribeMapping("/join/{email}")
    public List<String> join(@DestinationVariable("email") String email)
    {
    	if(!users.contains(email)) {
    		users.add(email);
    	}
    	messagingTemplate.convertAndSend("/topic/join",email);
    	return users;
    }
    
    @MessageMapping(value="/chat")
    public void chatReveived(Chat chat) {
    	if("all".equals(chat.getToId())) {
    		messagingTemplate.convertAndSend("/queue/chats",chat);
    	}
    	else {
    		messagingTemplate.convertAndSend("/queue/chats"+chat.getToId(),chat);
    		messagingTemplate.convertAndSend("/queue/chats"+chat.getFromId(),chat);
    	}
    }
}
*/