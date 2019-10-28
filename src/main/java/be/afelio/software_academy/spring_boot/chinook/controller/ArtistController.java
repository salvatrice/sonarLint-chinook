package be.afelio.software_academy.spring_boot.chinook.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import be.afelio.software_academy.spring_boot.chinook.persistence.ApplicationRepository;

@Controller
@RequestMapping(value="artist")
public class ArtistController {

@Autowired ApplicationRepository repository;
	
	
}
