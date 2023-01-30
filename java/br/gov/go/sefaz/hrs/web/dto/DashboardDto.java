package br.gov.go.sefaz.hrs.web.dto;

public class DashboardDto {

	private Long countriesQty;
	private Long locationsQty;
	private Long employeesQty;
	private Long jobsQty;
	private Long regionsQty;
	
	public Long getCountriesQty() {
		return countriesQty;
	}
	
	public void setCountriesQty(Long countriesQty) {
		this.countriesQty = countriesQty;
	}
	
	public Long getLocationsQty() {
		return locationsQty;
	}
	
	public void setLocationsQty(Long locationsQty) {
		this.locationsQty = locationsQty;
	}
	
	public Long getEmployeesQty() {
		return employeesQty;
	}
	
	public void setEmployeesQty(Long employeesQty) {
		this.employeesQty = employeesQty;
	}
	
	public Long getJobsQty() {
		return jobsQty;
	}
	
	public void setJobsQty(Long jobsQty) {
		this.jobsQty = jobsQty;
	}

	public Long getRegionsQty() {
		return regionsQty;
	}

	public void setRegionsQty(Long regionsQty) {
		this.regionsQty = regionsQty;
	}
}