package br.com.teamfrank.domain.models.entities;

import lombok.Data;

@Data
public class Coordenada {
	private final String latitude;
	private final String longitude;

	public Coordenada(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}
}
