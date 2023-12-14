package com.renderz.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/appFeatures/", 
			glue = { "com.renderz.stepDef", "com.renderz.renderzhooks" }, 
			//tags = "@RunOnly",
			publish = false,
			plugin = { "pretty", "html:target/reports", "rerun:target/failedtc.txt" },
			monochrome = true
			//dryRun = true
			)

public class TestRunner extends AbstractTestNGCucumberTests{

//    @Override
//    @DataProvider(parallel = false)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }
}
