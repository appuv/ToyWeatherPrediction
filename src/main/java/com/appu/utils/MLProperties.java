package com.appu.utils;

import java.util.ResourceBundle;

import com.appu.utils.constants.PropertFileConstants;

/**
 * ML Properties Class.
 * 
 * Date Mar 22 2017
 * 
 * @author Appu V
 * @version 0.1
 * 
 */
public final class MLProperties {

	private static final ResourceBundle rb = ResourceBundle.getBundle("ml");

	public static final String CLASSIFIER_NAME = rb.getString(PropertFileConstants.CLASSIFIER_NAME_KEY);

	public static final String CONDITION_MODEL_LOCATION = rb
			.getString(PropertFileConstants.CONDITION_MODEL_LOCATION_KEY);
	public static final String HUMIDITY_MODEL_LOCATION = rb.getString(PropertFileConstants.HUMIDITY_MODEL_LOCATION_KEY);
	public static final String TEMPERATURE_MODEL_LOCATION = rb
			.getString(PropertFileConstants.TEMPERATURE_MODEL_LOCATION_KEY);
	public static final String PRESSURE_MODEL_LOCATION = rb.getString(PropertFileConstants.PRESSURE_MODEL_LOCATION_KEY);

	public static final String CONDITION_CSV_TRAINING_DATA_LOCATION = rb
			.getString(PropertFileConstants.CONDITION_CSV_TRAINING_DATA_LOCATION_KEY);
	public static final String CONDITION_LIBSVM_TRAINING_DATA_LOCATION = rb
			.getString(PropertFileConstants.CONDITION_LIBSVM_TRAINING_DATA_LOCATION_KEY);
	public static final String HUMIDITY_CSV_TRAINING_DATA_LOCATION = rb
			.getString(PropertFileConstants.HUMIDITY_CSV_TRAINING_DATA_LOCATION_KEY);
	public static final String HUMIDITY_LIBSVM_TRAINING_DATA_LOCATION = rb
			.getString(PropertFileConstants.HUMIDITY_LIBSVM_TRAINING_DATA_LOCATION_KEY);
	public static final String TEMPERATURE_CSV_TRAINING_DATA_LOCATION = rb
			.getString(PropertFileConstants.TEMPERATURE_CSV_TRAINING_DATA_LOCATION_KEY);
	public static final String TEMPERATURE_LIBSVM_TRAINING_DATA_LOCATION = rb
			.getString(PropertFileConstants.TEMPERATURE_LIBSVM_TRAINING_DATA_LOCATION_KEY);
	public static final String PRESSURE_CSV_TRAINING_DATA_LOCATION = rb
			.getString(PropertFileConstants.PRESSURE_CSV_TRAINING_DATA_LOCATION_KEY);
	public static final String PRESSURE_LIBSVM_TRAINING_DATA_LOCATION = rb
			.getString(PropertFileConstants.PRESSURE_LIBSVM_TRAINING_DATA_LOCATION_KEY);

	public static final String TRAIN_SIZE = rb.getString(PropertFileConstants.TRAIN_SIZE_KEY);
	public static final String TEST_SIZE = rb.getString(PropertFileConstants.TEST_SIZE_KEY);
	public static final String CLASSIFIER_CLASSES = rb.getString(PropertFileConstants.CLASSIFIER_CLASSES_KEY);
	public static final String CLASSIFIER_IMPURITY = rb.getString(PropertFileConstants.CLASSIFIER_IMPURITY_KEY);
	public static final String CLASSIFIER_MAX_DEPTH = rb.getString(PropertFileConstants.CLASSIFIER_MAX_DEPTH_KEY);
	public static final String CLASSIFIER_MAX_BIN = rb.getString(PropertFileConstants.CLASSIFIER_MAX_BIN_KEY);
	public static final String TEMPERATURE_MODEL_NUM_ITERATION = rb
			.getString(PropertFileConstants.TEMPERATURE_MODEL_NUM_ITERATION_KEY);
	public static final String TEMPERATURE_MODEL_STEP_SIZE = rb
			.getString(PropertFileConstants.TEMPERATURE_MODEL_STEP_SIZE_KEY);
	public static final String HUMIDITY_MODEL_NUM_ITERATION = rb
			.getString(PropertFileConstants.HUMIDITY_MODEL_NUM_ITERATION_KEY);
	public static final String HUMIDITY_MODEL_STEP_SIZE = rb
			.getString(PropertFileConstants.HUMIDITY_MODEL_STEP_SIZE_KEY);
	public static final String PRESSURE_MODEL_NUM_ITERATION = rb
			.getString(PropertFileConstants.PRESSURE_MODEL_NUM_ITERATION_KEY);
	public static final String PRESSURE_MODEL_STEP_SIZE = rb
			.getString(PropertFileConstants.PRESSURE_MODEL_STEP_SIZE_KEY);

}
