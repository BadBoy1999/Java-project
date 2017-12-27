package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Scanner;

public class ClientOutput extends Thread {
	ObjectOutputStream oos;
	BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	String str;
	String str1;
	int number = 1;
	public ClientOutput(ObjectOutputStream oos) {
		this.oos = oos;
	}
	public void run() {
		try {
			while ((str = keyboard.readLine() )!=null) {
				System.out.println(str);
			      Date date = new Date();
			      str1 = date.toString();
			      DTO dto = new DTO();
			      dto.setDate(str1);
			      dto.setMessege(str);
			      dto.setNumber(number);
			      oos.writeObject(dto);
			      number++;
			      }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
