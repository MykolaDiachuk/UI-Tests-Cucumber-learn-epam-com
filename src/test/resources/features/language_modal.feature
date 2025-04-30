Feature: Language modal behavior on the catalog page

  Background:
    Given User on the catalog page

  Scenario: Verify that selecting valid languages in modal
    When User select languages in modal:
      | Spanish |
      | Czech   |
      | French  |
    Then Verify Languages should be selected:
      | Spanish |
      | Czech   |
      | French  |

    And Verify all visible courses should have language codes:
      | SPA |
      | CZE |
      | FRA |

  Scenario: Verify that other languages were not selected
    When User select languages in modal:
      | Czech   |
      | Spanish |
      | French  |
    Then Verify languages should not be selected:
      | Armenian   |
      | Hebrew     |
      | Belarusian |
    And Verify no visible course should have language codes:
      | ARM |
      | BEL |
      | HEB |