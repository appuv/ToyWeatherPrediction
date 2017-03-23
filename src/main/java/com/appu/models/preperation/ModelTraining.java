package com.appu.models.preperation;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.util.MLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appu.data.loader.ModelDataLoader;
import com.appu.model.ToyWeatherDecisionTreeClassifier;
import com.appu.model.ToyWeatherLinearRegression;
import com.appu.utils.constants.WeatherDTOConstants;

/**
 * Class for loading training data and saving the model.
 * 
 * Date Mar 21 2017
 * 
 * @author Appu V
 * @version 0.1
 * 
 */

public class ModelTraining {
	/**
	 * Logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(ModelTraining.class);
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

	public ToyWeatherDecisionTreeClassifier getClassifier() {
		return classifier;
	}

	public ToyWeatherLinearRegression getTemperatureModel() {
		return temperatureModel;
	}

	public ToyWeatherLinearRegression getHumidityModel() {
		return humidityModel;
	}

	public ToyWeatherLinearRegression getPressureModel() {
		return pressureModel;
	}

	/**
	 * The static block loads and populate all the data for the required modesl
	 * TrainingData Location,Model Location,Training Parameters are being set
	 */

	static {

		classifier = ModelDataLoader.loadModelDatas(new ToyWeatherDecisionTreeClassifier());
		temperatureModel = ModelDataLoader.loadModelDatas(new ToyWeatherLinearRegression(), WeatherDTOConstants.TEMPERATURE);
		humidityModel = ModelDataLoader.loadModelDatas(new ToyWeatherLinearRegression(), WeatherDTOConstants.HUMIDITY);
		pressureModel = ModelDataLoader.loadModelDatas(new ToyWeatherLinearRegression(), WeatherDTOConstants.PRESSURE);

	}

	public static void main(String[] args) {

		// For Loading Training Data into libsvm Format for the model
		loadDatasForTraining();
		SparkConf sparkConf = new SparkConf().setAppName("ModelTraining").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		try {
			// Training the classifier
			classifier.trainModel(MLUtils.loadLibSVMFile(jsc.sc(), classifier.getLibsvmTrainingLocation()).toJavaRDD()
					.cache().randomSplit(new double[] { classifier.getTrainSize(), classifier.getTestSize() })[0]);
			// Saving the classifier
			classifier.getModel().save(jsc.sc(), classifier.getModelLocation());

			// Training Temperature Model (linear Regression)
			temperatureModel.trainModel(MLUtils.loadLibSVMFile(jsc.sc(), temperatureModel.getLibsvmTrainingLocation())
					.toJavaRDD().cache()
					.randomSplit(new double[] { temperatureModel.getTrainSize(), temperatureModel.getTestSize() })[0]);

			// Saving Temperature Model (linear Regression)

			temperatureModel.getModel().save(jsc.sc(), temperatureModel.getModelLocation());

			// Training Humidity Model (linear Regression)

			humidityModel.trainModel(MLUtils.loadLibSVMFile(jsc.sc(), humidityModel.getLibsvmTrainingLocation())
					.toJavaRDD().cache()
					.randomSplit(new double[] { humidityModel.getTrainSize(), humidityModel.getTestSize() })[0]);

			// Saving Temperature Model (linear Regression)

			humidityModel.getModel().save(jsc.sc(), humidityModel.getModelLocation());

			// Training PRessure Model (linear Regression)

			pressureModel.trainModel(MLUtils.loadLibSVMFile(jsc.sc(), pressureModel.getLibsvmTrainingLocation())
					.toJavaRDD().cache()
					.randomSplit(new double[] { pressureModel.getTrainSize(), pressureModel.getTestSize() })[0]);

			// Saving Pressure Model (linear Regression)

			pressureModel.getModel().save(jsc.sc(), pressureModel.getModelLocation());

			logger.info("Trained and saved the models");
		} catch (Exception e) {

			logger.error(e.getMessage());
		}
		jsc.close();
		jsc.stop();
	}

	/**
	 * 
	 * @return boolean Will Transform input from csv to libvm format
	 */

	public static boolean loadDatasForTraining() {
		return ModelDataLoader.loadDatasForTraining();
	}

}
