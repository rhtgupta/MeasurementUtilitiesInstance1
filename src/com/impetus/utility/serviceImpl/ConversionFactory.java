package com.impetus.utility.serviceImpl;

import static com.impetus.utility.common.Constants.CONVERSION_TYPE_MEASUREMENT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.utility.service.ConversionAbstract;

@Service
public class ConversionFactory {

	@Autowired
	private MeasurmentConversionImpl measurmentConversionImpl;

	private Logger logger = LoggerFactory.getLogger(ConversionFactory.class);

	public ConversionAbstract getConversionType(String conversionType) {
		if (CONVERSION_TYPE_MEASUREMENT.equals(conversionType)) {
			logger.info("Returning Measurment implementation");
			return measurmentConversionImpl;
		}
		return null;
	}
}
