package com.appu.data.loader;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appu.model.ToyWeatherDecisionTreeClassifier;
import com.appu.model.ToyWeatherLinearRegression;
import com.appu.utils.MLProperties;
import com.appu.utils.constants.WeatherDTOConstants;

/**
 * Classfor Loading Models.
 * 
 * Date Mar 20 2017
 * 
 * @author Appu V
 * @version 0.1
 * 
 */
public class ModelDataLoader {

	/**
	 * Logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(ModelDataLoader.class);

	/**
	 * This contain all the properties for the project
	 */

	public static ToyWeatherDecisionTreeClassifier loadModelDatas(ToyWeatherDecisionTreeClassifier classifier) {
		try {
			classifier.setCategoricalFeaturesInfo(new HashMap<Integer, Integer>());
			classifier.setImpurity(MLProperties.CLASSIFIER_IMPURITY);
			classifier.setMaxBins(Integer.parseInt(MLProperties.CLASSIFIER_MAX_BIN));
			classifier.setMaxDepth(Integer.parseInt(MLProperties.CLASSIFIER_MAX_DEPTH));
			classifier.setNumClasses(Integer.parseInt(MLProperties.CLASSIFIER_CLASSES));
			classifier.setCsvDataLocation(MLProperties.CONDITION_CSV_TRAINING_DATA_LOCATION);
			classifier.setLibsvmTrainingLocation(MLProperties.CONDITION_LIBSVM_TRAINING_DATA_LOCATION);
			classifier.setTrainSize(Double.parseDouble(MLProperties.TRAIN_SIZE));
			classifier.setTestSize(Double.parseDouble(MLProperties.TEST_SIZE));
			classifier.setModelLocation(MLProperties.CONDITION_MODEL_LOCATION);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return classifier;
	}

	/**
	 * 
	 * @param regression
	 * @param condition
	 * @return
	 * 
	 * 		This method populates the Model parameters of Linear Regression
	 *         StepSize NumIterations TrainSize TestSize Training Data Location
	 *         Model Saving Location
	 */

	public static ToyWeatherLinearRegression loadModelDatas(ToyWeatherLinearRegression regression, String condition) {
		try {

			regression.setTrainSize(Double.parseDouble(MLProperties.TRAIN_SIZE));
			regression.setTestSize(Double.parseDouble(MLProperties.TEST_SIZE));
			switch (condition) {

			case (WeatherDTOConstants.HUMIDITY): {
				regression.setLibsvmTrainingLocation(MLProperties.HUMIDITY_LIBSVM_TRAINING_DATA_LOCATION);
				regression.setModelLocation(MLProperties.HUMIDITY_MODEL_LOCATION);
				regression.setNumIterations(Integer.parseInt(MLProperties.HUMIDITY_MODEL_NUM_ITERATION));
				regression.setStepSize((Double.parseDouble(MLProperties.HUMIDITY_MODEL_STEP_SIZE)));
				break;

			}
			case (WeatherDTOConstants.PRESSURE): {
				regression.setLibsvmTrainingLocation(MLProperties.PRESSURE_LIBSVM_TRAINING_DATA_LOCATION);
				regression.setModelLocation(MLProperties.PRESSURE_MODEL_LOCATION);
				regression.setNumIterations(Integer.parseInt(MLProperties.PRESSURE_MODEL_NUM_ITERATION));
				regression.setStepSize((Double.parseDouble(MLProperties.PRESSURE_MODEL_STEP_SIZE)));
				break;

			}
			case (WeatherDTOConstants.TEMPERATURE): {
				regression.setLibsvmTrainingLocation(MLProperties.TEMPERATURE_LIBSVM_TRAINING_DATA_LOCATION);
				regression.setModelLocation(MLProperties.TEMPERATURE_MODEL_LOCATION);
				regression.setNumIterations(Integer.parseInt(MLProperties.TEMPERATURE_MODEL_NUM_ITERATION));
				regression.setStepSize((Double.parseDouble(MLProperties.TEMPERATURE_MODEL_STEP_SIZE)));

				break;
			}
			default: {
				// throw new ToyWeatherPredictorException("Invalid weather
				// condition");
			}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return regression;
	}

	/**
	 * 
	 * @return
	 * 
	 * 		Populate LibSVM Data for the training of models
	 */
	public static boolean loadDatasForTraining() {
		boolean returnData = false;
		try {
			WeatherDataLoader.saveAsLibSVM(MLProperties.CONDITION_CSV_TRAINING_DATA_LOCATION,
					MLProperties.CONDITION_LIBSVM_TRAINING_DATA_LOCATION, WeatherDTOConstants.CONDITION);
			WeatherDataLoader.saveAsLibSVM(MLProperties.HUMIDITY_CSV_TRAINING_DATA_LOCATION,
					MLProperties.HUMIDITY_LIBSVM_TRAINING_DATA_LOCATION, WeatherDTOConstants.HUMIDITY);
			WeatherDataLoader.saveAsLibSVM(MLProperties.PRESSURE_CSV_TRAINING_DATA_LOCATION,
					MLProperties.PRESSURE_LIBSVM_TRAINING_DATA_LOCATION, WeatherDTOConstants.PRESSURE);
			WeatherDataLoader.saveAsLibSVM(MLProperties.TEMPERATURE_CSV_TRAINING_DATA_LOCATION,
					MLProperties.TEMPERATURE_LIBSVM_TRAINING_DATA_LOCATION, WeatherDTOConstants.TEMPERATURE);

			returnData = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return returnData;
	}

}
