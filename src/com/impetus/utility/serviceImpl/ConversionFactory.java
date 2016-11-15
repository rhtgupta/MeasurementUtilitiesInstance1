package com.impetus.utility.serviceImpl;

import static com.impetus.utility.common.Constants.CONVERSION_TYPE_MEASUREMENT;
import static com.impetus.utility.common.Constants.CONVERSION_TYPE_TEMPERATURE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.utility.service.ConversionAbstract;

@Service
public class ConversionFactory {

	@Autowired
	private MeasurmentConversionImpl measurmentConversionImpl;
	@Autowired
	private TemperatureConversionImpl temperatureConversionImpl;

	public ConversionAbstract getConversionType(String conversionType) {
		if (CONVERSION_TYPE_MEASUREMENT.equals(conversionType)) {
			return measurmentConversionImpl;
		} else if (CONVERSION_TYPE_TEMPERATURE.equals(conversionType)) {
			return temperatureConversionImpl;
		}
		return null;
	}
}
