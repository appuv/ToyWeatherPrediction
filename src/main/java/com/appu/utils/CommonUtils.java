package com.appu.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import com.appu.enums.WeatherConditions;
import com.appu.utils.constants.Constants;
import com.appu.utils.constants.NumericAttributeMapping;

/**
 * Common Utils Date
 * 
 * Mar 22 2017
 * 
 * @author Appu V
 * @version 0.1 *
 */
public class CommonUtils {

	final static Logger logger = Logger.getLogger(CommonUtils.class);

	/**
	 * @param latitude
	 * @param longitude
	 * @param elevation
	 * @return
	 * 
	 * 		can use google maps api to find the location
	 */
	public static String findLocation(double latitude, double longitude, double elevation) {
		return Constants.NOT_AVAILABLE;
	}

	/**
	 * 
	 * @param unixTime
	 * @return
	 * 
	 * 		To return calender based on unix time
	 */
	public static Calendar calenderTimeFromUnixTime(long unixTime) {
		Date date = new Date();
		date.setTime(unixTime * 1000);
		Calendar myCal = new GregorianCalendar();
		myCal.setTime(date);
		return myCal;

	}

	/**
	 * 
	 * @param condition
	 * @return Populate the condition based on Mapping
	 */

	public static WeatherConditions poulateCondition(double condition) {
		if (condition == NumericAttributeMapping.RAIN_MAPPING)
			return WeatherConditions.Rain;
		else if (condition == NumericAttributeMapping.SNOW_MAPPING)
			return WeatherConditions.Snow;
		else if (condition == NumericAttributeMapping.SUNNY_MAPPING)
			return WeatherConditions.Sunny;
		else
			return null;

	}

	/**
	 * 
	 * 
	 * @param timeinMilliSec
	 * @return
	 * 
	 * 		For setting all the year to a default year and reduce the effect
	 *         of year Currently the feature of time is (month*10+(day/100)) for
	 *         normalizing time feature
	 */
	@SuppressWarnings("deprecation")
	public static double timeAttributeParser(long timeinMilliSec) {
		Date date = new Date(timeinMilliSec * 1000);

		return (date.getMonth() * 10 + ((double) date.getDay()) / 100);

	}

	/**
	 * 
	 * @param Data
	 * @param outputLocation
	 * @return
	 * 
	 * 		saves outputData
	 */
	public static boolean writeData(String data, String outputLocation) {
		boolean returnBoolean = false;
		Writer writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(outputLocation));

			writer.write(data + "\n");
			writer.flush();
			returnBoolean = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}

		}
		return returnBoolean;
	}

}
