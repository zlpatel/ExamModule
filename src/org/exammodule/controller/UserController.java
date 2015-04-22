package org.exammodule.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.exammodule.handler.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController 
{

	protected static Logger logger = Logger.getLogger("controller");
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getStudentPage(HttpSession session) {
		logger.debug("Received request to show user home page");
		return "userpage";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String startTest(HttpSession session) {
		logger.debug("Received request to show test page");
		
		long testStartTime=new Date().getTime()/1000;
		
		if (session != null) {
            session.setAttribute("TEST_START_TIME", testStartTime);
            session.setAttribute("TIME", Constants.TEST_TIME);
        } 
		return "redirect:../user/question";
	}
}

