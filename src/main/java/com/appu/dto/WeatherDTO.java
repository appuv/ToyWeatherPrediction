package com.appu.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import com.appu.enums.WeatherConditions;
import com.appu.utils.constants.Constants;

/**
 * Weather Data Details for the end user 
 * 
 * Date Mar 17 2017
 * 
 * @author Appu V
 * @version 0.1
 * 
 */
public class WeatherDTO {
	// Sydney|-33.86,151.21,39|2015-12-23T05:02:12Z|Rain|+12.5|1004.3|97
	// Location Position Local Time Conditions

	/**
	 * Represents the location where the weather record is captured.
	 */
	private String location;

	/**
	 * Coordinates of the location.
	 */
	private GeoLocationDTO position;

	/**
	 * Calendar represents the time when the weather record captured.
	 */
	private Calendar time;

	/**
	 * Indicates the summary of weather condition. Can be either of
	 * <em>Rain,Snow</em> or <em>Sunny</em>
	 */
	private WeatherConditions conditions;

	/**
	 * Temperature meassured.
	 */
	private double temperature;
	/**
	 * Pressure meassured.
	 */
	private double pressure;
	/**
	 * Humidity meassured.
	 */
	private double humidity;

	public WeatherDTO() {
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public WeatherConditions getCondition() {
		return conditions;
	}

	public void setCondition(WeatherConditions condition) {
		this.conditions = condition;
	}

	public GeoLocationDTO getPosition() {
		return position;
	}

	public void setPosition(GeoLocationDTO position) {
		this.position = position;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	@Override
	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_1, Locale.US);
		dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
		return String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s", location, Constants.DELIMITTER_PIPE, position,
				Constants.DELIMITTER_PIPE, dateFormat.format(time.getTime()), Constants.DELIMITTER_PIPE, conditions,
				Constants.DELIMITTER_PIPE, temperature, Constants.DELIMITTER_PIPE, pressure, Constants.DELIMITTER_PIPE,
				humidity);
	}
}
