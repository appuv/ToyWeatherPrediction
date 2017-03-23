package com.appu.dto;

import com.appu.utils.constants.Constants;

/**
 * DTO of Geo Location
 * 
 * Date Mar 17 2017
 * 
 * @author Appu V
 * @version 0.1 *
 */
public class GeoLocationDTO {

	/**
	 * Latitude of location.
	 */
	private double latitude;
	/**
	 * Longitude of location.
	 */
	private double longitude;
	/**
	 * Elevation of location from sea level.
	 */
	private double elevation;

	public GeoLocationDTO() {
		super();
	}

	public GeoLocationDTO(double latitude, double longitude, double elevation) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getElevation() {
		return elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	@Override
	public String toString() {
		return latitude + Constants.DELIMITTER_COMA + longitude + Constants.DELIMITTER_COMA + elevation;
	}
}
