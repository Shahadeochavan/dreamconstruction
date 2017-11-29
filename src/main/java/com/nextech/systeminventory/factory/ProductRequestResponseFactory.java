package com.nextech.systeminventory.factory;


import javax.servlet.http.HttpServletRequest;


import com.nextech.systeminventory.dto.ProductDTO;
import com.nextech.systeminventory.model.Product;


public class ProductRequestResponseFactory {
	
	public static Product setProduct(ProductDTO productDTO,HttpServletRequest request){
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setDescription(productDTO.getDescription());
		product.setDesign(productDTO.getDesign());
		product.setProductCode(productDTO.getProductCode());
		product.setName(productDTO.getName());
		product.setHsnCode(productDTO.getHsnCode());
		product.setGst(productDTO.getGst());
		product.setIsactive(true);
		product.setPricePerUnit(Float.valueOf(productDTO.getPricePerUnit()));
		//product.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
		return product;
	}

	public static Product setProductUpdate(ProductDTO productDTO){
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setDescription(productDTO.getDescription());
		product.setDesign(productDTO.getDesign());
		product.setProductCode(productDTO.getProductCode());
		product.setName(productDTO.getName());
		product.setDesign(productDTO.getDesign());
		product.setHsnCode(productDTO.getHsnCode());
		product.setGst(productDTO.getGst());
		product.setIsactive(true);
		return product;
	}

	public static ProductDTO setProductDto(Product product){
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setPricePerUnit(String.valueOf(product.getPricePerUnit()));
		productDTO.setDescription(product.getDescription());
		productDTO.setDesign(product.getDesign());
		productDTO.setProductCode(product.getProductCode());
		productDTO.setHsnCode(product.getHsnCode());
		productDTO.setName(product.getName());
		productDTO.setActive(true);
		productDTO.setCreatedBy(product.getCreatedBy());
		productDTO.setCreatedDate(product.getCreatedDate());
		productDTO.setUpdatedBy(product.getUpdatedBy());
		productDTO.setUpdatedDate(product.getUpdatedDate());
		productDTO.setDesign(product.getDesign());
		productDTO.setGst(product.getGst());
		return productDTO;
	}
}
