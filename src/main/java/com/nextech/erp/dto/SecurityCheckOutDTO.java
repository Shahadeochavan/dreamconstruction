package com.nextech.erp.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;

public class SecurityCheckOutDTO {

	private String description;
	private String firstName;
	private String lastName;
	private Date createDate;
	private Time intime;
	private String invoice_No;
	private Time outtime;
	private int poNo;
	private String vehicleNo;
	private String clientname;
	private String licence_no;
	private List<SecurityCheckOutPart> securityCheckOutParts;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Time getIntime() {
		return intime;
	}

	public void setIntime(Time intime) {
		this.intime = intime;
	}
	public Time getOuttime() {
		return outtime;
	}

	public void setOuttime(Time outtime) {
		this.outtime = outtime;
	}

	public int getPoNo() {
		return poNo;
	}

	public void setPoNo(int poNo) {
		this.poNo = poNo;
	}

	public String getLicence_no() {
		return licence_no;
	}

	public void setLicence_no(String licence_no) {
		this.licence_no = licence_no;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public List<SecurityCheckOutPart> getSecurityCheckOutParts() {
		return securityCheckOutParts;
	}

	public void setSecurityCheckOutParts(
			List<SecurityCheckOutPart> securityCheckOutParts) {
		this.securityCheckOutParts = securityCheckOutParts;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getInvoice_No() {
		return invoice_No;
	}

	public void setInvoice_No(String invoice_No) {
		this.invoice_No = invoice_No;
	}

}
