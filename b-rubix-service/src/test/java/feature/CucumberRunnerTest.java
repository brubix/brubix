package feature;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber/reports.json"},
        glue = {"feature"},
        snippets = SnippetType.CAMELCASE,
        tags = {"~@skip", "~@ignore"},
        features = "src/test/resources/feature/inventory/school.feature")
public class CucumberRunnerTest {

}
