Feature: Filter courses by selected skills in modal
  Background:
    Given User on the catalog page

  Scenario: Verify that skills can be selected and added for search
    When User select skills in modal:
      | AT/Java |
      | Java Core |
      | Gen AI in Test Automation |
    Then Verify skills should be selected:
      | AT/Java                   |
      | Java Core                 |
      | Gen AI in Test Automation |
    And Verify all visible courses should contain skills:
      | AT/Java                   |
      | Java Core                 |
      | Gen AI in Test Automation |

  Scenario: Verify that other skills are not selected
    When User select skills in modal:
      | Cucumber |
      | Java Core |
      | AT/Python |
    Then Verify all visible courses should contain skills:
      | Cucumber  |
      | Java Core |
      | AT/Python |
    And Verify all visible courses should not contain skills:
      | AT/Java                   |
      | AWS App Runner            |
      | Gen AI in Test Automation |

