package com.impetus.service.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.impetus.service.dto.ConversionInfo;
import com.impetus.service.impl.MeasurmentConversionImpl;
import com.impetus.service.impl.TemperatureConversionImpl;

@RestController
public class ServiceController {

	@Autowired
	private MeasurmentConversionImpl measurmentConversionImpl;
	@Autowired
	private TemperatureConversionImpl temperatureConversionImpl;
	
	private Logger logger = LoggerFactory.getLogger(ServiceController.class);

	private Map<String, Double> cachedMap;

	public ServiceController() {
		logger.info("Creating new instance of Hazelcast cache");
		HazelcastInstance instance = Hazelcast.newHazelcastInstance();
		cachedMap = instance.getMap("cachedConversion");
	}

	@RequestMapping(value = "/measurement/{convert}/{from}/{to}", produces = "application/json")
	public @ResponseBody ConversionInfo calculateMeasurementConversion(
			@PathVariable(value = "convert") String convert,
			@PathVariable(value = "from") String from,
			@PathVariable(value = "to") String to) {
		logger.info("Serving measurement request, convert " + convert + " " + from + "to "
				+ to);
		ConversionInfo conversionInfo = new ConversionInfo();
		conversionInfo.setConvert(convert);
		conversionInfo.setFrom(from);
		conversionInfo.setTo(to);
		return measurmentConversionImpl.convert(conversionInfo, cachedMap);
	}

	@RequestMapping(value = "/temperature/{convert}/{from}/{to}", produces = "application/json")
	public @ResponseBody ConversionInfo calculateTemperatureConversion(
			@PathVariable(value = "convert") String convert,
			@PathVariable(value = "from") String from,
			@PathVariable(value = "to") String to) {
		logger.info("Serving temperature request, convert " + convert + " " + from + "to "
				+ to);
		ConversionInfo conversionInfo = new ConversionInfo();
		conversionInfo.setConvert(convert);
		conversionInfo.setFrom(from);
		conversionInfo.setTo(to);

		return temperatureConversionImpl.convert(conversionInfo, cachedMap);
	}

}
