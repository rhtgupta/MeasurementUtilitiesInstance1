package com.impetus.utility.service;

import static com.impetus.utility.common.Constants.NO;
import static com.impetus.utility.common.Constants.YES;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import com.impetus.utility.dto.ConversionInfo;

public abstract class ConversionAbstract {

	public abstract double getMultiplicationFactor(String conversionKey);

	public ConversionInfo populateConversionResult(ConversionInfo request,
			Map<String, Double> cachedMap) {
		String conversionKey = request.getFrom().concat(request.getTo());
		String cacheConversionKey = request.getConvert().concat(conversionKey);

		try {
			request.setServedBy(InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			// implement logger here.
		}

		if (isConversionUnitSame(request))
			return request;

		if (isAvailableInCache(cacheConversionKey, cachedMap, request))
			return request;

		return calculate(cacheConversionKey, cacheConversionKey,
				getMultiplicationFactor(conversionKey), cachedMap, request);
	}

	private boolean isConversionUnitSame(ConversionInfo request) {
		if (!request.getFrom().equalsIgnoreCase(request.getTo()))
			return false;
		request.setAvailableInCache(YES);
		request.setOutput(Double.valueOf(request.getConvert()));
		return true;
	}

	private boolean isAvailableInCache(String cacheConversionKey,
			Map<String, Double> cachedMap, ConversionInfo request) {
		if (cachedMap.get(cacheConversionKey) == null)
			return false;
		request.setAvailableInCache(YES);
		request.setOutput(cachedMap.get(cacheConversionKey));
		return true;
	}

	private ConversionInfo calculate(String cacheConversionKey,
			String conversionKey, double multiplicationFactor,
			Map<String, Double> cachedMap, ConversionInfo request) {

		double formattedOutput = new BigDecimal(Double.valueOf(request
				.getConvert()) * multiplicationFactor).setScale(2,
				RoundingMode.HALF_UP).doubleValue();
		cachedMap.put(cacheConversionKey, formattedOutput);
		request.setAvailableInCache(NO);
		request.setOutput(formattedOutput);
		return request;
	}

}
