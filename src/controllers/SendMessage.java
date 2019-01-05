package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessageDAO;
import models.MessageBean;
import models.UserBean;

/**
 * Servlet implementation class SendMessage
 */
@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SendMessage() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		MessageBean inquiry = new MessageBean();
		String message = request.getParameter("message");
		
		
		if(request.getSession(false).getAttribute("sendTo") != null && request.getSession(false).getAttribute("prodID") !=null) {
			int sellerID = (Integer)request.getSession(false).getAttribute("sendTo");
			int productID = (Integer)request.getSession(false).getAttribute("prodID");
			inquiry.setRecipientID(sellerID);
			inquiry.setSenderID(user.getId());
			inquiry.setMessage(message);
			
			MessageDAO.sendMessage(inquiry);
			
			System.out.println(productID);
			request.getSession().removeAttribute("prodID");
			request.getSession().removeAttribute("sendTo");
			response.sendRedirect(request.getContextPath()+"/products/product?id=" +productID);
		}
		else {
			int sendTo = Integer.parseInt(request.getParameter("sendTo"));
			inquiry.setRecipientID(sendTo);
			inquiry.setSenderID(user.getId());
			inquiry.setMessage(message);
			
			MessageDAO.sendMessage(inquiry);
			
			response.sendRedirect(request.getContextPath()+"/buyingMessage.jsp");
		}
			
	}

}
