import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.configuration.RunConfiguration as RC
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil
import org.json.JSONObject
import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import helpers.Utils


class specialtyStepDefinition {
	/**
	 * The step definitions below match with Katalon sample Gherkin steps
	 */
	JSONObject getSpecialty
	def responseStatus
	Utils newUtil = new Utils()
	@Given("I want to load all the test data in (.*)")
	def I_want_to_write_a_step_with_name(String scenario) {
		getSpecialty =newUtil.getSpecialtyData(RC.getExecutionProfile().toString(), scenario.toString())
	}

	@When("I send the request for adding new specialty")
	def I_send_the_request_for_adding_new_specialty() {

		println getSpecialty["specialty"]
		def	addSpecialtyRequest= findTestObject('Add Specialty', [('id') : 2, ('name') : getSpecialty["specialty"].toString()])
		WS.comment("Sending POST request: ${addSpecialtyRequest.getHttpBody()}")
		responseStatus = WS.sendRequest(addSpecialtyRequest)
	}

	@Then("I verify the response is successful")
	def I_verify_the_response_is_successful() {
		WS.verifyResponseStatusCode(responseStatus, 201)
	}
}