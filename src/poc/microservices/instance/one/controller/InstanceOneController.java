package poc.microservices.instance.one.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import poc.microservices.instance.one.dto.Result;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@RestController
public class InstanceOneController {

	private static final String NO = "No";
	private static final String YES = "Yes";
	public static final String SERVER_NAME_KEY = "ServedBy";
	public static final String SERVER_NAME_VALUE = "Server 1";
	public static final String AVAILABLE_IN_CACHE_KEY = "AvaiilableInCache";
	public static final String RESULT_KEY = "Result";
	public static final String INCH_FOOT = "InchFoot";
	public static final String INCH_CENTIMETRE = "InchCentimetre";
	public static final String INCH_METRE = "InchMetre";

	public static final String FOOT_INCH = "FootInch";
	public static final String FOOT_CENTIMETRE = "FootCentimetre";
	public static final String FOOT_METRE = "FootMetre";

	public static final String CENTIMETRE_INCH = "CentimetreInch";
	public static final String CENTIMETRE_FOOT = "CentimetreFoot";
	public static final String CENTIMETRE_METRE = "CentimetreMetre";

	public static final String METRE_INCH = "MetreInch";
	public static final String METRE_FOOT = "MetreFoot";
	public static final String METRE_CENTIMETRE = "MetreCentimetre";

	public static final String CELSIUS_FAHRENHEIT = "CelsiusFahrenheit";
	public static final String CELSIUS_KELVIN = "CelsiusKelvin";

	public static final String FAHRENHEIT_CELSIUS = "FahrenheitCelsius";
	public static final String FAHRENHEIT_KELVIN = "FahrenheitKelvin";

	public static final String KELVIN_CELSIUS = "KelvinCelsius";
	public static final String KELVIN_FAHRENHEIT = "KelvinFahrenheit";

	private Map<String, Double> cachedConversion;

	public InstanceOneController() {
		HazelcastInstance instance = Hazelcast.newHazelcastInstance();
		cachedConversion = instance.getMap("cachedConversion");
	}

	@RequestMapping(value = "/getMeasurementConversionResult/{convertFromTextField}/{convertFrom}/{convertTo}", produces = "application/json")
	public @ResponseBody Result getUserConversionResult(
			@PathVariable(value = "convertFromTextField") String convertFromTextFieldParam,
			@PathVariable(value = "convertFrom") String convertFrom,
			@PathVariable(value = "convertTo") String convertTo) {
		String conversionKey = convertFrom + convertTo;
		String cacheConversionKey = convertFromTextFieldParam + conversionKey;
		double result = 0;
		Result resultObject = new Result();
		resultObject.setServedBy(SERVER_NAME_VALUE);
		double convertFromTextField = Double.valueOf(convertFromTextFieldParam);
		if (convertFrom.equalsIgnoreCase(convertTo)) {
			resultObject.setAvaiilableInCache(YES);
			resultObject.setResult(convertFromTextField);
			return resultObject;
		}

		if (cachedConversion.get(cacheConversionKey) != null) {
			resultObject.setAvaiilableInCache(YES);
			resultObject.setResult(cachedConversion.get(cacheConversionKey));
			return resultObject;
		}

		switch (conversionKey) {
		case INCH_FOOT: {
			result = convertFromTextField * 0.083;
			break;
		}
		case INCH_CENTIMETRE: {
			result = convertFromTextField * 2.54;
			break;
		}
		case INCH_METRE: {
			result = convertFromTextField * 0.025;
			break;
		}
		case FOOT_INCH: {
			result = convertFromTextField * 12;
			break;
		}
		case FOOT_CENTIMETRE: {
			result = convertFromTextField * 30.48;
			break;
		}
		case FOOT_METRE: {
			result = convertFromTextField * 0.3048;
			break;
		}
		case CENTIMETRE_INCH: {
			result = convertFromTextField * 0.393701;
			break;
		}
		case CENTIMETRE_FOOT: {
			result = convertFromTextField * 0.0328084;
			break;
		}
		case CENTIMETRE_METRE: {
			result = convertFromTextField * 0.01;
			break;
		}
		case METRE_INCH: {
			result = convertFromTextField * 39.3701;
			break;
		}
		case METRE_FOOT: {
			result = convertFromTextField * 3.28084;
			break;
		}
		case METRE_CENTIMETRE: {
			result = convertFromTextField * 100;
			break;
		}
		}
		double calculatedResult = new BigDecimal(result).setScale(2,
				RoundingMode.HALF_UP).doubleValue();
		cachedConversion.put(cacheConversionKey, calculatedResult);
		resultObject.setAvaiilableInCache(NO);
		resultObject.setResult(calculatedResult);
		return resultObject;

	}

	@RequestMapping(value = "/getTemperatureConversionResult/{convertFromTextField}/{convertFrom}/{convertTo}", produces = "application/json")
	public @ResponseBody Result calculateTemperatureConversion(
			@PathVariable(value = "convertFromTextField") String convertFromTextFieldParam,
			@PathVariable(value = "convertFrom") String convertFrom,
			@PathVariable(value = "convertTo") String convertTo) {
		String conversionKey = convertFrom + convertTo;
		String cacheConversionKey = convertFromTextFieldParam + conversionKey;
		double result = 0;
		double convertFromTextField = Double.valueOf(convertFromTextFieldParam);

		Result resultObject = new Result();
		resultObject.setServedBy(SERVER_NAME_VALUE);

		if (convertFrom.equalsIgnoreCase(convertTo)) {
			resultObject.setAvaiilableInCache(YES);
			resultObject.setResult(convertFromTextField);
			return resultObject;
		}

		if (cachedConversion.get(cacheConversionKey) != null) {
			resultObject.setAvaiilableInCache(YES);
			resultObject.setResult(cachedConversion.get(cacheConversionKey));
			return resultObject;
		}

		switch (conversionKey) {
		case CELSIUS_FAHRENHEIT: {
			result = convertFromTextField * 33.8;
			break;
		}
		case CELSIUS_KELVIN: {
			result = convertFromTextField * 274.15;
			break;
		}
		case FAHRENHEIT_CELSIUS: {
			result = convertFromTextField * -17.22;
			break;
		}
		case FAHRENHEIT_KELVIN: {
			result = convertFromTextField * 255.92;
			break;
		}
		case KELVIN_CELSIUS: {
			result = convertFromTextField * -272.15;
			break;
		}
		case KELVIN_FAHRENHEIT: {
			result = convertFromTextField * -457.87;
			break;
		}
		}
		double calculatedResult = new BigDecimal(result).setScale(2,
				RoundingMode.HALF_UP).doubleValue();
		cachedConversion.put(cacheConversionKey, calculatedResult);

		resultObject.setAvaiilableInCache(NO);
		resultObject.setResult(calculatedResult);
		return resultObject;
	}

}
