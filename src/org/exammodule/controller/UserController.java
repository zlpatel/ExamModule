package org.exammodule.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController 
{

	protected static Logger logger = Logger.getLogger("controller");
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getStudentPage() {
		logger.debug("Received request to show user home page");

		return "userpage";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String startTest() {
		logger.debug("Received request to show test page");

		return "questionpage";
	}
}

