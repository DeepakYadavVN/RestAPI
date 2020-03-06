package com.ResuableMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResuableClass {

	public JsonPath rawsToJson(Response res) {
		//TODO: MASTI
		String ResponseString = res.asString();
		JsonPath js = new JsonPath(ResponseString);
		return js;
	}

}
