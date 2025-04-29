package org.example.demo.tests.steps.hooks;

import io.cucumber.java.*;
import org.example.demo.utils.config.ConfigReader;
import org.example.demo.utils.driver.DriverManager;
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
