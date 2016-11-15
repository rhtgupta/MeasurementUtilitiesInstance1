package com.impetus.utility.serviceImpl;

import static com.impetus.utility.common.Constants.CELSIUS_FAHRENHEIT;
import static com.impetus.utility.common.Constants.CELSIUS_KELVIN;
import static com.impetus.utility.common.Constants.FAHRENHEIT_CELSIUS;
import static com.impetus.utility.common.Constants.FAHRENHEIT_KELVIN;
import static com.impetus.utility.common.Constants.KELVIN_CELSIUS;
import static com.impetus.utility.common.Constants.KELVIN_FAHRENHEIT;

import org.springframework.stereotype.Service;

import com.impetus.utility.service.ConversionAbstract;

@Service
public class TemperatureConversionImpl extends ConversionAbstract {

	@Override
	public double getMultiplicationFactor(String conversionKey) {
		switch (conversionKey) {
		case CELSIUS_FAHRENHEIT:
			return 33.80;

		case CELSIUS_KELVIN:
			return 274.15;

		case FAHRENHEIT_CELSIUS:
			return -17.22;

		case FAHRENHEIT_KELVIN:
			return 255.92;

		case KELVIN_CELSIUS:
			return -272.15;

		case KELVIN_FAHRENHEIT:
			return -457.87;

		default:
			return 1.00;

		}
	}

}
