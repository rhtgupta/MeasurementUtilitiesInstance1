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
	public double getConvertedResult(String conversionKey, double input) {
		switch (conversionKey) {
		case CELSIUS_FAHRENHEIT:
			return (input * 9 / 5) + 32;

		case CELSIUS_KELVIN:
			return input + 273.15;

		case FAHRENHEIT_CELSIUS:
			return (input - 32) * 5 / 9;

		case FAHRENHEIT_KELVIN:
			return (input - 32) * 5 / 9 + 273.15;

		case KELVIN_CELSIUS:
			return input - 273.15;

		case KELVIN_FAHRENHEIT:
			return (input - 273.15) * 9 / 5 + 32;

		default:
			return 1.00;

		}
	}

}
