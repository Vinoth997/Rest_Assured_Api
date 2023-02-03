package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
 
 given()
   content type, set cookies, add auth, add parameter, set header info etc
  
 when()
   all req - get,put,post,delete
   
 then()
   validate status code, extract response, extract headers cookies & response body
 
 
 */

public class HTTPRequests {
	
	int id;
	
	@Test(priority = 1)
	void getListUsers() {
		
		given()
		
		.when()
		  .get("https://reqres.in/api/users?page=2")
		
		.then()
		 .statusCode(200)
		 .body("page",equalTo(2))
		 .log().all();
		
	}
	
	@Test(priority = 2)
	void createUser() {
		
		HashMap data = new HashMap();
		data.put("name", "vinoth");
		data.put("job", "qa tester");
		
		id = given()
		.contentType("application/json").body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		
//		.then()
//		.statusCode(201).log().all();
	}
	
	@Test(priority = 3, dependsOnMethods = { "createUser" })
	void updateUser() {
		
		HashMap data = new HashMap();
		data.put("name", "vinoth kumar");
		data.put("job", "qa");
		
		given()
		.contentType("application/json").body(data)
		
		.when()
		.put("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(200).log().all();
	}
	
	@Test(priority = 4, dependsOnMethods = {"updateUser"})
	void deleteUser() {
		
		given()
		
		.when()
		.delete("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(204).log().all();
		
	}

}
