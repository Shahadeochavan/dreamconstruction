package com.nextech.erp.dao;

import com.nextech.erp.model.Dispatch;

public interface DispatchDao extends SuperDao<Dispatch>{

	public Dispatch getDispatchByProductOrderIdAndProductId(long orderID,long productID) throws Exception;

}
