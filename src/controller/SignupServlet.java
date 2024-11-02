package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/signup")
public class SignupServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		// Get parameter from request
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confpassword = req.getParameter("confpassword");
		String role = req.getParameter("role");

		String userCredentials = username + "|" + password + "|" + role;

		PrintWriter out = res.getWriter();

		// Validate password
		if (!password.equals(confpassword)) {
		    out.println("<html><body>");
		    out.println("<h2>Passwords do not match!</h2>");
		    out.println("</body></html>");
		    
		    RequestDispatcher dispatcher = req.getRequestDispatcher("/signup.html");
		    dispatcher.include(req, res);
		} else {
		    // Check for existing cookie
		    Cookie[] cookies = req.getCookies();
		    String existingCredentials = "";
		    
		    // Loop through cookies to find userCredentials
		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            if (cookie.getName().equals("userCredentials")) {
		                existingCredentials = cookie.getValue();
		                break;
		            }
		        }
		    }

		    // Append new user credentials to existing ones
		    if (!existingCredentials.isEmpty()) {
		        existingCredentials += "#" + userCredentials; // Use ';' as delimiter
		    } else {
		        existingCredentials = userCredentials;
		    }

		    // Create or update the cookie with combined credentials
		    Cookie cookie = new Cookie("userCredentials", existingCredentials);
		    cookie.setMaxAge(60 * 60 * 24); // Set cookie expiration
		    res.addCookie(cookie);

		    res.setContentType("text/html");
		    
		    // Display success message
		    out.println("<html><body>");
		    out.println("<h2>Account created successfully!</h2>");
		    out.println("</body></html>");
		    
		    RequestDispatcher dispatcher = req.getRequestDispatcher("/signup.html");
		    dispatcher.include(req, res);
		}
		  
	 

		
		
	}

}
