package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class loggingDemo {

	@Test
	void testLogs() {
		
		given()
		.when()
		.get("https://reqres.in/api/users?page=2&id=8")
		.then()
//		.log().body(); // log only the response body
//		.log().headers(); // log only the headers info
//		.log().cookies(); // log only cookies info
		.log().all();
	}
}
