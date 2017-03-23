package com.appu.exceptions;

import org.apache.log4j.Logger;

/**
 * ToyWeatherPredictorException represents all possible exceptions in ToyWeather
 * Predictor. Exception is identified by the message passed.
 * 
 * * Date Mar 20 2017
 * 
 * @author Appu V
 * @version 0.1
 *
 */
public class ToyWeatherPredictorException extends Exception {

	private static final long serialVersionUID = 3725912523790905L;
	final static Logger logger = Logger.getLogger(ToyWeatherPredictorException.class);

	/**
	 * 
	 * @param message
	 *            Passing message argument to superclass. @see
	 *            {@link java.lang.Exception#Exception(String)}
	 */
	public ToyWeatherPredictorException(String message) {
		super(message);
		logger.debug("Exception thrown :: " + message);
	}

}