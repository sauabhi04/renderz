package com.renderz.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/appFeatures/", 
			glue = { "com.renderz.stepDef"/*, "com.renderz.hooks"*/ }, 
			//tags = "@RunOnly",
			plugin = { "pretty", "html:target/reports" },
			monochrome = true
			)

public class TestRunner extends AbstractTestNGCucumberTests{

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
