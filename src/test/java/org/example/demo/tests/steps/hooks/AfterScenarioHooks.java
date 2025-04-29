package org.example.demo.tests.steps.hooks;

import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.example.demo.utils.reporting.AllureAttachmentService;
import org.example.demo.utils.reporting.ReportCleaner;
import org.example.demo.utils.reporting.ScreenshotService;

public class AfterScenarioHooks {
    @BeforeAll
    public static void cleanAllureReports() {
        ReportCleaner.cleanReports();
    }

    @AfterStep
    public void onFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotService.saveScreenshotToFile(scenario.getName());
            AllureAttachmentService.attachScreenshot();
            AllureAttachmentService.attachLogs();
        }
    }
}
