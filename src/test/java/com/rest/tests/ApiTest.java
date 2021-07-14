package com.rest.tests;

import org.testng.annotations.Test;

import junit.framework.Assert;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {
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

//	@Test
//	public void checkNameData() {
//		int name = given().
//				when().get(userEndPoint).
//				then().extract().jsonPath().
//				getInt("data[1]");
//		System.out.println(name);
//	}
	@Test
	public void postUserTest() {
		given().body("{'name':'alimjan','job':'qa'}").when().post(userEndPoint).then().statusCode(201);
	}

	@Test
	public void postUserJobValidationTest() {
		String id = given().body("{'name':'alimjan','job':'qa'}").when().post(userEndPoint).then().extract().jsonPath()
				.getString("id");

		System.out.println(id);
	}

	@Test
	public void getUserWithIdTest() {
//		String response = given().queryParam("id","844").
//				when().post(userEndPoint).andReturn().
//				asString();
//		System.out.println(response);

//		String paramPrint = given().param("id", 2).
//				get(userEndpoint).
//				then().extract().jsonPath().getString("data.first_name");
//		System.out.println(paramPrint);

		String name = given().queryParam("id", 3).when().get(userEndPoint).then().extract().jsonPath()
				.getString("data.first_name");

		Assert.assertEquals(name, "Emma");
		// System.out.println(name);
	}

	@Test
	public void validateFirstNameIsEmma() {
		// set condition
		given().queryParam("id", 3)
				// send request
				.when().get(userEndPoint).then()
				// validate status code
				.statusCode(200).and()
				// validate body
				.body("data.first_name", equalTo("Emma")).and()
				// validate header
				.header("Content-Type", containsString("json"));
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
	
	@Test
	public void loginTest() {
		// set condition
		given().body("{'email': 'eve.holt@reqres.in','password': 'pistol'}")
				// send request
				.when()
				.post(loginEndpoint)
				.then()
				// validate status code
				.statusCode(200)
				.and()
				// validate body
				// validate token is not null
				.body("token", notNullValue())
				// validate header
				.header("Content-Type", containsString("json"));
	}
	
	// Chained API Request
	
	// Register a user and use the registered user to login
	// hit register endPoint to register a user and extract the userID, then
	// use the extracted userID to hit user endPoint with id and validate the name. 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
