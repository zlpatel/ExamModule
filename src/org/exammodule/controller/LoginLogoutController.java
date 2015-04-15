package org.exammodule.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.exammodule.service.SecurityContextAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/auth")
public class LoginLogoutController {
        
	protected static Logger logger = Logger.getLogger("controller");

	@Autowired
	SecurityContextAccessor securityContextAccessor;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value="error", required=false) boolean error, 
			ModelMap model,HttpServletRequest request,HttpSession session) {
		logger.debug("Received request to show login page");
		if (securityContextAccessor.isCurrentAuthenticationAnonymous()) {
			if (error == true) {
				model.put("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
			} else {
				model.put("error", "");
			}
		    return "loginpage";
		 } else { 
			 System.out.println(session.getAttribute("USERNAME"));
		    return securityContextAccessor.determineDefaultTargetUrl();
		 }
		
	}
	
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
 	public String getDeniedPage() {
		logger.debug("Received request to show denied page");
		
		return "deniedpage";
	}
	
	private String getErrorMessage(HttpServletRequest request, String key){
		 
		Exception exception = 
                   (Exception) request.getSession().getAttribute(key);
 
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		}else if(exception instanceof LockedException) {
			error = exception.getMessage();
		}else{
			error = "Invalid username and password!";
		}
		return error;
	}
}