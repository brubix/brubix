Feature: Get logged in info for all users

  Background:
    Given all country data present in system
    And all role data present in system
    And all privilege data present in system
    And the user provided school name - "ABC school" and below addresses
      | first line     | second line | third line  | state code | country code | pin code |
      | HSR 3rd sector | BDA complex | BDA complex | KAR        | IND          | 560101   |
      | Texas city 1   | Texas       | Texas       | TXS        | USA          | 765012   |

  Scenario: Get logged in info for an ADMINISTRATOR
    Given below "ADMINISTRATOR" is going be created in system
      | name  | password | date of birth | joining date |
      | admin | admin    | 12/02/1995    | 12/02/2007   |
    And below address provided
      | first line     | second line | third line  | state code | country code | pin code |
      | HSR 3rd sector | BDA complex | BDA complex | KAR        | IND          | 560101   |
      | Texas city 1   | Texas       | Texas       | TXS        | USA          | 765012   |
    And below phone number provided
      | number    |
      | 123456789 |
      | 789654123 |
    And below emails provided
      | email          |
      | xyz@gmail.come |
      | abc@gmail.come |
    And user created




