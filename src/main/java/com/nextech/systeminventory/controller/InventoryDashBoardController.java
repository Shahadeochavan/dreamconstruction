package com.nextech.systeminventory.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
				if(strExptDate.equals(strDate)&&productorder.getStatus().getId()==75){
					productOrderCompleteCountCurrentDate++;
				}
				if(strExptDate.equals(strDate)&&productorder.getStatus().getId()==76){
					productOrderInCompleteCountCurrentDate++;
				}
				if(productorder.getStatus().getId()==75){
					productOrderCompleteCount++;
				}
				if(productorder.getStatus().getId()==76){
					productOrderIncompleteCount++;
				}
				if(productorder.getStatus().getId()==74){
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
