package com.nextech.systeminventory.factory;
import com.nextech.systeminventory.dto.ProductOrderDTO;
import com.nextech.systeminventory.model.Client;
import com.nextech.systeminventory.model.Product;
import com.nextech.systeminventory.model.Productorder;
import com.nextech.systeminventory.model.Productorderassociation;


public class ProductOrderRequestResponseFactory {
	
	public static Productorder setProductOrder(ProductOrderDTO productOrderDTO){
		Productorder productorder = new Productorder();
		Client client = new Client();
		client.setId(productOrderDTO.getClientId());
		productorder.setClient(client);
		productorder.setCreateDate(productOrderDTO.getCreateDate());
		productorder.setDescription(productOrderDTO.getDescription());
		productorder.setExpecteddeliveryDate(productOrderDTO.getExpecteddeliveryDate());
		productorder.setId(productOrderDTO.getId());
		productorder.setPoNO(productOrderDTO.getPoNO());
		productorder.setQuantity(productOrderDTO.getProductOrderAssociationDTOs().size());
		productorder.setTotalPrice(productOrderDTO.getTotalPrice());
		productorder.setTax(productOrderDTO.getTax());
		productorder.setActualPrice(productOrderDTO.getActualPrice());
		productorder.setIsactive(true);
		return productorder;
	}
	public static Productorderassociation setProductOrderAsso(ProductOrderDTO productOrderDTO,Productorderassociation productOrderAssociationDTO){
		Productorderassociation productorderassociation = new Productorderassociation();
		Productorder productorder = new Productorder();
		productorder.setId(productOrderDTO.getId());
		productorderassociation.setQuantity(productOrderAssociationDTO.getQuantity());
		productorderassociation.setRemainingQuantity(productOrderAssociationDTO.getQuantity());
		Product product =  new Product();
		product.setId(productOrderAssociationDTO.getProduct().getId());
		productorderassociation.setProduct(product);
		productorderassociation.setProductorder(productorder);
		productorderassociation.setIsactive(true);
		return productorderassociation;
	}
	public static ProductOrderDTO setProductOrderDTO(Productorder productorder){
		ProductOrderDTO productOrderDTO =  new ProductOrderDTO();
		productOrderDTO.setActive(true);
		productOrderDTO.setId(productorder.getId());
		productOrderDTO.setClientId(productorder.getClient().getId());
		productOrderDTO.setCreatedDate(productorder.getCreatedDate());
		productOrderDTO.setDescription(productorder.getDescription());
		productOrderDTO.setExpecteddeliveryDate(productorder.getExpecteddeliveryDate());
		productOrderDTO.setInvoiceNo(productorder.getInvoiceNo());
		productOrderDTO.setCreateDate(productorder.getCreateDate());
		productOrderDTO.setTotalPrice(productorder.getTotalPrice());
		productOrderDTO.setActualPrice(productorder.getActualPrice());
		productOrderDTO.setTax(productorder.getActualPrice());
		productOrderDTO.setPoNO(productorder.getPoNO());
		return productOrderDTO;
	}
}
