package com.nextech.systeminventory.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import javax.persistence.PersistenceException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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
import com.nextech.systeminventory.dto.MultiplePurchaseOrderDTO;
import com.nextech.systeminventory.dto.NotificationDTO;
import com.nextech.systeminventory.dto.ProductDTO;
import com.nextech.systeminventory.dto.ProductOrderDTO;
import com.nextech.systeminventory.dto.PurchaseAssnDTO;
import com.nextech.systeminventory.dto.PurchaseDTO;
import com.nextech.systeminventory.dto.PurchaseOrderPdfData;
import com.nextech.systeminventory.dto.StatusDTO;
import com.nextech.systeminventory.dto.VendorDTO;
import com.nextech.systeminventory.factory.MailResponseRequestFactory;
import com.nextech.systeminventory.factory.PurchaseRequestResponseFactory;
import com.nextech.systeminventory.factory.PurhcaseAssnRequestResponseFactory;
import com.nextech.systeminventory.model.Mail;
import com.nextech.systeminventory.model.PrVndrAssn;
import com.nextech.systeminventory.model.Purchase;
import com.nextech.systeminventory.model.PurchaseAssn;
import com.nextech.systeminventory.model.Status;
import com.nextech.systeminventory.model.Vendor;
import com.nextech.systeminventory.pdfClass.PurchaseOrderPdf;
import com.nextech.systeminventory.service.MailService;
import com.nextech.systeminventory.service.NotificationService;
import com.nextech.systeminventory.service.PrVndrAssnService;
import com.nextech.systeminventory.service.ProductService;
import com.nextech.systeminventory.service.ProductinventoryService;
import com.nextech.systeminventory.service.PurchaseAssnService;
import com.nextech.systeminventory.service.PurchaseService;
import com.nextech.systeminventory.service.StatusService;
import com.nextech.systeminventory.service.VendorService;
import com.nextech.systeminventory.status.UserStatus;
import com.nextech.systeminventory.util.PDFToByteArrayOutputStreamUtil;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;
	
	@Autowired
	PurchaseAssnService purchaseAssnService;
	
	@Autowired
	ProductinventoryService productinventoryService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	VendorService vendorService;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	PrVndrAssnService PrVndrAssnService;
	
	@Autowired
	static Logger logger = Logger.getLogger(PurchaseController.class);

	@RequestMapping(value = "/createMultiple", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addPurchase(@Valid @RequestBody PurchaseDTO purchaseDTO,
			BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError()
						.getDefaultMessage());
			}
		long id =	purchaseService.addEntity(PurchaseRequestResponseFactory.setPurchase(purchaseDTO));
		purchaseDTO.setId(id);
		Purchase purchase =  purchaseService.getEntityById(Purchase.class, id);
		String poNumber = generatePoId()+purchase.getId();
		purchase.setName(poNumber);;
		purchaseService.updateEntity(purchase);
		addPurchaseOrderAsso(purchaseDTO, request, response);
			return new UserStatus(1, "Purchase added Successfully !");
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
	public @ResponseBody Purchase getPurchase(@PathVariable("id") long id) {
		Purchase Purchase = null;
		try {
			Purchase = purchaseService.getEntityById(Purchase.class, id);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return Purchase;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody UserStatus updateUserType(
			@RequestBody PurchaseDTO purchaseDTO,HttpServletRequest request,HttpServletResponse response) {
		try {
			purchaseService.updateEntity(PurchaseRequestResponseFactory.setPurchase(purchaseDTO));
			return new UserStatus(1, "Purchase update Successfully !");
		} catch (Exception e) {
			 e.printStackTrace();
			return new UserStatus(0, e.toString());
		}
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Purchase> getPurchase() {

		List<Purchase> Purchases = null;
		try {
			Purchases = purchaseService.getEntityList(Purchase.class);

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

		return Purchases;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public @ResponseBody UserStatus deletePurchase(@PathVariable("id") long id) {

		try {
			Purchase Purchase = purchaseService.getEntityById(Purchase.class,id);
			Purchase.setIsactive(false);
			purchaseService.updateEntity(Purchase);
			return new UserStatus(1, "Purchase deleted Successfully !");
		} catch (Exception e) {
			logger.error(e);
			return new UserStatus(0, e.toString());
		}

	}
	@RequestMapping(value = "purchaseId/{orderId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<PurchaseAssnDTO> getProductOrder(
			@PathVariable("orderId") long id) {
		List<PurchaseAssn> purchaseAssns = null;
		List<PurchaseAssnDTO> purchaseAssnDTOs = new ArrayList<PurchaseAssnDTO>();
		try {
			purchaseAssns = purchaseAssnService.getPurchaseAssnByPurchaseId(id);
			if(!purchaseAssns.isEmpty()){
			for (PurchaseAssn purchaseAssn : purchaseAssns) {
				PurchaseAssnDTO  purchaseAssnDTO =  new PurchaseAssnDTO();
				//Productinventory productinventory = productinventoryService.getProductinventoryByProductId(purchaseAssn.getProduct().getId());
				ProductDTO productDTO =  new ProductDTO();
				productDTO.setId(purchaseAssn.getProduct().getId());
				productDTO.setProductCode(purchaseAssn.getProduct().getProductCode());
				purchaseAssnDTO.setProductId(productDTO);
				purchaseAssnDTO.setQuantity(purchaseAssn.getQuantity());
				purchaseAssnDTO.setRemainingQuantity(purchaseAssn.getRemainingQuantity());
				purchaseAssnDTOs.add(purchaseAssnDTO);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return purchaseAssnDTOs;
	}
	
	@RequestMapping(value = "/pendingList", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Purchase> getPendingsProductorders() {

		List<Purchase> purchases = null;
		try {
			purchases = purchaseService.getPendingPurchaseOrders(Long.parseLong(messageSource.getMessage(ERPConstants.PURCHASE_NEW_PRODUCT, null, null)),Long.parseLong(messageSource.getMessage(ERPConstants.PURCHASE_ORDER_INCOMPLETE, null, null)));
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return purchases;
	}
	
	private void addPurchaseOrderAsso(PurchaseDTO purchaseDTO,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<PurchaseAssnDTO> purchaseAssnDTOs = purchaseDTO.getPurchaseAssnDTOs();
		List<PurchaseOrderPdfData> productOrderPDFDatas = new ArrayList<PurchaseOrderPdfData>();
		VendorDTO vendorDTO = vendorService.getVendorById(purchaseDTO.getVendorId());
	//	List<PrVndrAssn> prVndrAssns  = PrVndrAssnService.getPrVndrAssnByVendorId(vendorDTO.getId());
		if (purchaseAssnDTOs != null&& !purchaseAssnDTOs.isEmpty()) {
			for (PurchaseAssnDTO purchaseAssnDTO : purchaseAssnDTOs) {
						PurchaseOrderPdfData purchaseOrderPdfData = new PurchaseOrderPdfData();
						purchaseAssnDTO.setPurchaseId(purchaseDTO.getId());
						ProductDTO  productDTO = productService.getProductDTO(purchaseAssnDTO.getProductId().getId());
						purchaseOrderPdfData.setProductPartNumber(productDTO.getProductCode());
						purchaseOrderPdfData.setPricePerUnit(purchaseAssnDTO.getPricePerUnit());
						purchaseOrderPdfData.setQuantity(purchaseAssnDTO.getQuantity());
						productOrderPDFDatas.add(purchaseOrderPdfData);
						purchaseAssnService.addEntity(PurhcaseAssnRequestResponseFactory.setPurchaseAssn(purchaseAssnDTO));
			
			}
		}
		createPurchaseOrderPdf(request, response, purchaseDTO, productOrderPDFDatas, vendorDTO);
	}
	
	public void createPurchaseOrderPdf(HttpServletRequest request, HttpServletResponse response,PurchaseDTO purchaseDTO ,List<PurchaseOrderPdfData> productOrderPDFDatas,VendorDTO vendor) throws IOException {
		final ServletContext servletContext = request.getSession().getServletContext();
	    final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
	    final String temperotyFilePath = tempDirectory.getAbsolutePath();
	    String fileName = "PurchaseOrder.pdf";
	    response.setContentType("application/pdf");
	    response.setHeader("Content-disposition", "attachment; filename="+ fileName);
	    try {
	    	PurchaseOrderPdf purchaseOrderPdf = new PurchaseOrderPdf();
	    	purchaseOrderPdf.createPDF(temperotyFilePath+"\\"+fileName,purchaseDTO,productOrderPDFDatas,vendor);
	        String rmOrderPdffile =    PDFToByteArrayOutputStreamUtil.convertPDFToByteArrayOutputStream(temperotyFilePath+"\\"+fileName);
	        Purchase purchase  = purchaseService.getEntityById(Purchase.class, purchaseDTO.getId());
			StatusDTO status = statusService.getStatusById(purchase.getStatus().getId());
			NotificationDTO notification = notificationService.getNotifiactionByStatus(status.getId());
			emailNotificationPurchaseOrder(notification, purchaseDTO, vendor, rmOrderPdffile, productOrderPDFDatas);
	    } catch (Exception e1) {
	    	logger.error(e1);
	        e1.printStackTrace();
	    }
	}
	
	private void emailNotificationPurchaseOrder(NotificationDTO notification,PurchaseDTO purchaseDTO,VendorDTO vendor,String fileName,List<PurchaseOrderPdfData> productOrderPDFDatas) throws Exception{
		Mail mail = mailService.setMailCCBCCAndTO(notification);
	    String userEmailTO = mail.getMailTo()+","+vendor.getEmail();
	    mail.setMailTo(userEmailTO);
		mail.setMailSubject(notification.getSubject());
		mail.setAttachment(fileName);
		mail.setModel(MailResponseRequestFactory.setMailDetailsPurchaseOrder(notification, productOrderPDFDatas, vendor,purchaseDTO));
		mailService.sendEmail(mail,notification);
	}
	
	
	private String generatePoId(){
		String year="";
		Date currentDate = new Date();
		if(currentDate.getMonth()+1 > 3){
			int str = currentDate.getYear()+1900;
			int stri = str + 1;
			String strDate = stri+"";
			year = str+"/"+strDate.substring(2);
		}else{
			int str = currentDate.getYear()+1899;
			int stri = str + 1;
			String strDate = stri+"";
			year = str+"/"+strDate.substring(2);
		}
		year = "AS/PO/"+year+"/";
		return year;
	}
	
	@Transactional @RequestMapping(value = "/createMultiplePurchase", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody UserStatus addMultipleRMOrder(
			@Valid  @RequestBody MultiplePurchaseOrderDTO multiplePurchaseOrderDTO, BindingResult bindingResult,HttpServletRequest request,HttpServletResponse response) {
		try {
			if (bindingResult.hasErrors()) {
				return new UserStatus(0, bindingResult.getFieldError().getDefaultMessage());
			}
			//TODO save call raw material order
			List<PurchaseDTO> purchaseDTOs =  new ArrayList<PurchaseDTO>();
			HashMap<Long, List<PurchaseAssnDTO>> multipleOrder = new HashMap<Long, List<PurchaseAssnDTO>>();
			PurchaseDTO purchaseDTO = new PurchaseDTO();
			for(PurchaseDTO purDto : multiplePurchaseOrderDTO.getPurchaseDTOs()){
				purchaseDTO.setExpectedDeliveryDate(purDto.getExpectedDeliveryDate());
				purchaseDTO.setDescription(purDto.getDescription());
				List<PurchaseAssnDTO> purchaseAssnDTOs = null;
				if(multipleOrder.get(purDto.getVendorId()) == null){
					purchaseAssnDTOs = new ArrayList<PurchaseAssnDTO>();
				}else{
					purchaseAssnDTOs = multipleOrder.get(purDto.getVendorId());
				}
				
				for (PurchaseAssnDTO rmOrderAssociationDTO1 : purDto.getPurchaseAssnDTOs()) {
					PurchaseAssnDTO purchaseAssnDTO = new PurchaseAssnDTO();
					purchaseAssnDTO.setQuantity(rmOrderAssociationDTO1.getQuantity());
					purchaseAssnDTO.setProductId(rmOrderAssociationDTO1.getProductId());
					purchaseAssnDTO.setPricePerUnit(rmOrderAssociationDTO1.getPricePerUnit());
					purchaseAssnDTO.setExpectedDeliveryDate(purDto.getExpectedDeliveryDate());
					purchaseAssnDTOs.add(purchaseAssnDTO);
					multipleOrder.put(purDto.getVendorId(), purchaseAssnDTOs);
				}
				
			}
			Set<Entry<Long, List<PurchaseAssnDTO>>> multpleRMAssoEntries =  multipleOrder.entrySet();
			for(Entry<Long, List<PurchaseAssnDTO>> multpleRMAssoEntry : multpleRMAssoEntries){
				PurchaseDTO purchaseDTO2 = new PurchaseDTO();
				purchaseDTO2.setPurchaseAssnDTOs(multpleRMAssoEntry.getValue());
				Vendor vendor =  new Vendor();
				vendor.setId(multpleRMAssoEntry.getKey());
				purchaseDTO2.setVendorId(vendor.getId());
				for(PurchaseAssnDTO purchaseAssnDTO:purchaseDTO2.getPurchaseAssnDTOs()){
					purchaseDTO2.setExpectedDeliveryDate(purchaseAssnDTO.getExpectedDeliveryDate());	
				}
				purchaseDTOs.add(purchaseDTO2);
			}
			for (PurchaseDTO purchaseDTO2 : purchaseDTOs) {
			long id=	  purchaseService.addEntity(PurchaseRequestResponseFactory.setPurchase(purchaseDTO2));
			      purchaseDTO2.setId(id);
					Purchase purchase =  purchaseService.getEntityById(Purchase.class, id);
					String poNumber = generatePoId()+purchase.getId();
					purchase.setName(poNumber);;
					purchaseService.updateEntity(purchase);
				  addPurchaseOrderAsso(purchaseDTO2, request, response);
			}
			return new UserStatus(1, "Multiple Purchase  Order added Successfully !");
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
}
