package com.nextech.dreamConstruction.dto;


public class UserTypePageAssoPart extends AbstractDTO{
	private PageDTO pageId;
	
	public UserTypePageAssoPart(){
		
	}
	public UserTypePageAssoPart(int id){
		this.setId(id);
	}

	public PageDTO getPageId() {
		return pageId;
	}

	public void setPageId(PageDTO pageId) {
		this.pageId = pageId;
	}

}
