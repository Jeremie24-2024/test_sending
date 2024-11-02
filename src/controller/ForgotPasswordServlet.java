package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet(urlPatterns="/ForgotPassword")
public class ForgotPasswordServlet extends HttpServlet {

	 protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 res.setContentType("text/html");
	        PrintWriter out = res.getWriter();

	        String usernameInput = req.getParameter("username");
	        Cookie[] cookies = req.getCookies();
	        String passwordFound = null;

	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if ("userCredentials".equals(cookie.getName())) {
	                    String[] userInfo = cookie.getValue().split("#");
	                    for (String user : userInfo) {
	                        String[] credentials = user.split("\\|");
	                        if (credentials.length == 3) { // Ensure there are exactly 3 parts
	                            String storedUsername = credentials[0];
	                            String storedPassword = credentials[1];

	                            // Check if the provided username matches
	                            if (usernameInput.equalsIgnoreCase(storedUsername)) {
	                                passwordFound = storedPassword; // Retrieve the password
	                                break; // Exit the inner loop if found
	                            }
	                        }
	                    }
	                    break; // Exit the cookie loop after processing
	                }
	            }
	        }

	        // Prepare the response
	        if (passwordFound != null) {
	            // If the password was found, display it in a popup
	            out.println("<html>");
	            out.println("<head>");
	            out.println("<script type='text/javascript'>");
	            out.println("alert('Your password is: " + passwordFound + "');");
	            out.println("window.location.href='index.html';"); // Redirect to the form page after alert
	            out.println("</script>");
	            out.println("</head>");
	            out.println("<body>");
	            out.println("</body></html>");
	        } else {
	            // If the username was not found, show an error message
	            out.println("<html>");
	            out.println("<body>");
	            out.println("<h2>User not found. Please check your username.</h2>");
	            out.println("<a href='index.html'>Go back</a>"); // Provide a link to go back
	            out.println("</body></html>");
	        }
	    }
	
	
	
}
