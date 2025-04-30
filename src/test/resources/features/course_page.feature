Feature: Course page navigation

  Scenario: Open course page from filtered catalog
    Given User on the catalog page
    When User select filters by language "English", effort "Up to 1 hour", and level "Not defined"
    Then User search for course "Amazon Aurora Service Primer"
    And Verify the course should be visible in the catalog
    Then User open the course page
    And Verify the course page title should be "Amazon Aurora Service Primer"

