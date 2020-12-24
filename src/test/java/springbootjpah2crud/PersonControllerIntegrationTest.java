package springbootjpah2crud;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.mars.Application;
import com.mars.controller.PersonController;
import com.mars.model.Person;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class PersonControllerIntegrationTest {
	
	@Autowired
    private TestRestTemplate restTemplate;
	@Autowired
	private PersonController personController;

	
    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() throws Exception {
    	assertThat(personController).isNotNull();
    	

    }

    @Test
    public void testGetAllPersons() throws URISyntaxException {
    HttpHeaders headers = new HttpHeaders();
       HttpEntity<String> entity = new HttpEntity<String>(null, headers);
       ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "api/persons",HttpMethod.GET, entity, String.class);
       Assertions.assertEquals(200, response.getStatusCodeValue());
       assertNotNull(response.getBody());
       
   }

   @Test
   public void testGetpersonById() {
       Person person = restTemplate.getForObject(getRootUrl() + "api/persons/1", Person.class);
       assertNotNull(person);
   }

   @Test
   public void testCreateperson() {
	   Person person = new Person();
	   person.setEmailId("admin@gmail.com");
	   person.setFirstName("admin");
	   person.setLastName("admin");
       ResponseEntity<Person> postResponse = restTemplate.postForEntity(getRootUrl() + "api/persons", person, Person.class);
       assertNotNull(postResponse);
       assertNotNull(postResponse.getBody());
   }

   @Test
   public void testUpdatePerson() {
       int id = 1;
       Person person = restTemplate.getForObject(getRootUrl() + "api/persons/" + id, Person.class);
       person.setFirstName("admin1");
       person.setLastName("admin2");
       restTemplate.put(getRootUrl() + "/person/" + id, person);
       Person updatedPerson = restTemplate.getForObject(getRootUrl() + "api/persons/" + id, Person.class);
       assertNotNull(updatedPerson);
   }

   @Test
   public void testDeletePerson() {
        long id=2l;
        Person person = restTemplate.getForObject(getRootUrl() + "api/persons/" + id, Person.class);
        assertNotNull(person);
        restTemplate.delete(getRootUrl() + "api/persons/" + id);
        
		/*
		 * try { person = restTemplate.getForObject(getRootUrl() + "api/persons/" + id,
		 * Person.class); } catch (final HttpClientErrorException e) {
		 * assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND); } finally {
		 * assertEquals(200, HttpStatus.FOUND); }
		 */
   }
   
   @Test
	public void testForTotalCount() throws URISyntaxException {

	   HttpHeaders headers = new HttpHeaders();
       HttpEntity<String> entity = new HttpEntity<String>(null, headers);
       ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "api/personCount",HttpMethod.GET, entity, String.class);
       Assertions.assertEquals(200, response.getStatusCodeValue());
       assertNotNull(response.getBody());

	}
   
}


