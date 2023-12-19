Feature: reading test case

  Background: Login to site
    Given User logs in

  Scenario Outline:
    When User opens project
    And User clicks <caseName>
    And User clicks Edit button
    Then Title is <caseTitle>

    Examples:
      | caseName    | caseTitle     |
      | "Cucu Case" | "Cucu Case" |
      | "BDD Case"  | "BDD Case"  |