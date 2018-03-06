package Chat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Context ctx;
		Connection con;
		ResultSet rs;
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if(login!=null && login.trim().length()!=0 && password!=null && password.trim().length()!=0) {
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/root");
			con = ds.getConnection();
			String log = "SELECT * FROM user WHERE login = ? AND password = ?";
		    PreparedStatement ps = con.prepareStatement(log);
		    ps.setString(1, login);
		    ps.setString(2, password);
		    rs = ps.executeQuery();
		    if(rs.next()){
		    	request.getRequestDispatcher("/Chat.jsp").forward(request, response);
		    }else {request.getRequestDispatcher("/reg.jsp").forward(request, response);}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else{
			request.setAttribute("null", "fill in the blank fields");
			request.getRequestDispatcher("/login.jsp").forward(request, response);}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
