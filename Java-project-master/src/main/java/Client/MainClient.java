package Client;
import java.io.IOException;
import java.io.InputStream;
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
		      System.out.println("This is a test.");
		      ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		      ClientOutput outputTh = new ClientOutput(oos);
		      outputTh.start();
		      if(outputTh.isAlive()){
		      InputStream input = socket.getInputStream();
		      ObjectInputStream ois = new ObjectInputStream(input);
		      boolean flag = true;
		      ClientInput inputTh = new ClientInput(ois);
		      inputTh.start();
		      while(flag) {
		    	  Thread.sleep(1000);
		    	  if(!inputTh.isAlive()&&!outputTh.isAlive()) {
		    		  flag = false;
		    	  }
		      }
		     }
		      System.out.println("Connetcion closed");
		     }

	}

}
