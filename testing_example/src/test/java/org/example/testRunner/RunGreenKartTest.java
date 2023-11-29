package org.example.testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/org/example/features"},
        glue = {"org.example.stepDefinitions"},
        tags = "@test",
        plugin = {"html:target/cucumber-reports.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        , snippets = io.cucumber.junit.CucumberOptions.SnippetType.UNDERSCORE
)
public class RunGreenKartTest {

}