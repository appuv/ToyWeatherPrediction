package com.appu.model;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.regression.LabeledPoint;

import com.appu.exceptions.ToyWeatherPredictorException;

/**
 * AbstractClassifier For machine learning model
 * 
 * Date Mar 20 2017
 * 
 * @author Appu V
 * @version 0.1
 */
public interface ToyWeatherAbstractModel {

	/**
	 * 
	 * @param trainingData
	 * @throws ToyWeatherPredictorException
	 * 
	 *             Used for training the model
	 */
	public void trainModel(JavaRDD<LabeledPoint> trainingData) throws ToyWeatherPredictorException;

}
