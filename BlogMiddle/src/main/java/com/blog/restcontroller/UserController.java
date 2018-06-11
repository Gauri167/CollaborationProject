package com.blog.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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
		System.out.println(emailId);
		System.out.println(nUser);
		if(nUser==null) {
			System.out.println("Line 35 of UserController");
			return new ResponseEntity<String>("User update fail",HttpStatus.NOT_FOUND);}
		nUser.setEmail(user.getEmail());
		nUser.setOnline(user.isOnline());
		nUser.setPassword(user.getPassword());
		nUser.setPhone(user.getPhone());
		nUser.setUsername(user.getUsername());
		userDAO.updateUser(nUser);
		return new ResponseEntity<String>("User updated successfully",HttpStatus.OK);
	}

	@PostMapping(value="/deleteUser/{emailId}")
	public ResponseEntity<String> deleteUser(@PathVariable("emailId") String emailId)
	{
		if(userDAO.deleteUser(emailId))
			return new ResponseEntity<String>("UserDeleted Successfully",HttpStatus.OK);
		else
			return new ResponseEntity<String>("User Delete Fail",HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/showUser/{emailId}")
	@ResponseBody
	public ResponseEntity<?> showUser(@PathVariable("emailId") String emailId)
	{
		User user=userDAO.getUser(emailId);
		if(user==null)
			return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
		else {
			System.out.println(user.getEmail());
			System.out.println(user.getUsername());
			return new ResponseEntity<User>(user,HttpStatus.OK);}
	}
	
	@GetMapping(value="/showAllUsers")
	public ResponseEntity<List<User>> showAllUsers()
	{
		List<User> list=userDAO.getAllUsers();
		if(list.size()>0)
			return new ResponseEntity<List<User>>(list,HttpStatus.OK);
		else
			return new ResponseEntity<List<User>>(list,HttpStatus.NOT_FOUND);
	}
}
