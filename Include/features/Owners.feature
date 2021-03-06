#Author: sumayya.shahzad@confiz.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Test Owners

  @Valid
  Scenario: list all owners
    When I search for all pet owners
    Then a list of all owners is displayed

  @Valid
  Scenario Outline: Adding new owner in database
    Given I want to load all the owner test data in <scenario>
    When I send the request for adding new owner
    Then I verify the response is 201

    Examples: 
      | scenario |
      |       01 |

  @Valid
  Scenario Outline: getting new owner in database
    Given I want to load all the owner test data in <scenario>
    When I send the request for get new owner
    Then I verify the response is 200
    And I verify the response is the same as test data

    Examples: 
      | scenario |
      |       01 |

  @Valid
  Scenario Outline: updating new owner in database
    Given I want to load all the owner test data in <scenario>
    When I send the request for update telephone to be "090078601"
    Then I verify the response is 204

    Examples: 
      | scenario |
      |       01 |

  @Valid
  Scenario: deleting the new owner in database
    When I send the request for delete owner
    Then I verify the response is 204

  @InValid
  Scenario: get owner which is deleted
    When I send the request for get new owner
    Then I verify the response is 404

  @InValid
  Scenario: send request without basic auth
    When I send the request for get new owner without auth
    Then I verify the response is 401

  @InValid
  Scenario Outline: getting new owner in database with invalid telephone
    Given I want to load all the owner test data in <scenario>
    When I send the request for update telephone to be "+92090078601"
    Then I verify the response is 400

    Examples: 
      | scenario |
      |       01 |
