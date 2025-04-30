package org.example.demo.tests.steps.hooks;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.Getter;
import org.example.demo.pages.CatalogMainPage;
import org.example.demo.pages.HomePage;


public class CatalogPageHooks {
    @Getter
    public static CatalogMainPage catalogMainPage;

    @Given("User on the catalog page")
    public void user_on_the_catalog_page() {
        HomePage homePage = new HomePage();
        homePage.acceptCookies();
        catalogMainPage = homePage.goToCatalog();
    }

    @When("User select filters by language {string}, effort {string}, and level {string}")
    public void i_select_filters(String language, String effort, String level) {
        catalogMainPage
                .selectCheckbox(language)
                .selectCheckbox(effort)
                .selectCheckbox(level);
    }
}
