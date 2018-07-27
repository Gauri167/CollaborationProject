package com.blog.restcontroller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dao.ChatDAO;
import com.blog.domain.Chat;

@RestController
public class ChatController {

	@Autowired
	private ChatDAO chatDAO;
	
	@PostMapping("/saveMessage")
	public ResponseEntity<?> saveMessage(@RequestBody Chat chat,HttpSession httpSession){
		String toId=chat.getToId();
		String fromId="gauri1697@yahoo.com";
		String message=chat.getMessage();
		Chat nchat=new Chat();
		nchat.setFromId(fromId);
		nchat.setMessage(message);
		nchat.setToId(toId);
		nchat.setMsgTime(new Date());
		if(chatDAO.saveMessage(nchat))
			return new ResponseEntity<Chat>(nchat,HttpStatus.OK);
		else 
			return new ResponseEntity<String>("Message not saved",HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/getMessages/{email}")
	public ResponseEntity<?> getMessages(@PathVariable("email") String email,HttpSession httpSession){
		String fromId="gauri1697@yahoo.com";
		String toId=email;
		List<Chat> list=chatDAO.getMessages(toId, fromId);
		System.out.println(list.size());
		return new ResponseEntity<List<Chat>>(list,HttpStatus.OK);
	}
}
