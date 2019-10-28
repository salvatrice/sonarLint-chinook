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

import be.afelio.software_academy.spring_boot.chinook.api.dto.ResponseDto;
import be.afelio.software_academy.spring_boot.chinook.api.dto.ResponseDtoStatus;
import be.afelio.software_academy.spring_boot.chinook.api.dto.TrackDto;
import be.afelio.software_academy.spring_boot.chinook.persistence.ApplicationRepository;

@Controller
@RequestMapping(value="track")
public class TrackController {
	
	private static final Logger Log = LoggerFactory.getLogger(TrackController.class);
	
	@Autowired ApplicationRepository repository;

	@GetMapping(value = "album/{albumName}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto<List<TrackDto>>> findOneByGenreName(
			@PathVariable("albumName") String albumName) {
				
		ResponseDto<List<TrackDto>> dto = null;
		try {
			List<TrackDto> track = repository.findAllTracksByAlbum(albumName);
			if (track == null) {
				dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "album not found");
			} else {
				dto = new ResponseDto<>(ResponseDtoStatus.SUCCESS, track.size() + " tracks found");
				dto.setPayload(track);
			}
		} catch(Exception e) {
			dto = new ResponseDto<>(ResponseDtoStatus.FAILURE, "unexpected exception");
			Log.trace("Hello Trace", e);
		}
		
		return ResponseEntity.ok(dto);
	}
}
