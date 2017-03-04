package hello.cucumber;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hello.Hello;
import static org.junit.Assert.assertEquals;

public class helloStep {
	
	Hello hello = null;
	String hi = null;

	@Given("^I have a greeting app with \"([^\"]*)\"$")
	public void i_have_a_greeting_app_with(String arg1) throws Throwable {
	    hello = new Hello(arg1);
	}

	@When("^I ask it to say hi$")
	public void i_ask_it_to_say_hi() throws Throwable {
	    hi = hello.sayHi();
	}

	@Then("^I receive \"([^\"]*)\"$")
	public void i_receive(String arg1) throws Throwable {
		assertEquals(arg1, hi);
	}
}
