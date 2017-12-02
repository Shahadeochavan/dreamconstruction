package com.nextech.systeminventory.dto;



import java.util.List;


public class UserTypePageAssoDTO extends AbstractDTO{

	private PageDTO page;
	private UserTypeDTO usertypeId;
	
	private List<UserTypePageAssoPart> userTypePageAssoParts;
	
	public UserTypePageAssoDTO(){
		
	}
	public UserTypePageAssoDTO(int id){
		this.setId(id);
	}

	public List<UserTypePageAssoPart> getUserTypePageAssoParts() {
		return userTypePageAssoParts;
	}

	public void setUserTypePageAssoParts(
			List<UserTypePageAssoPart> userTypePageAssoParts) {
		this.userTypePageAssoParts = userTypePageAssoParts;
	}

	public PageDTO getPage() {
		return page;
	}

	public void setPage(PageDTO page) {
		this.page = page;
	}

	public UserTypeDTO getUsertypeId() {
		return usertypeId;
	}

	public void setUsertypeId(UserTypeDTO usertypeId) {
		this.usertypeId = usertypeId;
	}
	
}
