package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class LoginServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) {
		
		String username= req.getParameter("username");
		String password= req.getParameter("password");
		
		
		 String usernameCookies="";
		 String passwordCookies="";
		 String roleCookies="";
		
		 
		 PrintWriter out;
		 RequestDispatcher dispatcher;
		 
		 try {
	            out = res.getWriter();

	            // Get cookies from the request
	            Cookie ck[] = req.getCookies();
				if(ck != null) {
					usernameCookies = ck[0].getValue();
					passwordCookies = ck[1].getValue();
					roleCookies=ck[2].getValue();
				}

	            // If no cookies found, prompt for account creation
	            if (usernameCookies.isEmpty() || passwordCookies.isEmpty() || roleCookies.isEmpty()) {
	            	out.println("<body>");
	                out.println("<h3>Please create an account</h3>");
	                out.println("</body>");
	                dispatcher = req.getRequestDispatcher("/index.html");
	                dispatcher.include(req, res);
	                return;
	            }

	            // Validate user credentials
	            if (!username.isEmpty() && !password.isEmpty()) {
	                // Check if the provided credentials match the cookies
	                if (username.equalsIgnoreCase(usernameCookies) && password.equals(passwordCookies)) {
	                    // Redirect based on role
	                    if ("student".equalsIgnoreCase(roleCookies)) {
	                        dispatcher = req.getRequestDispatcher("/homePage.html");
	                    } else if ("HOD".equalsIgnoreCase(roleCookies)) {
	                        dispatcher = req.getRequestDispatcher("/homeHOD.html");
	                    } else if ("lecturer".equalsIgnoreCase(roleCookies)) {
	                        dispatcher = req.getRequestDispatcher("/homeLecturer.html");
	                    }else if("registrar".equalsIgnoreCase(roleCookies)){
	                    	 dispatcher = req.getRequestDispatcher("/homeRegistrar.html");
	                    }
	                    else {
	                    	out.println("<body>");
	                        out.println("<h3>Invalid role detected!</h3>");
	                        out.println("</body>");
	                        dispatcher = req.getRequestDispatcher("/index.html");
	                    }
	                    dispatcher.forward(req, res);
	                } else {
	                	out.println("<body>");
	                    out.println("<h3>Invalid Username or Password</h3>");
	                    out.println("</body>");
	                    dispatcher = req.getRequestDispatcher("/index.html");
	                    dispatcher.include(req, res);
	                }
	            } else {
	            	out.println("<body>");
	                out.println("<h3>Fields are required</h3>");
	                out.println("</body>");
	                dispatcher = req.getRequestDispatcher("/index.html");
	                dispatcher.include(req, res);
	            }
	        } catch (IOException | ServletException e) {
	            ((Throwable) e).printStackTrace();
	        }
	}

}
