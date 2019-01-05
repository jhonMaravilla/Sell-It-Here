package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.*;
import models.UserBean;
import dao.UserDAO;
/**
 * Servlet implementation class RegisterUser
 */
//@WebServlet("/Signup")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/signup.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String username = request.getParameter("user");
		String email = request.getParameter("email");
		
		ArrayList<String> messages = new ArrayList<>();
		
		boolean valid = true;
		if(!UserDAO.isValidEmail(email)) {
			valid = false;
			messages.add("Email already used");
			System.out.println("email already taken");
		}if(!UserDAO.isValidUsername(username)) {
			valid = false;
			messages.add("Username already taken");
			System.out.println("Username already used");
		}
		if(valid) {
			UserBean createUser = new UserBean();
			
			createUser.setUsername(username);
			createUser.setEmail(email);

			createUser.setFname(request.getParameter("fname"));
			createUser.setLname(request.getParameter("lname"));
			createUser.hashPassword(request.getParameter("password"));
			createUser.setStreet(request.getParameter("street"));
			createUser.setCity(request.getParameter("city"));
			createUser.setProvince(request.getParameter("province"));
			createUser.setPostal(request.getParameter("postal"));
			createUser.setPhone(request.getParameter("phone"));
			
			createUser = UserDAO.createUser(createUser);
			
			HttpSession session = request.getSession();
			session.setAttribute("user", createUser);
			
			System.out.println("Complete!");
			response.sendRedirect(getServletContext().getContextPath());
		}else {
			request.setAttribute("messages", messages);
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		}
		
	}

}
