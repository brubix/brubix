@skip
Feature: Upload documents for school

  Scenario: Upload KYC documents for school
    Given the user provided school name - "ABC school" , school id - "abc_school" and below addresses
      | first line     | second line | third line  | state | country | pin    |
      | HSR 3rd sector | BDA complex | BDA complex | KAR   | IND     | 560101 |
      | Texas city 1   | Texas       | Texas       | TXS   | USA     | 765012 |
    When the user creates school
    Then a school code is generated
    And the user has uploaded below document
      | type            | number         | document             |
      | Aadhaar         | 123456789      | aadhaar.pdf          |
      | Driving license | 12345678912345 | driving-license.jpeg |
    Then the response status should be "NO_CONTENT"


  Scenario: Upload profile picture of school
    Given the user provided school name - "ABC school" , school id - "abc_school" and below addresses
      | first line     | second line | third line  | state | country | pin    |
      | HSR 3rd sector | BDA complex | BDA complex | KAR   | IND     | 560101 |
      | Texas city 1   | Texas       | Texas       | TXS   | USA     | 765012 |
    When the user creates school
    Then a school code is generated
    And the user has uploaded below document
      | type    | number | document        |
      | Profile |        | school-logo.jpg |
    Then the response status should be "NO_CONTENT"

  #Negative scenarios
  Scenario: Upload KYC documents without document number
    Given the user provided school name - "ABC school" , school id - "abc_school" and below addresses
      | first line     | second line | third line  | state | country | pin    |
      | HSR 3rd sector | BDA complex | BDA complex | KAR   | IND     | 560101 |
      | Texas city 1   | Texas       | Texas       | TXS   | USA     | 765012 |
    When the user creates school
    Then a school code is generated
    And the user has uploaded below document
      | type            | number         | document             |
      | Aadhaar         |                | aadhaar.pdf          |
      | Driving license | 12345678912345 | driving-license.jpeg |
    Then the user should get error as "Correct document details or attachments are not provided"


  Scenario: Upload documents without attachments
    Given the user provided school name - "ABC school" , school id - "abc_school" and below addresses
      | first line     | second line | third line  | state | country | pin    |
      | HSR 3rd sector | BDA complex | BDA complex | KAR   | IND     | 560101 |
      | Texas city 1   | Texas       | Texas       | TXS   | USA     | 765012 |
    When the user creates school
    Then a school code is generated
    And the user has uploaded below document
      | type            | number         |
      | Aadhaar         | 123456789      |
      | Driving license | 12345678912345 |
      | Profile         |                |
    Then the user should get error as "Correct document details or attachments are not provided"