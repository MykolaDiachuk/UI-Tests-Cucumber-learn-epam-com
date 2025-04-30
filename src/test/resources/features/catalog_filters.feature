Feature: Catalog Filters

  Background:
    Given User on the catalog page

  Scenario: Select filters on the catalog page
    When User select filters by language "English", effort "1-4 hours", and level "Novice"
    Then Verify all selected filters should be applied
    And Verify all visible courses should be in English
    And Verify all visible courses should be with effort from 1 to 4 hours

  Scenario: Filter courses by language
    When User select the language filter "English"
    Then Verify all visible courses should be in English
