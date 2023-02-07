package day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class parsingXmlResponse {
	
//	@Test
	void testXmlResponseApproach1 () {
		
		given()
		.when()
		.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
		.statusCode(200)
		.header("Content-Type","application/xml; charset=utf-8")
		.body("TravelerinformationResponse.page",equalTo("1"))
		.body("TravelerinformationResponse.travelers.Travelerinformation.name[0]", equalTo("Developer"));
		
	}
	
	@Test
	void testXmlResponseApproach2() {
		
		Response res = given()
				.when()
				.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
		
		String pageNum = res.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNum, "1");
		String travelerName = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		Assert.assertEquals(travelerName, "Developers");
	}

}
