package org.example.demo.utils.selenium;

import org.example.demo.elementcore.elements.PageElement;
import org.example.demo.elementcore.factory.WrapperFactory;
import org.example.demo.pages.CatalogMainPage;
import org.example.demo.utils.driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.function.BooleanSupplier;

public class Waiter {
    private static final Logger logger = LoggerFactory.getLogger(Waiter.class);

    private static final Duration TIMEOUT = Duration.ofSeconds(8);
    private static final Duration POLLING_INTERVAL = Duration.ofMillis(500);

    private Waiter() {
    }

    private static WebDriverWait getWait() {
        return new WebDriverWait(DriverManager.getDriver(), TIMEOUT);
    }

    public static PageElement waitForElementToBeVisible(By locator) {
        WebElement element = getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        return WrapperFactory.createInstance(PageElement.class, element, locator);
    }

    public static PageElement waitForElementToBeVisible(PageElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element.getElement()));
        return element;
    }

    public static List<WebElement> waitForAllElementsToBeVisible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static PageElement waitForElementToBeClickable(By locator) {
        WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
        return WrapperFactory.createInstance(PageElement.class, element, locator);
    }

    public static PageElement waitForElementToBeClickable(PageElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element.getElement()));
        return element;
    }

    public static PageElement waitForElementToBePresent(By locator) {
        WebElement element = getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
        return WrapperFactory.createInstance(PageElement.class, element, locator);
    }

    public static List<WebElement> waitForAllElementsToBePresent(By locator) {
        return getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static void waitForAllElementsToBeInvisible(By locator) {
        getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitForAllElementsToBeInvisible(PageElement element) {
        getWait().until(ExpectedConditions.invisibilityOf(element.getElement()));
    }

    public static FluentWait<WebDriver> getFluentWait() {
        return new FluentWait<>(DriverManager.getDriver())
                .withTimeout(TIMEOUT)
                .pollingEvery(POLLING_INTERVAL);
    }

    public static void waitForElementToVanish(WebElement element) {
        long startTime = System.currentTimeMillis();
        try {
            while (element.isDisplayed()) {
                if (System.currentTimeMillis() - startTime > TIMEOUT.getSeconds()) {
                    return;
                }
                Thread.sleep(200);
            }
        } catch (StaleElementReferenceException | NoSuchElementException e) {
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void waitForElementsToVanish(List<PageElement> elements) {
        for (PageElement el : elements) {
            waitForElementToVanish(el.getElement());
        }
    }

    public static void waitSoftForCondition(BooleanSupplier condition) {
        try {
            getFluentWait()
                    .ignoring(StaleElementReferenceException.class)
                    .until(driver -> condition.getAsBoolean());
        } catch (TimeoutException e) {
            logger.warn("Condition not met within timeout {}", TIMEOUT);
        }
    }

}
