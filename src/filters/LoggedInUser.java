package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoggedInUser
 */
@WebFilter("/LoggedInUser")
public class LoggedInUser implements Filter {
    public LoggedInUser() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		if(session == null || session.getAttribute("user") == null) {
			System.out.println("no session ");
			HttpServletRequest req = (HttpServletRequest) request;
			String uri = req.getRequestURI() + "?" + req.getQueryString();
			System.out.println("full uri  " +uri);
			request.setAttribute("uri",uri);
			request.getRequestDispatcher("/signin").forward(request, response);
		}else {
			System.out.println("yes session ");
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
