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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import com.kms.katalon.core.testdata.InternalData

public class TestDataUtil {

	InternalData testData
	int Rows
	int Cols

	public TestDataUtil(String dataFilePath){
		testData = findTestData(dataFilePath)
		Rows = testData.getRowNumbers()
		Cols = testData.getColumnNumbers()
	}

	public String getTestDataValue(int rowIndex, String columnName) {

		String returnValue = ""

		if (rowIndex <= 0)
			return returnValue

		try {
			returnValue = testData.getValue(columnName, rowIndex)
		}
		catch(IOException io){
			returnValue = ""
		}

		return returnValue
	}

	public int getRowIndex(String columnName, String columnValue){

		for (int i = 1; i <= Rows; i++){
			if (testData.getValue(columnName, i) == columnValue)
				return i
		}

		return -1
	}
}
