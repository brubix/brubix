Feature: Create courses for a school

  Scenario: Create for a school
    Given the user provided school name - "ABC school" , school id - "abc_school" and below addresses
      | first line     | second line | third line  | state code | country code | pin code |
      | HSR 3rd sector | BDA complex | BDA complex | KAR        | IND          | 560101   |
    When the user creates school
    Then a school code is generated
    And the user creates below courses for school
      | name | description |
      | STD1 | standard 1  |
      | STD2 | standard 2  |
      | STD3 | standard 3  |
      | STD4 | standard 4  |
    When user finds all courses for school
    Then user should get
      | name | description |
      | STD1 | standard 1  |
      | STD2 | standard 2  |
      | STD3 | standard 3  |
      | STD4 | standard 4  |

