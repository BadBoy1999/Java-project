package ru.Chat;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DTO
{	;
	ArrayList<String> com = new ArrayList<String>();
  public DTO(ResultSet rs) throws SQLException {
	      while(rs.next()) {
	    	  com.add(rs.getString(2));
	      }
	  
  }
  public ArrayList<String> show(){
	  return com;
  }
}
