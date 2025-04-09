package org.example.demo.dtos.parsers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CourseCardExtractor {

    private static final Logger logger = LoggerFactory.getLogger(CourseCardExtractor.class);

    private static final By TITLE_LOCATOR = By.xpath(".//h3//div[contains(@class, 'OverflowedTypography_content__')]");
    private static final By LANGUAGE_LOCATOR = By.xpath(".//*[@data-testid='label-language']//div[contains(@class, 'OverflowedTypography_content__')]");
    private static final By EFFORT_LOCATOR = By.xpath(".//*[@data-testid='test-estimated-efforts']//div[contains(@class, 'OverflowedTypography_content__')]");
    private static final By SKILLS_LOCATOR = By.xpath(".//*[@data-testid='test-learning-item-skills']//div[contains(@class, 'Typography_ellipses__')]");

    public static String extractTitle(WebElement card) {
        try {
            return card.findElement(TITLE_LOCATOR).getText().trim();
        } catch (Exception e) {
            logger.warn("Title not found in course card");
            return "";
        }
    }

    public static String extractLanguage(WebElement card) {
        try {
            return card.findElement(LANGUAGE_LOCATOR).getText().trim();
        } catch (Exception e) {
            logger.warn("Language not found");
            return "";
        }
    }

    public static Duration extractEffort(WebElement card) {
        try {
            String effortText = card.findElement(EFFORT_LOCATOR).getText().trim();
            int hours = 0;
            int minutes = 0;
            String[] parts = effortText.split(" ");
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].toLowerCase().contains("hour") || parts[i].toLowerCase().contains("hr")) {
                    hours = Integer.parseInt(parts[i - 1]);
                } else if (parts[i].toLowerCase().contains("min")) {
                    minutes = Integer.parseInt(parts[i - 1]);
                }
            }
            return Duration.ofHours(hours).plusMinutes(minutes);
        } catch (Exception e) {
            logger.warn("Failed to extract effort");
            return Duration.ZERO;
        }
    }

    public static List<String> extractSkills(WebElement card) {
        List<String> skills = new ArrayList<>();
        try {
            List<WebElement> skillElements = card.findElements(SKILLS_LOCATOR);
            for (WebElement skill : skillElements) {
                skills.add(skill.getText().trim());
            }
        } catch (Exception e) {
            logger.warn("Skills not found");
        }
        return skills;
    }
}
