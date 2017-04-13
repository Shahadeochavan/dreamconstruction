package com.nextech.erp.service;

import com.nextech.erp.model.Dispatch;


public interface DispatchService  extends CRUDService<Dispatch>{

	public Dispatch getDispatchByProductOrderIdAndProductId(long orderID,long productID) throws Exception;

}
