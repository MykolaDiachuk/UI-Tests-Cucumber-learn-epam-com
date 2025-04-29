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

    @When("I select skills in modal:")
    public void i_select_skills_in_modal(List<String> skills) {
        var modal = catalogMainPage.openSkillSelectionModal();
        skills.forEach(modal::addSkill);
        modal.selectSkills();
    }

    @Then("skills {string}, {string}, {string} should be selected")
    public void skills_should_be_selected(String s1, String s2, String s3) {
        assertThat(catalogMainPage.isSkillSelected(s1)).isTrue();
        assertThat(catalogMainPage.isSkillSelected(s2)).isTrue();
        assertThat(catalogMainPage.isSkillSelected(s3)).isTrue();
    }

    @And("all visible courses should contain skills {string}, {string}, {string}")
    public void all_visible_courses_should_contain_skills(String skill1, String skill2, String skill3) {
        visibleCourses = catalogMainPage.getAllVisibleCourses();
        assertThat(visibleCourses).isNotEmpty();
        assertThat(visibleCourses).allSatisfy(course ->
                assertThat(course.getSkills()).contains(skill1, skill2, skill3)
        );
    }

    @Then("all visible courses should not contain skills {string}, {string}, {string}")
    public void all_visible_courses_should_not_contain_skills(String skill1, String skill2, String skill3) {
        visibleCourses = catalogMainPage.getAllVisibleCourses();
        assertThat(visibleCourses).isNotEmpty();
        assertThat(visibleCourses).allSatisfy(course ->
                assertThat(course.getSkills()).doesNotContain(skill1, skill2, skill3)
        );
    }
}
