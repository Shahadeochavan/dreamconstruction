package com.nextech.systeminventory.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nextech.systeminventory.dto.ClientDTO;
import com.nextech.systeminventory.dto.NotificationDTO;
import com.nextech.systeminventory.dto.ProductOrderDTO;
import com.nextech.systeminventory.dto.ProductOrderPDFData;


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
	
}
