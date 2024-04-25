package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{

		placeValidationSteps pc = new placeValidationSteps();
		
		if (placeValidationSteps.place_Id==null)
		{
			pc.add_place_payload_with("Sam", "French", "Asia");
			pc.user_calls_with_http_request("AddPlaceAPI", "POST");
			pc.verify_place_id_created_maps_to_using("Sam", "GetPlaceAI");
		}
	

	}

}
