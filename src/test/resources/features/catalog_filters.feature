Feature: Catalog Filters

  Background:
    Given I am on the catalog page

  Scenario: Select filters on the catalog page
    When I select filters by language "English", effort "1-4 hours", and level "Novice"
    Then all selected filters should be applied
    And all visible courses should be in English
    And all visible courses should be with effort from 1 to 4 hours

  Scenario: Filter courses by language
    When I select the language filter "English"
    Then all visible courses should be in English
