Feature: Filter bar behavior on the catalog page

  Background:
    Given I am on the catalog page

  Scenario: Verify that filter bar has chips that were selected
    When I apply filters: effort "1-4 hours", level "Novice", with languages
      | English  |
      | Armenian |
    Then filter chips should contain "Estimated efforts", "Target level", "Language"

  Scenario Outline: Verify that count of filters on filter bar is working well
    When I apply filters: effort "1-4 hours", level "Novice", with languages
      | Armenian   |
      | French     |
      | Hebrew     |
      | Spanish    |
      | Czech      |
      | Belarusian |
    Then <filter bar> filter count should be <number>

    Examples:
      | filter bar        | number |
      | Language          | 6      |
      | Target level      | 1      |
      | Estimated efforts | 1      |

  Scenario: Verify that Clear All button in filter bar works well
    When I apply filters: effort "1-4 hours", level "Novice", with languages
      | French |
    Then all visible courses should have language "FRA" and effort between 1 and 4 hours
    When I clear all filters
    Then all visible courses should have effort between 0 and 12000 minutes
