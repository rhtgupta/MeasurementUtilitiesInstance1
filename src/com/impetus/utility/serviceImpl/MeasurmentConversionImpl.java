package com.impetus.utility.serviceImpl;

import static com.impetus.utility.common.Constants.CENTIMETRE_FOOT;
import static com.impetus.utility.common.Constants.CENTIMETRE_INCH;
import static com.impetus.utility.common.Constants.CENTIMETRE_METRE;
import static com.impetus.utility.common.Constants.FOOT_CENTIMETRE;
import static com.impetus.utility.common.Constants.FOOT_INCH;
import static com.impetus.utility.common.Constants.FOOT_METRE;
import static com.impetus.utility.common.Constants.INCH_CENTIMETRE;
import static com.impetus.utility.common.Constants.INCH_FOOT;
import static com.impetus.utility.common.Constants.INCH_METRE;
import static com.impetus.utility.common.Constants.METRE_CENTIMETRE;
import static com.impetus.utility.common.Constants.METRE_FOOT;
import static com.impetus.utility.common.Constants.METRE_INCH;

import org.springframework.stereotype.Service;

import com.impetus.utility.service.ConversionAbstract;

@Service
public class MeasurmentConversionImpl extends ConversionAbstract {

	@Override
	public double getConvertedResult(String conversionKey, double input) {

		switch (conversionKey) {
		case INCH_FOOT:
			return input * 0.083;

		case INCH_CENTIMETRE:
			return input * 2.54;

		case INCH_METRE:
			return input * 0.025;

		case FOOT_INCH:
			return input * 12;

		case FOOT_CENTIMETRE:
			return input * 30.48;

		case FOOT_METRE:
			return input * 0.3048;

		case CENTIMETRE_INCH:
			return input * 0.393701;

		case CENTIMETRE_FOOT:
			return input * 0.0328084;

		case CENTIMETRE_METRE:
			return input * 0.01;

		case METRE_INCH:
			return input * 39.3701;

		case METRE_FOOT:
			return input * 3.28084;

		case METRE_CENTIMETRE:
			return input * 100;
		default:
			return input;

		}
	}
}
