package Client;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientInput extends Thread {
 	ObjectInputStream ois;
      DTO DTO;
	
	public ClientInput(ObjectInputStream ois) {
		// TODO Auto-generated constructor stub
		this.ois = ois;
	}

	public void run() {
	  try {
	   while((DTO  = (DTO)ois.readObject())!=null) {
	    System.out.println(DTO.date);
	    System.out.println(DTO.messege);
	    System.out.println(DTO.mes);
	    System.out.print(DTO.number);
	    System.out.println("otvet");
	   }
	  } catch (ClassNotFoundException | IOException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	 }
	}

