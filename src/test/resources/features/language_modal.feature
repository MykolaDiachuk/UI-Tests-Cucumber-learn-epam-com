Feature: Language modal behavior on the catalog page

  Background:
    Given I am on the catalog page

  Scenario: Verify that selecting valid languages in modal
    When I select languages in modal:
      | Spanish |
      | Czech   |
      | French  |
    Then languages "Spanish", "Czech", "French" should be selected
    And all visible courses should have language codes "SPA", "CZE", "FRA"

  Scenario: Verify that other languages were not selected
    When I select languages in modal:
      | Czech   |
      | Spanish |
      | French  |
    Then languages "Armenian", "Belarusian", "Hebrew" should not be selected
    And no visible course should have language codes "ARM", "BEL", "HEB"