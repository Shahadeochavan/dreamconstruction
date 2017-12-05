package com.nextech.dreamConstruction.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.dreamConstruction.constants.DreamConstructionConstants;
import com.nextech.dreamConstruction.dto.InventoryDashBoardDTO;
import com.nextech.dreamConstruction.model.Product;
import com.nextech.dreamConstruction.model.Productorder;
import com.nextech.dreamConstruction.model.Purchase;
import com.nextech.dreamConstruction.service.ProductService;
import com.nextech.dreamConstruction.service.ProductorderService;
import com.nextech.dreamConstruction.service.PurchaseService;

@Controller
@RequestMapping("/InventoryDashBoard")
public class InventoryDashBoardController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductorderService productorderService;
	
	@Autowired
	PurchaseService purchaseService;
	
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody InventoryDashBoardDTO getInventoryDashBoard() {
		List<Product> productList = null;
		List<Productorder> productorderList = null;
		List<Purchase> purchases =null;
		InventoryDashBoardDTO inventoryDashBoardDTO = new InventoryDashBoardDTO();
		try {
			productList = productService.getEntityList(Product.class);
			productorderList = productorderService.getEntityList(Productorder.class);
			purchases =purchaseService.getEntityList(Purchase.class);
			
			long productOrderCompleteCountCurrentDate = 0;
			long productOrderInCompleteCountCurrentDate = 0;
			long productOrderCompleteCount = 0;
			long productOrderIncompleteCount = 0;
			long productOrderNewCount =0;
			LocalDate localDate = LocalDate.now();
			String strDate =String.valueOf(localDate);
			for (Purchase purchase : purchases) {
				String strExptDate = String.valueOf(purchase.getExpecteddeliveryDate());
				if(strExptDate.equals(strDate)&&purchase.getStatus().getId()==Long.valueOf(messageSource.getMessage(DreamConstructionConstants.PURCHASE_ORDER_COMPLETE, null, null))){
					productOrderCompleteCountCurrentDate++;
				}
				if(strExptDate.equals(strDate)&&purchase.getStatus().getId()==Long.valueOf(messageSource.getMessage(DreamConstructionConstants.PURCHASE_ORDER_INCOMPLETE, null, null))){
					productOrderInCompleteCountCurrentDate++;
				}
				if(purchase.getStatus().getId()==Long.valueOf(messageSource.getMessage(DreamConstructionConstants.PURCHASE_ORDER_COMPLETE, null, null))){
					productOrderCompleteCount++;
				}
				if(purchase.getStatus().getId()==Long.valueOf(messageSource.getMessage(DreamConstructionConstants.PURCHASE_ORDER_INCOMPLETE, null, null))){
					productOrderIncompleteCount++;
				}
				if(purchase.getStatus().getId()==Long.valueOf(messageSource.getMessage(DreamConstructionConstants.STATUS_NEW_PRODUCT_ORDER, null, null))){
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
