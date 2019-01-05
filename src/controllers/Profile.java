package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import models.UserBean;

/**
 * Servlet implementation class Profile
 */
//@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/profile.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String province = request.getParameter("province");
		String postal = request.getParameter("postal");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("password1");
		String creditcard = request.getParameter("creditcard");
		
		UserBean user = (UserBean)request.getSession().getAttribute("user");
		
		user.setFname(fname);
		user.setLname(lname);
		user.setStreet(street);
		user.setCity(city);
		user.setProvince(province);
		user.setPostal(postal);
		user.setPhone(phone);
		user.setEmail(email);	
		
		if(password.length() == 0) {
			System.out.println(user.getSalt() + " " + user.getPassword());
		} else {
			user.hashPassword(password);
		}

		if(creditcard.length() == 0) {
			System.out.println(user.getCreditcard());
		} else {
			System.out.println("New Creditcard2: "  + creditcard);
			user.setCreditcard(creditcard);
		}
		
		System.out.println(user);
		// NEED LOGIC HERE
		
		user = UserDAO.updateUser(user);
		

//		System.out.println("Got inside profile servlet retrive user");
		if(user != null) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Profile Updated')");
			out.println("</script>");
			request.getRequestDispatcher("/profile.jsp").forward(request, response);
		}
	}

}
