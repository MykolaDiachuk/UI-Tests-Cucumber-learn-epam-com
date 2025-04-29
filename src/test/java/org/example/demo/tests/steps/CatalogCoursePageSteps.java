package org.example.demo.tests.steps;

import io.cucumber.java.en.*;
import org.example.demo.pages.CatalogMainPage;
import org.example.demo.pages.CourseEntityPage;
import org.example.demo.tests.steps.hooks.CatalogPageHooks;

import static org.assertj.core.api.Assertions.assertThat;

public class CatalogCoursePageSteps {

    private final CatalogMainPage catalogMainPage = CatalogPageHooks.getCatalogMainPage();
    private CourseEntityPage courseEntityPage;
    private String selectedCourseName;

    @Then("I search for course {string}")
    public void i_search_for_course(String string) {
        this.selectedCourseName = string;

        assertThat(catalogMainPage.getAllVisibleCourses())
                .allSatisfy(course -> {
                    assertThat(course.getLanguage()).isEqualTo("ENG");
                    assertThat(course.getEffort().toHours()).isBetween(0L, 1L);
                });
    }

    @Then("the course should be visible in the catalog")
    public void the_course_should_be_visible_in_the_catalog() {
        boolean exists = catalogMainPage.getAllVisibleCourses()
                .stream()
                .anyMatch(course -> course.getTitle().equals(selectedCourseName));

        assertThat(exists).as("Course is present").isTrue();
    }

    @Then("I open the course page")
    public void i_open_the_course_page() {
        courseEntityPage = catalogMainPage.goToCourse(selectedCourseName);
    }

    @Then("the course page title should be {string}")
    public void the_course_page_title_should_be(String string) {
        assertThat(courseEntityPage.getTitle()).isEqualTo(string);
    }
}
