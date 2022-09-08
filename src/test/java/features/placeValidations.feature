Feature: Validating Place Api's
@AddPlace @Regression
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
	Given Add Place payload "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "Post" http request
	Then the API Call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "GetPlaceAPI"
	
Examples:
|name    |language  |address  |
|AA House|English-EN|101 Sai  |
#|BB House|Spanish-SN|102 Swami|

@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working
	Given DeletPlace Payload
	When user calls "DeletePlaceAPI" with "Post" http request
	Then the API Call got success with status code 200
	And "status" in response body is "OK"





