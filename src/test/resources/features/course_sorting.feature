Feature: Sorting courses on the catalog page

  Background:
    Given I am on the catalog page

  Scenario Outline: Verify ascending sorting by course name
    When I select <direction> sorting by "Course Name"
    And I save all course titles
    Then courses should be sorted in <order> order

    Examples:
      | direction | order      |
      | ascent    | ascending  |
      | descent   | descending |