Feature: Validating place API

@AddPlace
Scenario Outline: Verify if place id added scessfully using AddPlace API
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then API call is success with response status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "GetPlaceAI"
	
Examples:
   |name |language |address |
   |Raju |English  | pune   |
   #|Damu |Spain    | Mumbai |
   
@DeletePlace   
Scenario: Verify if delete place functionality is working

	Given DeletePlace Payload
	When user calls "DeletePlaceAPI" with "POST" http request
	Then API call is success with response status code 200
	And "status" in response body is "OK"