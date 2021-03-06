package com.nextech.dreamConstruction.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nextech.dreamConstruction.dto.ClientDTO;
import com.nextech.dreamConstruction.dto.NotificationDTO;
import com.nextech.dreamConstruction.dto.ProductOrderDTO;
import com.nextech.dreamConstruction.dto.ProductOrderPDFData;
import com.nextech.dreamConstruction.dto.PurchaseDTO;
import com.nextech.dreamConstruction.dto.PurchaseOrderPdfData;
import com.nextech.dreamConstruction.dto.UserDTO;
import com.nextech.dreamConstruction.dto.VendorDTO;
import com.nextech.dreamConstruction.model.Contractor;


public class MailResponseRequestFactory {
	
	public static Map<String,Object> setMailDetailsProductOrder(NotificationDTO notification,List<ProductOrderPDFData> productOrderPDFDatas,Contractor contractor,ProductOrderDTO productOrderDTO){
	       Map < String, Object > model = new HashMap < String, Object >();
	        model.put("companyName", contractor.getFirstName());
	        model.put("mailfrom", notification.getName());
	        model.put("location", "Pune");
	        model.put("productOrderPDFDatas",productOrderPDFDatas);
	        model.put("invoiceNumber",productOrderDTO.getInvoiceNo());
	        model.put("date",productOrderDTO.getCreatedDate());
	        model.put("totalPrice", productOrderDTO.getTotalPrice());
	        model.put("tax", productOrderDTO.getTax());
	        model.put("address", contractor.getAddress());
	        model.put("signature", "www.NextechServices.in");
	        return model;
	}
	
	public static Map<String,Object> setMailDetailsPurchaseOrder(NotificationDTO notification,List<PurchaseOrderPdfData> purchaseOrderPdfDatas,VendorDTO vendorDTO,PurchaseDTO purchaseDTO){
	       Map < String, Object > model = new HashMap < String, Object >();
	        model.put("companyName", vendorDTO.getCompanyName());
	        model.put("mailfrom", notification.getName());
	        model.put("invoiceNumber",purchaseDTO.getName());
	        model.put("address", vendorDTO.getAddress());
	        model.put("location", "Pune");
	        model.put("purchaseOrderPdfDatas",purchaseOrderPdfDatas);
	        model.put("signature", "www.NextechServices.in");
	        return model;
	}
	
	public static Map<String, Object> setMailDetailsUser(UserDTO userDTO) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("email", userDTO.getEmailId());
		model.put("userId", userDTO.getUserId());
		model.put("password", userDTO.getPassword());
		model.put("location", "Pune");
		model.put("signature", "www.NextechServices.in");
		return model;
	}
	public static Map<String,Object> setMailDetailsClient(ClientDTO clientDTO){
		 Map < String, Object > model = new HashMap < String, Object > ();
	        model.put("firstName", clientDTO.getCompanyName());
	        model.put("email", clientDTO.getEmailId());
	        model.put("contactNumber", clientDTO.getContactNumber());
	        model.put("location", "Pune");
	        model.put("signature", "www.NextechServices.in");
	        return model;
	}
	public static Map<String, Object> setMailDetailsVendor(VendorDTO vendorDTO) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("firstName", vendorDTO.getFirstName());
		model.put("lastName", vendorDTO.getLastName());
		model.put("email", vendorDTO.getEmail());
		 model.put("contactNumber", vendorDTO.getContactNumberMobile());
		model.put("location", "Pune");
		model.put("signature", "www.NextechServices.in");
		return model;
	}
}
