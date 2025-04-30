package org.example.demo.tests.steps;

import io.cucumber.java.en.*;
import org.example.demo.enums.EstimatedEffort;
import org.example.demo.enums.Language;
import org.example.demo.enums.TargetLevel;
import org.example.demo.pages.CatalogMainPage;
import org.example.demo.tests.steps.hooks.CatalogPageHooks;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogFilterSteps {

    private final CatalogMainPage catalogMainPage = CatalogPageHooks.getCatalogMainPage();

    @Then("Verify all selected filters should be applied")
    public void all_selected_filters_should_be_applied() {
        assertThat(catalogMainPage.isCheckboxSelected(Language.ENGLISH.getLabel())).isTrue();
        assertThat(catalogMainPage.isCheckboxSelected(EstimatedEffort.ONE_TO_FOUR_HOURS.getLabel())).isTrue();
        assertThat(catalogMainPage.isCheckboxSelected(TargetLevel.NOVICE.getLabel())).isTrue();
    }

    @Then("Verify all visible courses should be with effort from 1 to 4 hours")
    public void verify_all_visible_courses() {
        assertThat(catalogMainPage.getAllVisibleCourses())
                .allSatisfy(course -> {
                    assertThat(course.getEffort().toHours()).isBetween(1L, 4L);
                });
    }

    @When("User select the language filter {string}")
    public void i_select_language_filter(String language) {
        catalogMainPage.selectCheckbox(language);
    }

    @Then("Verify all visible courses should be in English")
    public void all_visible_courses_should_be_in_english() {
        assertThat(catalogMainPage.getAllVisibleCourses())
                .allSatisfy(course -> assertThat(course.getLanguage()).isEqualTo("ENG"));
    }
}
