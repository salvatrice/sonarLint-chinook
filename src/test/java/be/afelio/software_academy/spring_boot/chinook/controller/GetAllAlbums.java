package be.afelio.software_academy.spring_boot.chinook.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GetAllAlbums {

	@Autowired TestRestTemplate restTemplate;

	
	@Test
	public void test() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity("/album", String.class);
		assertEquals(200, response.getStatusCodeValue());
	}

}
