package com.appu.data.loader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appu.exceptions.ToyWeatherPredictorException;
import com.appu.utils.CommonUtils;
import com.appu.utils.constants.Constants;
import com.appu.utils.constants.ExceptionConstants;
import com.appu.utils.constants.MetaConstants;
import com.appu.utils.constants.NumericAttributeMapping;
import com.appu.utils.constants.WeatherDTOConstants;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Basic Data Loading
 * 
 * Date Mar 20 2017
 * 
 * 
 * @author Appu V
 * @version 0.1
 * 
 */
public class WeatherDataLoader {
	/**
	 * Logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(WeatherDataLoader.class);

	/**
	 * 
	 * @param inputPath
	 *            csv location of trianing data
	 * @param outputPath
	 *            libsvm location of trianing data
	 * @param Condition
	 *            which data to train
	 * @throws Exception
	 */
	public static void saveAsLibSVM(String inputPath, String outputPath, String Condition) throws Exception {

		Writer writer = new BufferedWriter(new FileWriter(outputPath));
		StringBuilder tempData = new StringBuilder();

		CSVReader reader = new CSVReader(new FileReader(inputPath), ',', '"', 1);

		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			if (nextLine != null) {
				switch (Condition) {
				case (WeatherDTOConstants.CONDITION): {
					conditionParser(tempData, nextLine);
					break;
				}

				default: {
					predictionParser(tempData, nextLine, Condition);
				}
				}

			}

			writer.write(tempData + "\n");
			writer.flush();

			tempData.setLength(0);
		}
		reader.close();
		writer.close();
	}

	/**
	 * 
	 * @param tempData
	 * @param nextLine
	 * 
	 *            This will populate the data in libsvm format from the current
	 *            csv format for classifier
	 */
	private static StringBuilder conditionParser(StringBuilder tempData, String[] nextLine) {
		try {
			tempData.setLength(0);

			// Numeric mapping to the weather conditions
			switch (nextLine[MetaConstants.CONDITION_CSV_INDEX]) {

			case (Constants.CONDITION_RAIN): {
				tempData.append(NumericAttributeMapping.RAIN_MAPPING).append(" ");
				break;

			}
			case (Constants.CONDITION_SNOW): {
				tempData.append(NumericAttributeMapping.SNOW_MAPPING).append(" ");
				break;

			}
			case (Constants.CONDITION_SUNNY): {
				tempData.append(NumericAttributeMapping.SUNNY_MAPPING).append(" ");
				break;

			}
			default: {
				throw new ToyWeatherPredictorException(ExceptionConstants.INVALID_WEATHER_INFO);
			}

			}

			tempData.append(NumericAttributeMapping.HUMIDITY_MAPPING).append(Constants.DELIMITTER_COLON)
					.append(nextLine[MetaConstants.HUMIDITY_CSV_INDEX]).append(" ")
					.append(NumericAttributeMapping.PRESSURE_MAPPING).append(Constants.DELIMITTER_COLON)
					.append(nextLine[MetaConstants.PRESSURE_CSV_INDEX]).append(" ")
					.append(NumericAttributeMapping.TEMPERATURE_MAPPING).append(Constants.DELIMITTER_COLON)
					.append(nextLine[MetaConstants.TEMPERATURE_CSV_INDEX]).append(" ")
					.append(NumericAttributeMapping.TIME_MAPPING).append(Constants.DELIMITTER_COLON)
					.append(""
							+ CommonUtils.timeAttributeParser(Long.parseLong(nextLine[MetaConstants.TIME_CSV_INDEX])))
					.append(" ").append(NumericAttributeMapping.DUMMY_MAPPING).append(Constants.DELIMITTER_COLON)
					.append(NumericAttributeMapping.DUMMY_ATTRIBUTE);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return tempData;
	}
	/**
	 * 
	 * @param tempData
	 * @param nextLine
	 * @param conition
	 * 
	 *   This will populate the data in libsvm format from the current
	 *            csv format for regression
	 */

	private static void predictionParser(StringBuilder tempData, String[] nextLine, String conition) {
		try {

			switch (conition) {

			case (WeatherDTOConstants.HUMIDITY): {
				tempData.append(nextLine[MetaConstants.HUMIDITY_CSV_INDEX]).append(" ");

				break;

			}
			case (WeatherDTOConstants.PRESSURE): {
				tempData.append(nextLine[MetaConstants.PRESSURE_CSV_INDEX]).append(" ");

				break;

			}
			case (WeatherDTOConstants.TEMPERATURE): {
				tempData.append(nextLine[MetaConstants.TEMPERATURE_CSV_INDEX]).append(" ");

				break;

			}
			default: {
				throw new ToyWeatherPredictorException(ExceptionConstants.INVALID_WEATHER_INFO);
			}

			}

			tempData.append(NumericAttributeMapping.LAT_MAPPING).append(Constants.DELIMITTER_COLON)
					.append(nextLine[MetaConstants.LAT_CSV_INDEX]).append(" ")
					.append(NumericAttributeMapping.LONG_MAPPING).append(Constants.DELIMITTER_COLON)
					.append(nextLine[MetaConstants.LONG_CSV_INDEX]).append(" ")
					.append(NumericAttributeMapping.ELEVATION_MAPPING).append(Constants.DELIMITTER_COLON)
					.append(nextLine[MetaConstants.ELEVATION_CSV_INDEX]).append(" ")
					.append(NumericAttributeMapping.TIME_MAPPING).append(Constants.DELIMITTER_COLON)
					.append(""
							+ CommonUtils.timeAttributeParser(Long.parseLong(nextLine[MetaConstants.TIME_CSV_INDEX])))
					.append(" ").append(NumericAttributeMapping.DUMMY_MAPPING).append(Constants.DELIMITTER_COLON)
					.append(NumericAttributeMapping.DUMMY_ATTRIBUTE);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
