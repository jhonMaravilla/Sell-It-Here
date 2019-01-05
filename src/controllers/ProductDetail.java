package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.ProductDAO;
import dao.UserDAO;
import models.ProductBean;
import models.UserBean;

/**
 * Servlet implementation class ProductDetail
 */
// @WebServlet("/ProductDetail")
@MultipartConfig(maxFileSize = 16177215)
public class ProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("product uri " + requestURI);
		switch (requestURI) {
			case "/products/product/edit":  // ?id=
				editProduct(request, response);
				break;
			case "/products/product":  //?id=
				showProduct(request,response);
				break;
			case "/products/product/new":
				newProduct(request,response);
				break;
			case "/products/product/delete":  //id=
				deleteProduct(request,response);
				break;
			case "/products/acitve":  //id=
				activeProduct(request,response);
				break;
		}
	}

	private void activeProduct(HttpServletRequest request, HttpServletResponse response) throws IOException{
		UserBean user = (UserBean) request.getSession(false).getAttribute("user");
		ArrayList<ProductBean> products = ProductDAO.getActiveProducts(user.getId());
		request.setAttribute("products", products);
		
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int productID = Integer.parseInt(request.getParameter("id"));
		int status = ProductDAO.deleteProduct(productID);
		if(status == 1) {
			System.out.println("Successfully deleted product id " + productID);
		}else {
			System.out.println("Failed to delete product id " + productID);
		}
		response.sendRedirect(request.getContextPath());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("delete")) {
			deleteProduct(request,response);
		}else {
			UserBean seller = (UserBean) request.getSession(false).getAttribute("user");
			ProductBean product = new ProductBean();
			product.setName(request.getParameter("name"));
			product.setSellerId(seller.getId());
			product.setCategory(request.getParameter("category"));
			product.setPrice(Double.parseDouble(request.getParameter("price")));
			product.setDescription(request.getParameter("description"));
			Part image = request.getPart("photo");
			
			System.out.println(image.getSize());
			System.out.println(request.getParameter("id"));
			
			if(image.getSize() == 0) {
				switch(action) {
				case "new":
						System.out.println("Got in here to create new product");
						product = ProductDAO.createProduct(product); break;
				case "edit":
						product.setProductId( Integer.parseInt(request.getParameter("id")));
						product = ProductDAO.updateProduct(product); break;
		
				}
				System.out.println("NO PHOTO");
			} 
			
			else {
				switch(action) {
				case "new":
						System.out.println("Got in here to create new product");
						product = ProductDAO.withPhoto(product, image); break;
				case "edit":
						product.setProductId( Integer.parseInt(request.getParameter("id")));
						product = ProductDAO.photoUpdate(product,image); break;
		
				}
			}
			
			response.sendRedirect(request.getRequestURI() + "?id=" + product.getProductId());
		}
	}

	
	private void editProduct(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		ProductBean product = ProductDAO.getProductByPhoto(id);
		req.setAttribute("product", product);
		req.setAttribute("action", "edit");
		req.setAttribute("submit", "Save Change");
		req.getRequestDispatcher("/productEdit.jsp").forward(req, res);
	}
	
	private void showProduct(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		ProductBean product = ProductDAO.getProductByPhoto(id);
		req.setAttribute("product", product);
		UserBean seller = UserDAO.getUserById(product.getSellerId());
		req.setAttribute("seller", seller);
		req.getSession().setAttribute("sendTo", seller.getId());
		req.getSession().setAttribute("prodID", product.getProductId());
		req.getRequestDispatcher("/productDetail.jsp").forward(req, res);
	}
	
	private void newProduct(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/productEdit.jsp").forward(req, res);
	}
	
}
