package com.nextech.erp.dto;

import java.util.List;

public class ReportInputDTO {
	private long id;
	private String dispalyName;
	private String inputType;
	private List<ReportInputDataDTO> data;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDispalyName() {
		return dispalyName;
	}
	public void setDispalyName(String dispalyName) {
		this.dispalyName = dispalyName;
	}
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public List<ReportInputDataDTO> getData() {
		return data;
	}
	public void setData(List<ReportInputDataDTO> data) {
		this.data = data;
	}
}