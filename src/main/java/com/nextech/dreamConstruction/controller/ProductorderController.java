package com.nextech.dreamConstruction.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.ServletContext;
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

import com.nextech.dreamConstruction.constants.DreamConstructionConstants;
import com.nextech.dreamConstruction.dto.ClientDTO;
import com.nextech.dreamConstruction.dto.NotificationDTO;
import com.nextech.dreamConstruction.dto.ProductDTO;
import com.nextech.dreamConstruction.dto.ProductOrderAssociationDTO;
import com.nextech.dreamConstruction.dto.ProductOrderDTO;
import com.nextech.dreamConstruction.dto.ProductOrderPDFData;
import com.nextech.dreamConstruction.dto.StatusDTO;
import com.nextech.dreamConstruction.factory.MailResponseRequestFactory;
import com.nextech.dreamConstruction.factory.ProductOrderAssoRequestResponseFactory;
import com.nextech.dreamConstruction.factory.ProductOrderRequestResponseFactory;
import com.nextech.dreamConstruction.model.Contractor;
import com.nextech.dreamConstruction.model.Mail;
import com.nextech.dreamConstruction.model.Productinventory;
import com.nextech.dreamConstruction.model.Productorder;
import com.nextech.dreamConstruction.model.Productorderassociation;
import com.nextech.dreamConstruction.model.Status;
import com.nextech.dreamConstruction.pdfClass.ProductOrderPdf;
import com.nextech.dreamConstruction.service.ClientService;
import com.nextech.dreamConstruction.service.ContractorService;
import com.nextech.dreamConstruction.service.MailService;
import com.nextech.dreamConstruction.service.NotificationService;
import com.nextech.dreamConstruction.service.ProductService;
import com.nextech.dreamConstruction.service.ProductinventoryService;
import com.nextech.dreamConstruction.service.ProductorderService;
import com.nextech.dreamConstruction.service.ProductorderassociationService;
import com.nextech.dreamConstruction.service.StatusService;
import com.nextech.dreamConstruction.service.UserService;
import com.nextech.dreamConstruction.status.UserStatus;
import com.nextech.dreamConstruction.util.PDFToByteArrayOutputStreamUtil;

import org.apache.log4j.Logger;

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
	
	@Autowired
	static Logger logger = Logger.getLogger(ProductorderController.class);
	
	@Autowired
	MailService mailService;
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	ContractorService contractorService;


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
			logger.error(cve);
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			logger.error(pe);
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			logger.error(e);
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

			String invoiceNumber = generateInvoice() + productorder.getId();
			productorder.setInvoiceNo(invoiceNumber);
			productorderService.updateEntity(productorder);
			// TODO add product order association
			productOrderDTO.setInvoiceNo(productorder.getInvoiceNo());
			addProductOrderAsso(productOrderDTO, productorder, request, response);
			
			return new UserStatus(1,"Multiple Product Order added Successfully !");
		} catch (ConstraintViolationException cve) {
			logger.error(cve);
			cve.printStackTrace();
			return new UserStatus(0, cve.getCause().getMessage());
		} catch (PersistenceException pe) {
			logger.error(pe);
			pe.printStackTrace();
			return new UserStatus(0, pe.getCause().getMessage());
		} catch (Exception e) {
			logger.error(e);
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
			logger.error(e);
			e.printStackTrace();
		}
		return productorder;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateProductorder(
			@RequestBody ProductOrderDTO productOrderDTO,HttpServletRequest request,HttpServletResponse response) {
		try {
			Productorder productorder = ProductOrderRequestResponseFactory.setProductOrder(productOrderDTO);
			productorder.setStatus(statusService.getEntityById(Status.class,Long.parseLong(messageSource.getMessage(DreamConstructionConstants.STATUS_NEW_PRODUCT_ORDER, null, null))));
			productorderService.updateEntity(productorder);
			return new UserStatus(1, "Product Order update Successfully !");
		} catch (Exception e) {
			logger.error(e);
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
			logger.error(e);
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
			logger.error(e);
			return new UserStatus(0, e.toString());
		}
	}
	private Productorder saveProductOrder(ProductOrderDTO productOrderDTO,HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		Productorder productorder = ProductOrderRequestResponseFactory.setProductOrder(productOrderDTO);
		productorder.setStatus(statusService.getEntityById(Status.class,Long.parseLong(messageSource.getMessage(DreamConstructionConstants.STATUS_NEW_PRODUCT_ORDER, null, null))));
		productorderService.addEntity(productorder);
		return productorder;
	}
	
	private void addProductOrderAsso(ProductOrderDTO productOrderDTO,Productorder productorder,HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<ProductOrderAssociationDTO> ProductOrderAssociationDTOs = productOrderDTO.getProductOrderAssociationDTOs();
		List<ProductOrderPDFData> productOrderPDFDatas = new ArrayList<ProductOrderPDFData>();
		Contractor contractor = contractorService.getEntityById(Contractor.class, productOrderDTO.getContractorId().getId());
		productOrderDTO.setStatusId(productorder.getStatus().getId());
		if (ProductOrderAssociationDTOs != null&& !ProductOrderAssociationDTOs.isEmpty()) {
			for (ProductOrderAssociationDTO productOrderAssociationDTO : ProductOrderAssociationDTOs) {
				ProductOrderPDFData productOrderPDFData = new ProductOrderPDFData();
				productOrderAssociationDTO.setProductOrderId(productorder.getId());
				ProductDTO  productDTO = productService.getProductDTO(productOrderAssociationDTO.getProductId().getId());
				productOrderPDFData.setProductPartNumber(productDTO.getProductCode());
				productOrderPDFData.setHsnCode(productDTO.getHsnCode());
				productOrderPDFData.setQuantity(productOrderAssociationDTO.getQuantity());
				productOrderPDFDatas.add(productOrderPDFData);
				productorderassociationService.addEntity(ProductOrderAssoRequestResponseFactory.setProductPrderAsso(productOrderAssociationDTO, request));
			}
		}
		createPdfProductOrder(request, response, productOrderPDFDatas, productOrderDTO, contractor);
	}
	
	@RequestMapping(value = "/pendingList", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Productorder> getPendingsProductorders() {

		List<Productorder> productorderList = null;
		try {
			// TODO afterwards you need to change it from properties.
			productorderList = productorderService.getPendingProductOrders(Long.parseLong(messageSource.getMessage(DreamConstructionConstants.STATUS_NEW_PRODUCT_ORDER, null, null)),Long.parseLong(messageSource.getMessage(DreamConstructionConstants.STATUS_PRODUCT_ORDER_INCOMPLETE, null, null)));
		} catch (Exception e) {
			logger.error(e);
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
				productDTO.setProductCode(productorderassociation.getProduct().getProductCode());
				productOrderAssociationDTO.setProductId(productDTO);
				productOrderAssociationDTO.setInventoryQuantity(productinventory.getQuantityavailable());
				productOrderAssociationDTO.setQuantity(productorderassociation.getQuantity());
				productOrderAssociationDTO.setRemainingQuantity(productorderassociation.getRemainingQuantity());
				productOrderAssociationDTOs.add(productOrderAssociationDTO);
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return productOrderAssociationDTOs;
	}
	@Transactional @RequestMapping(value = "incompleteProductOrder/{CLIENT-ID}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Productorder> getInCompleteProductOrder(@PathVariable("CLIENT-ID") long clientId) {

		List<Productorder> productorderList = null;
		try {
			productorderList = productorderService.getInCompleteProductOrder(clientId,Long.parseLong(messageSource.getMessage(DreamConstructionConstants.STATUS_PRODUCT_ORDER_INCOMPLETE, null, null)),
					Long.parseLong(messageSource.getMessage(DreamConstructionConstants.STATUS_PRODUCT_ORDER_COMPLETE, null, null)));
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return productorderList;
	}
	
	public void createPdfProductOrder(HttpServletRequest request, HttpServletResponse response,List<ProductOrderPDFData> productOrderPDFDatas,ProductOrderDTO productOrderDTO,Contractor contractor) throws IOException {
		final ServletContext servletContext = request.getSession().getServletContext();
	    final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
	    final String temperotyFilePath = tempDirectory.getAbsolutePath();
	    String fileName = "ProductOrder.pdf";
	    response.setContentType("application/pdf");
	    response.setHeader("Content-disposition", "attachment; filename="+ fileName);
	    try {
	    	ProductOrderPdf createPDFProductOrder = new ProductOrderPdf();
	    	createPDFProductOrder.createPDF(temperotyFilePath+"\\"+fileName,productOrderPDFDatas,productOrderDTO,contractor);
	 
	       String productOrderPdfFile =    PDFToByteArrayOutputStreamUtil.convertPDFToByteArrayOutputStream(temperotyFilePath+"\\"+fileName);
	 	   StatusDTO status = statusService.getStatusById(productOrderDTO.getStatusId());
		   NotificationDTO notificationDTO = notificationService.getNotifiactionByStatus(status.getId());
		   emailNotificationProductOrder(notificationDTO, productOrderPDFDatas, contractor, productOrderPdfFile, productOrderDTO);
	    } catch (Exception e1) {
	    	logger.error(e1);
	        e1.printStackTrace();
	    }
	}
	private void emailNotificationProductOrder(NotificationDTO notification,List<ProductOrderPDFData> productOrderPDFDatas,Contractor contractor,String fileName,ProductOrderDTO productOrderDTO) throws Exception{
		 Mail mail = mailService.setMailCCBCCAndTO(notification);
	    String userEmailCC = mail.getMailCc()+","+contractor.getEmailId();
	    mail.setMailCc(userEmailCC);
	    mail.setAttachment(fileName);
       mail.setModel(MailResponseRequestFactory.setMailDetailsProductOrder(notification, productOrderPDFDatas, contractor, productOrderDTO));
       mailService.sendEmail(mail,notification);
	}
	
	private String generateInvoice() {
		String invoiceNumber = "";
		invoiceNumber = "000";
		return invoiceNumber;
	}
}
