package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks 
{

	@Before("@DeletePlace")
	public void beforeDelete() throws IOException
	{
		if (StepDefination.place_id==null)
		{
			StepDefination sd = new StepDefination();
			sd.add_place_payload("Shetty", "Japnese", "303 CityOne");
			sd.user_calls_with_http_request("AddPlaceAPI", "Post");
			sd.verify_place_id_created_maps_to_using("Shetty", "GetPlaceAPI");
		}
	}
}
