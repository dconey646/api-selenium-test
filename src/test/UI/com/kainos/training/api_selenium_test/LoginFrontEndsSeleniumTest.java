package com.kainos.training.api_selenium_test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LoginFrontEndsSeleniumTest{
	
	private WebDriver driver;
	private WebElement usernameInput;
	private WebElement passwordInput;
	private WebElement submitButton;
	
	@Before
	public void setup(){
		driver = new FirefoxDriver();
		driver.get("http://localhost:8900/test/login");
		usernameInput = driver.findElement(By.id("usernameInput"));
		passwordInput = driver.findElement(By.id("passwordInput"));
		submitButton = driver.findElement(By.id("submitButton"));
	}
	
	@After
	public void close(){
		driver.quit();
	}
	
	@Test
	public void shouldShowLoginSuccessScreenWhenCorrectUsernameAndPassword(){
		usernameInput.sendKeys("admin");
		passwordInput.sendKeys("password");
		
		submitButton.submit();
		assertTrue(driver.findElement(By.id("content")).isDisplayed());
		assertThat(driver.findElement(By.id("content")).getText().contains("You have successfully logged in!"), is(true));
	}
	
	@Test
	public void shouldShowLoginFailureScreenWhenIncorrectUsernameAndPassword(){
		usernameInput.sendKeys("not an admin");
		passwordInput.sendKeys("not a password");

		submitButton.submit();
		assertTrue(driver.findElement(By.id("content")).isDisplayed());
		assertThat(driver.findElement(By.id("content")).getText().contains("Login Failed"), is(true));
	}
}
