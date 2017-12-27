package Server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
		   ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
		   ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
		   final PrintWriter writer = new PrintWriter(output);
		   String str;
		   DTO dto;
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
		    oos.writeObject(dto);;
		    System.out.print(dto.getMes());
		    System.out.print(dto.getNumber());
		    System.out.println(dto.getMessege());
		    System.out.print(dto.getDate());
		   }
		  } catch (IOException | ClassNotFoundException e) {
		   e.printStackTrace();
		  }
	 }
}