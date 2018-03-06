package Chat;

import java.util.ArrayList;

public class DTO {
	ArrayList<String> comments = new ArrayList<String>();
	  public DTO(ArrayList<String> com2) {
		      this.comments=com2;
		  
	  }
	  public ArrayList<String> getComments(){
		  return comments;
	  }
}
