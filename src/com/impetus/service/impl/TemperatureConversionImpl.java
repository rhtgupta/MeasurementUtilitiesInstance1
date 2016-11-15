package com.impetus.service.impl;

import static com.impetus.service.common.Constants.CELSIUS_FAHRENHEIT;
import static com.impetus.service.common.Constants.CELSIUS_KELVIN;
import static com.impetus.service.common.Constants.FAHRENHEIT_CELSIUS;
import static com.impetus.service.common.Constants.FAHRENHEIT_KELVIN;
import static com.impetus.service.common.Constants.KELVIN_CELSIUS;
import static com.impetus.service.common.Constants.KELVIN_FAHRENHEIT;
import static com.impetus.service.common.Constants.NO;
import static com.impetus.service.common.Constants.YES;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.impetus.service.dto.ConversionInfo;

@Service
public class TemperatureConversionImpl {

	@Value("${server.port}")
	private String port;

	public ConversionInfo convert(ConversionInfo request,
			Map<String, Double> cachedMap) {
		double output = 0;
		double input = Double.valueOf(request.getConvert());
		String conversionKey = request.getFrom() + request.getTo();
		String cacheConversionKey = request.getConvert() + conversionKey;
		ConversionInfo response = new ConversionInfo();
		try {
			response.setServedBy(InetAddress.getLocalHost().getHostName()
					+ " : " + port);
		} catch (UnknownHostException e) {
			// implement logger here.
		}
		response.setConvert(request.getConvert());
		response.setFrom(request.getFrom());
		response.setTo(request.getTo());

		if (request.getFrom().equalsIgnoreCase(request.getTo())) {
			response.setAvailableInCache(YES);
			response.setOutput(input);
			return response;
		}

		if (cachedMap.get(cacheConversionKey) != null) {
			response.setAvailableInCache(YES);
			response.setOutput(cachedMap.get(cacheConversionKey));
			return response;
		}

		switch (conversionKey) {
		case CELSIUS_FAHRENHEIT: {
			output = input * 33.8;
			break;
		}
		case CELSIUS_KELVIN: {
			output = input * 274.15;
			break;
		}
		case FAHRENHEIT_CELSIUS: {
			output = input * -17.22;
			break;
		}
		case FAHRENHEIT_KELVIN: {
			output = input * 255.92;
			break;
		}
		case KELVIN_CELSIUS: {
			output = input * -272.15;
			break;
		}
		case KELVIN_FAHRENHEIT: {
			output = input * -457.87;
			break;
		}
		}
		double formattedOutput = new BigDecimal(output).setScale(2,
				RoundingMode.HALF_UP).doubleValue();
		cachedMap.put(cacheConversionKey, formattedOutput);
		response.setAvailableInCache(NO);
		response.setOutput(formattedOutput);
		return response;

	}

}
