package cucmbertestlearning
import com.kms.katalon.core.configuration.RunConfiguration as RC
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder as RestRequestObjectBuilder
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable

class StepDef {

	String FirstName;
	String LastName;
	String executionProfile;
	File f;
	ResponseObject response;
	def supl;
	Map resp;
	Map ad;
	RestRequestObjectBuilder Builder= new RestRequestObjectBuilder();
	def zunaa;
	def asd;
	def Respon;
	@Before
	public void before() {

		executionProfile = RC.getExecutionProfile()
		print (executionProfile);
		zunaa=new jsonfilereading().mappingff();
		asd=zunaa.getFeatureData().get('sd')
		//print(asd)
	}


	@Given("I have select the method {string}")
	public void i_have_select_the_method(String string) {
		// Write code here that turns the phrase above into concrete actions
		new requestbuilding().setme(Builder,string);

	}



	@When("I have a base url")
	public void i_have_a_base_url() {
		// Write code here that turns the phrase above into concrete actions
		new requestbuilding().requewithurl(Builder, GlobalVariable.URL)
	}

	@When("I set the header")
	public void i_set_the_header() {
		// Write code here that turns the phrase above into concrete actions
		new requestbuilding().httpheaderset(Builder)
	}

	@When("I set the body with value from {int}")
	public void i_set_the_body(int a) {
		// Write code here that turns the phrase above into concrete actions
		FirstName=asd.get(executionProfile).get(a).get('firstName')

		LastName=asd.get(executionProfile).get(a).get('lastName')
		//print(FirstName)
		String innerBodyContentMap = ' {"firstName": "'+FirstName+'","lastName": "'+LastName+'","specialties": [{"id": 2,"name":"test"}]}'
		new requestbuilding().stbody(Builder, innerBodyContentMap)
	}

	@When("I send the request")
	public void i_send_the_request() {
		// Write code here that turns the phrase above into concrete actions
		RequestObject r=new requestbuilding().sendresq(Builder)
		response = WS.sendRequest(r)


	}

	@Then("Check Response code")
	public void check_Response_code() {
		// Write code here that turns the phrase above into concrete actions
		JsonSlurper js = new JsonSlurper()
		Respon=js.parseText(response.responseText)
		GlobalVariable.ID=Respon.id;
		print("A new user is created with id:  "+GlobalVariable.ID+"\n")
	}


	@Then("Check Response code for all items")
	public void check_Response_code_for_all_items() {
		// Write code here that turns the phrase above into concrete actions
		JsonSlurper js = new JsonSlurper()
		Respon=js.parseText(response.responseText)
		print("All User that is added so far:"+Respon+"\n")
	}

	@When("I have a base url with id")
	public void i_have_a_base_url_with_id() {
		// Write code here that turns the phrase above into concrete actions

		new requestbuilding().requewithurlpara(Builder, GlobalVariable.URL, GlobalVariable.ID)
	}

	@When("I set the body with some new text")
	public void i_set_the_body_with_some_new_text() {
		// Write code here that turns the phrase above into concrete actions
		String innerBodyContentMap = ' {"firstName": "Zuni","lastName": "karachi","specialties": [{"id": 2,"name":"test"}]}'
		new requestbuilding().stbody(Builder, innerBodyContentMap)
	}

	@Then("check response code {int}")
	public void check_response_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assert response.getStatusCode()==int1
		print("The Response code is :"+response.getStatusCode() +"\n")
	}
}
