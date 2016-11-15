package com.impetus.utility.controller;

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
import com.impetus.utility.dto.ConversionInfo;
import com.impetus.utility.serviceImpl.ConversionFactory;

@RestController
public class ServiceController {

	@Autowired
	private ConversionFactory conversionFactory;

	private Logger logger = LoggerFactory.getLogger(ServiceController.class);

	private Map<String, Double> cachedMap;

	public ServiceController() {
		logger.info("Creating new instance of Hazelcast cache");
		HazelcastInstance instance = Hazelcast.newHazelcastInstance();
		cachedMap = instance.getMap("cachedConversion");
	}

	@RequestMapping(value = "/calculate/{convert}/{from}/{to}/{type}", produces = "application/json")
	public @ResponseBody ConversionInfo calculateConversion(
			@PathVariable(value = "convert") String convert,
			@PathVariable(value = "from") String from,
			@PathVariable(value = "to") String to,
			@PathVariable(value = "type") String type) {
		logger.info("Serving request, convert " + convert + " " + from + "-to-"
				+ to + "-type-" + type);
		ConversionInfo conversionInfo = new ConversionInfo();
		conversionInfo.setConvert(convert);
		conversionInfo.setFrom(from);
		conversionInfo.setTo(to);
		return conversionFactory.getConversionType(type)
				.populateConversionResult(conversionInfo, cachedMap);
	}
}
