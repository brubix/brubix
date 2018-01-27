@skip
Feature: Create school in system

  Scenario: Create school in system without school logo without KYC details / attachments / social details
    Given the user provided school name - "ABC school" , school id - "abc_school" and below addresses
      | first line     | second line | third line  | state | country | pin    |
      | HSR 3rd sector | BDA complex | BDA complex | KAR   | IND     | 560101 |
    When the user creates school
    Then a school code is generated
    When user finds school detail by school code
    Then below address data should be present for school "ABC school" without logo
      | first line     | second line | third line  | state | country | pin    |
      | HSR 3rd sector | BDA complex | BDA complex | KAR   | IND     | 560101 |

  Scenario: Create school in system  with KYC detail with school logo / KYC attachments / social details
    Given the user provided school name - "XYZ school" , school id - "xyz_school" and below addresses
      | first line     | second line | third line | state | country | pin    |
      | HSR 5th sector | BDA complex |            | KAR   | IND     | 560103 |
    And the user has provided below social details
      | face book                       | twitter                        | google plus                       | linked in                       |
      | http://facebook.com/sanjeev.blr | http://twitter.com/sanjeev.blr | http://googleplus.com/sanjeev.blr | http://linkedin.com/sanjeev.blr |
    When the user creates school
    Then a school code is generated
    When user finds school detail by school code
    Then below address data should be present for school "XYZ school" with logo
      | first line     | second line | third line | state | country | pin    |
      | HSR 5th sector | BDA complex |            | KAR   | IND     | 560103 |
    Then below social details should be present
      | face book                       | twitter                        | google plus                       | linked in                       |
      | http://facebook.com/sanjeev.blr | http://twitter.com/sanjeev.blr | http://googleplus.com/sanjeev.blr | http://linkedin.com/sanjeev.blr |


  Scenario: Create school in system with school logo / with KYC details and without KYC attachments
    Given the user provided school name - "XYZ school" , school id - "xyz_school" and below addresses
      | first line     | second line | third line | state | country | pin    |
      | HSR 5th sector | BDA complex |            | KAR   | IND     | 560103 |
    When the user creates school
    Then a school code is generated
    When user finds school detail by school code
    Then below address data should be present for school "XYZ school" with logo
      | first line     | second line | third line | state | country | pin    |
      | HSR 5th sector | BDA complex |            | KAR   | IND     | 560103 |
      | Texas city 2   |             | Texas      | TXS   | USA     | 765014 |


  Scenario: Create school in system without optional parameters(second line / third line)
    Given the user provided school name - "XYZ school" , school id - "xyz_school" and below addresses
      | first line     | second line | third line | state | country | pin    |
      | HSR 5th sector |             |            | KAR   | IND     | 560103 |
    When the user creates school
    Then a school code is generated


  #Negative scenarios
  Scenario: Create school in system with without address line
    Given the user provided school name - "XYZ school" , school id - "xyz_school" and below addresses
      | first line | second line | third line | state | country | pin |
      |            |             |            |       |         |     |
    When the user creates school
    Then the user should get error as "Request payload is malformed or invalid"


  Scenario: Create school in system without mandataory parameters(first line/pin code/state code/country code)
    Given the user provided school name - "XYZ school" , school id - "xyz_school" and below addresses
      | first line | second line | third line | state | country | pin |
      |            | BDA complex | Bangalore  |       |         |     |
    When the user creates school
    Then the user should get error as "Request payload is malformed or invalid"

  # FIXME - Need to fixe once form validation for School decided
  @skip
  Scenario: Create school in system without school logo / KYC details and with KYC attachments
    Given the user provided school name - "XYZ school" , school id - "xyz_school" and below addresses
      | first line     | second line | third line | state | country | pin    |
      | HSR 5th sector | BDA complex |            | KAR   | IND     | 560103 |
    When the user creates school
    Then the user should get error as "Number of KYC documents uploaded not matching with KYC data provided"



