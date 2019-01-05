package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.*;
import util.dbConnection;

public class MessageDAO {

	public static void sendMessage(MessageBean message) {
		Connection con = null;
		try {
			con = dbConnection.getConnection();
			String query = "Insert into messages (senderID,recipientID,message) values (?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, message.getSenderID());
			pstmt.setInt(2, message.getRecipientID());
			pstmt.setString(3, message.getMessage());
			
			pstmt.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			dbConnection.closeConnection(con);
		}
	}
	
	public static ArrayList<MessageBean> getAllMessage(int id){
		Connection con = null;
		ArrayList<MessageBean> messages = new ArrayList<>();			
		
		try {
			con = dbConnection.getConnection();
			String query = "select a.messageID, a.senderID, b.username, a.message, a.recipientID, c.username, a.dateSent, a.isRead from messages a " + 
					"inner join users b on (b.userID = a.senderID)" + 
					"inner join users c on (c.userID = a.recipientID)" + 
					"where recipientID = ? or senderID = ? order by dateSent";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MessageBean mBean = new MessageBean();
				mBean.setMessageID(rs.getInt("a.messageID"));
				mBean.setSenderID(rs.getInt("a.senderID"));
				mBean.setSenderUser(rs.getString("b.username"));
				mBean.setRecipientID(rs.getInt("a.recipientID"));
				mBean.setRecipientUser(rs.getString("c.username"));
				mBean.setMessage(rs.getString("a.message"));
				mBean.setDateCreated(rs.getTimestamp("a.dateSent"));
				mBean.setRead(rs.getBoolean("a.isRead"));
				messages.add(mBean);
			}
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			dbConnection.closeConnection(con);
		}
		
		return messages;
	}
	
	public static ArrayList<MessageBean> getBuyingMessage(int id){
		Connection con = null;
		ArrayList<MessageBean> messages = new ArrayList<>();			
		
		try {
			con = dbConnection.getConnection();
			String query = "select a.messageID, a.senderID, b.username, a.message, a.recipientID, c.username, a.dateSent, a.isRead from messages a " + 
					"inner join users b on (b.userID = a.senderID)" + 
					"inner join users c on (c.userID = a.recipientID)" + 
					"where senderID = ? order by isRead ASC";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MessageBean mBean = new MessageBean();
				mBean.setMessageID(rs.getInt("a.messageID"));
				mBean.setSenderID(rs.getInt("a.senderID"));
				mBean.setSenderUser(rs.getString("b.username"));
				mBean.setRecipientID(rs.getInt("a.recipientID"));
				mBean.setRecipientUser(rs.getString("c.username"));
				mBean.setMessage(rs.getString("a.message"));
				mBean.setDateCreated(rs.getTimestamp("a.dateSent"));
				mBean.setRead(rs.getBoolean("a.isRead"));
				messages.add(mBean);
			}
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			dbConnection.closeConnection(con);
		}
		
		return messages;
	}
	
	public static ArrayList<MessageBean> getSellingMessage(int id){
		Connection con = null;
		ArrayList<MessageBean> messages = new ArrayList<>();			
		
		try {
			con = dbConnection.getConnection();
			String query = "select a.messageID, a.senderID, b.username, a.message, a.recipientID, c.username, a.dateSent, a.isRead from messages a " + 
					"inner join users b on (b.userID = a.senderID)" + 
					"inner join users c on (c.userID = a.recipientID)" + 
					"where recipientID = ? order by isRead DESC";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MessageBean mBean = new MessageBean();
				mBean.setMessageID(rs.getInt("a.messageID"));
				mBean.setSenderID(rs.getInt("a.senderID"));
				mBean.setSenderUser(rs.getString("b.username"));
				mBean.setRecipientID(rs.getInt("a.recipientID"));
				mBean.setRecipientUser(rs.getString("c.username"));
				mBean.setMessage(rs.getString("a.message"));
				mBean.setDateCreated(rs.getTimestamp("a.dateSent"));
				mBean.setRead(rs.getBoolean("a.isRead"));
				messages.add(mBean);
			}
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			dbConnection.closeConnection(con);
		}
		
		return messages;
	}
	
	public static MessageBean getMessageByID(int id){
		Connection con = null;		
		MessageBean mBean = new MessageBean();
		try {
			con = dbConnection.getConnection();
			String query = "select a.messageID, a.senderID, b.username, a.message, a.recipientID, c.username, a.dateSent, a.isRead from messages a " + 
					"inner join users b on (b.userID = a.senderID)" + 
					"inner join users c on (c.userID = a.recipientID)" + 
					"where messageID = ? order by dateSent";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mBean.setMessageID(rs.getInt("a.messageID"));
				mBean.setSenderID(rs.getInt("a.senderID"));
				mBean.setSenderUser(rs.getString("b.username"));
				mBean.setRecipientID(rs.getInt("a.recipientID"));
				mBean.setRecipientUser(rs.getString("c.username"));
				mBean.setMessage(rs.getString("a.message"));
				mBean.setDateCreated(rs.getTimestamp("a.dateSent"));
				mBean.setRead(rs.getBoolean("a.isRead"));
			}
			
			// Set message to read
			setReadMessage(id);
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			dbConnection.closeConnection(con);
		}
		
		return mBean;
	}
	
	public static void setReadMessage(int id) {
		Connection con = null;
		try {
			con = dbConnection.getConnection();
			String query = "Update messages set isRead = true where messageID = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, id);

			pstmt.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			dbConnection.closeConnection(con);
		}
	}

}
