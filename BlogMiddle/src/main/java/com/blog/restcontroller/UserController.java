package com.blog.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dao.UserDAO;
import com.blog.domain.User;

@RestController
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@PostMapping(value="/addUser")
	public ResponseEntity<String> addUser(@RequestBody User user)
	{
		if(userDAO.addUser(user))
			return new ResponseEntity<String>("User added successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("User add failed",HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/updateUser/{emailId}")
	public ResponseEntity<String> updateUser(@PathVariable("emailId") String emailId,@RequestBody User user)
	{
		User nUser=userDAO.getUser(emailId);
		if(nUser==null)
			return new ResponseEntity<String>("User update fail",HttpStatus.NOT_FOUND);
		nUser.setEmail(user.getEmail());
		nUser.setOnline(user.isOnline());
		nUser.setPassword(user.getPassword());
		nUser.setPhone(user.getPhone());
		nUser.setUsername(user.getUsername());
		userDAO.updateUser(nUser);
		return new ResponseEntity<String>("User updated successfully",HttpStatus.OK);
	}

}
