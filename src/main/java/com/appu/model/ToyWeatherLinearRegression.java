package com.appu.model;

import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.regression.LinearRegressionWithSGD;

import com.appu.exceptions.ToyWeatherPredictorException;

/**
 * 
 * Linear Regression Implementation of the model
 * 
 * Date Mar 20 2017
 * 
 * @author Appu V
 * @version 0.1
 *
 *
 */
public class ToyWeatherLinearRegression implements ToyWeatherAbstractModel {

	/**
	 * model
	 */
	private LinearRegressionModel model;

	/**
	 * numIterations
	 */
	private int numIterations;
	/**
	 * stepSize
	 */
	private double stepSize;
	/**
	 * libsvmTrainingLocation
	 */
	private String libsvmTrainingLocation;
	/**
	 * modelLocation
	 */
	private String modelLocation;
	/**
	 * trainSize
	 */
	private double trainSize;
	/**
	 * testSize
	 */
	private double testSize;

	final static Logger logger = Logger.getLogger(ToyWeatherLinearRegression.class);

	public LinearRegressionModel getModel() {
		return model;
	}

	public void setModel(LinearRegressionModel model) {
		this.model = model;
	}

	public int getNumIterations() {
		return numIterations;
	}

	public void setNumIterations(int numIterations) {
		this.numIterations = numIterations;
	}

	public double getStepSize() {
		return stepSize;
	}

	public void setStepSize(double stepSize) {
		this.stepSize = stepSize;
	}

	public String getLibsvmTrainingLocation() {
		return libsvmTrainingLocation;
	}

	public void setLibsvmTrainingLocation(String libsvmTrainingLocation) {
		this.libsvmTrainingLocation = libsvmTrainingLocation;
	}

	public String getModelLocation() {
		return modelLocation;
	}

	public void setModelLocation(String modelLocation) {
		this.modelLocation = modelLocation;
	}

	public double getTrainSize() {
		return trainSize;
	}

	public void setTrainSize(double trainSize) {
		this.trainSize = trainSize;
	}

	public double getTestSize() {
		return testSize;
	}

	public void setTestSize(double testSize) {
		this.testSize = testSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.appu.model.AbstractClassifier#trainModel(org.apache.spark.api.java.
	 * JavaRDD)
	 */
	@Override
	public void trainModel(JavaRDD<LabeledPoint> trainingData) throws ToyWeatherPredictorException {
		model = LinearRegressionWithSGD.train(JavaRDD.toRDD(trainingData), numIterations, stepSize);

	}

}
