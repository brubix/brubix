Feature: Create school in system

  Scenario: Create school in system without school logo without KYC details and attachments
    Given the user provided school name as "ABC school" and below addresses
      | first line     | second line | third line  | state code | country code | pin code |
      | HSR 3rd sector | BDA complex | BDA complex | KAR        | IND          | 560101   |
      | Texas city 1   | Texas       | Texas       | TXS        | USA          | 765012   |
    When the user creates school
    Then a school code is generated
    When user finds school detail by school code
    Then below address data should be present for school "ABC school" without logo
      | first line     | second line | third line  | state code | country code | pin code |
      | HSR 3rd sector | BDA complex | BDA complex | KAR        | IND          | 560101   |
      | Texas city 1   | Texas       | Texas       | TXS        | USA          | 765012   |


  Scenario: Create school in system  with KYC detail with school logo / KYC attachments
    Given the user provided school name as "XYZ school" and below addresses
      | first line     | second line | third line | state code | country code | pin code |
      | HSR 5th sector | BDA complex |            | KAR        | IND          | 560103   |
      | Texas city 2   |             | Texas      | TXS        | USA          | 765014   |
    And the user has provided below kyc
      | type            | number         | document             |
      | Aadhaar         | 123456789      | aadhaar.pdf          |
      | Driving license | 12345678912345 | driving-license.jpeg |
    And logo "school-logo.jpg" provided
    When the user creates school
    Then a school code is generated
    When user finds school detail by school code
    Then below address data should be present for school "XYZ school" with logo
      | first line     | second line | third line | state code | country code | pin code |
      | HSR 5th sector | BDA complex |            | KAR        | IND          | 560103   |
      | Texas city 2   |             | Texas      | TXS        | USA          | 765014   |

  Scenario: Create school in system with school logo / with KYC details and without KYC attachments
    Given the user provided school name as "XYZ school" and below addresses
      | first line     | second line | third line | state code | country code | pin code |
      | HSR 5th sector | BDA complex |            | KAR        | IND          | 560103   |
      | Texas city 2   |             | Texas      | TXS        | USA          | 765014   |
    And the user has provided below kyc
      | type            | number         | document |
      | Aadhaar         | 123456789      |          |
      | Driving license | 12345678912345 |          |
    And logo "school-logo.jpg" provided
    When the user creates school
    Then a school code is generated
    When user finds school detail by school code
    Then below address data should be present for school "XYZ school" with logo
      | first line     | second line | third line | state code | country code | pin code |
      | HSR 5th sector | BDA complex |            | KAR        | IND          | 560103   |
      | Texas city 2   |             | Texas      | TXS        | USA          | 765014   |

  Scenario: Create school in system without optional parameters(second line / third line)
    Given the user provided school name as "XYZ school" and below addresses
      | first line     | second line | third line | state code | country code | pin code |
      | HSR 5th sector |             |            | KAR        | IND          | 560103   |
    When the user creates school
    Then a school code is generated

  #Negative scenarios
  Scenario: Create school in system with without address line
    Given the user provided school name as "XYZ school" and below addresses
      | first line | second line | third line | state code | country code | pin code |
      |            |             |            |            |              |          |
    When the user creates school
    Then the user should get error as "Request payload is malformed or invalid"

  Scenario: Create school in system without mandataory parameters(first line/pin code/state code/country code)
    Given the user provided school name as "XYZ school" and below addresses
      | first line | second line | third line | state code | country code | pin code |
      |            | BDA complex | Bangalore  |            |              |          |
    When the user creates school
    Then the user should get error as "Request payload is malformed or invalid"

  Scenario: Create school in system without school logo / KYC details and with KYC attachments
    Given the user provided school name as "XYZ school" and below addresses
      | first line     | second line | third line | state code | country code | pin code |
      | HSR 5th sector | BDA complex |            | KAR        | IND          | 560103   |
    And the user has provided below kyc
      | type | number | document             |
      |      |        | aadhaar.pdf          |
      |      |        | driving-license.jpeg |
    When the user creates school
    Then the user should get error as "Number of KYC documents uploaded not matching with KYC data provided"



