package com.blog.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="Chat_Table")
@SequenceGenerator(name="messageidseq",sequenceName="message_id_seq",allocationSize=1)
public class Chat {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="messageidseq")
	private int id;
	private String message;
	private String toId;
	private String fromId;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy,hh:mm",timezone="IST")
	private Date msgTime;
	
	public Date getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
}
