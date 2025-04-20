package org.example.demo.pages;

import org.example.demo.elementcore.elements.PageElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {
    private final Logger logger = LoggerFactory.getLogger(HomePage.class);

    @FindBy(css = "a.AppMenuItem_appMenuItem__-8c3R[href='/catalog']")
    private PageElement catalogLink;

    @FindBy(id = "onetrust-accept-btn-handler")
    private PageElement cookieAcceptButton;

    public HomePage() {
        super();
    }

    public CatalogMainPage goToCatalog() {
        logger.info("Navigating to catalog page");
        catalogLink.clickWithJS();
        return new CatalogMainPage();
    }

    public void acceptCookies() {
        try {
            cookieAcceptButton.waitUntilClickable().click();
        } catch (TimeoutException e) {
            logger.warn("Cookie accept button not found, skipping");
        }
    }
}
