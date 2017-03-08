package com.nextech.erp.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.nextech.erp.constants.ERPConstants;
import com.nextech.erp.model.Page;
import com.nextech.erp.model.User;
import com.nextech.erp.service.PageService;
import com.nextech.erp.service.UserService;
import com.nextech.erp.service.UserTypeService;
import com.nextech.erp.service.UsertypepageassociationService;
public class AjaxLoginProcessingFilter implements Filter {

	@Autowired
	UserService userService;
	
	@Autowired
	TokenFactory tokenFactory;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	UserTypeService userTypeService;
	
	@Autowired
	UsertypepageassociationService usertypepageassociationService;

	@Autowired
	 PageService pageservice;
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			String url = ((HttpServletRequest) request).getRequestURL().toString();
			if (!url.contains("login")) {
				try {
					if(((HttpServletRequest) request).getHeader("auth_token") != null){
						String token = TokenFactory.decrypt(((HttpServletRequest) request).getHeader("auth_token"), TokenFactory.getSecretKeySpec());
						String[] string = token.split("-");
						User user = userService.getUserByUserId(string[0]);
						Page page = pageservice.getPageByUrl(url); 
						if(user != null && user.getPassword().equals(string[1]) /*&& usertypepageassociationService.checkPageAccess(userTypeService.getEntityById(Usertype.class, user.getUsertype().getId()).getId(), page.getId())*/){
							String str = string[string.length - 1];
							Long time = new Long(messageSource.getMessage(ERPConstants.SESSIONTIMEOUT,null, null));
							if (new Date().getTime() - time * 1000 < new Long(str)) {
								String generatedToken = TokenFactory.createAccessJwtToken(user);
								System.out.println(generatedToken);
								((HttpServletResponse) response).addHeader("auth_token", generatedToken);
								request.setAttribute("auth_token", true);
								chain.doFilter(request, response);
							} else {
								HttpServletResponse HTTPresponse = setResponse(request, response);
								HTTPresponse.sendError(HttpServletResponse.SC_FORBIDDEN);
							}
						}
						else{
							HttpServletResponse HTTPresponse = setResponse(request, response);
							HTTPresponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
						}
					}else{
						System.out.println("Header Access-Control-Request-Headers : " + ((HttpServletRequest) request).getHeader("Access-Control-Request-Headers"));
						if(((HttpServletRequest) request).getHeader("Access-Control-Request-Headers")==null){
						HttpServletResponse HTTPresponse = setResponse(request, response);
						HTTPresponse.sendError(HttpServletResponse.SC_FORBIDDEN);
						}else{
							request.setAttribute("auth_token", true);
							chain.doFilter(request, response);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				request.setAttribute("auth_token", true);
				request.setAttribute("current_token", true);
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		userService = (UserService)WebApplicationContextUtils. getRequiredWebApplicationContext(cfg.getServletContext()).getBean("userservice");
		messageSource = (MessageSource)WebApplicationContextUtils. getRequiredWebApplicationContext(cfg.getServletContext()).getBean("messageSource");
		userTypeService = (UserTypeService)WebApplicationContextUtils. getRequiredWebApplicationContext(cfg.getServletContext()).getBean("userTypeService");
		usertypepageassociationService = (UsertypepageassociationService)WebApplicationContextUtils. getRequiredWebApplicationContext(cfg.getServletContext()).getBean("usertypepageassociationService");
		pageservice = (PageService)WebApplicationContextUtils. getRequiredWebApplicationContext(cfg.getServletContext()).getBean("pageservice");
	}
	
	
	private HttpServletResponse setResponse(ServletRequest request,ServletResponse response){
		HttpServletResponse HTTPresponse = (HttpServletResponse) response;
		HTTPresponse.reset();
		HTTPresponse.setHeader("Content-Type", "application/json;charset=UTF-8");
		HTTPresponse.setHeader("Access-Control-Allow-Origin", ((HttpServletRequest) request).getHeader("Origin"));
		HTTPresponse.setHeader("Access-Control-Allow-Credentials", "true");
		HTTPresponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		HTTPresponse.setHeader("Access-Control-Max-Age", "3600");
		HTTPresponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, auth_token, Origin");
		HTTPresponse.setHeader("Access-Control-Expose-Headers", "auth_token, Origin");
		return HTTPresponse;
	}
}
