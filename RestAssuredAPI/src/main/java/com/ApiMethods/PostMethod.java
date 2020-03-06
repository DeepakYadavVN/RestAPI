package com.ApiMethods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ResuableMethods.ResuableClass;

import Files.Resources;
import Files.PayLoad;


public class PostMethod {

	Properties prop = new Properties();
	ResuableClass resuableClass = new ResuableClass();

	@BeforeTest
	public void beforeClass() throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(
				"//Users//dy0618//SmartDeviceTestUS//RestAssuredAPI//src//main//java//Files//Env.properties");
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Json format
	@Test
	public void pMethod() {

		RestAssured.baseURI = prop.getProperty("HOST");
		Response res = given().

		//Api key required.
				queryParam("key", prop.getProperty("KEY")).body(PayLoad.payLoadData()).when()
				.post(Resources.placePostData()).then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.and().body("status", equalTo("OK")).extract().response();
		JsonPath js=resuableClass.rawsToJson(res);
		String placeid = js.get("place_id");
		System.out.println(placeid);

		// Take the placeid and then delete.
		given().queryParam("key", prop.getProperty("KEY")).body("{" + "\"place_id\":\"" + placeid + "\"" + "}")
				.post(Resources.deletePostData()).then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("status", equalTo("OK")).extract().response();
	}

	
	// Verify data in XML format
	@Test
	public void pMethodVerifyXMLResponse() throws IOException {
		String Postdata = Resources
				.GenerateStringFromResources("//Users//dy0618//SmartDeviceTestUS//RestAssuredAPI//Xmldata.xml⁩");
		RestAssured.baseURI = prop.getProperty("HOST");
		Response res = given().

		// Api key required.
				queryParam("Key", prop.getProperty("KEY")).body(Postdata).when().post(Resources.placePostData()).then()
				.assertThat().statusCode(200).and().contentType(ContentType.XML).and().body("status", equalTo("OK"))
				.extract().response();

		String Xmlresponse = res.asString();
		System.out.println(Xmlresponse);
		XmlPath xml = new XmlPath(Xmlresponse);
		XmlPath xmldata1 = xml.get("place_id");
		System.out.println(xmldata1);

	}
}
