package poc.microservices.instance.one.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import poc.microservices.instance.one.dto.User;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@RestController
public class InstanceOneController {

	private Map<String, Double> cachedConversion;

	public InstanceOneController() {
		HazelcastInstance instance = Hazelcast.newHazelcastInstance();
		cachedConversion = instance.getMap("cachedConversion");
	}

	@RequestMapping(value = "/getUserConversionResult/{convertFromTextField}/{convertFrom}/{convertTo}", produces = "application/json")
	public @ResponseBody double getUserConversionResult(
			@PathVariable(value = "convertFromTextField") String convertFromTextFieldParam,
			@PathVariable(value = "convertFrom") String convertFrom,
			@PathVariable(value = "convertTo") String convertTo) {
		String conversionKey = convertFrom + convertTo;
		String cacheConversionKey = convertFromTextFieldParam + conversionKey;
		double result = 0;
		System.out.println("11111111111111111111111111111 "
				+ convertFromTextFieldParam);

		double convertFromTextField = Double.valueOf(convertFromTextFieldParam);

		if (convertFrom.equalsIgnoreCase(convertTo))
			return convertFromTextField;

		if (cachedConversion.get(cacheConversionKey) != null) {
			System.out.println("Server 1 from cache");
			return cachedConversion.get(cacheConversionKey);
		}

		switch (conversionKey) {
		case "inchfeet": {
			result = convertFromTextField * 0.083;

			break;
		}
		case "inchmeter": {
			result = convertFromTextField * 0.025;
			break;
		}
		case "feetmeter": {
			result = convertFromTextField * 0.304;
			break;
		}
		case "feetinch": {
			result = convertFromTextField * 12;
			break;
		}
		case "meterfeet": {
			result = convertFromTextField * 3.280;
			break;
		}
		case "meterinch": {
			result = convertFromTextField * 39.370;
			break;
		}
		}
		double calculatedResult = new BigDecimal(result).setScale(2,
				RoundingMode.HALF_UP).doubleValue();
		cachedConversion.put(cacheConversionKey, calculatedResult);
		System.out.println("Server 1 Not in cache");
		return calculatedResult;
	}

}
