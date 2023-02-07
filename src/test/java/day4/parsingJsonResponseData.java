package day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class parsingJsonResponseData {
	
//	@Test
	void testJsonResponse1() {
		
		given()
		.contentType(ContentType.JSON)
		.when()
		.get("http://localhost:3000/store")
		.then()
		.statusCode(200)
		.header("Content-Type","application/json; charset=utf-8")
		.body("books[2].title", equalTo("God of Lightning"))
		.body("books[4].title", equalTo("Gone Day"))
		.body("books[6].title", equalTo("A Pale Blue Eye"));
	}
	
//	@Test
	void testJsonResponse2() {
		
		Response res = given()
		.contentType(ContentType.JSON)
		.when()
		.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(), 200); // validation 1
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
		String bookName = res.jsonPath().get("books[4].title").toString();
		Assert.assertEquals(bookName,"Gone Day");		
	}
	
	@Test
	void testJsonResponseBody() {
		
		Response res = given()
				.contentType(ContentType.JSON)
				.when()
				.get("http://localhost:3000/store");
		
		// JSON Object class
		
		JSONObject js = new JSONObject(res.asString()); // converting response to JSONobject
		 
		
		System.out.println(js.getJSONArray("books").length());
		
		
		//search for title of the book in json
		boolean status = false;
		
		for(int i=0;i<js.getJSONArray("books").length();i++) {
			
			String bookTitle = js.getJSONArray("books").getJSONObject(i).getString("title");
			
			if(bookTitle.equals("The Hole")) {
				status = true;
				String bookPrice = js.getJSONArray("books").getJSONObject(i).getString("price");
				String bookAuthor = js.getJSONArray("books").getJSONObject(i).getString("author");
				System.out.println("---------");
				System.out.println(bookTitle+"   "+bookPrice+"   "+bookAuthor);
				System.out.println("---------");
				break;
			}
						
		}
		Assert.assertEquals(status, true);
		
		//validate total price of the books
		int totalPrice = 0;
		
		for(int i=0;i<js.getJSONArray("books").length();i++) {
			int price = js.getJSONArray("books").getJSONObject(i).getInt("price");
			totalPrice = totalPrice+price;
		}
		System.out.println("Total Price : "+totalPrice);
		boolean priceDet = totalPrice>100;
		
		Assert.assertEquals(priceDet, true);
	}

}
