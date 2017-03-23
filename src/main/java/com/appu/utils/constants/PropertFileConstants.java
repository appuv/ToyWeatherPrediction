package com.appu.utils.constants;
/**
 * Constant class for Property File Keys .
 *
 * Date Mar 22 2017
 * 
 * @author Appu V
 * @version 0.1
 */
public class PropertFileConstants {
	public static final String PROPERTY_FILE_LOCATION = "src/main/resources/ml.properties";
	public static final String CLASSIFIER_NAME_KEY = "classifier.name";
		public static final String CONDITION_MODEL_LOCATION_KEY = "condition.model.location";
	public static final String HUMIDITY_MODEL_LOCATION_KEY = "humidity.model.location";
	public static final String TEMPERATURE_MODEL_LOCATION_KEY = "temperature.model.location";
	public static final String PRESSURE_MODEL_LOCATION_KEY = "pressure.model.location";

	public static final String CONDITION_CSV_TRAINING_DATA_LOCATION_KEY = "condition.training.data.csv.location";
	public static final String CONDITION_LIBSVM_TRAINING_DATA_LOCATION_KEY = "condition.training.data.libsvm.location";
	public static final String HUMIDITY_CSV_TRAINING_DATA_LOCATION_KEY = "humidity.training.data.csv.location";
	public static final String HUMIDITY_LIBSVM_TRAINING_DATA_LOCATION_KEY = "humidity.training.data.libsvm.location";
	public static final String TEMPERATURE_CSV_TRAINING_DATA_LOCATION_KEY = "temperature.training.data.csv.location";
	public static final String TEMPERATURE_LIBSVM_TRAINING_DATA_LOCATION_KEY = "temperature.training.data.libsvm.location";
	public static final String PRESSURE_CSV_TRAINING_DATA_LOCATION_KEY = "pressure.training.data.csv.location";
	public static final String PRESSURE_LIBSVM_TRAINING_DATA_LOCATION_KEY = "pressure.training.data.libsvm.location";

	public static final String TRAIN_SIZE_KEY = "train.size";
	public static final String TEST_SIZE_KEY = "test.size";
	public static final String CLASSIFIER_CLASSES_KEY = "classes";
	public static final String CLASSIFIER_IMPURITY_KEY = "impurity";
	public static final String CLASSIFIER_MAX_DEPTH_KEY = "maxDepth";
	public static final String CLASSIFIER_MAX_BIN_KEY = "maxBins";
	public static final String TEMPERATURE_MODEL_NUM_ITERATION_KEY = "tempmodel.numIterations";
	public static final String TEMPERATURE_MODEL_STEP_SIZE_KEY = "tempmodel.stepSize";
	public static final String HUMIDITY_MODEL_NUM_ITERATION_KEY = "humiditymodel.numIterations";
	public static final String HUMIDITY_MODEL_STEP_SIZE_KEY = "humiditymodel.stepSize";
	public static final String PRESSURE_MODEL_NUM_ITERATION_KEY = "pressuremodel.numIterations";
	public static final String PRESSURE_MODEL_STEP_SIZE_KEY = "pressuremodel.stepSize";

}
