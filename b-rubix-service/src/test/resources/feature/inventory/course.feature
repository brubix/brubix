Feature: Create courses for a school

  Scenario: Create courses with subjects for a school
    Given the user provided school name - "ABC school" , school id - "abc_school" and below addresses
      | first line     | second line | third line  | state code | country code | pin code |
      | HSR 3rd sector | BDA complex | BDA complex | KAR        | IND          | 560101   |
    When the user creates school
    Then a school code is generated
    And the user creates course "Standard 7" for school with below subjects
      | name        | description |
      | Mathematics | Mathematics |
      | Physics     | Physics     |
      | Chemistry   | Chemistry   |
      | Biology     | Biology     |
    And the user creates course "Standard 8" for school with below subjects
      | name        | description |
      | Mathematics | Mathematics |
      | Physics     | Physics     |
      | Chemistry   | Chemistry   |
    Then the user creates courses for school
    When user finds all courses for school
    Then user should get course "Standard 7" has below subjects
      | name        | description |
      | Mathematics | Mathematics |
      | Physics     | Physics     |
      | Chemistry   | Chemistry   |
      | Biology     | Biology     |
    And user should get course "Standard 8" has below subjects
      | name        | description |
      | Mathematics | Mathematics |
      | Physics     | Physics     |
      | Chemistry   | Chemistry   |

  Scenario: Create courses without subjects for a school
    Given the user provided school name - "DEF school" , school id - "def_school" and below addresses
      | first line     | second line | third line  | state code | country code | pin code |
      | HSR 3rd sector | BDA complex | BDA complex | KAR        | IND          | 560101   |
    When the user creates school
    Then a school code is generated
    And the user creates course "Standard 7" for school with below subjects
      | name | description |
    And the user creates course "Standard 8" for school with below subjects
      | name | description |
    Then the user creates courses for school
    When user finds all courses for school
    Then user should get course "Standard 7" has below subjects
      | name | description |
    And user should get course "Standard 8" has below subjects
      | name | description |

  #Negative scenario
  Scenario: Create courses with subject name without description for a school
    Given the user provided school name - "ABC school" , school id - "abc_school" and below addresses
      | first line     | second line | third line  | state code | country code | pin code |
      | HSR 3rd sector | BDA complex | BDA complex | KAR        | IND          | 560101   |
    When the user creates school
    Then a school code is generated
    And the user creates course "Standard 7" for school with below subjects
      | name        | description |
      | Mathematics |             |
    Then the user creates courses for school
    Then the user should get error as "Request payload is malformed or invalid"

  Scenario: Create courses with subject description / without subject name
    Given the user provided school name - "ABC school" , school id - "abc_school" and below addresses
      | first line     | second line | third line  | state code | country code | pin code |
      | HSR 3rd sector | BDA complex | BDA complex | KAR        | IND          | 560101   |
    When the user creates school
    Then a school code is generated
    And the user creates course "Standard 7" for school with below subjects
      | name | description |
      |      |             |
    Then the user creates courses for school
    Then the user should get error as "Request payload is malformed or invalid"

  Scenario: Create courses for a school not exist in system
    Given the user creates course "Standard 7" for school "SCHL0001" with below subjects
      | name        | description |
      | Mathematics | Mathematics |
      | Physics     | Physics     |
    When the user creates courses for school
    Then the user should get error as "School code is not found in system"

  Scenario: Create courses with invalid subjects
    Given the user provided school name - "ABC school" , school id - "abc_school" and below addresses
      | first line     | second line | third line  | state code | country code | pin code |
      | HSR 3rd sector | BDA complex | BDA complex | KAR        | IND          | 560101   |
    When the user creates school
    Then a school code is generated
    And the user creates course "Standard 7" for school with below subjects
      | name                 | description          |
      | Mathematics          | Mathematics          |
      | Physics              | Physics              |
      | Chemistry            | Chemistry            |
      | Astronomical Science | Astronomical Science |
    When the user creates courses for school
    Then the user should get error as "Subject is not found in system"