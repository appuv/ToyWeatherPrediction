package com.appu.utils;

import com.beust.jcommander.Parameter;

/**
 * commander CommandLine Parser.
 * 
 * Date Mar 22 2017
 * 
 * @author Appu V
 * @version 0.1
 * 
 */
public class Settings {
	/**
	 * For Getting Lattitude
	 */
	@Parameter(names = "--lat", description = "Latitude", required = true)
	private double latitude;

	/**
	 * For Getting Longitutde
	 */
	@Parameter(names = "--long", description = "Longitude", required = true)
	private double longitude;

	/**
	 * For Getting Elevation
	 */
	@Parameter(names = "--ele", description = "Elevation", required = true)
	private double elevation;

	/**
	 * For Getting Unix Time
	 */
	@Parameter(names = "--time", description = "Unix TimeStamp", required = true)
	private long unixTime;

	/**
	 * For getting output Location
	 */
	@Parameter(names = "--out", description = "Output Location", required = true)
	private String outLocation;

	/**
	 * For displaying help
	 */
	@Parameter(names = "--help", description = "Help", help = true)
	private boolean help = false;

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

	public long getUnixTime() {
		return unixTime;
	}

	public void setUnixTime(long unixTime) {
		this.unixTime = unixTime;
	}

	public String getOutLocation() {
		return outLocation;
	}

	public void setOutLocation(String outLocation) {
		this.outLocation = outLocation;
	}

	public boolean isHelp() {
		return help;
	}

	public void setHelp(boolean help) {
		this.help = help;
	}

}