package com.nextech.systeminventory.dto;

import java.util.List;

public class MultiplePurchaseOrderDTO {
	
	private List<PurchaseDTO> purchaseDTOs;

	public List<PurchaseDTO> getPurchaseDTOs() {
		return purchaseDTOs;
	}

	public void setPurchaseDTOs(List<PurchaseDTO> purchaseDTOs) {
		this.purchaseDTOs = purchaseDTOs;
	}

}
