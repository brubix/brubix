@ignore
Feature: Load country / states in system

  Scenario: Load country / states in system
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
    When user loaded country data into system
    Then we should see "IND" has below states
      | code | description |
      | KAR  | Karnataka   |
      | MAH  | Maharastra  |
    And we should see "USA" has below states
      | code | description   |
      | TXS  | Texas         |
      | WDC  | Washington DC |