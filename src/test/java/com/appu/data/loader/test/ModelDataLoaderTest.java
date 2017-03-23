package com.appu.data.loader.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appu.data.loader.ModelDataLoader;
import com.appu.model.ToyWeatherDecisionTreeClassifier;
import com.appu.model.ToyWeatherLinearRegression;
import com.appu.utils.MLProperties;
import com.appu.utils.constants.WeatherDTOConstants;

/**
 * Test Class for ModelDataLoader
 * 
 * Date Mar 22 2017
 * 
 * @author Appu V
 * @version 0.1
 */
public class ModelDataLoaderTest {

	/**
	 * Logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(ModelDataLoaderTest.class);
	/**
	 * Decision Tree classifier
	 */
	private static ToyWeatherDecisionTreeClassifier classifier;

	/**
	 * Linear Regression for Temperature Prediction
	 */
	private static ToyWeatherLinearRegression temperatureModel;

	/**
	 * Linear Regression for Humidity Prediction
	 */
	private static ToyWeatherLinearRegression humidityModel;

	/**
	 * Linear Regression for Pressure Prediction
	 */
	private static ToyWeatherLinearRegression pressureModel;

	/**
	 * Delta value for unit floating comparisons
	 */
	private static int delta;

	/**
	 * Load Datas for initial Testing
	 */
	@Before
	public void loadDatas() {
		classifier = ModelDataLoader.loadModelDatas(new ToyWeatherDecisionTreeClassifier());
		temperatureModel = ModelDataLoader.loadModelDatas(new ToyWeatherLinearRegression(), WeatherDTOConstants.TEMPERATURE);
		humidityModel = ModelDataLoader.loadModelDatas(new ToyWeatherLinearRegression(), WeatherDTOConstants.HUMIDITY);
		pressureModel = ModelDataLoader.loadModelDatas(new ToyWeatherLinearRegression(), WeatherDTOConstants.PRESSURE);
		delta = 0;
		logger.info("Loaded datas for testing com.appu.data.loader.ModelDataLoader");

	}

	/**
	 * 
	 * Tests the loading part
	 */

	@Test
	public void loadModelDatasTest() {
		assertEquals(ModelDataLoader.loadModelDatas(null), null);
		assertEquals(ModelDataLoader.loadModelDatas(null, null), null);

		assertEquals(classifier.getCsvDataLocation(), MLProperties.CONDITION_CSV_TRAINING_DATA_LOCATION);
		assertEquals(classifier.getLibsvmTrainingLocation(), MLProperties.CONDITION_LIBSVM_TRAINING_DATA_LOCATION);
		assertEquals(classifier.getMaxBins(), Integer.parseInt(MLProperties.CLASSIFIER_MAX_BIN));
		assertEquals(classifier.getImpurity(), MLProperties.CLASSIFIER_IMPURITY);
		assertEquals(classifier.getMaxDepth(), Integer.parseInt(MLProperties.CLASSIFIER_MAX_DEPTH));
		assertEquals(classifier.getModelLocation(), MLProperties.CONDITION_MODEL_LOCATION);
		assertEquals(classifier.getNumClasses(), Integer.parseInt(MLProperties.CLASSIFIER_CLASSES));
		assertEquals(classifier.getTestSize(), Double.parseDouble(MLProperties.TEST_SIZE), delta);
		assertEquals(classifier.getTrainSize(), Double.parseDouble(MLProperties.TRAIN_SIZE), delta);

		assertEquals(temperatureModel.getLibsvmTrainingLocation(),
				MLProperties.TEMPERATURE_LIBSVM_TRAINING_DATA_LOCATION);
		assertEquals(temperatureModel.getModelLocation(), MLProperties.TEMPERATURE_MODEL_LOCATION);
		assertEquals(temperatureModel.getStepSize(), Double.parseDouble(MLProperties.TEMPERATURE_MODEL_STEP_SIZE),
				delta);
		assertEquals(temperatureModel.getNumIterations(),
				Double.parseDouble(MLProperties.TEMPERATURE_MODEL_NUM_ITERATION), delta);
		assertEquals(temperatureModel.getTrainSize(), Double.parseDouble(MLProperties.TRAIN_SIZE), delta);
		assertEquals(temperatureModel.getTestSize(), Double.parseDouble(MLProperties.TEST_SIZE), delta);

		assertEquals(humidityModel.getLibsvmTrainingLocation(), MLProperties.HUMIDITY_LIBSVM_TRAINING_DATA_LOCATION);
		assertEquals(humidityModel.getModelLocation(), MLProperties.HUMIDITY_MODEL_LOCATION);
		assertEquals(humidityModel.getStepSize(), Double.parseDouble(MLProperties.HUMIDITY_MODEL_STEP_SIZE), delta);
		assertEquals(humidityModel.getNumIterations(), Double.parseDouble(MLProperties.HUMIDITY_MODEL_NUM_ITERATION),
				0);
		assertEquals(humidityModel.getTrainSize(), Double.parseDouble(MLProperties.TRAIN_SIZE), delta);
		assertEquals(humidityModel.getTestSize(), Double.parseDouble(MLProperties.TEST_SIZE), delta);

		assertEquals(pressureModel.getLibsvmTrainingLocation(), MLProperties.PRESSURE_LIBSVM_TRAINING_DATA_LOCATION);
		assertEquals(pressureModel.getModelLocation(), MLProperties.PRESSURE_MODEL_LOCATION);
		assertEquals(pressureModel.getStepSize(), Double.parseDouble(MLProperties.PRESSURE_MODEL_STEP_SIZE), delta);
		assertEquals(pressureModel.getNumIterations(), Double.parseDouble(MLProperties.PRESSURE_MODEL_NUM_ITERATION),
				0);
		assertEquals(pressureModel.getTrainSize(), Double.parseDouble(MLProperties.TRAIN_SIZE), delta);
		assertEquals(pressureModel.getTestSize(), Double.parseDouble(MLProperties.TEST_SIZE), delta);

		logger.info("Completed Test case for com.appu.data.loader.ModelDataLoader.loadModelDatas");

	}

	/**
	 * Test for loading data for training
	 */
	@Test
	public void loadDatasForTrainingTest() {
		assertEquals(ModelDataLoader.loadDatasForTraining(), true);
		logger.info("Completed Test case for com.appu.data.loader.ModelDataLoader.loadDatasForTraining(");

	}

}
