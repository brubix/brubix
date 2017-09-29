Feature: Create school in system

  Scenario: Create school in system without school logo without KYC details and attachments
    Given the user provided school name as "ABC school" and below addresses
      | first line     | second line | third line | state code | country code | pin code |
      | HSR 3rd sector | BDA complex |            | KAR        | IND          | 560101   |
      | Texas city 1   |             | Texas      | TXS        | USA          | 765012   |
    When the user creates school
    Then a school code is generated

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
