package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogoutServlet extends HttpServlet {
	
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	Cookie usernameCookie = new Cookie("username", null);
    usernameCookie.setMaxAge(0); // Expire immediately
    res.addCookie(usernameCookie);

    Cookie passwordCookie = new Cookie("password", null);
    passwordCookie.setMaxAge(0); 
    res.addCookie(passwordCookie);

    Cookie roleCookie = new Cookie("role", null);
    roleCookie.setMaxAge(0); 
    res.addCookie(roleCookie);
		
		
		
		
 // Redirecting to the index page or login page
    RequestDispatcher dispatcher = req.getRequestDispatcher("/index.html");
    dispatcher.forward(req, res);
	}

}
