Feature: GetPageLocations Functionality

  Scenario Outline: GET request of application response code 200
    Given I have select the method "post"
    When I have a base url
    And I set the header
    And I set the body with value from <rowNumber>
    And I send the request
    Then Check Response code 
Examples:
    |rowNumber|
    |  0   |
    
    
    
Scenario: Get Request response code 200
Given I have select the method "get"
When I have a base url
And I set the header
And I send the request
Then Check Response code for all items 


Scenario: Put Request response code 204
Given I have select the method "put"
When I have a base url with id
And I set the header
 And I set the body with some new text
And I send the request
Then check response code 204 


Scenario: Delete Request response code 204
Given I have select the method "deleted"
When I have a base url with id
And I set the header
And I send the request
Then check response code 204 

Scenario: if try to update record that is deleted then response code 404
Given I have select the method "put"
When I have a base url with id
And I set the header
 And I set the body with some new text
And I send the request
Then check response code 404 


Scenario: if try to delete record that is already Deleted Request response code 404
Given I have select the method "deleted"
When I have a base url with id
And I set the header
And I send the request
Then check response code 404 