package runner;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.dockerjava.api.model.Driver;

import base.baseClass;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@Test
@CucumberOptions(
		features = "src\\test\\resources\\features",
		glue = "stepDefinitions",
		//tags = "@Login , @CheckHomePage",
		plugin = {"pretty" , "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
		monochrome = true
	)



public class TestRunner extends AbstractTestNGCucumberTests{

	@BeforeClass(alwaysRun = true)
	public static void runnn() throws IOException {
		baseClass.initializeDriver();
	}
	
	@AfterClass(alwaysRun = true)
	public static void runnnAfter() throws IOException {
		baseClass.closeDriver();
	}
	
	
}
