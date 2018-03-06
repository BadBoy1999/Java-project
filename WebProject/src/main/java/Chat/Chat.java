package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;


@WebServlet("/Chat")
public class Chat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Context ctx = null;
		Connection con = null;
		ResultSet rs = null;
		String comments = null;
		String str = request.getParameter("text");
		StringBuilder jb = new StringBuilder();
		String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { e.printStackTrace(); }
        System.out.println(jb.toString());
//		  try {
////		    JSONObject jsonObject =  HTTP.toJSONObject(jb.toString());
////		     comments = jsonObject.getString("text");
////		    System.out.println(comments);
//		  } catch (JSONException e) {
//		    e.printStackTrace();
//		  }
		  
		if(str!=null&&str.trim().length()!=0){ 
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/root");
			con = ds.getConnection();
			String quar = "INSERT INTO mydb.chat (text) " + "VALUES (?)";
		    PreparedStatement ps = con.prepareStatement(quar);
		    ps.setString(1,str);
		    ps.executeUpdate();
		    PreparedStatement pst = con.prepareStatement("SELECT * FROM chat ORDER BY id DESC LIMIT 100");
		    rs = pst.executeQuery();
		    ArrayList<String> com = new ArrayList<String>();
		    while(rs.next()) {
		    	  com.add(rs.getString(2));
		      }
		    DTO dto = new DTO(com);
			    request.setAttribute("dto",dto);
			    request.getRequestDispatcher("/Chat.jsp").forward(request, response);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
		       request.setAttribute("null", "Enter you messege");
		       request.getRequestDispatcher("/Chat.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
