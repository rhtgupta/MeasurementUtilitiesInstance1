package poc.microservices.instance.one.dto;

public class Result {

	private String servedBy;
	private String availableInCache;
	private double result;
	private String convertFromTextField;
	private String convertFrom;
	private String convertTo;

	public String getServedBy() {
		return servedBy;
	}

	public void setServedBy(String servedBy) {
		this.servedBy = servedBy;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public String getAvailableInCache() {
		return availableInCache;
	}

	public void setAvailableInCache(String availableInCache) {
		this.availableInCache = availableInCache;
	}

	public String getConvertFromTextField() {
		return convertFromTextField;
	}

	public void setConvertFromTextField(String convertFromTextField) {
		this.convertFromTextField = convertFromTextField;
	}

	public String getConvertFrom() {
		return convertFrom;
	}

	public void setConvertFrom(String convertFrom) {
		this.convertFrom = convertFrom;
	}

	public String getConvertTo() {
		return convertTo;
	}

	public void setConvertTo(String convertTo) {
		this.convertTo = convertTo;
	}

}
