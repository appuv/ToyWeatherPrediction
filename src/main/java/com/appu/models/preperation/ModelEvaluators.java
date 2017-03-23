package com.appu.models.preperation;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.evaluation.RegressionMetrics;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appu.data.loader.ModelDataLoader;
import com.appu.model.ToyWeatherDecisionTreeClassifier;
import com.appu.model.ToyWeatherLinearRegression;
import com.appu.utils.constants.WeatherDTOConstants;

import scala.Tuple2;

/**
 * Class for evaluating the model.
 * 
 * Date Mar 21 2017
 * 
 * @author Appu V
 * @version 0.1
 * 
 */

public class ModelEvaluators {

	/**
	 * Logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(ModelEvaluators.class);

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
	 * The static block loads and populate all the data for the required models
	 * TrainingData Location,Model Location,Training Parameters are being set
	 */

	static {

		classifier = ModelDataLoader.loadModelDatas(new ToyWeatherDecisionTreeClassifier());
		temperatureModel = ModelDataLoader.loadModelDatas(new ToyWeatherLinearRegression(),
				WeatherDTOConstants.TEMPERATURE);
		humidityModel = ModelDataLoader.loadModelDatas(new ToyWeatherLinearRegression(), WeatherDTOConstants.HUMIDITY);
		pressureModel = ModelDataLoader.loadModelDatas(new ToyWeatherLinearRegression(), WeatherDTOConstants.PRESSURE);

	}

	/**
	 * 
	 * @param args
	 * 
	 *            Main class for evaluating the model
	 */
	public static void main(String[] args) {

		SparkConf sparkConf = new SparkConf().setAppName("ModelEvaluators").setMaster("local");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		try {

			logger.info("Temperature Model");

			evaluateRegressionModel(LinearRegressionModel.load(jsc.sc(), temperatureModel.getModelLocation()),
					jsc.textFile(temperatureModel.getLibsvmTrainingLocation()));

			logger.info("Humidity Model ");

			evaluateRegressionModel(LinearRegressionModel.load(jsc.sc(), humidityModel.getModelLocation()),
					jsc.textFile(humidityModel.getLibsvmTrainingLocation()));

			logger.info("Pressure Model");

			evaluateRegressionModel(LinearRegressionModel.load(jsc.sc(), pressureModel.getModelLocation()),
					jsc.textFile(pressureModel.getLibsvmTrainingLocation()));

			logger.info("Decision Tree Model");

			evaluateDecisionTreeModel(DecisionTreeModel.load(jsc.sc(), classifier.getModelLocation()));

			logger.info("Evaluated the models");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		jsc.close();
		jsc.stop();
	}

	/**
	 * 
	 * @param model
	 * 
	 *            Debugging DecisionTreeModel
	 */
	private static void evaluateDecisionTreeModel(final DecisionTreeModel model) {
		logger.info("Learned classification tree model:\n" + model.toDebugString());
	}

	/**
	 * 
	 * @param model
	 * @param data
	 * 
	 *            Getting model parameters of LinearRegressionModel
	 */
	private static void evaluateRegressionModel(final LinearRegressionModel model, JavaRDD<String> data) {
		try {

			JavaRDD<LabeledPoint> parsedData = data.map(new Function<String, LabeledPoint>() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 3637551957567264784L;

				public LabeledPoint call(String line) {
					String[] parts = line.split(" ");
					double[] v = new double[parts.length - 1];
					for (int i = 1; i < parts.length - 1; i++)
						v[i - 1] = Double.parseDouble(parts[i].split(":")[1]);
					return new LabeledPoint(Double.parseDouble(parts[0]), Vectors.dense(v));
				}
			});
			parsedData.cache();
			// Evaluate model on training examples and compute training error
			JavaRDD<Tuple2<Object, Object>> valuesAndPreds = parsedData
					.map(new Function<LabeledPoint, Tuple2<Object, Object>>() {

						/**
						 * 
						 */
						private static final long serialVersionUID = 8336215147458434693L;

						public Tuple2<Object, Object> call(LabeledPoint point) {
							double prediction = model.predict(point.features());
							return new Tuple2<Object, Object>(prediction, point.label());
						}
					});

			// Instantiate metrics object
			RegressionMetrics metrics = new RegressionMetrics(valuesAndPreds.rdd());

			// Squared error
			logger.info("MSE = %f\n", metrics.meanSquaredError());
			logger.info("RMSE = %f\n", metrics.rootMeanSquaredError());

			// R-squared
			logger.info("R Squared = %f\n", metrics.r2());

			// Mean absolute error
			logger.info("MAE = %f\n", metrics.meanAbsoluteError());

			// Explained variance
			logger.info("Explained Variance = %f\n", metrics.explainedVariance());

		} catch (Exception e)

		{
			logger.error(e.getMessage());
		}

	}

}
