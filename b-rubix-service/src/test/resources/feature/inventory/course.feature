@skip
Feature: Create courses for school

  Scenario: Create school in system without school logo without KYC details and attachments
    Given the user provided school name as "ABC school" and below addresses
      | first line     | second line | third line  | state code | country code | pin code |
      | HSR 3rd sector | BDA complex | BDA complex | KAR        | IND          | 560101   |
      | Texas city 1   | Texas       | Texas       | TXS        | USA          | 765012   |
    When the user creates school
    Then a school code is generated