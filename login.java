
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpa.UserDao;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao userdao;
   
	 public void init() {
	        userdao = new UserDao();
	 }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("user");
		String pswd = request.getParameter("password");
		
		boolean b  = userdao.getUser(name,pswd);
		if (b) {
			RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
			rd.include(request, response);
		}
		else{
			//out.println("Invalid username or password.");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.include(request, response);
		}
	}
}
