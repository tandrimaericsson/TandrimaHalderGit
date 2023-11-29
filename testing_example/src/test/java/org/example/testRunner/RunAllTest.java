package org.example.testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/org/example/features"},
        glue = {"org.example.stepDefinitions"},
        tags = "@ApiScenarios or @UiScenarios",
        plugin = {"html:target/cucumber-reports.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        ,snippets = CucumberOptions.SnippetType.UNDERSCORE
)
public class RunAllTest {

}