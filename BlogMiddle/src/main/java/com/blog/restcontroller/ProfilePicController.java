package com.blog.restcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.blog.dao.ProfilePicDAO;
import com.blog.domain.ProfilePicture;

@RestController
public class ProfilePicController {

	@Autowired
	private ProfilePicDAO profilepicDAO;
	
	@PostMapping("/uploadPic")
	public ResponseEntity<?> uploadPic(@RequestParam CommonsMultipartFile image,HttpSession httpSession){
		ProfilePicture picture=new ProfilePicture();
		String email=(String) httpSession.getAttribute("email");
		picture.setEmail(email);
		picture.setImage(image.getBytes());
		profilepicDAO.uploadPic(picture);
		return new ResponseEntity<ProfilePicture>(picture,HttpStatus.OK);
	}
	
	@GetMapping("/getImage/{email}")
	public @ResponseBody byte[] getImage(@PathVariable String email){
		ProfilePicture picture=profilepicDAO.getPic(email);
		if(picture==null) 
			return null;
		return picture.getImage();
	}
}
