package com.blog.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dao.FriendDAO;
import com.blog.domain.Friend;
import com.blog.domain.User;

@RestController
public class FriendController {
	
	@Autowired
	private FriendDAO friendDAO;
	
	@GetMapping(value="/suggestedUsers")
	public ResponseEntity<?> SuggestedUsers(HttpSession httpSession){
		String email=(String) httpSession.getAttribute("email");
		List<User> list=friendDAO.suggestedUserList(email);
		if(list.size()>0)
			return new ResponseEntity<List<User>>(list,HttpStatus.OK);
		return new ResponseEntity<String>("No user Found",HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/addFriendRequest")
	public ResponseEntity<?> addFriendRequest(@RequestBody User user,HttpSession httpSession){
		String email=(String) httpSession.getAttribute("email");
		Friend friend=new Friend();
		friend.setFromId(email);
		friend.setToId(user.getEmail());
		friend.setStatus('P');
		friendDAO.addFriendRequest(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	
	@GetMapping(value="/pendingRequests")
	public ResponseEntity<?> pendingRequests(){
		String email="167gauri.g@gmail.com";
		List<Friend> list=friendDAO.getAllPendingRequests(email);
		return new ResponseEntity<List<Friend>>(list,HttpStatus.OK);
	}
	
	@PostMapping(value="/updateRequest")
	public ResponseEntity<?> updateRequest(@RequestBody Friend friend){
		friendDAO.updateFriendRequest(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}

}
