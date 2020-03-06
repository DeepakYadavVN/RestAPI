package com.ApiMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Properties;

import org.testng.annotations.Test;

import com.ResuableMethods.ResuableClass;

import Files.Resources;



public class GetMethod {
	
		// Google Api =AIzaSyC1rmY6r3C5np8m8RV5y0X2Mlwa4UOuOTc
		Resources resoiurces = new Resources();
		ResuableClass resuable = new ResuableClass();
		Properties prop = new Properties();
		
	
	
		@Test
		public void test() {
			RestAssured.baseURI = prop.getProperty("HOST");
			
			// BaseURI
					  //RestAssured.baseURI = "https://maps.googleapis.com";
					  Response res = given().
					
					given().

							param("location", "-33.8670522").param("key", prop.getProperty("KEY"))
							//when means to hit the requesttype.
							.param("radius", "500").when().
							get("/maps/api/place/nearbysearch/json").then().assertThat().statusCode(200).contentType(ContentType.JSON)
							.and().body("status", equalTo("OK")).extract().response();
					JsonPath js=resuable.rawsToJson(res);
					System.out.println(js);
					
	}

}
