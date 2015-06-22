package com.kainos.training.api_selenium_test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import javax.ws.rs.WebApplicationException;

import org.junit.Before;
import org.junit.Test;

import com.kainos.training.jersey.client.BaseClient;

public class LoginServiceApiTest {
	
	private BaseClient baseClient;
	private static final String CORRECT_USERNAME = "admin";
	private static final String CORRECT_PASSWORD = "password";
	private static final String INCORRECT_USERNAME = "not admin";
	private static final String INCORRECT_PASSWORD = "not password";
	
	@Before
	public void setup(){
		baseClient = new BaseClient();
	}

	@Test
    public void shouldSuccessfullyLoginWithRightCredentials(){		
		assertThat(baseClient.getLogin(CORRECT_USERNAME, CORRECT_PASSWORD), is(true));
	}
	
	@Test
	public void shouldFailLoginWithIncorrectCredentials(){
		try {
			baseClient.getLogin(INCORRECT_USERNAME, INCORRECT_PASSWORD);
		}catch(WebApplicationException e) {
			assertThat(e.getResponse().getStatus(), is(500));
		}
	}
}
