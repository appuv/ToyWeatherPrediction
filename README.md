# ToyWeatherPredictor
Machine Learning model for weather generation using Java and Spark

[![CodeQL](https://github.com/appuv/ToyWeatherPrediction/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/appuv/ToyWeatherPrediction/actions/workflows/codeql-analysis.yml) [![Java CI with Maven](https://github.com/appuv/ToyWeatherPrediction/actions/workflows/maven.yml/badge.svg?branch=master)](https://github.com/appuv/ToyWeatherPrediction/actions/workflows/maven.yml) [![All JDKs on all OSs](https://github.com/appuv/ToyWeatherPrediction/actions/workflows/jacoco.yml/badge.svg)](https://github.com/appuv/ToyWeatherPrediction/actions/workflows/jacoco.yml)


## Background
The problem was to develop a toy weather predictor which can generate weather conditions for a given location for a given time.

This can be achieved in multiple ways,  
1. Find out the Fluid Mechanics and Physics equations for prediction.   
2. Collect historical data and observe the change in weather based on parameters like latitude, time etc..  
3. ....(probably more)

The first approach is purely a mathematical substitution, which may need consideration of large number of parameters (say 40 variables in an equation).  
The second approach is more of solving the problem with some Machine Learning.  


#### The ML approach
The machine learning approach needs a data. It also requires data cleaning and formatting. There are lot of api services which provides historical weather data. Some examples,
* [OpenWeatherMap](https://openweathermap.org/api)
* [WorldWeatherOnline](https://developer.worldweatheronline.com/)


Prediction of weather includes prediction of temperature,pressure,humidity and weather condition. The first three predicates are **continuous** values which needs a **regression model** for prediction. The weather condition can be either of Rain,Snow or Sunny, predicting which is a **classification problem**.

As the prediction is of a continuous value for humidity, pressure and temperature the simplest model to try out was **Linear Regression**. For the condition **Decision Tree Classifier** seems to be the simple model.

Here I have used [spark.mllib](https://spark.apache.org/mllib/) library for the linear regression and decision tree classifier. Spark Provides support of big data frameworks, which would help production level deployment


## Prerequisite

1. [Java 1.8](https://www.azul.com/downloads/?version=java-11-lts&package=jdk)
2. [Apache Spark 3.1.2](https://www.apache.org/dyn/closer.lua/spark/spark-3.1.2/spark-3.1.2-bin-hadoop3.2.tgz)
3. [Maven](https://maven.apache.org/)
## Getting Started
To run the application, 
Change **ml.properties** accordingly

Build the maven project

```
mvn clean install  
```

## Run the project
Follow the steps to get output  

To Create Models
```
spark-submit --class com.appu.models.preperation.ModelTraining <jarlocation>
eg: spark-submit --class com.appu.models.preperation.ModelTraining ToyWeatherPrediction-0.0.1-SNAPSHOT.jar
```

To Evaluate Models
```
 spark-submit --class com.appu.models.preperation.ModelEvaluators <jarlocation>
eg: spark-submit --class com.appu.models.preperation.ModelEvaluators ToyWeatherPrediction-0.0.1-SNAPSHOT.jar
```


To Predict Weather
```
spark-submit --class com.appu.application.ToyWeatherPredictor <jarlocation> --lat <latitiude> --long <longitude> --ele <elevation> --time <unixTimeStamp>  --out <outputLocation>
eg: spark-submit --class com.appu.application.ToyWeatherPredictor ToyWeatherPrediction-0.0.1-SNAPSHOT.jar --lat -33.8688197 --long 151.2092955 --ele 24.5399284363 --time 1490147427  --out /resuts/prediction.txt
```


```
##Command line arguments 

--help          Displays help  
--lat            **Latitiude of the location 
--lon            **Longitude of the location
--ele            **Elevation of the location 
--time           **Unix TimeStamp
--out		 **Output Location





**  -> Mandatory arguments  

