package dao;

import models.ProductBean;
import models.UserBean;
import util.SortBy;
import util.dbConnection;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.http.Part;

import java.sql.SQLException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class ProductDAO {
	// Create new product 
	
	public static ProductBean createProduct(ProductBean product) {
		Connection conn = null;
		try {
			conn = dbConnection.getConnection();
		
			String query = "INSERT INTO Products "
					+ "(sellerID, name, price, category, description) "
					+ "VALUES (?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, product.getSellerId());
			stmt.setString(2,product.getName());
			stmt.setDouble(3, product.getPrice());
			stmt.setString(4, product.getCategory());
			stmt.setString(5, product.getDescription());
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				product = getProductById(rs.getInt(1));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			dbConnection.closeConnection(conn);
		}
		
		return product;
	}
	
	// Read record by productID
		public static ProductBean getProductById(int id) {
			Connection con = null;
			ProductBean product = null;
			try{
				con = dbConnection.getConnection();
				String query = "Select * from Products where productID=? ";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, id);
				
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					product = new ProductBean();
					product.setProductId(rs.getInt("productID"));
					product.setSellerId(rs.getInt("sellerID"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getDouble("price"));
					product.setCategory(rs.getString("category"));
					product.setDescription(rs.getString("description"));
					product.setSold(rs.getBoolean("is_sold"));
					product.setCreated_at(rs.getTimestamp("created_at"));
					product.setUpdated_at(rs.getTimestamp("updated_at"));
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				dbConnection.closeConnection(con);
			}
			System.out.println("return by id" + product.toString());
			return product;
		}
	
		
	// WITH PHOTO METHOD ---------------------------------------------------------
	
	public static ProductBean withPhoto(ProductBean product, Part part) throws IOException{
		Connection conn = null;
		try {
			conn = dbConnection.getConnection();
			
			InputStream is = part.getInputStream();
		
			String query = "INSERT INTO Products "
					+ "(sellerID, name, price, category, description, image) "
					+ "VALUES (?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, product.getSellerId());
			stmt.setString(2,product.getName());
			stmt.setDouble(3, product.getPrice());
			stmt.setString(4, product.getCategory());
			stmt.setString(5, product.getDescription());
			stmt.setBlob(6, is);
			
			stmt.executeUpdate();
			
			System.out.print("I PASSED HERE");
			
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				product = getProductByPhoto(rs.getInt(1));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			dbConnection.closeConnection(conn);
		}
		
		return product;
	}
	
	
	public static ProductBean getProductByPhoto(int id) throws IOException{
		Connection con = null;
		ProductBean product = null;
		
		try{
			con = dbConnection.getConnection();
			String query = "Select * from products where productID=? ";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				product = new ProductBean();
				product.setProductId(rs.getInt("productID"));
				product.setSellerId(rs.getInt("sellerID"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setCategory(rs.getString("category"));
				product.setDescription(rs.getString("description"));
				product.setSold(rs.getBoolean("is_sold"));
				product.setCreated_at(rs.getTimestamp("created_at"));
				product.setUpdated_at(rs.getTimestamp("updated_at"));
				
			 // Image part
				Blob blob = rs.getBlob("image");
				
				if(blob != null) {
					InputStream inputStream = blob.getBinaryStream();
	                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	                
	                byte[] buffer = new byte[4096];
	                int bytesRead = -1;
	                
	                while ((bytesRead = inputStream.read(buffer)) != -1) {
	                    outputStream.write(buffer, 0, bytesRead);                  
	                }
	                
	                byte[] imageBytes = outputStream.toByteArray();
	                String image2 = Base64.getEncoder().encodeToString(imageBytes);
	                
	                inputStream.close();
	                outputStream.close();
	                 
	                product.setImage(image2);
				} else {
					product.setImage(null);
				}

			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			dbConnection.closeConnection(con);
		}
		System.out.println("return by id phto" + product.toString() );
		return product;
	}
	
	
	
	// Return list of Products from the SE
		public static ArrayList<ProductBean> getFilteredProducts(String location, String category, String search, double pmin, double pmax, SortBy sort) 
				throws IOException{
			Connection con = null;
			ArrayList<ProductBean> list = null;
			try{
				con = dbConnection.getConnection();
				String query = "Select * from Products where "
						+ "category like ? AND "
						+ "is_sold IS FALSE AND "
						+ "name LIKE ? AND "
						+ "sellerID IN (SELECT userID FROM Users WHERE city like ?) AND "
						+ "price BETWEEN ? AND ? "
						+ "ORDER BY " + sort.getValue();
				System.out.println(query);
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, "%" + category);
				stmt.setString(2,"%" + search + "%");
				stmt.setString(3, location);
				stmt.setDouble(4, pmin);
				stmt.setDouble(5, pmax);
				
				ResultSet rs = stmt.executeQuery();
				list = new ArrayList<>();
				while(rs.next()) {
					ProductBean product = new ProductBean();
					product.setProductId(rs.getInt("productID"));
					product.setSellerId(rs.getInt("sellerID"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getDouble("price"));
					product.setCategory(rs.getString("category"));
					product.setDescription(rs.getString("description"));
					product.setSold(rs.getBoolean("is_sold"));
					product.setCreated_at(rs.getTimestamp("created_at"));
					product.setUpdated_at(rs.getTimestamp("updated_at"));
					
					Blob blob = rs.getBlob("image");
					
					if(blob != null) {
						InputStream inputStream = blob.getBinaryStream();
		                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		                
		                byte[] buffer = new byte[4096];
		                int bytesRead = -1;
		                
		                while ((bytesRead = inputStream.read(buffer)) != -1) {
		                    outputStream.write(buffer, 0, bytesRead);                  
		                }
		                
		                byte[] imageBytes = outputStream.toByteArray();
		                String image2 = Base64.getEncoder().encodeToString(imageBytes);
		                
		                inputStream.close();
		                outputStream.close();
		                 
		                product.setImage(image2);
					} else {
						product.setImage(null);
					}
		
					list.add(product);
				}			
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				dbConnection.closeConnection(con);
			}
		
			return list;
		}
		
		public static ArrayList<ProductBean> getFilteredProducts(String location, String category) throws IOException{
			return getFilteredProducts(location, category, "", 0, 5000, SortBy.NEW_OLD);
		}
		
		// Return list of products that a user is SELLING 
		public static ArrayList<ProductBean> getActiveProducts(int userId) throws IOException{
			Connection con = null;
			ArrayList<ProductBean> list = null;
			try{
				con = dbConnection.getConnection();
				String query = "Select * from Products where "
						+ "is_sold IS FALSE AND "
						+ "sellerID =? "
						+ "ORDER BY " + SortBy.NEW_OLD.getValue();
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, userId);
				
				ResultSet rs = stmt.executeQuery();
				list = new ArrayList<>();
				while(rs.next()) {
					ProductBean product = new ProductBean();
					product.setProductId(rs.getInt("productID"));
					product.setSellerId(rs.getInt("sellerID"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getDouble("price"));
					product.setCategory(rs.getString("category"));
					product.setDescription(rs.getString("description"));
					product.setSold(rs.getBoolean("is_sold"));
					product.setCreated_at(rs.getTimestamp("created_at"));
					product.setUpdated_at(rs.getTimestamp("updated_at"));
					
					Blob blob = rs.getBlob("image");
					
					if(blob != null) {
						InputStream inputStream = blob.getBinaryStream();
		                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		                
		                byte[] buffer = new byte[4096];
		                int bytesRead = -1;
		                
		                while ((bytesRead = inputStream.read(buffer)) != -1) {
		                    outputStream.write(buffer, 0, bytesRead);                  
		                }
		                
		                byte[] imageBytes = outputStream.toByteArray();
		                String image2 = Base64.getEncoder().encodeToString(imageBytes);
		                
		                inputStream.close();
		                outputStream.close();
		                 
		                product.setImage(image2);
					} else {
						product.setImage(null);
					}
					
					list.add(product);
				}			
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				dbConnection.closeConnection(con);
			}
			return list;
		}
		
		public static ArrayList<ProductBean> getRecentPosts() throws IOException{
			Connection con = null;
			ArrayList<ProductBean> list = null;
			try{
				con = dbConnection.getConnection();
				String query = "Select * from Products where "
						+ "is_sold IS FALSE "
						+ "ORDER BY " + SortBy.NEW_OLD.getValue()
						+ " LIMIT 10";
				System.out.println("query " +query);
				PreparedStatement stmt = con.prepareStatement(query);
				
				ResultSet rs = stmt.executeQuery();
				
				list = new ArrayList<>();
				while(rs.next()) {
					ProductBean product = new ProductBean();
					product.setProductId(rs.getInt("productID"));
					product.setSellerId(rs.getInt("sellerID"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getDouble("price"));
					product.setCategory(rs.getString("category"));
					product.setDescription(rs.getString("description"));
					product.setSold(rs.getBoolean("is_sold"));
					product.setCreated_at(rs.getTimestamp("created_at"));
					product.setUpdated_at(rs.getTimestamp("updated_at"));
					
					Blob blob = rs.getBlob("image");
					
					if(blob != null) {
						InputStream inputStream = blob.getBinaryStream();
		                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		                
		                byte[] buffer = new byte[4096];
		                int bytesRead = -1;
		                
		                while ((bytesRead = inputStream.read(buffer)) != -1) {
		                    outputStream.write(buffer, 0, bytesRead);                  
		                }
		                
		                byte[] imageBytes = outputStream.toByteArray();
		                String image2 = Base64.getEncoder().encodeToString(imageBytes);
		                
		                inputStream.close();
		                outputStream.close();
		                 
		                product.setImage(image2);
					} else {
						product.setImage(null);
					}
					
					list.add(product);
				}			
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				dbConnection.closeConnection(con);
			}
//			/System.out.println("size " +list.size());
			return list;
		}
		
		// Update Product info for SELL
		public static ProductBean updateProduct(ProductBean product) throws IOException{
			Connection conn = null;
			try {
				conn = dbConnection.getConnection();
				String query = "UPDATE Products SET "
						+ "name=?, "
						+ "price=?, "
						+ "category=?, "
						+ "description=?, "
						+ "WHERE productID=? AND is_sold IS FALSE";
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1,product.getName());
				stmt.setDouble(2, product.getPrice());
				stmt.setString(3, product.getCategory());
				stmt.setString(4, product.getDescription());
				stmt.setInt(5, product.getProductId());
				
				int status = stmt.executeUpdate();
				if(status == 1) {
					product = getProductByPhoto(product.getProductId());
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				dbConnection.closeConnection(conn);
			}
			return product;
		}
		
		public static ProductBean photoUpdate(ProductBean product, Part part) throws IOException{
			Connection conn = null;
			try {
				conn = dbConnection.getConnection();
				String query = "UPDATE Products SET "
						+ "name=?, "
						+ "price=?, "
						+ "category=?, "
						+ "description=?, "
						+ "image=? "
						+ "WHERE productID=? AND is_sold IS FALSE";
				
				InputStream is = part.getInputStream();
				
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1,product.getName());
				stmt.setDouble(2, product.getPrice());
				stmt.setString(3, product.getCategory());
				stmt.setString(4, product.getDescription());
				stmt.setInt(6, product.getProductId());
				stmt.setBlob(5,is);
				
				int status = stmt.executeUpdate();
				if(status == 1) {
					product = getProductByPhoto(product.getProductId());
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				dbConnection.closeConnection(conn);
			}
			return product;
		}
		
		// update isSold status when the product is sold
//		public static int setSoldProduct(int productId) {
//			Connection conn = null;
//			int status = 0;
//			try {
//				conn = dbConnection.getConnection();
//				String query = "UPDATE Products SET "
//						+ "is_sold = TRUE, "
//						+ "updated_at=? "
//						+ "WHERE productID=? AND is_sold IS FALSE";
//				PreparedStatement stmt = conn.prepareStatement(query);
//				java.util.Date now = new java.util.Date();
//				stmt.setTimestamp(1, new Timestamp(now.getTime()));
//				stmt.setInt(2, productId);
//				
//				status = stmt.executeUpdate();
//
//			}catch(SQLException e) {
//				e.printStackTrace();
//			}finally { 
//				dbConnection.closeConnection(conn);
//			}
//			return status;
//		}
		
		// delete product if it is NOT SOLD
		public static int deleteProduct(int productId){
			Connection conn = null;
			int status = 0;
			try {
				conn = dbConnection.getConnection();
				String query = "DELETE FROM Products WHERE productID=? AND is_sold IS FALSE";
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, productId);
				
				status = stmt.executeUpdate();

			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				dbConnection.closeConnection(conn);
			}
			return status;
		}
		
		
		
}
