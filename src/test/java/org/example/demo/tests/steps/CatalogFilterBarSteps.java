package org.example.demo.tests.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.example.demo.pages.CatalogMainPage;
import org.example.demo.tests.steps.hooks.CatalogPageHooks;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


public class CatalogFilterBarSteps {

    private final CatalogMainPage catalogMainPage = CatalogPageHooks.getCatalogMainPage();

    @When("User apply filters: effort {string}, level {string}, with languages")
    public void user_apply_filters_effort_level_with_languages(String effort, String level, io.cucumber.datatable.DataTable dataTable) {
        List<String> languages = dataTable.asList();
        catalogMainPage.selectCheckbox(effort)
                .selectCheckbox(level)
                .openLanguageSelectionModal()
                .selectLanguages(languages.toArray(new String[0]))
                .clickSelect();
    }

    @Then("Verify filter chips should contain:")
    public void filter_chips_should_contain(DataTable dataTable) {
        List<String> filterChips = dataTable.asList();
        List<String> filtersText = catalogMainPage.getFilterChipsBlock().getFiltersText();
        assertThat(filtersText).containsAll(filterChips);
    }

    @Then("^(.+?) filter count should be (\\d+)$")
    public void language_filter_count_should_be(String filterType, int expectedCount) {
        assertThat(catalogMainPage.getFilterChipsBlock().getFilterCount(filterType))
                .isEqualTo(expectedCount);
    }

    @Then("Verify all visible courses should have language {string} and effort between {int} and {int} hours")
    public void all_visible_courses_should_have_language_and_effort(String expectedLang, int minHrs, int maxHrs) {
        catalogMainPage.getAllVisibleCourses()
                .forEach(course -> {
                    assertThat(course.getLanguage()).isEqualTo(expectedLang);
                    assertThat(course.getEffort().toHours()).isBetween((long) minHrs, (long) maxHrs);
                });
    }

    @When("User clear all filters")
    public void user_clear_all_filters() {
        catalogMainPage.getFilterChipsBlock().clearAllFilters();
    }

    @Then("Verify all visible courses should have effort between {int} and {int} minutes")
    public void all_visible_courses_should_have_effort_in_minutes(long min, long max) {
        assertThat(catalogMainPage.getAllVisibleCourses())
                .allSatisfy(course -> {
                    assertThat(course.getEffort().toMinutes()).isBetween(min, max);
                });
    }
}
