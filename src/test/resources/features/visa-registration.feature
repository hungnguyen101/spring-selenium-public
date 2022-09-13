Feature: User visa registration feature

  @regression
  Scenario Outline: I should be able to submit visa form
    Given I am on registration form
    When I select my from country "<fromCountry>" and to country "<toCountry>"
    And I enter my dob as "<dateOfBirth>"
    And I enter my name as "<firstName>" and "<lastName>"
    And I enter my contact details as "<email>" and "<phone>"
    And I enter the comment "<comments>"
    And I submit the form
    Then I should see the confirmation number

    Examples:
      | fromCountry | toCountry | dateOfBirth | firstName | lastName | email | phone | comments |
      | Isle of Man | Mali   | 2011-05-31 | Kraig     | Wiza        | Kraig@nobody.com   | 1-000-884-1373 | haha     |
      | Lithuania   | Mexico | 2001-01-01 | Houston   | Kertzmann   | Houston@nobody.com | 284.864.6580   | comment  |
      | Somalia     | Greece | 2004-07-02 | Ruthie    | Stamm       | Ruthie@nobody.com  | 1-209-813-9712 | comment  |


