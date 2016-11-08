package poc.microservices.instance.one.dto;

public class Result {

	private String servedBy;
	private String avaiilableInCache;
	private double result;
	public String getServedBy() {
		return servedBy;
	}
	public void setServedBy(String servedBy) {
		this.servedBy = servedBy;
	}
	public String getAvaiilableInCache() {
		return avaiilableInCache;
	}
	public void setAvaiilableInCache(String avaiilableInCache) {
		this.avaiilableInCache = avaiilableInCache;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}

	

}
