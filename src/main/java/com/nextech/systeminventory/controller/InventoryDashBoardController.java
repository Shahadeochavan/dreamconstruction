package com.nextech.systeminventory.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.systeminventory.constants.ERPConstants;
import com.nextech.systeminventory.dto.InventoryDashBoardDTO;
import com.nextech.systeminventory.model.Product;
import com.nextech.systeminventory.model.Productorder;
import com.nextech.systeminventory.service.ProductService;
import com.nextech.systeminventory.service.ProductorderService;

@Controller
@RequestMapping("/InventoryDashBoard")
public class InventoryDashBoardController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductorderService productorderService;
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody InventoryDashBoardDTO getInventoryDashBoard() {
		List<Product> productList = null;
		List<Productorder> productorderList = null;
		InventoryDashBoardDTO inventoryDashBoardDTO = new InventoryDashBoardDTO();
		try {
			productList = productService.getEntityList(Product.class);
			productorderList = productorderService.getEntityList(Productorder.class);
			
			long productOrderCompleteCountCurrentDate = 0;
			long productOrderInCompleteCountCurrentDate = 0;
			long productOrderCompleteCount = 0;
			long productOrderIncompleteCount = 0;
			long productOrderNewCount =0;
			LocalDate localDate = LocalDate.now();
			String strDate =String.valueOf(localDate);
			for (Productorder productorder : productorderList) {
				String strExptDate = String.valueOf(productorder.getExpecteddeliveryDate());
				if(strExptDate.equals(strDate)&&productorder.getStatus().getId()==Long.valueOf(messageSource.getMessage(ERPConstants.STATUS_PRODUCT_ORDER_COMPLETE, null, null))){
					productOrderCompleteCountCurrentDate++;
				}
				if(strExptDate.equals(strDate)&&productorder.getStatus().getId()==Long.valueOf(messageSource.getMessage(ERPConstants.STATUS_PRODUCT_ORDER_INCOMPLETE, null, null))){
					productOrderInCompleteCountCurrentDate++;
				}
				if(productorder.getStatus().getId()==Long.valueOf(messageSource.getMessage(ERPConstants.STATUS_PRODUCT_ORDER_COMPLETE, null, null))){
					productOrderCompleteCount++;
				}
				if(productorder.getStatus().getId()==Long.valueOf(messageSource.getMessage(ERPConstants.STATUS_PRODUCT_ORDER_INCOMPLETE, null, null))){
					productOrderIncompleteCount++;
				}
				if(productorder.getStatus().getId()==Long.valueOf(messageSource.getMessage(ERPConstants.STATUS_NEW_PRODUCT_ORDER, null, null))){
					productOrderNewCount++;
				}
			}
		
			inventoryDashBoardDTO.setProductCount(productList.size());
			inventoryDashBoardDTO.setProductOrderCompleteCount(productOrderCompleteCount);
			inventoryDashBoardDTO.setProductOrderIncompleteCount(productOrderIncompleteCount);
			inventoryDashBoardDTO.setProductOrderCompleteCountCurrentDate(productOrderCompleteCountCurrentDate);
			inventoryDashBoardDTO.setProductOrderInCompleteCountCurrentDate(productOrderInCompleteCountCurrentDate);
			inventoryDashBoardDTO.setProductOrderNewCount(productOrderNewCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inventoryDashBoardDTO;
	}
}
