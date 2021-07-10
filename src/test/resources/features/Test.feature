Feature: This feature will validate API

#  Background: User generates token for Authorisation
#    Given I am an authorized user
#
#
#

  Scenario: the Authorized user can Add and Remove a book.
    Given A list of books are available
    When I add a book to my reading list
#    Then The book is added
    When I remove a book from my reading list
#    Then The book is removed



#
#  Scenario: Check the Healthily Chat API
#    Given I have Chat Bot API
#    When I get response for Chat Bot API
#    Then I verify the Response code



  Scenario: Check the Healthily Chat API
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




  @APItest
  Scenario: Check the Healthily Chat API
    Given I have Healthily Chat Bot
#    When I enter user name as "Test6"
    Then I interact with chat bot for 12 times

