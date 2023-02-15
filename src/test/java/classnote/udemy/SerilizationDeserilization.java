package classnote.udemy;

import com.google.gson.Gson;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import day2.Pojo_PostRequest;

//Pojo  ---- Serilize-->  JSON Object -- de-serilize----->POjo

public class SerilizationDeserilization {

	// Pojo -----> JSON    ( Serilization)
	@Test(priority =1)
	void convertPojo2Json() throws JsonProcessingException
	{
		//created java object using pojo class
		Student stupojo=new Student();    //pojo
		
		stupojo.setName("Scott");
		stupojo.setLocation("France");
		stupojo.setPhone("123456");
		String coursesArr[]= {"C","C++"};
		stupojo.setCourses(coursesArr);
		
		//convert java objet ---> json object ( serilization) by Jackson
		ObjectMapper objMapper=new ObjectMapper();
		String jsondata=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
		System.out.println(jsondata);

		/*convert java objet ---> json object ( serilization) by Gson
		Gson gson=new Gson();
		String student=gson.toJson(stupojo);
		System.out.println(student);*/
	}
	
	// Json -----> Pojo    ( De-Serilization)
		@Test(priority =2)
		void convertJson2Pojo() throws JsonProcessingException
		{
			
			String jsondata="{\r\n"
					+ "  \"name\" : \"Scott\",\r\n"
					+ "  \"location\" : \"France\",\r\n"
					+ "  \"phone\" : \"123456\",\r\n"
					+ "  \"courses\" : [ \"C\", \"C++\" ]\r\n"
					+ "}";
			//convert json data---> Pojo object
			ObjectMapper objMapper=new ObjectMapper();
			Student stupojo=objMapper.readValue(jsondata,Student.class); //convert json to pojo
			/*convert json data---> Pojo object decenterlization by Gson
			Gson ggson=new Gson();
			Student stupojo=ggson.fromJson(jsondata,Student.class);*/

			System.out.println("Name:"+stupojo.getName());
			System.out.println("Location:"+stupojo.getLocation());
			System.out.println("Phone:"+stupojo.getPhone());
			System.out.println("Course 1:"+stupojo.getCourses()[0]);
			System.out.println("Course2:"+stupojo.getCourses()[1]);	
			
			
		}
		
		
		
	
	
	
	
	
	
	
}
