package com.test.Stepdefinition;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Exchange_Rates {
	private static final String BASE_URL = "https://ratesapi.io/documentation/";

	private static String token;
	private static Response response;
	private static String jsonString;
	
	WebDriver driver=new ChromeDriver();


	@Given("^User is on the API landing page$")
	public void userOnAPILandingPage() {
		driver.get(BASE_URL);
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		Response response = request.get("/api");
		String jsonString = response.asString();
		token = JsonPath.from(jsonString).get("token");

	}

	@Given("API is available")
	public void APIisAvailable() {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		response = request.get("api/latest?base=USD&symbols=GBP HTTP/2");

		jsonString = response.asString();	   
	}
	
	@Then("Verify the success status of response")
	public void AssertSuccsessStatus() {
		Assert.assertEquals(201, response.getStatusCode());
	}

	@Then("Verify the response for incorrect url")
	public void verifyStatusIncorrectUrl() {
		Assert.assertEquals(410, response.getStatusCode());


	}

}