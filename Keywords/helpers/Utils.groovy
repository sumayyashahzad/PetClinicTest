package helpers

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.json.JSONObject
import internal.GlobalVariable

public class Utils {
	def getSpecialtyData(String environment, String scenario){
		JSONObject specialty
		TestDataUtil td = new TestDataUtil(GlobalVariable.InternalDataFile)

		int envDataRowIndex = td.getRowIndex("Environment", environment)
		int scenarioDataRowIndex = td.getRowIndex("Scenario", scenario)
		if (envDataRowIndex < 1 | scenarioDataRowIndex < 1)
			return null

		if(envDataRowIndex == scenarioDataRowIndex ) {
			specialty = new JSONObject()

			specialty.put("specialty", td.getTestDataValue(envDataRowIndex, "Specialty"))

			println specialty
			return specialty
		}
		else
			return null
	}
}
