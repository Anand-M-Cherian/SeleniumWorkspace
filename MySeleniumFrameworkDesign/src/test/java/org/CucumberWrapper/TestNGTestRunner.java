package org.CucumberWrapper;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// Firstly we have to provide helper attributes under Cucumber options

// By default, output of cucumber is in encoded format. Hence, use monochrome attribute

// all plugins should be key value pairs: key -> what kind of report is needed
// value -> target location of report

// AbstractTestNGCucumberTests contains all the Cucumber wrappers for running TestNG code
// What kind of runner to use??
// Depends on the assertions in our code -> JUnit v/s TestNG

// When run, the feature files in the path are executed one by one.

// Glue - specify the name of the *****PACKAGE*****, where step definitions are stored.

// Tags can be used to group TCs and run selectively. Similar to Groups in TestNG

// Cucumber can also be run from command terminal using maven. Refer pom.xml file
@CucumberOptions(features = "src/test/resources/BehaviourDrivenDevelopment",
        glue = "org/TestUtilities/CucumberStepDefinitions",
        monochrome = true, tags = "@Default", plugin = {"html:reports/CucumberReport.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
