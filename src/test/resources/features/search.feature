Feature: Google Search

  @regression
  Scenario Outline: I want to search on google site
    Given I am on google site
    When I enter "<keyword>" as a keyword
    And I should see the search results page
    Then I should see at least <count> results

   Examples:
     | keyword  | count |
     | selenium | 20    |
     | java     | 50    |
     | spring   | 100   |



