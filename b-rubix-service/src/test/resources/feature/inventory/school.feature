Feature: Create school in system

  Background:
    Given that below countries loaded in system
      | code | currency | description   |
      | IND  | INR      | India         |
      | USA  | USD      | United States |
    And below states for "IND" are associated
      | code | description |
      | KAR  | Karnataka   |
      | MAH  | Maharastra  |
    And below states for "USA" are associated
      | code | description   |
      | TXS  | Texas         |
      | WDC  | Washington DC |
    Then user loaded country data into system

  Scenario: Create school in system without KYC / Logo
    Given the user provided school name as "ABC school" and below addresses
      | first line     | second line | third line | state code | country code | pin code |
      | HSR 3rd sector | BDA complex |            | KAR        | IND          | 560102   |
      | HSR 4rd sector |             | Goregaon   | TXS        | USA          | 765019   |
    When the user creates school
    Then a school code is generated