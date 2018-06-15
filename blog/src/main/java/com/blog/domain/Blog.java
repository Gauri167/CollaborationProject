package com.blog.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Blog_Table")
public class Blog {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int blogId;
	private String title;
	private String content;
	private String postedBy;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	private Date  postedOn;
	private String username;
	private String status;
	private int likes;
	private int shared;
	private String sharedBy;
	
	@OneToMany(mappedBy="blog",fetch=FetchType.EAGER)
	private Set<BlogComment> blogComments;
	
	public Set<BlogComment> getBlogComments() {
		return blogComments;
	}
	public void setBlogComments(Set<BlogComment> blogComments) {
		this.blogComments = blogComments;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getShared() {
		return shared;
	}
	public void setShared(int shared) {
		this.shared = shared;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String username) {
		this.postedBy =username;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	public String getSharedBy() {
		return sharedBy;
	}
	public void setSharedBy(String sharedBy) {
		this.sharedBy = sharedBy;
	}
	
}
