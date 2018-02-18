package ru.Chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

@WebServlet("/Chat")
public class Chat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String str = request.getParameter("text");
	  	String url = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false";
	  	String name = "root";
	  	String password = "root";
	  	if(!str.isEmpty()) {
	try {   
		    
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection con;
		    con = DriverManager.getConnection(url, name, password);
		    String quar = "INSERT INTO mydb.chat (text) " + "VALUES ('"+str+"')";
		    Statement sta = con.createStatement();
		    sta.execute(quar);
		    ResultSet result = sta.executeQuery("SELECT * FROM chat");
		    DTO dto = new DTO(result);
		    int size = 0;
		    result.last();
		    size = result.getRow();
		    if(size>100) {
		    request.setAttribute("dto",dto);
		    }
		    request.getRequestDispatcher("/NewFile.jsp").forward(request, response);
	} catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  	}
	}
	

}
