@skip
Feature: Load subjects in system

  Scenario: Load subjects in system
    Given the below subjects available
      | name        | description |
      | Mathematics | Mathematics |
      | Physics     | Physics     |
      | Chemistry   | Chemistry   |
      | Biology     | Biology     |
    When the administrator want to create subjects
    Then the administrator should get below subjects
      | name        | description |
      | Mathematics | Mathematics |
      | Physics     | Physics     |
      | Chemistry   | Chemistry   |
      | Biology     | Biology     |

  #Negative scenarios
  Scenario: Load subjects in system without description
    Given the below subjects available
      | name        | description |
      | Mathematics | Mathematics |
      | Physics     |             |
    When the administrator want to create subjects
    Then the administrator should get error message as "Request payload is malformed or invalid"

  Scenario: Load subjects in system without name / description
    Given the below subjects available
      | name        | description |
      | Mathematics | Mathematics |
      |             |             |
    When the administrator want to create subjects
    Then the administrator should get error message as "Request payload is malformed or invalid"
