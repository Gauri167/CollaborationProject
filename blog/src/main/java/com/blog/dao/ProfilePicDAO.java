package com.blog.dao;

import com.blog.domain.ProfilePicture;

public interface ProfilePicDAO {

	boolean uploadPic(ProfilePicture picture);
	ProfilePicture getPic(String email);
}
