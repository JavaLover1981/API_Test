package com.rest.tests;

import org.testng.annotations.Test;

import junit.framework.Assert;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest2 {
	String userEndPoint = "https://reqres.in/api/users";
	String registerEndpoint = "https://reqres.in/api/register";
	String loginEndpoint = "https://reqres.in/api/login";

	@Test
	public void getUserTest() {
		given().when().get(userEndPoint).then().statusCode(200);
	}

	@Test
	public void checkUserResponse() {
		String response = given().when().get(userEndPoint).andReturn().asString();
		System.out.println(response);
	}
	
	@Test
	public void registerTest() {
		// set condition
		given().body("{'email': 'eve.holt@reqres.in','password': 'pistol'}")
				// send request
				.when()
				.post(registerEndpoint)
				.then()
				// validate status code
				.statusCode(200)
				.and()
				// validate body
				// validate token is not null
				.body("token", notNullValue())
				.and()
				.body("id", notNullValue())
				// validate header
				.header("Content-Type", containsString("json"));
	}
}