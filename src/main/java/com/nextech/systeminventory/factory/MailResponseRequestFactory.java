package com.nextech.systeminventory.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nextech.systeminventory.dto.ClientDTO;
import com.nextech.systeminventory.dto.NotificationDTO;
import com.nextech.systeminventory.dto.ProductOrderDTO;
import com.nextech.systeminventory.dto.ProductOrderPDFData;
import com.nextech.systeminventory.dto.PurchaseDTO;
import com.nextech.systeminventory.dto.PurchaseOrderPdfData;
import com.nextech.systeminventory.dto.UserDTO;
import com.nextech.systeminventory.dto.VendorDTO;


public class MailResponseRequestFactory {
	
	public static Map<String,Object> setMailDetailsProductOrder(NotificationDTO notification,List<ProductOrderPDFData> productOrderPDFDatas,ClientDTO clientDTO,ProductOrderDTO productOrderDTO){
	       Map < String, Object > model = new HashMap < String, Object >();
	        model.put("companyName", clientDTO.getCompanyName());
	        model.put("mailfrom", notification.getName());
	        model.put("location", "Pune");
	        model.put("productOrderPDFDatas",productOrderPDFDatas);
	        model.put("invoiceNumber",productOrderDTO.getInvoiceNo());
	        model.put("date",productOrderDTO.getCreatedDate());
	        model.put("totalPrice", productOrderDTO.getTotalPrice());
	        model.put("tax", productOrderDTO.getTax());
	        model.put("address", clientDTO.getAddress());
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
	
}
