package day2;

import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 
 Diff ways we create request body

1)Hashmap
2)using org.json
3)using POJO (Plain Old Java Object)
4)using external json file
 
 */

public class waysToCreatePostReqBody {
	
//	Post request body using HashMap
	
//	@Test(priority = 1)
	void testPostUsingHashMap() {
		HashMap data = new HashMap();
		
		data.put("name", "Ben");
		data.put("location", "chennai");
		data.put("phone", "5465451256");
		
		String courseArr[] = {"Java","Selenium"};
		
		data.put("courses", courseArr);
		
		given()
		.contentType("application/json").body(data)
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name",equalTo("Ben"))
		.body("location",equalTo("chennai"))
		.body("phone",equalTo("5465451256"))
		.body("courses[0]",equalTo("Java"))
		.body("courses[1]",equalTo("Selenium"))
		.header("Content-type","application/json; charset=utf-8")
		.log().all();
	}
	
//	Post request using org.json library
//	@Test(priority = 1)
	void testPostUsingJsonLibrary() {
		JSONObject data = new JSONObject();
		
		data.put("name", "Ben");
		data.put("location", "chennai");
		data.put("phone", "5465451256");
		
		String courseArr[] = {"Java","Selenium"};
		
		data.put("courses", courseArr);
		
		given()
		.contentType("application/json").body(data.toString())
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name",equalTo("Ben"))
		.body("location",equalTo("chennai"))
		.body("phone",equalTo("5465451256"))
		.body("courses[0]",equalTo("Java"))
		.body("courses[1]",equalTo("Selenium"))
		.header("Content-type","application/json; charset=utf-8")
		.log().all();
	}
	
//	Post request body using POJO class
	@Test(priority = 1)
	void testPostUsingPojoClass() {
		
		Pojo_PostRequest pojo = new Pojo_PostRequest();
		
		pojo.setName("Scott");
		pojo.setLocation("chennai");
		pojo.setPhone("5462152354");
		
		String coursesArr[] = {"Java","C++"};
		
		pojo.setCourses(coursesArr);
		
		given()
		.contentType("application/json").body(pojo)
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("chennai"))
		.body("phone",equalTo("5462152354"))
		.body("courses[0]",equalTo("Java"))
		.body("courses[1]",equalTo("C++"))
		.header("Content-type","application/json; charset=utf-8")
		.log().all();
		
	}
	
	
//	Delete a student detail
	@Test(priority = 2)
	void testDelete() {
		given()
		.when()
		.delete("http://localhost:3000/students/4")
		.then()
		.statusCode(200).log().all();
	}
	
	

}
