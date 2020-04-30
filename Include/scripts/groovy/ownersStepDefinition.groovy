import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.json.JSONObject

import com.kms.katalon.core.configuration.RunConfiguration as RC
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import groovy.json.JsonSlurper as JsonSlurper
import helpers.Utils
import internal.GlobalVariable

class ownersStepDefinition {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */

	def jsonSlurper = new JsonSlurper()

	def	getOwnerResponse
	JSONObject getOwner
	def responseStatus
	Utils newUtil = new Utils()
	def basicToken


	@When("I search for all pet owners")
	def I_search_for_all_pet_owners() {

		def basicToken = generateBasicToken()
		getOwnerResponse= WS.sendRequest(findTestObject('getOwners', [('basicToken') : basicToken]))
		GlobalVariable.basicToken = basicToken
		
	}

	@Then("a list of all owners is displayed")
	def A_list_of_all_owners_is_displayed() {
		def parsedgetOwnerResponse = jsonSlurper.parseText(getOwnerResponse.responseBodyContent)

		if(parsedgetOwnerResponse.size() == null || parsedgetOwnerResponse.size() == 0) {
			KeywordUtil.markFailed("Response is not present")
		}

	}
	def	generateBasicToken() {
		def usernamePassword = GlobalVariable.userName +':'+ GlobalVariable.password
		def token = usernamePassword.bytes.encodeBase64().toString()
		return 'Basic '+ token
	}

	@Given("I want to load all the owner test data in (.*)")
	def I_want_to_load_all_the_owner_test_data(String scenario) {
		getOwner =newUtil.getOwnerData(RC.getExecutionProfile().toString(), scenario.toString())
	}

	@When("I send the request for adding new owner")
	def I_send_the_request_for_adding_new_owner() {

		def addOwnerRequest = findTestObject('AddOwner', [('basicToken') : GlobalVariable.basicToken, ('firstName') : getOwner['firstName'], ('lastName') : getOwner[
				'lastName'], ('address') : getOwner['address'], ('city') : getOwner['city'], ('telephone') : getOwner['telephone']])
		responseStatus = WS.sendRequest(addOwnerRequest)
		getOwnerResponse = jsonSlurper.parseText(responseStatus.responseBodyContent)
		GlobalVariable.Id = getOwnerResponse.id
 
	}

	@Then("I verify the response is {int}")
	def I_verify_the_response_is_successful(int code) {
		WS.verifyResponseStatusCode(responseStatus, code)
	}
	
	@When("I send the request for get new owner")
	def I_send_the_request_for_get_new_owner() {

		def getOwnerRequest = findTestObject('getOwner', [('basicToken') : GlobalVariable.basicToken, ('ownerId') : GlobalVariable.Id])
		
			responseStatus = WS.sendRequest(getOwnerRequest)
		
	}
		
	@Then("I verify the response is the same as test data")
	def I_verify_the_response_is_the_same_as_test_data() {
		getOwnerResponse = jsonSlurper.parseText(responseStatus.responseBodyContent)
		assert getOwner['firstName'] == getOwnerResponse.firstName
		assert getOwner['lastName'] == getOwnerResponse.lastName
		assert getOwner['address'] == getOwnerResponse.address
		assert getOwner['city'] == getOwnerResponse.city
		assert getOwner['telephone'] == getOwnerResponse.telephone	
	}
	
	
	@When("I send the request for update telephone to be \"([^\"]*)\"")
	public void I_send_the_request_for_update_telephone(String telephone) {
	def updateOwnerRequest = findTestObject('Update Owner', [('basicToken') : GlobalVariable.basicToken, ('firstName') : getOwner['firstName'], ('lastName') : getOwner['lastName'], ('address') : getOwner['address']
			, ('city') : getOwner['city'], ('telephone') : telephone, ('id') : GlobalVariable.Id])
	responseStatus	= WS.sendRequest(updateOwnerRequest)
	WS.comment("Sending POST request: ${updateOwnerRequest.getHttpBody()}")
		
	}
	
	@When("I send the request for delete owner")
	def I_send_the_request_for_delete_owner() {

		def deleteOwnerRequest = findTestObject('DeleteOwner', [('basicToken') : GlobalVariable.basicToken, ('ownerId') : GlobalVariable.Id])
			responseStatus = WS.sendRequest(deleteOwnerRequest)
		
	}
	
	
	@When("I send the request for get new owner without auth")
	def I_send_the_request_for_get_new_owner_without_auth() {
		def getOwnerRequest = findTestObject('getOwner', [('basicToken') : '', ('ownerId') : GlobalVariable.Id])
			responseStatus = WS.sendRequest(getOwnerRequest)
	}
		
}