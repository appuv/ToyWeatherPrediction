package com.appu.model;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.DecisionTree;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;

import com.appu.exceptions.ToyWeatherPredictorException;

/**
 * 
 * Decision Tree Implementation of the model
 * 
 * Date Mar 20 2017
 * 
 * @author Appu V
 * @version 0.1
 *
 *
 */
public class ToyWeatherDecisionTreeClassifier implements ToyWeatherAbstractModel {

	/**
	 * Number of classes
	 */
	private int numClasses;
	/**
	 * impurity
	 */
	private String impurity;
	/**
	 * Max Depth
	 */
	private int maxDepth;
	/**
	 * Max Bins
	 */
	private int maxBins;
	/**
	 * Model
	 */
	private DecisionTreeModel model;
	/**
	 * categoricalFeaturesInfo
	 */
	private Map<Integer, Integer> categoricalFeaturesInfo;
	/**
	 * trainSize
	 */
	private double trainSize;
	/**
	 * testSize
	 */
	private double testSize;
	/**
	 * csvDataLocation
	 */
	private String csvDataLocation;
	/**
	 * libsvmTrainingLocation
	 */
	private String libsvmTrainingLocation;
	/**
	 * modelLocation
	 */
	private String modelLocation;

	final static Logger logger = Logger.getLogger(ToyWeatherDecisionTreeClassifier.class);

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

	public String getCsvDataLocation() {
		return csvDataLocation;
	}

	public void setCsvDataLocation(String csvDataLocation) {
		this.csvDataLocation = csvDataLocation;
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

	public int getNumClasses() {
		return numClasses;
	}

	public void setNumClasses(int numClasses) {
		this.numClasses = numClasses;
	}

	public String getImpurity() {
		return impurity;
	}

	public void setImpurity(String impurity) {
		this.impurity = impurity;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	public int getMaxBins() {
		return maxBins;
	}

	public void setMaxBins(int maxBins) {
		this.maxBins = maxBins;
	}

	public DecisionTreeModel getModel() {
		return model;
	}

	public void setModel(DecisionTreeModel model) {
		this.model = model;
	}

	public Map<Integer, Integer> getCategoricalFeaturesInfo() {
		return categoricalFeaturesInfo;
	}

	public void setCategoricalFeaturesInfo(Map<Integer, Integer> categoricalFeaturesInfo) {
		this.categoricalFeaturesInfo = categoricalFeaturesInfo;
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

		model = DecisionTree.trainClassifier(trainingData, numClasses, categoricalFeaturesInfo, impurity, maxDepth,
				maxBins);

	}

}
