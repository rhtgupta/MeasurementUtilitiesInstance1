package com.impetus.utility.serviceImpl;

import static com.impetus.utility.common.Constants.CONVERSION_TYPE_MEASUREMENT;
import static com.impetus.utility.common.Constants.CONVERSION_TYPE_TEMPERATURE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.utility.service.ConversionAbstract;

@Service
public class ConversionFactory {

	@Autowired
	private MeasurmentConversionImpl measurmentConversionImpl;
	@Autowired
	private TemperatureConversionImpl temperatureConversionImpl;
	
	private Logger logger = LoggerFactory.getLogger(ConversionFactory.class);

	public ConversionAbstract getConversionType(String conversionType) {
		if (CONVERSION_TYPE_MEASUREMENT.equals(conversionType)) {
			logger.info("Returning Measurment implementation");
			return measurmentConversionImpl;
		} else if (CONVERSION_TYPE_TEMPERATURE.equals(conversionType)) {
			logger.info("Returning Temprature implementation");
			return temperatureConversionImpl;
		}
		return null;
	}
}
