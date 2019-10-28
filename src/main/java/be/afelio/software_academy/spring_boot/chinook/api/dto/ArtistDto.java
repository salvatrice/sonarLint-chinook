package be.afelio.software_academy.spring_boot.chinook.api.dto;

public class ArtistDto {
	
	String name;
	
	
	public ArtistDto() {}
	
	public ArtistDto(String title) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
