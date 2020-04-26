package helpers

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
