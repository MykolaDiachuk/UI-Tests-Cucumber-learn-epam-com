package org.example.demo.tests.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.demo.dtos.CourseDTO;
import org.example.demo.pages.CatalogMainPage;
import org.example.demo.tests.steps.hooks.CatalogPageHooks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogSortingSteps {

    private final CatalogMainPage catalogMainPage = CatalogPageHooks.getCatalogMainPage();
    private List<String> actualCourseTitles;

    @When("User select descent sorting by {string}")
    public void i_select_descent_sorting(String criteria) {
        catalogMainPage.selectDescentSortBy(criteria);
    }

    @When("User select ascent sorting by {string}")
    public void i_select_ascent_sorting(String criteria) {
        catalogMainPage.selectAscentSortBy(criteria);
    }

    @And("User save all course titles")
    public void i_save_all_course_titles() {
        actualCourseTitles = catalogMainPage.getAllVisibleCourses().stream()
                .map(CourseDTO::getTitle)
                .toList();
    }

    @Then("Verify courses should be sorted in descending order")
    public void courses_should_be_sorted_desc() {
        List<String> sortedCourseTitles = new ArrayList<>(actualCourseTitles);
        sortedCourseTitles.sort(String::compareToIgnoreCase);
        Collections.reverse(sortedCourseTitles);
        assertThat(actualCourseTitles).isEqualTo(sortedCourseTitles);
    }

    @Then("Verify courses should be sorted in ascending order")
    public void courses_should_be_sorted_asc() {
        List<String> sortedCourseTitles = new ArrayList<>(actualCourseTitles);
        sortedCourseTitles.sort(String::compareToIgnoreCase);
        assertThat(actualCourseTitles).isEqualTo(sortedCourseTitles);
    }
}