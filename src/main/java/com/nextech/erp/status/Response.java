package com.nextech.erp.status;

import java.util.List;

import com.nextech.erp.model.Productinventory;
import com.nextech.erp.model.Productorderassociation;

public class Response {
	private int code;
	private String message;
	private Object data;
	private List<Productorderassociation> productorderassociations;
	private List<Productinventory> productinventories;
	public Response() {

	}

	public Response(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public Response(int code, Object data) {
		this.code = code;
		this.data = data;
	}
	public Response(Object data){
		this.data=data;
	}

	public Response(int i, String string,
			List<Productorderassociation> productorderassociationList,
			List<Productinventory> productinventoriesList) {
		// TODO Auto-generated constructor stub
		this.code=i;
		this.message=string;
		this.productorderassociations=productorderassociationList;
		this.productinventories=productinventoriesList;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<Productorderassociation> getProductorderassociations() {
		return productorderassociations;
	}

	public void setProductorderassociations(
			List<Productorderassociation> productorderassociations) {
		this.productorderassociations = productorderassociations;
	}

	public List<Productinventory> getProductinventories() {
		return productinventories;
	}

	public void setProductinventories(List<Productinventory> productinventories) {
		this.productinventories = productinventories;
	}
	
}
