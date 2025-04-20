package org.example.demo.utils.reporting;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.example.demo.utils.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AllureAttachmentService {
    private static final Logger logger = LoggerFactory.getLogger(AllureAttachmentService.class);
    private static final String LOG_FILE_PATH = "target/report/logs/test.log";

    public static void attachScreenshot() {
        try {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.step("Attach failure screenshot", () -> {
                Allure.getLifecycle().addAttachment(
                        "Failure Screenshot", "image/png", ".png", new ByteArrayInputStream(screenshot));
            });
        } catch (Exception e) {
            logger.error("Failed to attach screenshot: {}", e.getMessage());
        }
    }


    public static void attachLogs() {
        try {
            byte[] logBytes = Files.readAllBytes(Paths.get(LOG_FILE_PATH));
            Allure.step("Attach execution logs", () -> {
                Allure.getLifecycle().addAttachment(
                        "Execution Logs", "text/plain", ".log", new ByteArrayInputStream(logBytes));
            });
        } catch (IOException e) {
            logger.error("Failed to attach logs: {}", e.getMessage());
        }
    }
}

