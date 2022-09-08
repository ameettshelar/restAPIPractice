package resources;

public enum EnumAPI 
{

	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;
	
	EnumAPI(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
}
