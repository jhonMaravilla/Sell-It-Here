package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.ProductBean;
import models.TransactionBean;
import models.UserBean;
import util.dbConnection;

public class TransactionDAO {
	public static ArrayList<TransactionBean> getSaleHistory(UserBean seller){
		Connection conn = null;
		ArrayList<TransactionBean> transactions = null;
		try {
			conn = dbConnection.getConnection();
			String query = "SELECT * , U.userID AS 'buyerID' "
					+ "FROM transactions T JOIN users U ON T.buyerID = U.userID "
					+ "JOIN products P ON T.productID = P.productID "
					+ "where T.sellerID = ? "
					+ "ORDER BY T.created_at DESC";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, seller.getId());
			
			transactions = new ArrayList<>();
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserBean buyer = new UserBean();
				buyer.setId(rs.getInt("buyerID"));
				buyer.setUsername(rs.getString("U.username"));
				buyer.setCity(rs.getString("U.city"));
				buyer.setEmail(rs.getString("U.email"));
				
				ProductBean product = new ProductBean();
				product.setProductId(rs.getInt("P.productID"));
				product.setName(rs.getString("P.name"));
				product.setPrice(rs.getDouble("P.price"));
				product.setDescription(rs.getString("P.description"));
				product.setCategory(rs.getString("P.category"));
				
				TransactionBean transaction = new TransactionBean(product,seller,buyer);
				transaction.setCreated_at(rs.getTimestamp("T.created_at"));
				
				transactions.add(transaction);
			}
			rs.close();
			pstmt.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			dbConnection.closeConnection(conn);
		}
		return transactions;		
	}
	
	public static ArrayList<TransactionBean> getBuyHistory(UserBean buyer){
		Connection conn = null;
		ArrayList<TransactionBean> transactions = null;
		try {
			conn = dbConnection.getConnection();
			String query = "SELECT * , U.userID AS 'sellerID' "
					+ "FROM transactions T JOIN users U ON T.sellerID = U.userID "
					+ "JOIN products P ON T.productID = P.productID "
					+ "where T.buyerID = ? "
					+ "ORDER BY T.created_at DESC";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, buyer.getId());
			
			transactions = new ArrayList<>();
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserBean seller = new UserBean();
				seller.setId(rs.getInt("sellerID"));
				seller.setUsername(rs.getString("U.username"));
				seller.setCity(rs.getString("U.city"));
				seller.setEmail(rs.getString("U.email"));
				
				ProductBean product = new ProductBean();
				product.setProductId(rs.getInt("P.productID"));
				product.setName(rs.getString("P.name"));
				product.setPrice(rs.getDouble("P.price"));
				product.setDescription(rs.getString("P.description"));
				product.setCategory(rs.getString("P.category"));
				
				TransactionBean transaction = new TransactionBean(product,seller,buyer);
				transaction.setCreated_at(rs.getTimestamp("T.created_at"));
				
				transactions.add(transaction);
			}
			rs.close();
			pstmt.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			dbConnection.closeConnection(conn);
		}
		return transactions;		
	}
	
	public static int createTransaction(TransactionBean transaction) {
		Connection conn = null;
		int status = 0;
		try {
			conn = dbConnection.getConnection();
			conn.setAutoCommit(false);
			String query = "INSERT INTO Transactions (sellerID, buyerID, productID) VALUES (?,?,?)";
			String query2 = "UPDATE Products SET is_sold = TRUE WHERE productID=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, transaction.getSellerID());
			pstmt.setInt(2, transaction.getBuyerID());
			pstmt.setInt(3, transaction.getProductID());
			PreparedStatement pstmt2 = conn.prepareStatement(query2);
			pstmt2.setInt(1, transaction.getProductID());
			
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			
			conn.commit();

			pstmt.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			dbConnection.closeConnection(conn);
		}
		return status;
	}
	
}
