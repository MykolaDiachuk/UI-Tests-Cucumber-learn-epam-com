package org.example.demo.tests.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.demo.dtos.CourseDTO;
import org.example.demo.pages.CatalogMainPage;
import org.example.demo.tests.steps.hooks.CatalogPageHooks;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogSkillModalSteps {
    private final CatalogMainPage catalogMainPage = CatalogPageHooks.getCatalogMainPage();
    private List<CourseDTO> visibleCourses;

    @When("User select skills in modal:")
    public void i_select_skills_in_modal(List<String> skills) {
        var modal = catalogMainPage.openSkillSelectionModal();
        skills.forEach(modal::addSkill);
        modal.selectSkills();
    }

    @Then("Verify skills should be selected:")
    public void skills_should_be_selected(List<String> skills) {
        assertThat(skills).allSatisfy(
                skill -> assertThat(catalogMainPage.isSkillSelected(skill)).isTrue()
        );
    }

    @And("Verify all visible courses should contain skills:")
    public void all_visible_courses_should_contain_skills(List<String> skills) {
        visibleCourses = catalogMainPage.getAllVisibleCourses();
        assertThat(visibleCourses).isNotEmpty();
        assertThat(visibleCourses).allSatisfy(course ->
                assertThat(course.getSkills()).containsAnyElementsOf(skills)
        );
    }

    @Then("Verify all visible courses should not contain skills:")
    public void all_visible_courses_should_not_contain_skills(List<String> skills) {
        visibleCourses = catalogMainPage.getAllVisibleCourses();
        assertThat(visibleCourses).isNotEmpty();
        assertThat(visibleCourses).allSatisfy(course ->
                assertThat(course.getSkills()).doesNotContainAnyElementsOf(skills)
        );
    }
}
