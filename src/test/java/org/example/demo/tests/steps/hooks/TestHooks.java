package org.example.demo.tests.steps.hooks;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.Getter;
import org.example.demo.pages.CatalogMainPage;
import org.example.demo.pages.HomePage;
import org.example.demo.utils.config.ConfigReader;
import org.example.demo.utils.driver.DriverManager;
import org.example.demo.utils.reporting.AllureAttachmentService;
import org.example.demo.utils.reporting.ReportCleaner;
import org.example.demo.utils.reporting.ScreenshotService;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestHooks {
    private static final Logger logger = LoggerFactory.getLogger(TestHooks.class);

    @Before
    public void setUp() {
        logger.info("Initialize browser");
        WebDriver driver = DriverManager.getDriver();
        driver.get(ConfigReader.getProperty("base_url"));
    }

    @After
    public void tearDown() {
        logger.info("Quit driver");
        DriverManager.quitDriver();
    }
}
