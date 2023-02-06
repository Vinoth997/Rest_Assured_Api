package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class cookiesDemo {
	
//	@Test(priority = 1)
	void testCookies() {
		
		given()
		
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		.cookie("AEC","ARSKqsJb19iI_2PV_0dIjDG1s-r-5SMvVnLx2eqYe3dFx9ATU6o4WfDAEg")
		.log().all();
		
	}
	
	@Test(priority = 2)
	void getCookiesInfo() {
		
		Response res = given()
		.when()
		.get("https://www.google.com/");
		
		//get single cookie information
		
		String cookie_value = res.getCookie("AEC");
		System.out.println("Value of cookie is ======>"+cookie_value);
		
		//get all cookies information
		
		Map<String, String> cookies_values = res.getCookies();
		System.out.println("Keys info : "+cookies_values.keySet());
		
	}

}
