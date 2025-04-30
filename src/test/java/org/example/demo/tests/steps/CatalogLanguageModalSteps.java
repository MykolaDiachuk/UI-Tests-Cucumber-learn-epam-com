package org.example.demo.tests.steps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.demo.pages.CatalogMainPage;
import org.example.demo.tests.steps.hooks.CatalogPageHooks;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogLanguageModalSteps {

    private final CatalogMainPage catalogMainPage = CatalogPageHooks.getCatalogMainPage();

    @When("User select languages in modal:")
    public void user_select_languages_in_modal(DataTable dataTable) {
        List<String> languages = dataTable.asList();
        catalogMainPage.openLanguageSelectionModal()
                .selectLanguages(languages.toArray(new String[0]))
                .clickSelect();
    }

    @Then("Verify Languages should be selected:")
    public void selected_languages_should_be(DataTable languages) {
        assertThat(languages.asList()).allSatisfy(
                lang -> assertThat(catalogMainPage.isLanguageSelected(lang)).isTrue()
        );
    }

    @Then("Verify all visible courses should have language codes:")
    public void all_courses_should_have_languages(DataTable languages) {
        assertThat(catalogMainPage.getAllVisibleCourses())
                .allSatisfy(course -> assertThat(course.getLanguage()).isIn(languages.asList()));
    }

    @Then("Verify languages should not be selected:")
    public void unselected_languages_should_be(DataTable languages) {
        assertThat(languages.asList()).allSatisfy(
                lang -> assertThat(catalogMainPage.isLanguageSelected(lang)).isFalse()
        );
    }

    @Then("Verify no visible course should have language codes:")
    public void all_courses_should_not_have_languages(DataTable languages) {
        assertThat(catalogMainPage.getAllVisibleCourses())
                .allSatisfy(course -> assertThat(course.getLanguage()).isNotIn(languages.asList()));
    }
}
