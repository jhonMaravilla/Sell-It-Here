package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import dao.TransactionDAO;
import dao.UserDAO;
import models.ProductBean;
import models.TransactionBean;
import models.UserBean;

/**
 * Servlet implementation class TransactionController
 */
//@WebServlet("/TransactionController")
public class TransactionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TransactionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("product uri " + requestURI);
		switch (requestURI) {
			case "/transaction/new":
				newTransaction(request, response);
				break;
			case "/transaction":
				showTransaction(request,response);
				break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action) {
		case "buy":
			makeTransaction(request, response);
			break;
		case "cancel":
			cancelTransaction(request, response);
			break;
		}
	}
	
	private void cancelTransaction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("product");
		session.removeAttribute("seller");
		
		request.setAttribute("message", "Transaction has been successfully cancelled");
		this.showTransaction(request, response);
		
	}

	private void makeTransaction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ProductBean product = (ProductBean) session.getAttribute("product");
		UserBean seller = (UserBean) session.getAttribute("seller");
		UserBean buyer = (UserBean) session.getAttribute("user");
		
		TransactionBean transaction = new TransactionBean(product, seller, buyer);
		int status = TransactionDAO.createTransaction(transaction);
		
		request.setAttribute("message", "You have successfully bought " + product.getName());
	
		this.showTransaction(request, response);
	}

	private void newTransaction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("id"));
		ProductBean product = ProductDAO.getProductByPhoto(productId);
		UserBean seller = UserDAO.getUserById(product.getSellerId());
		
		HttpSession session = request.getSession();
		session.setAttribute("product", product);
		session.setAttribute("seller", seller);
		
		request.getRequestDispatcher("/transactionForm.jsp").forward(request, response);
	}
	
	private void showTransaction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		
		ArrayList<TransactionBean> sales = TransactionDAO.getSaleHistory(user);
		ArrayList<TransactionBean> purchases = TransactionDAO.getBuyHistory(user);
		request.setAttribute("sales", sales);
		request.setAttribute("purchases", purchases);
		
		request.getRequestDispatcher("/transactionHistory.jsp").forward(request, response);
	}
	

}
