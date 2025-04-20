package org.example.demo.tests.runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlSuite;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "org.example.demo.tests.steps",
                "org.example.demo.tests.steps.hooks"
        },
        plugin = {
                "pretty",
                "html:target/report/cucumber-reports.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
        //,tags = "@smoke or @regression"
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {

    //To control the number of threads in tests.
    // When running through Maven, the value in this method is used, so it should be removed
    @BeforeClass(alwaysRun = true)
    public void setupParallelism(ITestContext context) {
        int threadCount = Integer.parseInt(System.getProperty("threadCount", "6"));
        System.setProperty("dataproviderthreadcount", String.valueOf(threadCount));
        context.getCurrentXmlTest().getSuite().setParallel(XmlSuite.ParallelMode.METHODS);
        context.getCurrentXmlTest().getSuite().setThreadCount(threadCount);
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}