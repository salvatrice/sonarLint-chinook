package be.afelio.software_academy.spring_boot.chinook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import be.afelio.software_academy.spring_boot.chinook.api.dto.AlbumDto;
import be.afelio.software_academy.spring_boot.chinook.api.dto.ResponseDto;
import be.afelio.software_academy.spring_boot.chinook.api.dto.ResponseDtoStatus;
import be.afelio.software_academy.spring_boot.chinook.persistence.ApplicationRepository;


@Controller
@RequestMapping(value="album")
public class AlbumController {
	
	// pour remplacer e.printStackTrace();
	private static final Logger Log = LoggerFactory.getLogger(AlbumController.class);
	
	String albumNotFound = "Album not found";
	String unexpectedException = "unexpected exception";

	@Autowired ApplicationRepository repository;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<AlbumDto>>> findAllAlbums() {

		ResponseDto<List<AlbumDto>> dto = null;
		try {
			List<AlbumDto> album = repository.findAllAlbums();
			if (album == null) {
				dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, albumNotFound);
			} else {
				dto = new ResponseDto<>(ResponseDtoStatus.SUCCESS, "ok");
				dto.setPayload(album);
			}
		} catch(Exception e) {
			dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, unexpectedException);
			Log.trace("Hello Trace", e); // = e.printStackTrace();
			
		}
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(value = "artist/{name}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<AlbumDto>>> findOne(
			@PathVariable("name") String name) {
				
		ResponseDto<List<AlbumDto>> dto = null;
		try {
			List<AlbumDto> album = repository.findAllAlbumByArtist(name);
			if (album == null) {
				dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, albumNotFound);
			} else {
				dto = new ResponseDto<>(ResponseDtoStatus.SUCCESS, "ok");
				dto.setPayload(album);
			}
		} catch(Exception e) {
			dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, unexpectedException);
			Log.trace("Hello Trace", e);
		}
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(value = "genre/{genreName}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<AlbumDto>>> findOneByGenreName(
			@PathVariable("genreName") String genreName) {
				
		ResponseDto<List<AlbumDto>> dto = null;
		try {
			List<AlbumDto> album = repository.findAllAlbumByGenre(genreName);
			if (album == null) {
				dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, albumNotFound);
			} else {
				dto = new ResponseDto<>(ResponseDtoStatus.SUCCESS, album.size() + " albums found");
				dto.setPayload(album);
			}
		} catch(Exception e) {
			dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, unexpectedException);
			Log.trace("Hello Trace", e);
		}
		
		return ResponseEntity.ok(dto);
	}
}
