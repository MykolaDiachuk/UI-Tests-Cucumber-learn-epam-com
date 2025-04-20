package org.example.demo.elementcore.elements;

import lombok.Getter;
import lombok.Setter;
import org.example.demo.utils.driver.DriverManager;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;

import java.util.List;

import static org.example.demo.utils.selenium.Waiter.*;

@Setter
@Getter
public class PageElement implements WebElement {

    private WebElement element;
    private By locator;

    public PageElement(WebElement element, By locator) {
        this.element = element;
        this.locator = locator;
    }

    public PageElement( By locator) {
        this.locator = locator;
        this.element = waitUntilPresent().getElement();
    }

    public PageElement type(CharSequence... keysToSend) {
        waitUntilPresent();
        refreshElement().sendKeys(keysToSend);
        return this;
    }

    public PageElement clickWithJS() {
        highlight();
        waitUntilClickable();
        return doJS("arguments[0].click();");
    }

    public PageElement scrollTo() {
        return doJS("arguments[0].scrollIntoView({block: 'center'}); arguments[0].focus();");
    }

    public PageElement highlight() {
        return doJS("arguments[0].style.border='3px solid red'");
    }

    private PageElement doJS(String script) {
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript(script, refreshElement());
        return this;
    }

    public PageElement waitUntilClickable() {
        element = waitForElementToBeClickable(locator).getElement();
        return this;
    }

    public PageElement waitUntilPresent() {
        element = waitForElementToBePresent(locator).getElement();
        return this;
    }

    public PageElement waitUntilVisible() {
        element = waitForElementToBeVisible(locator).getElement();
        return this;
    }

    public WebElement refreshElement() {
        this.element = waitForElementToBePresent(locator).getElement();
        return element;
    }

    public boolean isExist() {
        try {
            DriverManager.getDriver().findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //WebElement methods
    @Override
    public void click() {
        highlight();
        waitUntilClickable();
        refreshElement().click();
    }
    @Override
    public void submit() {
        refreshElement().submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        refreshElement().sendKeys(keysToSend);
    }
    @Override
    public void clear() {
        refreshElement().clear();
    }
    @Override
    public String getTagName() {
        return refreshElement().getTagName();
    }

    @Override
    public @Nullable String getAttribute(String name) {
        return refreshElement().getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return waitUntilPresent().getElement().isSelected();
    }
    @Override
    public boolean isEnabled() {
        return refreshElement().isEnabled();
    }

    @Override
    public String getText() {
        return this.waitUntilVisible().getElement().getText();
    }
    @Override
    public List<WebElement> findElements(By by) {
        return refreshElement().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return refreshElement().isDisplayed();
    }
    @Override
    public Point getLocation() {
        return refreshElement().getLocation();
    }

    @Override
    public Dimension getSize() {
        return refreshElement().getSize();
    }

    @Override
    public Rectangle getRect() {
        return refreshElement().getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return refreshElement().getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return refreshElement().getScreenshotAs(target);
    }
}
