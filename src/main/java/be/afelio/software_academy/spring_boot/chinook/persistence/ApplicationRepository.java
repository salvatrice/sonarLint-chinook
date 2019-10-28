package be.afelio.software_academy.spring_boot.chinook.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.afelio.software_academy.spring_boot.chinook.api.dto.AlbumDto;
import be.afelio.software_academy.spring_boot.chinook.api.dto.TrackDto;
import be.afelio.software_academy.spring_boot.chinook.persistence.entities.AlbumEntity;
import be.afelio.software_academy.spring_boot.chinook.persistence.entities.GenreEntity;
import be.afelio.software_academy.spring_boot.chinook.persistence.entities.TrackEntity;
import be.afelio.software_academy.spring_boot.chinook.persistence.repositories.AlbumRepository;
import be.afelio.software_academy.spring_boot.chinook.persistence.repositories.ArtistRepository;
import be.afelio.software_academy.spring_boot.chinook.persistence.repositories.TrackRepository;

@Component
public class ApplicationRepository {

	@Autowired
	AlbumRepository albumRepository;
	@Autowired
	ArtistRepository artistRepository;
	@Autowired
	TrackRepository trackRepository;

	public List<AlbumDto> FindAllAlbums() {
		List<AlbumEntity> list = albumRepository.findAll();
		return createAlbumDto(list);
	}

	private List<AlbumDto> createAlbumDto(List<AlbumEntity> entity) {
		List<AlbumDto> list = new ArrayList<AlbumDto>();

		for (AlbumEntity elm : entity) {
			List<String> genre = new ArrayList<String>();
			for (TrackEntity track : elm.getTrackList()) {
				String s = track.getGenre().getGenreName();
				if (!genre.contains(s))
					genre.add(s);
			}
			list.add(new AlbumDto(elm.getTitle(), elm.getArtist().getName(), genre));
		}
		return list;
	}

	public List<AlbumDto> findAllAlbumByArtist(String name) {
		List<AlbumEntity> list = albumRepository.getAllByArtistName(name);
		return createAlbumDto(list);
	}

	public List<AlbumDto> findAllAlbumByGenre(String name) {
		List<AlbumEntity> listAlbumEntity = albumRepository.findAll();
		List<AlbumDto> listGoodAlbumDto = new ArrayList<AlbumDto>();

		for (AlbumEntity albumEntity : listAlbumEntity) {
			List<TrackEntity> trackEntities = albumEntity.getTrackList();
			Set<String> genres = new HashSet<String>();
			boolean good = false;
			for (TrackEntity track : trackEntities) {
				GenreEntity genreEntity = track.getGenre();
				genres.add(genreEntity.getGenreName());
				if (genreEntity.getGenreName().equals(name)) {
					good = true;
				}
			}
			if (good) {
				AlbumDto albumDto = new AlbumDto();
				albumDto.setTitle(albumEntity.getTitle());
				albumDto.setArtistName(albumEntity.getArtist().getName());
				List<String> genresAsList = new ArrayList<String>(genres);
				albumDto.setGenre(genresAsList);
				listGoodAlbumDto.add(albumDto);
			}
		}
		return listGoodAlbumDto;
	}

	public List<TrackDto> findAllTracksByAlbum(String name) {
		List<TrackEntity> list = trackRepository.findAllByAlbumTitle(name);
		return createTrackDto(list);
	}

	private List<TrackDto> createTrackDto(List<TrackEntity> entity) {
		List<TrackDto> list = new ArrayList<TrackDto>();

		for (TrackEntity trackEntity : entity) {
			TrackDto trackDto = new TrackDto();
			trackDto.setName(trackEntity.getName());
			trackDto.setComposer(trackEntity.getComposer());
			trackDto.setSeconds(trackEntity.getSeconds()/1000);
			trackDto.setBytes(trackEntity.getBytes());
			trackDto.setPrice(trackEntity.getPrice());
			trackDto.setMediatype(trackEntity.getMediaType().getName());
			trackDto.setAlbum(trackEntity.getAlbum().getTitle());
			trackDto.setArtist(trackEntity.getAlbum().getArtist().getName());
			trackDto.setGenre(trackEntity.getGenre().getGenreName());

			list.add(trackDto);
		}
		return list;
	}
}
