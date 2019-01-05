

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ProductBean;
import util.dbConnection;

@WebServlet("/TEST")
public class TEST extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TEST() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		ProductBean product = null;
		try{
			con = dbConnection.getConnection();
			String query = "Select * from products where productID=? ";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, 1);
			
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
		System.out.println("return by id" + product.toString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
