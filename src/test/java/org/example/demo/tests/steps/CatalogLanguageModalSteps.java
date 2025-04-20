package org.example.demo.tests.steps;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.demo.pages.CatalogMainPage;
import org.example.demo.tests.steps.hooks.CatalogPageHooks;
import org.example.demo.tests.steps.hooks.TestHooks;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogLanguageModalSteps {

    private final CatalogMainPage catalogMainPage = CatalogPageHooks.getCatalogMainPage();

    @When("I select languages in modal:")
    public void i_select_languages_in_modal(List<String> languages) {
        catalogMainPage.openLanguageSelectionModal()
                .selectLanguages(languages.toArray(new String[0]))
                .clickSelect();
    }

    @Then("languages {string}, {string}, {string} should be selected")
    public void selected_languages_should_be(String lang1, String lang2, String lang3) {
        assertThat(catalogMainPage.isLanguageSelected(lang1)).isTrue();
        assertThat(catalogMainPage.isLanguageSelected(lang2)).isTrue();
        assertThat(catalogMainPage.isLanguageSelected(lang3)).isTrue();
    }

    @Then("all visible courses should have language codes {string}, {string}, {string}")
    public void all_courses_should_have_languages(String code1, String code2, String code3) {
        assertThat(catalogMainPage.getAllVisibleCourses())
                .allSatisfy(course -> assertThat(course.getLanguage()).isIn(code1, code2, code3));
    }

    @Then("languages {string}, {string}, {string} should not be selected")
    public void unselected_languages_should_be(String lang1, String lang2, String lang3) {
        assertThat(catalogMainPage.isLanguageSelected(lang1)).isFalse();
        assertThat(catalogMainPage.isLanguageSelected(lang2)).isFalse();
        assertThat(catalogMainPage.isLanguageSelected(lang3)).isFalse();
    }

    @Then("no visible course should have language codes {string}, {string}, {string}")
    public void all_courses_should_not_have_languages(String code1, String code2, String code3) {
        assertThat(catalogMainPage.getAllVisibleCourses())
                .allSatisfy(course -> assertThat(course.getLanguage()).isNotIn(code1, code2, code3));
    }
}
