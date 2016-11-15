package com.impetus.service.impl;

import static com.impetus.service.common.Constants.CENTIMETRE_FOOT;
import static com.impetus.service.common.Constants.CENTIMETRE_INCH;
import static com.impetus.service.common.Constants.CENTIMETRE_METRE;
import static com.impetus.service.common.Constants.FOOT_CENTIMETRE;
import static com.impetus.service.common.Constants.FOOT_INCH;
import static com.impetus.service.common.Constants.FOOT_METRE;
import static com.impetus.service.common.Constants.INCH_CENTIMETRE;
import static com.impetus.service.common.Constants.INCH_FOOT;
import static com.impetus.service.common.Constants.INCH_METRE;
import static com.impetus.service.common.Constants.METRE_CENTIMETRE;
import static com.impetus.service.common.Constants.METRE_FOOT;
import static com.impetus.service.common.Constants.METRE_INCH;
import static com.impetus.service.common.Constants.NO;
import static com.impetus.service.common.Constants.YES;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.impetus.service.dto.ConversionInfo;

@Service
public class MeasurmentConversionImpl {

	@Value("${server.name}")
	private String serverName;

	public ConversionInfo convert(ConversionInfo request,
			Map<String, Double> cachedMap) {
		double output = 0;
		double input = Double.valueOf(request.getConvert());
		String conversionKey = request.getFrom() + request.getTo();
		String cacheConversionKey = request.getConvert() + conversionKey;
		ConversionInfo response = new ConversionInfo();
		response.setServedBy(serverName);
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
		case INCH_FOOT: {
			output = input * 0.083;
			break;
		}
		case INCH_CENTIMETRE: {
			output = input * 2.54;
			break;
		}
		case INCH_METRE: {
			output = input * 0.025;
			break;
		}
		case FOOT_INCH: {
			output = input * 12;
			break;
		}
		case FOOT_CENTIMETRE: {
			output = input * 30.48;
			break;
		}
		case FOOT_METRE: {
			output = input * 0.3048;
			break;
		}
		case CENTIMETRE_INCH: {
			output = input * 0.393701;
			break;
		}
		case CENTIMETRE_FOOT: {
			output = input * 0.0328084;
			break;
		}
		case CENTIMETRE_METRE: {
			output = input * 0.01;
			break;
		}
		case METRE_INCH: {
			output = input * 39.3701;
			break;
		}
		case METRE_FOOT: {
			output = input * 3.28084;
			break;
		}
		case METRE_CENTIMETRE: {
			output = input * 100;
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
