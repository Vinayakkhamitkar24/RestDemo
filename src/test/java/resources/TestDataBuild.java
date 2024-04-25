package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String addresss)
	{
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(addresss);
		ap.setLanguage(language);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shoe");
		ap.setTypes(myList);
		ap.setWebsite("https://rahulshettyacademy.com");
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		return ap;
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n"
		+ "		    \"place_id\":\""+placeId+"\"\r\n"
		+ "		}";
		

	}
}
