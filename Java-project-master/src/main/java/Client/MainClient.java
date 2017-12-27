package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainClient {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		try (Socket socket = new Socket()){
		      socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8181));
//		      DataInputStream input = new DataInputStream(socket.getInputStream());
//		      BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf8"));
		      ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		      ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		      boolean flag = true;
		      ClientInput inputTh = new ClientInput(ois);
		      inputTh.start();
		      ClientOutput outputTh = new ClientOutput(oos);
		      outputTh.start();
		      while(flag) {
		    	  Thread.sleep(1000);
		    	  if(!inputTh.isAlive()&&!outputTh.isAlive()) {
		    		  flag = false;
		    	  }
		      }
		      System.out.println("Connetcion closed");
		     }

	}

}
