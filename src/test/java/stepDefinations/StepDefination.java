package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Assert.*;


import pojo.AddPlace;
import pojo.Location;
import resources.EnumAPI;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils  {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	ResponseSpecification resspec1;
	Response response;
	TestDataBuild tdb=new TestDataBuild();
	EnumAPI enumAPI;
	static String place_id;
	
	@Given("Add Place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException
	{
		res=given().spec(requestSpecification())
		.body(tdb.addPlacePayload(name, language, address));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod)  {
		
		enumAPI=EnumAPI.valueOf(resource);
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(httpMethod.toUpperCase().equals("POST"))
		{
			response=res.when().post(enumAPI.getResource());
		}
		else if(httpMethod.toUpperCase().equals("GET"))
		{
			response=res.when().get(enumAPI.getResource());
		}
		else
		{
			response=res.when().delete(enumAPI.getResource());
		}
	}
	
	@Then("the API Call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		
		//response=then().log().all().spec(resspec).extract().response();
		
		assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expValue) {
	    
	    assertEquals(getResponseKeyValue(response, key), expValue);
	    
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expName, String resource) throws IOException {
	    
		place_id=getResponseKeyValue(response, "place_id");
		res=given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actName=getResponseKeyValue(response, "name");
		assertEquals(expName, actName);
	}

	@Given("DeletPlace Payload")
	public void delet_place_payload() throws IOException {
	    
		res=given().spec(requestSpecification())
				.body(tdb.getDeletePlacePayload(place_id));
	}

}
