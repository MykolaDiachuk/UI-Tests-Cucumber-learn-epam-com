Feature: Filter courses by selected skills in modal
  Background:
    Given I am on the catalog page

  Scenario: Verify that skills can be selected and added for search
    When I select skills in modal:
      | AT/Java |
      | Java Core |
      | Gen AI in Test Automation |
    Then skills "AT/Java", "Java Core", "Gen AI in Test Automation" should be selected
    And all visible courses should contain skills "AT/Java", "Java Core", "Gen AI in Test Automation"

  Scenario: Verify that other skills are not selected
    When I select skills in modal:
      | Cucumber |
      | Java Core |
      | AT/Python |
    Then all visible courses should contain skills "Cucumber", "Java Core", "AT/Python"
    And all visible courses should not contain skills "AT/Java", "AWS App Runner", "Gen AI in Test Automation"
