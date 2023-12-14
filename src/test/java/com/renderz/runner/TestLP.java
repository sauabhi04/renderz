package com.renderz.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/appFeatures/landingpage.feature", 
		glue = { "com.renderz.stepDef", "com.renderz.renderzhooks" },
		publish = false,
		plugin = { "pretty", "html:target/reports" },
		monochrome = true
)

public class TestLP  extends AbstractTestNGCucumberTests{

}
