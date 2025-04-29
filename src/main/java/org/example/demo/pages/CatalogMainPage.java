package org.example.demo.pages;

import lombok.Getter;
import org.example.demo.dtos.CourseDTO;
import org.example.demo.dtos.parsers.CourseCardParser;
import org.example.demo.elementcore.elements.PageElement;
import org.example.demo.elementcore.elements.PageElementCollection;
import org.example.demo.pages.blocks.FilterChipsBlock;
import org.example.demo.pages.modals.LanguageSelectionModal;
import org.example.demo.pages.modals.SkillSelectorModal;
import org.example.demo.utils.selenium.FormatElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.example.demo.utils.selenium.Waiter.waitForElementsToVanish;


public class CatalogMainPage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(CatalogMainPage.class);

    @Getter
    private final FilterChipsBlock filterChipsBlock;

    private final FormatElement checkboxLabel = new FormatElement("//label[.//div[contains(@class, 'uui-input-label') and text()='%s']]");
    private final FormatElement checkboxInput = new FormatElement("//div[@role='option'][.//div[text()='%s']]//input[@type='checkbox']");
    private final FormatElement selectedSkillCheckbox = new FormatElement("//div[@role='option'][@aria-checked='true'][.//div[@class='uui-input-label' and text()='%s']]//input[@type='checkbox']");
    private final FormatElement courseTitle = new FormatElement("//div[contains(@class, 'OverflowedTypography_content__') and text()='%s']");
    private final FormatElement sortDropdownItem = new FormatElement("//div[contains(@class,'DropdownBaseItem_md__IT1ES')]/div/div[text()='%s']");

    @FindBy(xpath = "//div[contains(@class, 'CatalogCard_catalogCard__+qmum')]")
    private PageElementCollection<PageElement> courseCards;

    @FindBy(xpath = "//button[contains(@class, 'uui-button-box') and .//div[text()='SHOW ALL 33 LANGUAGES']]")
    private PageElement languageModal;

    @FindBy(css = "input.uui-input[placeholder='Search skill']")
    private PageElement skillSelectionInput;

    @FindBy(css = "button[class*='DropdownSortingTarget_linkButton__yEhkX']")
    private PageElement sortByButton;

    public CatalogMainPage() {
        super();
        filterChipsBlock = new FilterChipsBlock();
    }

    public CatalogMainPage selectCheckbox(String checkboxText) {
        logger.info("Select checkbox: {}", checkboxText);
        checkboxLabel.format(checkboxText).scrollTo().click();
        return this;
    }

    public LanguageSelectionModal openLanguageSelectionModal() {
        logger.info("Open language selection modal");
        languageModal.waitUntilPresent().scrollTo().click();
        return new LanguageSelectionModal();
    }

    public SkillSelectorModal openSkillSelectionModal() {
        logger.info("Open skill selection modal");
        skillSelectionInput.waitUntilPresent().scrollTo().click();
        return new SkillSelectorModal();
    }

    public List<CourseDTO> getAllVisibleCourses() {
        logger.info("Collecting all visible courses info");

        /*try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        waitForElementsToVanish(courseCards.getElements());

        try {
            return courseCards.waitUntilPresent().getElements().stream()
                    .map(el -> CourseCardParser.parse(el.getElement()))
                    .toList();
        } catch (TimeoutException e) {
            logger.warn("No course cards found");
            return List.of();
        }
    }

    public CourseEntityPage goToCourse(String courseName) {
        logger.info("Go to course: {}", courseName);
        courseTitle.format(courseName).click();
        return new CourseEntityPage();
    }

    public void selectDescentSortBy(String sortItem) {
        logger.info("Select descending sort by: {}", sortItem);
        sortByButton.waitUntilPresent().scrollTo().click();
        sortDropdownItem.format(sortItem).click();
    }

    public void selectAscentSortBy(String sortItem) {
        logger.info("Select ascending sort by: {}", sortItem);
        sortByButton.waitUntilPresent().scrollTo().click();
        sortDropdownItem.format(sortItem).click();
        sortDropdownItem.format(sortItem).click();
    }

    public boolean isCheckboxSelected(String label) {
        return checkboxInput.format(label).isSelected();
    }

    public boolean isSkillSelected(String skillName) {
        return selectedSkillCheckbox.format(skillName).isSelected();
    }

    public boolean isLanguageSelected(String language) {
        try {
            return "true".equals(checkboxInput.format(language).getElement().getDomAttribute("aria-checked"));
        } catch (TimeoutException e) {
            return false;
        }
    }
}
