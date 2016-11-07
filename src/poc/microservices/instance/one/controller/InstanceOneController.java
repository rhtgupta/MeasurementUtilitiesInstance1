package poc.microservices.instance.one.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poc.microservices.instance.one.dto.User;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@RestController
public class InstanceOneController {

	private Map<String, Integer> totalRequestCountMap;

	public InstanceOneController() {
		HazelcastInstance instance = Hazelcast.newHazelcastInstance();
		totalRequestCountMap = instance.getMap("requestCount");
	}

	@RequestMapping("/getUser")
	public User test(
			@RequestParam(value = "name", defaultValue = "Instance1") String name) {
		System.out.println("11111111111111111111111111111");
		Integer count = totalRequestCountMap.get("user");
		if (count == null) {
			totalRequestCountMap.put("user", 0);
		} else {
			totalRequestCountMap.put("user", count + 1);
		}
		// System.out.println("Total nos. of rwquest :"
		// + totalRequestCountMap.get("user"));
		/*
		 * try { Thread.sleep(1000 * 60); } catch (InterruptedException e) {
		 * 
		 * }
		 */
		return new User(11111, name);
	}

	@RequestMapping("/getUserConversionResult")
	public double getUserConversionResult(/*
			@RequestParam(value = "convertFromTextField") String convertFromTextFieldParam,
			@RequestParam(value = "convertFrom") String convertFrom,
			@RequestParam(value = "convertTo") String convertTo*/) {
		
		double result = 0;
		System.out.println("11111111111111111111111111111");
		return result;
		/*
		 * Integer count = totalRequestCountMap.get("user"); if (count == null)
		 * { totalRequestCountMap.put("user", 0); } else {
		 * totalRequestCountMap.put("user", count + 1); }
		 */
		// System.out.println("Total nos. of rwquest :"
		// + totalRequestCountMap.get("user"));
		/*
		 * try { Thread.sleep(1000 * 60); } catch (InterruptedException e) {
		 * 
		 * }
		 */
		/*double convertFromTextField = Double.valueOf(convertFromTextFieldParam);
		System.out.println("convertFromTextField : " + convertFromTextField
				+ "convertFrom : " + convertFrom + " convertTo : " + convertTo);
		if (convertFrom.equalsIgnoreCase(convertTo))
			return convertFromTextField;

		switch (convertFrom + convertTo) {
		case "inchfeet": {
			result = convertFromTextField * 0.083;
		}
		case "inchmeter": {
			result = convertFromTextField * 0.025;
		}
		case "feetmeter": {
			result = convertFromTextField * 0.304;
		}
		case "feetinch": {
			result = convertFromTextField * 12;
		}
		case "meterfeet": {
			result = convertFromTextField * 3.280;
		}
		case "meterinch": {
			result = convertFromTextField * 39.370;
		}
		}
		return new BigDecimal(result).setScale(2, RoundingMode.HALF_UP)
				.doubleValue();*/
	}

}
