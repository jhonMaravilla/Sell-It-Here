package models;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageBean implements Serializable{
	private int messageID;
	private int senderID;
	private int recipientID;
	private String senderUser;
	private String recipientUser;
	private String message;
	private java.sql.Timestamp dateCreated;
	private boolean isRead;
	
	public MessageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageBean(int messageID, int senderID, int recipientID, String message, Timestamp dateCreated,
			boolean isRead) {
		super();
		this.messageID = messageID;
		this.senderID = senderID;
		this.recipientID = recipientID;
		this.message = message;
		this.dateCreated = dateCreated;
		this.isRead = isRead;
	}

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public int getSenderID() {
		return senderID;
	}

	public void setSenderID(int senderID) {
		this.senderID = senderID;
	}

	public int getRecipientID() {
		return recipientID;
	}

	public void setRecipientID(int recipientID) {
		this.recipientID = recipientID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public java.sql.Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(java.sql.Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean isRead() {
		return this.isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public String getSenderUser() {
		return senderUser;
	}

	public void setSenderUser(String senderUser) {
		this.senderUser = senderUser;
	}

	public String getRecipientUser() {
		return recipientUser;
	}

	public void setRecipientUser(String recipientUser) {
		this.recipientUser = recipientUser;
	}

	@Override
	public String toString() {
		return "MessageBean [messageID=" + messageID + ", senderID=" + senderID + ", recipientID=" + recipientID
				+ ", senderUser=" + senderUser + ", recipientUser=" + recipientUser + ", message=" + message
				+ ", dateCreated=" + dateCreated + ", isRead=" + isRead + "]";
	}
	
	

	
}
