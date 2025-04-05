package org.example.demo.pages.modals;

import org.example.demo.decorator.elements.PageElement;
import org.example.demo.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.demo.utils.Waiter.*;

public class SkillSelectorModal extends BasePage {
    private static final By SKILL_SEARCHER = By.cssSelector("input[placeholder='Type text for quick search']");
    private static final By SELECT_BUTTON = By.xpath("//button[.//div[text()='Select']]");
    private static final By MODAL_WINDOW = By.cssSelector("div.uui-modal-window");
    private final Logger logger = LoggerFactory.getLogger(SkillSelectorModal.class);

    public SkillSelectorModal() {
        super();
    }

    public SkillSelectorModal addSkill(String skillName) {
        logger.info("Added skill {} in modal", skillName);
        searchSkill(skillName);
        selectSkillFromResults(skillName);
        clearSearchInput();
        return this;
    }

    private void searchSkill(String skillName) {
        logger.info("Searching for skill '{}' in modal", skillName);
        PageElement searchInput = waitForElementToBeVisible(SKILL_SEARCHER);
        scrollToElement(searchInput);
        searchInput.sendKeys(skillName, Keys.ENTER);
    }

    private void selectSkillFromResults(String skillName) {
        logger.info("Selecting skill '{}' from results in modal", skillName);
        PageElement skillOption = waitForElementToBeClickable(getSkillLocator(skillName));
        clickElementWithJS(skillOption);
    }

    private void clearSearchInput() {
        logger.info("Clear search input in modal");
        WebElement searchInput = waitForElementToBeVisible(SKILL_SEARCHER);
        searchInput.sendKeys(Keys.CONTROL + "a");
        searchInput.sendKeys(Keys.BACK_SPACE);
    }

    public void selectSkills() {
        logger.info("Selecting skills from modal");
        waitForElementToBeClickable(SELECT_BUTTON).click();
        waitForAllElementsToBeInvisible(MODAL_WINDOW);
    }

    private By getSkillLocator(String skillName) {
        return By.xpath("//div[contains(text(), '" + skillName + "')]");
    }
}