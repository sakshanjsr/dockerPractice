package stepDefinitions;


import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import base.baseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pagess.login_Page;
import utils.highlightElement;

public class login_stepDeff extends baseClass{
	
	private WebDriver driver;
	private login_Page login_Page;
	private Properties props; 
	private WebDriverWait wait ;
	SoftAssert soft = new SoftAssert();
	//18002665844

	public  login_stepDeff() throws IOException {
		driver = baseClass.driver;
        props = baseClass.props;
        login_Page = new login_Page(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5)); 
	}
	
	
    @Severity(SeverityLevel.BLOCKER)
	@Given("The user in on the Login page")
	public void the_user_in_on_the_Login_page() {
	    // Write code here that turns the phrase above into concrete actions
    	System.out.println("*********************BACKGROUND***********************");
		driver.get(props.getProperty("url"));
	}
	
    @Severity(SeverityLevel.NORMAL)
    @Story("Login Story")
	@When("The user enter username and password")
	public void the_user_enter_username_and_password() {
	    // Write code here that turns the phrase above into concrete actions
		login_Page.enterUsername(props.getProperty("username"));
		login_Page.enterPassword(props.getProperty("password"));
		
	   
	}
	
    @Severity(SeverityLevel.CRITICAL)
	@When("Click on Login button")
	public void click_on_login_button() {
		login_Page.clickSublitButton();
	    // Write code here that turns the phrase above into concrete actions
	    
	}
	
    @When("The user enter {string} and password")
    public void the_user_enter_invalid_username(String invalid_username) {
    	login_Page.enterUsername(invalid_username);
    	login_Page.enterPassword("Password123");
    }

	@Then("User is navigated to home page")
	public void user_is_navigated_to_home_page() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement msg = driver.findElement(By.cssSelector(".post-title"));
		String loginMsg = msg.getText();
		if(loginMsg.matches("Logged In Successfully")) {
			System.out.println("Logged In Successfully");
		}else {
			System.out.println("ERROR");
			Assert.fail("Failed due to unccessful Logged in message ");
		}
	   
	}
	
	@Then("User is not navigated to home page")
	public void user_is_not_navigated_to_home_page() {
	    // Write code here that turns the phrase above into concrete actions
		
		
		WebElement msg = driver.findElement(By.cssSelector("#error.show"));
		wait.until(ExpectedConditions.visibilityOf(msg));
		String invalidLogin = msg.getText();
		System.out.println("invalidLogin msg : "+invalidLogin);
		soft.assertEquals(invalidLogin , "Your username is invaliddd!");
	}
	
	@Then("Check After Assert")
	public void check_after_assert() {
	    // Write code here that turns the phrase above into concrete actions
		soft.assertAll();
	    System.out.println("After Assert");
	}
	
	
	@When("Check the datatable")
	public void check_the_datatable(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	    
		List<Map<String, String>> userMap = dataTable.asMaps(String.class , String.class);
		
		System.out.println(userMap);
		
		
	}
	
	
	@Then("checklist")
	public void checklist(List<String> names) {
		System.out.println("Method");
		System.out.println(names);
	}


}
