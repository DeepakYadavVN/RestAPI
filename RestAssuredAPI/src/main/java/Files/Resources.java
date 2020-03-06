package Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class Resources {

	
	//In this file we can mention any request just like we define operations-Delete,ADD,POST
	//Create Methods.
	public static String placePostData() {
		String res = "maps/api/place/add/json3";
		return res;
	}
	
	public static String deletePostData() {
		String res = "maps/api/place/delete/json3";
		return res;
	}
	
	//XML Raw format convert into String format.
	public static String GenerateStringFromResources(String path) throws IOException{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	
}
 