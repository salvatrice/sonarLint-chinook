package be.afelio.software_academy.spring_boot.chinook.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.software_academy.spring_boot.chinook.api.dto.AlbumDto;
import be.afelio.software_academy.spring_boot.chinook.api.dto.ResponseDto;
import be.afelio.software_academy.spring_boot.chinook.api.dto.ResponseDtoStatus;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class FindAllAlbumByArtist {
	
	@Autowired TestRestTemplate restTemplate;
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void test() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity("/album/artist/IronMaiden", String.class);
		assertEquals(200, response.getStatusCodeValue());
		
		String json = response.getBody();
		
		// mapper.readValue(json, ResponseDto<CustomerDto>.class)
		TypeReference<ResponseDto<List<AlbumDto>>> type = new TypeReference<ResponseDto<List<AlbumDto>>>() {};
		ResponseDto<List<AlbumDto>> responseDto = mapper.readValue(json, type);
		
		assertEquals(ResponseDtoStatus.SUCCESS.name(), responseDto.getStatus());
		
		AlbumDto expected = createIronMaiden();
		List<AlbumDto> actual = responseDto.getPayload();
		assertTrue(actual.contains(expected));
	}

	AlbumDto createIronMaiden() {
		List<String> listGenre = new ArrayList<String>();
		listGenre.add("Rock");
		AlbumDto album = new AlbumDto("A Matter of Life And Death", "Iron Maiden", listGenre);
		return album;
	}
}
