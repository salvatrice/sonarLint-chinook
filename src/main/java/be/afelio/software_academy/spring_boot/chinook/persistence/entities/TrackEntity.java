package be.afelio.software_academy.spring_boot.chinook.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="Track")
@Table(name="track")
public class TrackEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="trackid") 
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="milliseconds")
	private Integer seconds;
	
	@Column(name="composer")
	private String composer;
	
	@Column(name="bytes")
	private Integer bytes;
	
	@Column(name="unitprice")
	private Float price;
	
	@ManyToOne
	@JoinColumn(name="albumid")
	private AlbumEntity album;
	
	@ManyToOne
	@JoinColumn(name="genreid")
	private GenreEntity genre;
	
	@ManyToOne
	@JoinColumn(name="mediatypeid")
	private MediaTypeEntity mediaType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AlbumEntity getAlbum() {
		return album;
	}

	public void setAlbum(AlbumEntity album) {
		this.album = album;
	}

	public GenreEntity getGenre() {
		return genre;
	}

	public void setGenre(GenreEntity genre) {
		this.genre = genre;
	}

	public MediaTypeEntity getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaTypeEntity mediaType) {
		this.mediaType = mediaType;
	}

	public Integer getSeconds() {
		return seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
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


	
}
