package com.nextech.systeminventory.controller;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nextech.systeminventory.constants.ERPConstants;
import com.nextech.systeminventory.dto.ProductDTO;
import com.nextech.systeminventory.dto.ProductOrderAssociationDTO;
import com.nextech.systeminventory.dto.ProductOrderDTO;
import com.nextech.systeminventory.factory.ProductOrderAssoRequestResponseFactory;
import com.nextech.systeminventory.factory.ProductOrderRequestResponseFactory;
import com.nextech.systeminventory.model.Productinventory;
import com.nextech.systeminventory.model.Productorder;
import com.nextech.systeminventory.model.Productorderassociation;
import com.nextech.systeminventory.model.Status;
import com.nextech.systeminventory.service.ClientService;
import com.nextech.systeminventory.service.ProductService;
import com.nextech.systeminventory.service.ProductinventoryService;
import com.nextech.systeminventory.service.ProductorderService;
import com.nextech.systeminventory.service.ProductorderassociationService;
import com.nextech.systeminventory.service.StatusService;
import com.nextech.systeminventory.service.UserService;
import com.nextech.systeminventory.status.UserStatus;

@Controller
@RequestMapping("/productorder")
public class ProductorderController {

	@Autowired
	ProductorderService productorderService;
	 
	@Autowired
	ProductorderassociationService productorderassociationService;

	@Autowired
	ClientService clientService;

	@Autowired
	StatusService statusService;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductinventoryService productinventoryService;


	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addProductorder(
			@Valid @RequestBody Productorder productorder,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
			productorder.setIsactive(true);
			//productorder.setCreatedBy(Long.parseLong(request.getAttribute("current_user").toString()));
			productorderService.addEntity(productorder);
			return new UserStatus(1, "Product Order added Successfully !");
		} catch (ConstraintViolationException cve) {
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			System.out.println("Inside PersistenceException");
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
			return new UserStatus(0, e.getCause().getMessage());
		}
	}
	
	@RequestMapping(value = "/createmultiple", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addMultipleProductorder(
			@Valid @RequestBody ProductOrderDTO productOrderDTO,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}

			// TODO save call product order
			Productorder productorder = saveProductOrder(productOrderDTO, request, response);

			// TODO add product order association
			addProductOrderAsso(productOrderDTO, productorder, request, response);
			
			return new UserStatus(1,"Multiple Product Order added Successfully !");
		} catch (ConstraintViolationException cve) {
			System.out.println("Inside ConstraintViolationException");
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			System.out.println("Inside PersistenceException");
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			System.out.println("Inside Exception");
			e.printStackTrace();
			return new UserStatus(0, e.getCause().getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Productorder getProductorder(
			@PathVariable("id") long id) {
		Productorder productorder = null;
		try {
			productorder = productorderService.getEntityById(
					Productorder.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productorder;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateProductorder(
			@RequestBody Productorder productorder,HttpServletRequest request,HttpServletResponse response) {
		try {
			productorder.setIsactive(true);
			productorder.setStatus(statusService.getEntityById(Status.class,
					Long.parseLong(messageSource.getMessage(ERPConstants.STATUS_NEW_PRODUCT_ORDER, null, null))));
			productorderService.updateEntity(productorder);
			return new UserStatus(1, "Product Order update Successfully !");
		} catch (Exception e) {
			e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Productorder> getProductorder() {

		List<Productorder> productorderList = null;
		try {
			productorderList = productorderService
					.getEntityList(Productorder.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productorderList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deleteProductorder(
			@PathVariable("id") long id) {

		try {
			Productorder productorder = productorderService.getEntityById(
					Productorder.class, id);
			productorder.setIsactive(false);
			productorderService.updateEntity(productorder);
			return new UserStatus(1, "Product Order deleted Successfully !");
		} catch (Exception e) {
			return new UserStatus(0, e.toString());
		}
	}
	private Productorder saveProductOrder(ProductOrderDTO productOrderDTO,HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		Productorder productorder = ProductOrderRequestResponseFactory.setProductOrder(productOrderDTO);
		productorder.setStatus(statusService.getEntityById(Status.class,Long.parseLong(messageSource.getMessage(ERPConstants.STATUS_NEW_PRODUCT_ORDER, null, null))));
		productorderService.addEntity(productorder);
		return productorder;
	}
	
	private void addProductOrderAsso(ProductOrderDTO productOrderDTO,Productorder productorder,HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<ProductOrderAssociationDTO> ProductOrderAssociationDTOs = productOrderDTO.getProductOrderAssociationDTOs();
		if (ProductOrderAssociationDTOs != null&& !ProductOrderAssociationDTOs.isEmpty()) {
			for (ProductOrderAssociationDTO productOrderAssociationDTO : ProductOrderAssociationDTOs) {
				productOrderAssociationDTO.setProductOrderId(productorder.getId());
				productorderassociationService.addEntity(ProductOrderAssoRequestResponseFactory.setProductPrderAsso(productOrderAssociationDTO, request));
			}
		}
	}
	
	@RequestMapping(value = "/pendingList", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Productorder> getPendingsProductorders() {

		List<Productorder> productorderList = null;
		try {
			// TODO afterwards you need to change it from properties.
			productorderList = productorderService.getPendingProductOrders(Long.parseLong(messageSource.getMessage(ERPConstants.STATUS_NEW_PRODUCT_ORDER, null, null)),Long.parseLong(messageSource.getMessage(ERPConstants.STATUS_PRODUCT_ORDER_INCOMPLETE, null, null)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productorderList;
	}
	
	@RequestMapping(value = "productorderId/{orderId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<ProductOrderAssociationDTO> getProductOrder(
			@PathVariable("orderId") long id) {
		List<Productorderassociation> productorderassociations = null;
		List<ProductOrderAssociationDTO> productOrderAssociationDTOs = new ArrayList<ProductOrderAssociationDTO>();
		try {
			productorderassociations = productorderassociationService.getProductorderassociationByOrderId(id);
			for (Productorderassociation productorderassociation : productorderassociations) {
				ProductOrderAssociationDTO productOrderAssociationDTO =  new ProductOrderAssociationDTO();
				Productinventory productinventory = productinventoryService.getProductinventoryByProductId(productorderassociation.getProduct().getId());
				ProductDTO productDTO =  new ProductDTO();
				productDTO.setId(productorderassociation.getProduct().getId());
				productDTO.setPartNumber(productorderassociation.getProduct().getPartNumber());
				productOrderAssociationDTO.setProductId(productDTO);
				productOrderAssociationDTO.setInventoryQuantity(productinventory.getQuantityavailable());
				productOrderAssociationDTO.setQuantity(productorderassociation.getQuantity());
				productOrderAssociationDTO.setRemainingQuantity(productorderassociation.getRemainingQuantity());
				productOrderAssociationDTOs.add(productOrderAssociationDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productOrderAssociationDTOs;
	}
	@Transactional @RequestMapping(value = "incompleteProductOrder/{CLIENT-ID}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Productorder> getInCompleteProductOrder(@PathVariable("CLIENT-ID") long clientId) {

		List<Productorder> productorderList = null;
		try {
			productorderList = productorderService.getInCompleteProductOrder(clientId,Long.parseLong(messageSource.getMessage(ERPConstants.STATUS_PRODUCT_ORDER_INCOMPLETE, null, null)),
					Long.parseLong(messageSource.getMessage(ERPConstants.STATUS_PRODUCT_ORDER_COMPLETE, null, null)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productorderList;
	}
}
