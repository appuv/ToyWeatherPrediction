package com.appu.application;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appu.data.loader.ModelDataLoader;
import com.appu.dto.GeoLocationDTO;
import com.appu.dto.WeatherDTO;
import com.appu.exceptions.ToyWeatherPredictorException;
import com.appu.model.ToyWeatherDecisionTreeClassifier;
import com.appu.model.ToyWeatherLinearRegression;
import com.appu.utils.CommonUtils;
import com.appu.utils.Settings;
import com.appu.utils.constants.ExceptionConstants;
import com.appu.utils.constants.NumericAttributeMapping;
import com.appu.utils.constants.WeatherDTOConstants;
import com.beust.jcommander.JCommander;

/**
 * Main class for getting the prediction
 * 
 * * Date Mar 20 2017
 * 
 * @author Appu V
 * @version 0.1
 * 
 */

public class ToyWeatherPredictor {

	private final static Logger logger = LoggerFactory.getLogger(ToyWeatherPredictor.class);

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
	 * Weather DTO Object
	 */

	private static WeatherDTO dtoObject;
	/**
	 * GeoLocation DTO Object
	 */
	private static GeoLocationDTO position;

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

	public WeatherDTO getDtoObject() {
		return dtoObject;
	}

	public static GeoLocationDTO getPosition() {
		return position;
	}

	public static void setPosition(GeoLocationDTO position) {
		ToyWeatherPredictor.position = position;
	}

	/**
	 * The static block loads and populate all the data for the required models
	 * TrainingData Location,Model Location,Training Parameters are being set
	 */

	static {

		classifier = ModelDataLoader.loadModelDatas(new ToyWeatherDecisionTreeClassifier());
		temperatureModel = ModelDataLoader.loadModelDatas(new ToyWeatherLinearRegression(),
				WeatherDTOConstants.TEMPERATURE);
		humidityModel = ModelDataLoader.loadModelDatas(new ToyWeatherLinearRegression(), WeatherDTOConstants.HUMIDITY);
		pressureModel = ModelDataLoader.loadModelDatas(new ToyWeatherLinearRegression(), WeatherDTOConstants.PRESSURE);
		dtoObject = new WeatherDTO();
		position = new GeoLocationDTO();

	}

	/**
	 * 
	 * @param args
	 * @throws ToyWeatherPredictorException
	 * 
	 *             Main Class for getting prediction
	 */
	public static void main(String[] args) throws ToyWeatherPredictorException {

		SparkConf sparkConf = new SparkConf().setAppName("WeatherPredictor").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);

		Settings settings = new Settings();
		JCommander jcommander = new JCommander(settings);
		try {
			jcommander = new JCommander(settings, args);
		} catch (com.beust.jcommander.ParameterException e) {
			logger.error(ExceptionConstants.INVALID_ARGUMENTS);
			jcommander.usage();
			jsc.close();
			jsc.stop();
			System.exit(1);
		}
		if (settings.isHelp()) {
			logger.error("Displaying help");
			jcommander.usage();

		} else {
			position.setLatitude(settings.getLatitude());
			position.setLongitude(settings.getLongitude());
			position.setElevation(settings.getElevation());
			dtoObject.setPosition(position);
			dtoObject.setLocation(
					CommonUtils.findLocation(position.getLatitude(), position.getLongitude(), position.getElevation()));
			dtoObject.setTime(CommonUtils.calenderTimeFromUnixTime(settings.getUnixTime()));

			// Including TimeStamp, creating vector to predict regression
			Vector testDataRegression = Vectors.sparse(4,
					new int[] { NumericAttributeMapping.LAT_MAPPING, NumericAttributeMapping.LONG_MAPPING,
							NumericAttributeMapping.ELEVATION_MAPPING, NumericAttributeMapping.TIME_MAPPING },
					new double[] { position.getLatitude(), position.getLongitude(), position.getElevation(),
							CommonUtils.timeAttributeParser(settings.getUnixTime()) });

			double temperature = LinearRegressionModel.load(jsc.sc(), temperatureModel.getModelLocation())
					.predict(testDataRegression);

			logger.info("\n Temperature :" + temperature);
			dtoObject.setTemperature(temperature);

			double humidity = LinearRegressionModel.load(jsc.sc(), humidityModel.getModelLocation())
					.predict(testDataRegression);

			logger.info("\n Humidity :" + humidity);
			dtoObject.setHumidity(humidity);

			double pressure = LinearRegressionModel.load(jsc.sc(), pressureModel.getModelLocation())
					.predict(testDataRegression);

			logger.info("\n Presure " + pressure);
			dtoObject.setPressure(pressure);

			// Including TimeStamp,  creating vector to predict condition

			Vector testDataClassifier = Vectors.sparse(4,
					new int[] { NumericAttributeMapping.HUMIDITY_MAPPING, NumericAttributeMapping.TEMPERATURE_MAPPING,
							NumericAttributeMapping.PRESSURE_MAPPING, NumericAttributeMapping.TIME_MAPPING },
					new double[] { humidity, temperature, pressure,
							CommonUtils.timeAttributeParser(settings.getUnixTime()) });

			double condition = DecisionTreeModel.load(jsc.sc(), classifier.getModelLocation())
					.predict(testDataClassifier);

			logger.info("\n Condition {}", condition);
			dtoObject.setCondition(CommonUtils.poulateCondition(condition));
			logger.info("\n Result :", dtoObject.toString());
			CommonUtils.writeData(dtoObject.toString(), settings.getOutLocation());

		}
		jsc.close();

		jsc.stop();

	}

}
