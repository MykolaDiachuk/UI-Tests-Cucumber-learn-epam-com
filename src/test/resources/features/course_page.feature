Feature: Course page navigation

  Scenario: Open course page from filtered catalog
    Given I am on the catalog page
    When I select filters by language "English", effort "Up to 1 hour", and level "Not defined"
    Then I search for course "Amazon Aurora Service Primer"
    And the course should be visible in the catalog
    Then I open the course page
    And the course page title should be "Amazon Aurora Service Primer"

