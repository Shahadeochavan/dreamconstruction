package com.nextech.systeminventory.factory;


import com.nextech.systeminventory.dto.VendorDTO;
import com.nextech.systeminventory.model.Vendor;

public class VendorFactory {
	
	public static Vendor setVendor(VendorDTO  vendorDTO){
		Vendor vendor = new Vendor();
		vendor.setId(vendorDTO.getId());
		vendor.setAddress(vendorDTO.getAddress());
		vendor.setCity(vendorDTO.getCity());
		vendor.setCommisionerate(vendorDTO.getCommisionerate());
		vendor.setCompanyName(vendorDTO.getCompanyName());
		vendor.setContactNumberMobile(vendorDTO.getContactNumberOffice());
		vendor.setContactNumberOffice(vendorDTO.getContactNumberOffice());
		vendor.setCst(vendorDTO.getCst());
		vendor.setCustomerEccNumber(vendorDTO.getCustomerEccNumber());
		vendor.setDescription(vendorDTO.getDescription());
		vendor.setDivison(vendorDTO.getDivison());
		vendor.setEmail(vendorDTO.getEmail());
		vendor.setFirstName(vendorDTO.getFirstName());
		vendor.setLastName(vendorDTO.getLastName());
		vendor.setPostalcode(vendorDTO.getPostalcode());
		vendor.setRenge(vendorDTO.getRenge());
		vendor.setState(vendorDTO.getState());
		vendor.setVatNo(vendorDTO.getVatNo());
		vendor.setIsactive(true);
		return vendor;
	}
	public static VendorDTO setVendorList(Vendor vendor){
		VendorDTO vendorDTO =  new VendorDTO();
		vendorDTO.setId(vendor.getId());
		vendorDTO.setAddress(vendor.getAddress());
		vendorDTO.setCity(vendor.getCity());
		vendorDTO.setCommisionerate(vendor.getCommisionerate());
		vendorDTO.setCompanyName(vendor.getCompanyName());
		vendorDTO.setContactNumberMobile(vendor.getContactNumberOffice());
		vendorDTO.setContactNumberOffice(vendor.getContactNumberOffice());
		vendorDTO.setCst(vendor.getCst());
		vendorDTO.setCustomerEccNumber(vendor.getCustomerEccNumber());
		vendorDTO.setDescription(vendor.getDescription());
		vendorDTO.setDivison(vendor.getDivison());
		vendorDTO.setEmail(vendor.getEmail());
		vendorDTO.setFirstName(vendor.getFirstName());
		vendorDTO.setLastName(vendor.getLastName());
		vendorDTO.setPostalcode(vendor.getPostalcode());
		vendorDTO.setRenge(vendor.getRenge());
		vendorDTO.setState(vendor.getState());
		vendorDTO.setVatNo(vendor.getVatNo());
		vendorDTO.setActive(true);
		return vendorDTO;
	}

}
