package Client;

import java.io.Serializable;

public class DTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMessege() {
		return messege;
	}
	public void setMessege(String messege) {
		this.messege = messege;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getMes() {
			return mes;
	}
	  String date;
	  String messege;
	  String mes = "messege №";
	 
	int number;
	
}
