package org.exammodule.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.exammodule.handler.Constants;
import org.exammodule.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	protected static Logger logger = Logger.getLogger("controller");

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getStudentPage(HttpSession session) {
		logger.debug("Received request to show user home page");
		ModelAndView mav = new ModelAndView();
		String fullName = null;
		try {
			fullName = customUserDetailsService
					.getUserFullName((String) session.getAttribute("USERNAME"));
		} catch (Exception e) {
			mav.setViewName("err");
			session.invalidate();
			return mav;
		}
		mav.setViewName("userpage");

		if(session.getAttribute("ACCOUNT_BLOCKED")!=null && (boolean)session.getAttribute("ACCOUNT_BLOCKED")){
			long testCurrentTime=new Date().getTime()/1000;
			long differenceTime=testCurrentTime-(long)session.getAttribute("TEST_START_TIME");
			long remainingTime=Constants.TEST_TIME-differenceTime;
			
			if (session != null) {
				session.setAttribute("TIME", remainingTime);
			}
		}
		if (session != null) {
			session.setAttribute("name", fullName);
		}
		
		return mav;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String startTest(HttpSession session, Model model) {
		logger.debug("Received request to show test page");

		long testStartTime = new Date().getTime() / 1000;

		if (session != null) {
			session.setAttribute("TEST_START_TIME", testStartTime);
			session.setAttribute("TIME", Constants.TEST_TIME);
			try {
				customUserDetailsService.blockUserAccount((String) session
						.getAttribute("USERNAME"));
				session.setAttribute("ACCOUNT_BLOCKED", true);
			} catch (Exception e) {
				session.invalidate();
				return "err";
			}
		}
		return "redirect:../user/question";
	}
}