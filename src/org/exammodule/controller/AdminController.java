package org.exammodule.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.exammodule.exception.StudentNotFoundException;
import org.exammodule.form.AdditionalQuestionsRecordFormBean;
import org.exammodule.form.RegularQuestionsRecordFormBean;
import org.exammodule.form.ResetFormBean;
import org.exammodule.form.StudentsRecordFormBean;
import org.exammodule.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin")
public class AdminController 
{
	@Autowired
	private AdminService adminService;
	
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getAdminPage(HttpSession session) {
		logger.debug("Received request to show admin page");
		
		ModelAndView model=new ModelAndView("adminpage");
		try {
			String fullName=adminService.getAdminName((String)session.getAttribute("USERNAME"));
			session.setAttribute("name", fullName);
			return model;
		} catch (Exception e) {
			model=new ModelAndView("err");
			session.invalidate();
			return model;
		}
	}
	
	@RequestMapping(value = "/resetAccount", method = RequestMethod.GET)
	public String resetStudentAccount() {
		logger.debug("Received request to show reset account page");
		return "resetaccountpage";
	}
	
	@RequestMapping(value = "/resetFormSubmit", method = RequestMethod.POST)
	public ModelAndView resetFormSubmitted(@ModelAttribute("command")ResetFormBean reset,
			HttpSession session) {
		logger.debug("Received request for submitting reset form");
		try {
			adminService.resetUserAccount(reset.getUserName(), reset.getFullName());
		} catch (StudentNotFoundException e) {
			ModelAndView model=new ModelAndView("resetformerr");
			model.addObject("message", e.getMessage());
			return model;
		}catch (Exception e) {
			logger.debug("DB exception during reset request.");
			ModelAndView model=new ModelAndView("err");
			session.invalidate();
			return model;
		}
		ModelAndView mav=new ModelAndView();
		mav.addObject("message", "Account successfully reset for "+reset.getFullName()+".");
		reset.setFullName("");
		reset.setUserName("");
		mav.setViewName("resetaccountpage");
		return mav;
	}
	
	@RequestMapping(value = "/studentsRecord", method = RequestMethod.GET)
	public ModelAndView getStudentsRecord(HttpSession session) {
		logger.debug("Received request to all student record");
		List<StudentsRecordFormBean> recordList =null;
		try {
			recordList = adminService.getStudentsRecord();
		} catch (Exception e) {
			ModelAndView model=new ModelAndView("err");
			session.invalidate();
			return model;
		} 
		ModelAndView mav=new ModelAndView();
		mav.setViewName("studentsrecord");
		mav.addObject("recordList",recordList);
		return mav;
	}
	
	@RequestMapping(value = "/regularQuestionsRecords/{userName}", method = RequestMethod.POST)
	public ModelAndView getSingleStudentRegularQuestionsDetails(@PathVariable String userName, HttpSession session) {
		logger.debug("Received request to show single student record for Regular Questions");
		ModelAndView mav=new ModelAndView();
		String studentName=null;
		List<RegularQuestionsRecordFormBean> recordList=null;
		try {
			studentName = adminService.getStudentName(userName);
			recordList=adminService.getRegularQuestionsRecord(userName);
		} catch (StudentNotFoundException e) {
			ModelAndView model=new ModelAndView("adminerr");
			model.addObject("message", e.getMessage());
			return model;
		}catch (Exception e) {
			ModelAndView model=new ModelAndView("err");
			session.invalidate();
			return model;
		}
		mav.addObject("regularQuestionsRecordList", recordList);
		mav.addObject("studentName", studentName);
		mav.setViewName("regularquestionsrecord");
		return mav;
	}
	@RequestMapping(value = "/additionalQuestionsRecords/{userName}", method = RequestMethod.POST)
	public ModelAndView getSingleStudentAdditionalQuestionsDetails(@PathVariable String userName, HttpSession session) {
		logger.debug("Received request to show single student record for Additional Questions");
		ModelAndView mav=new ModelAndView();
		String studentName=null;
		List<AdditionalQuestionsRecordFormBean> recordList=null;
		try {
			studentName = adminService.getStudentName(userName);
			recordList=adminService.getAdditionalQuestionsRecord(userName);
		} catch (StudentNotFoundException e) {
			ModelAndView model=new ModelAndView("adminerr");
			model.addObject("message", e.getMessage());
			return model;
		}catch (Exception e) {
			ModelAndView model=new ModelAndView("err");
			session.invalidate();
			return model;
		}
		mav.addObject("additionalQuestionsRecordList", recordList);
		mav.addObject("studentName", studentName);
		mav.setViewName("additionalquestionsrecord");
		return mav;
	}
}