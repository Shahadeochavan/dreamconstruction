package com.nextech.systeminventory.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.systeminventory.constants.ERPConstants;
import com.nextech.systeminventory.dto.MultipleProduct;
import com.nextech.systeminventory.dto.ProductDTO;
import com.nextech.systeminventory.factory.ProductRequestResponseFactory;
import com.nextech.systeminventory.model.Product;
import com.nextech.systeminventory.model.Productinventory;
import com.nextech.systeminventory.service.ProductService;
import com.nextech.systeminventory.service.ProductinventoryService;
import com.nextech.systeminventory.status.UserStatus;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	ProductinventoryService productinventoryService;

	@Autowired
	static Logger logger = Logger.getLogger(ProductController.class);
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addProduct(
			@Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			if (productService.getProductByName(productDTO.getName()) != null) {
				return new UserStatus(0, messageSource.getMessage(ERPConstants.PRODUCT_NAME_SHOULD_BE_UNIQUE, null, null));
			}
			if (productService.getProductByPartNumber(productDTO.getPartNumber()) != null) {
				return new UserStatus(0, messageSource.getMessage(ERPConstants.PART_NUMBER_SHOULD_BE_UNIQUE, null, null));
			}
			long id =productService.addEntity(ProductRequestResponseFactory.setProduct(productDTO));
			Product product = productService.getEntityById(Product.class, id);
			addProductInventory(product);
			return new UserStatus(1, "product added Successfully !");
		} catch (ConstraintViolationException cve) {
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			logger.error("Inside PersistenceException");
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			logger.error("Inside Exception");
			e.printStackTrace();
			return new UserStatus(0, e.getCause().getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Product getProduct(@PathVariable("id") long id) {
		Product product = null;
		try {
			product = productService.getEntityById(Product.class,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateProduct(@RequestBody ProductDTO productDTO,HttpServletRequest request,HttpServletResponse response) {
		try {
			ProductDTO oldProductInfo = productService.getProductDTO(productDTO.getId());
			  if(!productDTO.getName().equals(oldProductInfo.getName())){ 	
			     if (productService.getProductByName(productDTO.getName()) != null) {
				    	return new UserStatus(0, messageSource.getMessage(ERPConstants.PRODUCT_NAME_SHOULD_BE_UNIQUE, null, null));
			    	}
				 }
			
	            if(!productDTO.getPartNumber().equals(oldProductInfo.getPartNumber())){  			
					if (productService.getProductByPartNumber(productDTO.getPartNumber()) != null) {
				    	return new UserStatus(0, messageSource.getMessage(ERPConstants.PART_NUMBER_SHOULD_BE_UNIQUE, null, null));
					}
				 }
	            
			productService.updateEntity(ProductRequestResponseFactory.setProduct(productDTO));
			return new UserStatus(1, "Product update Successfully !");
		} catch (Exception e) {
			 e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Product> getProduct() {
		List<Product> ProductList = null;
		try {
			ProductList = productService.getEntityList(Product.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ProductList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteProduct(@PathVariable("id") long id) {

		try {
			Product product = productService.getEntityById(Product.class,id);
			product.setIsactive(false);
			productService.updateEntity(product);
			return new UserStatus(1, "Product deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}
	}

	private void addProductInventory(Product product) throws Exception{
		Productinventory productinventory = new Productinventory();
		productinventory.setProduct(product);
		productinventory.setQuantityavailable(0);
		productinventory.setIsactive(true);
		productinventoryService.addEntity(productinventory);
	}
	
	@RequestMapping(value = "/multipleProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addMultipleProduct(
			@Valid @RequestBody MultipleProduct multipleProduct, BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
		List<ProductDTO> productDTOs =  new ArrayList<ProductDTO>();
			for (ProductDTO productDTO : multipleProduct.getProductDTOList()) {
				if (productService.getProductByName(productDTO.getName()) == null) {
					productDTOs.add(productDTO);
				}
			}
			if(!productDTOs.isEmpty()){
			for (ProductDTO productDTO : productDTOs) {
				long id =productService.addEntity(ProductRequestResponseFactory.setProduct(productDTO));
				Product product = productService.getEntityById(Product.class, id);
				addProductInventory(product);	
			}
			}else{
				return  new UserStatus(0,"Product from uploaded file already exist or file is empty.Please add new products and upload file again.");
			}
			return new UserStatus(1, "Product added Successfully !");
		} catch (ConstraintViolationException cve) {
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			logger.error("Inside PersistenceException");
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			logger.error("Inside Exception");
			e.printStackTrace();
			return new UserStatus(0, e.getCause().getMessage());
		}
	}
}
