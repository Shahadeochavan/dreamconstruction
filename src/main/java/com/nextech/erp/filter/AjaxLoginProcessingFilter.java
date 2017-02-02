package com.nextech.erp.filter;

import java.io.IOException;
import java.util.Date;

import javax.naming.AuthenticationException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import com.nextech.erp.service.UserService;
public class AjaxLoginProcessingFilter implements Filter {

	@Autowired
	UserService userService;

	@Autowired
	TokenFactory tokenFactory;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			String url = ((HttpServletRequest) request).getRequestURL()
					.toString();
			if (!url.contains("login")) {
				try {
					if(((HttpServletRequest) request).getHeader("auth_token") != null){
						String token = TokenFactory.decrypt(((HttpServletRequest) request).getHeader("auth_token"), new TokenFactory().getSecretKeySpec());
						String[] string = token.split("-");
						String str = string[string.length - 1];
						if (new Date().getTime() - 1 * 60 * 1000 < new Long(str)) {
							request.setAttribute("user-auth", true);
							chain.doFilter(request, response);
						} else {
	
							throw new AuthenticationException();
						}
					}else{
						request.setAttribute("user-auth", false);
						chain.doFilter(request, response);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				request.setAttribute("user-auth", true);
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
