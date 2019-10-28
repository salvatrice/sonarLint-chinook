package be.afelio.software_academy.spring_boot.chinook.api.dto;

import java.util.List;

import be.afelio.software_academy.spring_boot.chinook.persistence.entities.AlbumEntity;
import be.afelio.software_academy.spring_boot.chinook.persistence.entities.ArtistEntity;

public class TrackDto {

	String name;
	String composer;
	Integer seconds;
	Integer bytes;
	Float price;
	String album;
	String artist;
	String mediatype;
	String genre;

	public TrackDto() {
	}

	public TrackDto(String name, String composer, Integer seconds, Integer bytes, Float price, String album,
			String artist, String mediatype, String genre) {
		super();
		this.name = name;
		this.composer = composer;
		this.seconds = seconds;
		this.bytes = bytes;
		this.price = price;
		this.album = album;
		this.artist = artist;
		this.mediatype = mediatype;
		this.genre = genre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public Integer getSeconds() {
		return seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}

	public Integer getBytes() {
		return bytes;
	}

	public void setBytes(Integer bytes) {
		this.bytes = bytes;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	
	public String getMediatype() {
		return mediatype;
	}

	public void setMediatype(String mediatype) {
		this.mediatype = mediatype;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	

}
