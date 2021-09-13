package com.appu.utils.test;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appu.enums.WeatherConditions;
import com.appu.utils.CommonUtils;
import com.appu.utils.constants.Constants;
import com.appu.utils.constants.NumericAttributeMapping;

/**
 * Test Class for CommonUtils
 * 
 * Date Mar 22 2017
 * 
 * @author Appu V
 * @version 0.1
 */

public class CommonUtilsTest {
	/**
	 * Logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(CommonUtilsTest.class);
	/**
	 * dummy value for populating condition
	 */
	private static int dummy;
	/**
	 * Unix timestamp for getting calendar
	 */
	private static long unixTime;
	/**
	 * calendar for checking the function
	 */
	private static Calendar calendar;
	/**
	 * Delta value for unit floating comparisons
	 */
	private static int delta;
	
	/**
	 * parsed time attvale
	 */
	private static double timeatt;
	
	/**
	 * sample output
	 */
	private static String sampOutput;
	
	/**
	 * sampleLocation
	 */
	private static String sampleLocation;
	
	
	
	/**
	 * Load Datas for initial Testing
	 */
	

	@SuppressWarnings("deprecation")
	@Before
	public void loadDatas() {
		dummy = 600;
		delta = 0;
		unixTime = 1490147427420L;
		DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT_1, Locale.US);
		dateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
		Date date = new Date(unixTime*1000);
		dateFormat.format(date);
		timeatt=(date.getMonth() * 10 + ((double) date.getDay()) / 100);
		calendar = new GregorianCalendar();
		calendar.setTime(date);
		sampOutput="TEST";
		sampleLocation="target/test.txt";
		logger.info("Loaded datas for testing com.appu.utils.CommonUtils");

	}

	/**
	 * Test for Finding Location
	 */
	@Test
	public void findLocationTest() {
		assertEquals(CommonUtils.findLocation(dummy, dummy, dummy), Constants.NOT_AVAILABLE);
		logger.info("Completed Test cases  for com.appu.utils.CommonUtils.findLocation");

	}

	/**
	 * Testing the feature parsing of time
	 */
	@Test
	public void timeAttributeParserTest() {
		calendar.set(Calendar.YEAR, Constants.DEFAULT_YEAR);
	assertEquals(CommonUtils.timeAttributeParser(unixTime), timeatt, delta);
		logger.info("Completed Test cases  for com.appu.utils.CommonUtils.timeAttributeParser");

	}

	/**
	 * Test for getting calender from Timestamp
	 */
	@Test
	public void calenderTimeFromUnixTimeTest() {
		assertEquals(CommonUtils.calenderTimeFromUnixTime(unixTime).getTime(), calendar.getTime());
		logger.info("Completed Test cases  for com.appu.utils.CommonUtils.calenderTimeFromUnixTime");

	}

	/**
	 * Test for populating Condition
	 */
	@Test
	public void poulateConditionTest() {
		assertEquals(CommonUtils.poulateCondition(dummy), null);
		assertEquals(CommonUtils.poulateCondition(NumericAttributeMapping.RAIN_MAPPING), WeatherConditions.Rain);
		assertEquals(CommonUtils.poulateCondition(NumericAttributeMapping.SNOW_MAPPING), WeatherConditions.Snow);
		assertEquals(CommonUtils.poulateCondition(NumericAttributeMapping.SUNNY_MAPPING), WeatherConditions.Sunny);
		logger.info("Completed Test cases  for com.appu.utils.CommonUtils.poulateCondition");

	}
	
	/**
	 * Test for writeData
	 */
	@Test
	public void writeDataTest() {
		assertEquals(CommonUtils.writeData(null,null), false);
		assertEquals(CommonUtils.writeData(sampOutput,sampleLocation), true);

		
		logger.info("Completed Test cases  for com.appu.utils.CommonUtils.writeData");

	}

}
