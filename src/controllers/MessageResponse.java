package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessageDAO;
import dao.UserDAO;
import models.MessageBean;

@WebServlet("/MessageResponse")
public class MessageResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MessageResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int messageID = Integer.parseInt(request.getParameter("messageID"));
		MessageBean message = MessageDAO.getMessageByID(messageID);
		request.setAttribute("message", message);
		request.getRequestDispatcher("/reply.jsp").forward(request, response);
	}

}
