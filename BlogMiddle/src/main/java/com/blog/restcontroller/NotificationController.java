package com.blog.restcontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.blog.dao.NotificationDAO;
import com.blog.domain.Notification;

@Controller
public class NotificationController {

	@Autowired
	NotificationDAO notificationDAO;
	
	@GetMapping(value="/getAllNotification")
	public ResponseEntity<?> getAllNotification(HttpSession httpSession)
	{
		String email=(String) httpSession.getAttribute("email");
		List<Notification> notifications=notificationDAO.getAllNotification(email);
		return new ResponseEntity<List<Notification>>(notifications,HttpStatus.OK);
	}
	
	@PostMapping(value="/updateNotification/{id}")
	public ResponseEntity<?> updateNotification(@PathVariable int id)
	{
		notificationDAO.updateViewedStatus(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(value="/getNotification/{id}")
	public ResponseEntity<?> getNotification(@PathVariable int id)
	{
		Notification notification=notificationDAO.getNotification(id);
		return new ResponseEntity<Notification>(notification,HttpStatus.OK);
	}
}
