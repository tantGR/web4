

import java.io.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpa.UserDao;
import jpa.User;

@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao userDao;
    
	 public void init() {
	        userDao = new UserDao();
	 }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//RequestDispatcher rd1=request.getRequestDispatcher("register.jsp");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("user");
		String pswd = request.getParameter("password");
		
		User newuser = new User();
		newuser.setUsername(name);
		newuser.setPassword(pswd);
		
		boolean b  = userDao.saveUser(newuser);
		if (b) {
			//out.println("Welcome!");
			RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
			rd.include(request, response);
		}
		else {
			//out.println("Select a different username");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
		}
	}

}
