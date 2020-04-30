import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration as RC
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

def usernamepassword = (GlobalVariable.username + ':') + GlobalVariable.password

def authorization = Base64.getEncoder().encodeToString(usernamepassword.getBytes())

String token = 'Basic ' + authorization

getOwner = CustomKeywords.'helpers.Utils.getOwnerData'(RC.getExecutionProfile(), '01')

def addOwnerRequest = findTestObject('AddOwner', [('basicToken') : token, ('firstName') : getOwner['firstName'], ('lastName') : getOwner[
        'lastName'], ('address') : getOwner['address'], ('city') : getOwner['city'], ('telephone') : getOwner['telephone']])

WS.comment("With body: $addOwnerRequest.getHttpBody()")

responseStatus = WS.sendRequest(addOwnerRequest)

KeywordUtil.logInfo(responseStatus.responseBodyContent)

WS.sendRequest(findTestObject('getOwners'))

WS.sendRequest(findTestObject('getOwners', [('basicToken') : '']))

WS.sendRequest(findTestObject('DeleteOwner', [('basicToken') : '', ('ownerId') : '']))

WS.sendRequest(findTestObject('Update Owner', [('basicToken') : '', ('firstName') : '', ('lastName') : '', ('address') : ''
            , ('city') : '', ('telephone') : '', ('id') : '', ('ownerId') : '']))

WS.sendRequest(findTestObject('Update Owner', [('basicToken') : '', ('firstName') : '', ('lastName') : '', ('address') : ''
            , ('city') : '', ('telephone') : '', ('id') : '']))



