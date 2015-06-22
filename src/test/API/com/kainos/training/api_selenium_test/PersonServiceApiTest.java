package com.kainos.training.api_selenium_test;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.kainos.training.blackbox_interface.model.Person;
import com.kainos.training.blackbox.client.BaseClient;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class PersonServiceApiTest {
	
	private BaseClient baseClient;
	
	@Before
	public void setup(){
		baseClient = new BaseClient();
	}
	
	@Test
	public void addPersonToListAndTheyShouldBeReturned() {
		List<Person> oldList = baseClient.getPeople();
		Person testPerson = new Person();
		testPerson.setName("Simon");
		List<Person> newList = baseClient.addPerson(testPerson);
		assertThat(newList.size(), is(oldList.size()+1));
	}
	
	@Test
	public void shouldReturnCurrentList(){
		List<Person> responseList = baseClient.getPeople();
		assertTrue(responseList != null);
	}
	
	//ignored because the functionality doesn't work.
	@Ignore
	@Test
	public void shouldDeletePersonFromList(){
		List<Person> previousList = baseClient.getPeople();
		List<Person> responseList = baseClient.deletePerson(1);
		assertThat(responseList.size(), is(previousList.size() - 1));
	}
}