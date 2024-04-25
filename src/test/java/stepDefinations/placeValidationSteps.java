package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class placeValidationSteps extends Utils{

	RequestSpecification req;
	RequestSpecification reqSpec;
	Response response;
	TestDataBuild td = new TestDataBuild();
	public static String place_Id;
	
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		reqSpec= given().spec(requestSpecification()).body(td.addPlacePayload(name,language,address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resources, String method) {
		APIResources resourceAPI = APIResources.valueOf(resources);
		
		if(method.equalsIgnoreCase("POST"))
			response = reqSpec.when().post(resourceAPI.getResources());
		else
			response = reqSpec.when().get(resourceAPI.getResources());
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	   
		reqSpec= given().spec(requestSpecification()).body(td.deletePlacePayload(place_Id));
	}

	
	@Then("API call is success with response status code {int}")
	public void api_call_is_success_with_response_status_code(int statusCode ) {
	    
		assertEquals(response.statusCode(),statusCode);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String ExpectedValue) {
		String res = response.asString();
		JsonPath js = new JsonPath(res);
		assertEquals(js.get(key).toString(),ExpectedValue);		
	    
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_Id = getJsonPath(response,"place_id");
		reqSpec= given().spec(requestSpecification()).queryParam("place_id", place_Id);
		user_calls_with_http_request(resource,"GET");
		String actualName =getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
	}



}
