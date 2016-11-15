package com.impetus.utility.service;

import static com.impetus.utility.common.Constants.NO;
import static com.impetus.utility.common.Constants.YES;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.impetus.utility.dto.ConversionInfo;

public abstract class ConversionAbstract {

	@Value("${server.port}")
	private String serverPort;

	private Logger logger = LoggerFactory.getLogger(ConversionAbstract.class);

	public abstract double getConvertedResult(String conversionKey, double input);

	public ConversionInfo populateConversionResult(ConversionInfo request,
			Map<String, Double> cachedMap) {
		String conversionKey = request.getFrom().concat(request.getTo());
		String cacheConversionKey = request.getConvert().concat(conversionKey);
		try {
			request.setServedBy(InetAddress.getLocalHost().getHostName()
					+ " : " + serverPort);
		} catch (UnknownHostException e) {
			logger.info("Supressing exception & printing stacktrace ");
			e.printStackTrace();
		}

		if (isConversionUnitSame(request))
			return request;
		logger.info("Check whether same request is already available in cache ?");
		if (isAvailableInCache(cacheConversionKey, cachedMap, request))
			return request;
		logger.info("Same request result is Not available in cache, So will calculate it & put it in cache");
		return calculate(
				cacheConversionKey,
				cacheConversionKey,
				getConvertedResult(conversionKey,
						Double.valueOf(request.getConvert())), cachedMap,
				request);
	}

	private boolean isConversionUnitSame(ConversionInfo request) {
		if (!request.getFrom().equalsIgnoreCase(request.getTo()))
			return false;
		logger.info("Convert unit to & from are same so just returning the input as output");
		request.setAvailableInCache(YES);
		request.setOutput(Double.valueOf(request.getConvert()));
		return true;
	}

	private boolean isAvailableInCache(String cacheConversionKey,
			Map<String, Double> cachedMap, ConversionInfo request) {
		if (cachedMap.get(cacheConversionKey) == null)
			return false;
		logger.info("Same request result is already available in cache, So returing the output from cache");
		request.setAvailableInCache(YES);
		request.setOutput(cachedMap.get(cacheConversionKey));
		return true;
	}

	private ConversionInfo calculate(String cacheConversionKey,
			String conversionKey, double calculatedResult,
			Map<String, Double> cachedMap, ConversionInfo request) {
		logger.info("Entring calculate method");
		double formattedOutput = new BigDecimal(calculatedResult).setScale(2,
				RoundingMode.HALF_UP).doubleValue();
		logger.info("Put formatted output in cache with key "
				+ cacheConversionKey);
		cachedMap.put(cacheConversionKey, formattedOutput);
		request.setAvailableInCache(NO);
		request.setOutput(formattedOutput);
		return request;
	}

}
