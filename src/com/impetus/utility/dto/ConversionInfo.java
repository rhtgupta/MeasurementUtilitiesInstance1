package com.impetus.utility.dto;

public class ConversionInfo {

	private String servedBy;
	private String availableInCache;
	private double output;
	private String convert;
	private String from;
	private String to;

	public String getServedBy() {
		return servedBy;
	}

	public void setServedBy(String servedBy) {
		this.servedBy = servedBy;
	}

	public String getAvailableInCache() {
		return availableInCache;
	}

	public void setAvailableInCache(String availableInCache) {
		this.availableInCache = availableInCache;
	}

	public double getOutput() {
		return output;
	}

	public void setOutput(double output) {
		this.output = output;
	}

	public String getConvert() {
		return convert;
	}

	public void setConvert(String convert) {
		this.convert = convert;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
