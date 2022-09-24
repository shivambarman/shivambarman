import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadUser implements Runnable {

	File file;
	public ReadUser(File jsonFile) {
		// TODO Auto-generated constructor stub
		System.out.println("On constructor");
	
		this.file=jsonFile;
		System.out.println(file.canRead());
	}
	@Override
	public void run() {
		System.out.println("On Run");	
		ObjectMapper object=new ObjectMapper();
		try {
			System.out.println("Reading");	
			List<User> data=Arrays.asList(object.readValue(file, User[].class));
			System.out.println("Thread Name- "+Thread.currentThread().getName());
			data.stream().forEach(System.out::println);
			//System.out.println(data);
		} catch (JsonParseException e) {
		
			e.printStackTrace();
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println("End");
		
		
	}
	
}
