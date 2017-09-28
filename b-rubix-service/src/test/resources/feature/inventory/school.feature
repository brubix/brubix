Feature: Create school in system

  Scenario: Create school in system without KYC / Logo
    Given the user provided school name as "ABC school" and below addresses
      | first line     | second line | third line | state code | country code | pin code |
      | HSR 3rd sector | BDA complex |            | KAR        | IND          | 560102   |
      | HSR 4rd sector |             | Goregaon   | TXS        | USA          | 765019   |
    When the user creates school
    Then a school code is generated

  @skip
  Scenario: Create school in system  with KYC detail without logo / KYC attachments
    Given the user provided school name as "ABC school" and below addresses
      | first line     | second line | third line | state code | country code | pin code |
      | HSR 3rd sector | BDA complex |            | KAR        | IND          | 560102   |
      | HSR 4rd sector |             | Goregaon   | TXS        | USA          | 765019   |
    And the user has below kyc
      | type    | number    |
      | Aadhaar | 123456789 |
    When the user creates school
    Then a school code is generated