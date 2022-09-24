import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserRead {
	File jsonFile;

	public UserRead(File jsonFile) {
		//super();
		this.jsonFile = jsonFile;
	}
	
	public List<User> userRead() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("User Read-> "+Thread.currentThread().getName());
		ObjectMapper object=new ObjectMapper();
		User[] users=object.readValue(jsonFile, User[].class);
		return Arrays.asList(users);
 	}
	
}
