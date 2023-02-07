package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class headersDemo {

//	@Test
	void testHeaders() {
		
		given()
		
		
		.when()
		.get("https://www.google.com/")
		
		.then()
		.header("Content-Type", "text/html; charset=ISO-8859-1")
		.header("Content-Encoding", "gzip")
		.header("Server", "gws");
	}
	
	@Test
	void getHeaders() {
		
		Response res = given()
				.when()
				.get("https://www.google.com/");
		
		// get single header info
		String header_value = res.getHeader("Content-Type");
		System.out.println("The value of Content-Type header : "+header_value);
		
		// get all headers info
		Headers headers_values = res.getHeaders();
		
		for(Header h : headers_values) {
			System.out.println(h.getName()+"   "+h.getValue());
		}
	}
	
}
