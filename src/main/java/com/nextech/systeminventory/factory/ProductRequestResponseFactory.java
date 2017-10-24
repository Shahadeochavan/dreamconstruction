package com.nextech.systeminventory.factory;


import com.nextech.systeminventory.dto.ProductDTO;
import com.nextech.systeminventory.model.Product;


public class ProductRequestResponseFactory {
	
	public static Product setProduct(ProductDTO productDTO){
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setClientpartnumber(productDTO.getClientPartNumber());
		product.setDescription(productDTO.getDescription());
		product.setDesign(productDTO.getDesign());
		product.setPartNumber(productDTO.getPartNumber());
		product.setName(productDTO.getName());
		product.setIsactive(true);
		product.setRatePerUnit(productDTO.getRatePerUnit());
		return product;
	}

	public static Product setProductUpdate(ProductDTO productDTO){
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setClientpartnumber(productDTO.getClientPartNumber());
		product.setDescription(productDTO.getDescription());
		product.setDesign(productDTO.getDesign());
		product.setPartNumber(productDTO.getPartNumber());
		product.setName(productDTO.getName());
		product.setDesign(productDTO.getDesign());
		product.setIsactive(true);
		return product;
	}

	public static ProductDTO setProductDto(Product product){
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setRatePerUnit(product.getRatePerUnit());
		productDTO.setClientPartNumber(product.getClientpartnumber());
		productDTO.setDescription(product.getDescription());
		productDTO.setDesign(product.getDesign());
		productDTO.setPartNumber(product.getPartNumber());
		productDTO.setName(product.getName());
		productDTO.setActive(true);
		productDTO.setCreatedBy(product.getCreatedBy());
		productDTO.setCreatedDate(product.getCreatedDate());
		productDTO.setUpdatedBy(product.getUpdatedBy());
		productDTO.setUpdatedDate(product.getUpdatedDate());
		productDTO.setDesign(product.getDesign());
		return productDTO;
	}
}
