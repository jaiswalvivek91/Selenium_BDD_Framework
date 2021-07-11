Feature: This feature will validate API




  Scenario: Check the Healthily Chat API's response code
    Given I have Chat Bot API
    When I get response for Chat Bot API
    Then I verify the Response code



  Scenario: Check the Healthily Chat API with predefined values
    Given I have Healthily Chat Bot
    When I enter user name as "Test1"
    Then I select an option or enter text as
      | options   |
      | Hello!    |
      | OK        |
      | I accept  |
      | OK        |
      | Check symptoms  |
      | Me        |
      | fever     |
      | Male        |
      | 1991      |
      | Information        |
      | Thanks     |
      | Yes        |



  Scenario: Check the Healthily Chat API with predefined values
    Given I have Healthily Chat Bot
#    When I enter user name as "Test14"
    Then I Type "Hello!"
    And I click on "OK" for GDPR
    And I click on "I accept" the GDPR terms
    And I click on "OK" to confirm
    And I select "Check symptoms" in response to What would you like to do?
    And I select "Me" in response to Who is the symptom check for?
    And I type my symptons as "Fever" in text box
    And I select my gender as "Male"
    And I select my year of birth as "1990"
    And I select "Information" in response to What would help you most
    And I click on "Thanks" after I get useful information
    And I click on "Yes" to acknowledge that the information was useful



  @APItest
  Scenario: First Opt for Health tests and cancel the conversation and then select Check symptoms
    Given I have Healthily Chat Bot
#    When I enter user name as "Test14"
    Then I Type "Hello!"
    And I click on "OK" for GDPR
    And I click on "I accept" the GDPR terms
    And I click on "OK" to confirm
    And I select "Health tests" in response to What would you like to do?
    And I select "Self-Assessments" in response
    Then I exit the consulation and go back to What would you like to do? page
    And I select "Check symptoms" in response to What would you like to do?
    And I select "Me" in response to Who is the symptom check for?
    And I type my symptons as "Fever" in text box
    And I select my gender as "Male"
    And I select my year of birth as "1990"
    And I select "Information" in response to What would help you most
    And I click on "Thanks" after I get useful information
    And I click on "Yes" to acknowledge that the information was useful



  Scenario: Check the Healthily Chat API and interact with it for number of times
    Given I have Healthily Chat Bot
#    When I enter user name as "Test6"
    Then I interact with chat bot for 12 times

