package Chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DTO {
	 Map<Integer,String> com = new HashMap<Integer,String>();
	  public DTO(Map<Integer,String> com2) {
		      this.com=com2;
		  
	  }
	  public Map<Integer,String> getComments(){
		  return com;
	  }
}
