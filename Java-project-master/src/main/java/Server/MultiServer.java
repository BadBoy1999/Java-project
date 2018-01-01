package Server;

import java.io.BufferedReader;
import java.sql.*;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import Client.DTO;

public class MultiServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket s = new ServerSocket(8181);
	       System.out.println("Server Started");
	       try {
	           while (true) {
	              Socket socket = s.accept();
	              new Multserver(socket).start();
	           }
	        }
	        finally {
	           s.close();
	        }

	}

}
class Multserver extends Thread {
	 Socket client;
	 public Multserver(Socket socket) {
	  this.client = socket;
	 }
	 public void run() {
		  try {
		   DataOutputStream output = new DataOutputStream(client.getOutputStream());
//		   InputStream input = client.getInputStream();
//		   byte [] byt = IOUtils.toByteArray(input);
//		   ByteArrayOutputStream bytes = new  ByteArrayOutputStream(byt);
//		   DataInputStream input = new DataInputStream(client.getInputStream());
		   InetAddress str3 = client.getLocalAddress();
		   String ip = str3.toString();
		   ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
		   ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
		   final PrintWriter writer = new PrintWriter(output);
		   String str;
		   DTO dto;
		   int number = 1;
//		   Timer timer = new Timer();
//		   TimerTask time = new TimerTask() {
//		       public void run() {
//		    	Date date = new Date();   
//		        writer.println(date.toString());
//		        writer.flush();
//		  }
//		 };
//		 timer.schedule(time, 5000,5000);
		   while ((dto = (DTO)ois.readObject()) != null) {
		    if (dto.getMessege().equals("!quit")) {
		     client.shutdownInput();
		     client.shutdownOutput();
		     client.close();
		     System.out.println("Server close");
		     break;
		    }
		    dto.setNumber(number);
		    oos.writeObject(dto);;
		    System.out.print(dto.getMes());
		    System.out.println(dto.getNumber());
		    System.out.println(dto.getMessege());
		    System.out.println(dto.getDate());
		    number++;
		    Class.forName("org.sqlite.JDBC");
		    Connection co = DriverManager.getConnection("jdbc:sqlite:C:\\Program Files\\Maven Project\\Java-project-master\\sqlite-dll-win64-x64-3210000\\client.db");
		    String quary = "INSERT INTO user (ip,messege,date) " +"VALUES ('"+ip+"','"+dto.getMessege()+"','"+dto.getDate()+"')";
		    Statement stat = co.createStatement();
		    stat.execute(quary);
		   }
		  } catch (IOException | ClassNotFoundException | SQLException e) {
		   e.printStackTrace();
		  }
	 }
}