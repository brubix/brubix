Feature: Get logged in info for all users

  Background:
    Given all country data present in system
    And all role data present in system
    And all privilege data present in system
    And the user provided school name - "ABC school" and below addresses
      | first line     | second line | third line  | state code | country code | pin code |
      | HSR 3rd sector | BDA complex | BDA complex | KAR        | IND          | 560101   |
      | Texas city 1   | Texas       | Texas       | TXS        | USA          | 765012   |

  Scenario Outline: Get logged in info for an ADMINISTRATOR
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
      | email         |
      | xyz@gmail.com |
      | abc@gmail.com |
    And user created
    And below client is already registered with identity service
      | client id | secret |
      | brubix    | secret |
    When proxy service calls identity service to get access token for user
      | user name   | password   |
      | <unique_id> | <password> |
    Then proxy service should get access token and details
    When calling me api with access token
    Then we should get role as
      | role          | description                                                            |
      | ADMINISTRATOR | The administrator has access to all functionality of particular school |
    And we should get privilege as
      | name          | description                          |
      | /affiliations | All affiliations endpoints privilege |
      | /institutions | All institutions endpoints privilege |
      | /languages    | All languages endpoints privilege    |
      | /subjects     | All subjects endpoints privilege     |
      | /countries    | All countries endpoints privilege    |
      | /schools      | All schools endpoints privilege      |
      | /documents    | All documents endpoints privilege    |
    And we should get associated school as "ABC school"

    Examples:
      | unique_id     | password |
      | xyz@gmail.com | admin    |