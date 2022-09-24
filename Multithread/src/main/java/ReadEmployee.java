import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadEmployee  {

	File jsonFile;
	public ReadEmployee(File jsonFile) {
		// TODO Auto-generated constructor stub
		//System.out.println("On constructor");
	
		this.jsonFile=jsonFile;
		System.out.println(jsonFile.canRead());
	}
	public List<Employee> userRead() throws JsonParseException, JsonMappingException, IOException {
		System.out.println("User Read-> "+Thread.currentThread().getName());
		ObjectMapper object=new ObjectMapper();
		Employee[] users=object.readValue(jsonFile, Employee[].class);
		return Arrays.asList(users);
 	}
}
