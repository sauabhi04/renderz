package com.renderz.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
					publish = false, 
					plugin = { "pretty", "html:target/re-reports"/*, "rerun:target/again_failed_tc.txt"*/ }, 
					monochrome = true,
					features = {"@target/failedtc.txt"},
					glue = { "com.renderz.stepDef", "com.renderz.renderzhooks" }
				)

public class TestReRunner extends AbstractTestNGCucumberTests {
//	@Override
//	@DataProvider(parallel = false)
//	public Object[][] scenarios() {
//		return super.scenarios();
//	}

}
