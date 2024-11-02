package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(urlPatterns="/login")
public class FilterAuthentication implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest sreq, ServletResponse sres, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		
		
		
		HttpServletRequest req = (HttpServletRequest) sreq;
		HttpServletResponse res = (HttpServletResponse) sres;

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		res.setContentType("text/html");

		PrintWriter out = res.getWriter();
		RequestDispatcher dispatcher;

		// Get cookies from the request
		Cookie[] cookies = req.getCookies();
		String matchedRole = null;
		boolean isAuthenticated = false;

		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if ("userCredentials".equals(cookie.getName())) {
		            String[] userInfo = cookie.getValue().split("#");
		            for (String user : userInfo) {
		                String[] credentials = user.split("\\|");
		                if (credentials.length == 3) { // Ensure there are exactly 3 parts
		                    String storedUsername = credentials[0];
		                    String storedPassword = credentials[1];
		                    String storedRole = credentials[2];

		                    // Check if the provided credentials match any in the cookie
		                    if (username.equalsIgnoreCase(storedUsername) && password.equals(storedPassword)) {
		                        matchedRole = storedRole;
		                        isAuthenticated = true;
		                        break; // Break out of the loop, user found
		                    }
		                }
		            }
		            if (isAuthenticated) {
		                break; // Exit the cookie loop if user is found
		            }
		        }
		    }
		}

		// If no matching user found
		if (!isAuthenticated) {
		    out.println("<html><body>");
		    out.println("<h2>Invalid Username or Password</h2>");
		    out.println("</body></html>");
		    dispatcher = req.getRequestDispatcher("/index.html");
		    dispatcher.include(req, res);
		    return;
		}

		// Create a new session if authenticated
		if (matchedRole != null) {
		    HttpSession session = req.getSession();
		    session.setAttribute("username", username);
		    session.setAttribute("role", matchedRole);

		    // Redirect based on role using if statements
		    if ("student".equalsIgnoreCase(matchedRole)) {
		        res.sendRedirect(req.getContextPath() + "/homePage.html");
		        return;
		    } else if ("lecturer".equalsIgnoreCase(matchedRole)) {
		        res.sendRedirect(req.getContextPath() + "/homeLecturer.html");
		        return;
		    } else if ("hod".equalsIgnoreCase(matchedRole)) {
		        res.sendRedirect(req.getContextPath() + "/homeHOD.html");
		        return;
		    } else if ("registrar".equalsIgnoreCase(matchedRole)) {
		        res.sendRedirect(req.getContextPath() + "/homeRegistrar.html");
		        return;
		    } else {
		        out.println("<html><body>");
		        out.println("<h2>Invalid role detected!</h2>");
		        out.println("</body></html>");
		        dispatcher = req.getRequestDispatcher("/index.html");
		        dispatcher.include(req, res);
		    }
		}

		chain.doFilter(sreq, sres);

		
		
		
		
	}
	
	
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
