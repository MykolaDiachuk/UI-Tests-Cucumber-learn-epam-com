Feature: Sorting courses on the catalog page

  Background:
    Given User on the catalog page

  Scenario Outline: Verify ascending sorting by course name
    When User select <direction> sorting by "Course Name"
    And User save all course titles
    Then Verify courses should be sorted in <order> order

    Examples:
      | direction | order      |
      | ascent    | ascending  |
      | descent   | descending |