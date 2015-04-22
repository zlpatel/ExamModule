package org.exammodule.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.exammodule.form.AdditionalQuestionsRecordFormBean;
import org.exammodule.form.QuestionFormBean;
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
	public String getAdminPage() {
		logger.debug("Received request to show admin page");
		return "adminpage";
	}
	
	@RequestMapping(value = "/resetAcccount", method = RequestMethod.GET)
	public String resetStudentAccount() {
		logger.debug("Received request to show reset account page");
		return "resetaccountpage";
	}
	
	@RequestMapping(value = "/resetFormSubmit", method = RequestMethod.POST)
	public ModelAndView resetFormSubmitted(@ModelAttribute("command")ResetFormBean reset,
			HttpSession session) {
		logger.debug("Received request for submitting reset form");
		ModelAndView mav=new ModelAndView();
		try {
			adminService.resetUserAccount(reset.getUserName(), reset.getFullName());
		} catch (Exception e) {
			ModelAndView model=new ModelAndView("adminerr");
			model.addObject("message", "Something went wrong, please try again later!");
			return model;
		}
		mav.addObject("message", "Account successfully reset for "+reset.getFullName());
		mav.setViewName("resetaccountpage");
		return mav;
	}
	
	@RequestMapping(value = "/studentsRecord", method = RequestMethod.GET)
	public ModelAndView getStudentsRecord() {
		logger.debug("Received request to all student record");
		List<StudentsRecordFormBean> recordList =null;
		try {
			recordList = adminService.getStudentsRecord();
		} catch (Exception e) {
			ModelAndView model=new ModelAndView("adminerr");
			model.addObject("message", "Something went wrong, please try again later!");
			return model;
		} 
		ModelAndView mav=new ModelAndView();
		mav.setViewName("studentsrecord");
		mav.addObject("recordList",recordList);
		return mav;
	}
	
	@RequestMapping(value = "/regularQuestionsRecords/{userName}", method = RequestMethod.POST)
	public ModelAndView getSingleStudentRegularQuestionsDetails(@PathVariable String userName) {
		logger.debug("Received request to show single student record for Regular Questions");
		ModelAndView mav=new ModelAndView();
		String studentName=null;
		List<RegularQuestionsRecordFormBean> recordList=null;
		try {
			studentName = adminService.getStudentName(userName);
			recordList=adminService.getRegularQuestionsRecord(userName);
		} catch (Exception e) {
			ModelAndView model=new ModelAndView("adminerr");
			model.addObject("message", "Something went wrong, please try again later!");
			return model;
		}
		mav.addObject("regularQuestionsRecordList", recordList);
		mav.addObject("studentName", studentName);
		mav.setViewName("regularquestionsrecord");
		return mav;
	}
	@RequestMapping(value = "/additionalQuestionsRecords/{userName}", method = RequestMethod.POST)
	public ModelAndView getSingleStudentAdditionalQuestionsDetails(@PathVariable String userName) {
		logger.debug("Received request to show single student record for Additional Questions");
		ModelAndView mav=new ModelAndView();
		String studentName=null;
		List<AdditionalQuestionsRecordFormBean> recordList=null;
		try {
			studentName = adminService.getStudentName(userName);
			recordList=adminService.getAdditionalQuestionsRecord(userName);
		} catch (Exception e) {
			ModelAndView model=new ModelAndView("adminerr");
			model.addObject("message", "Something went wrong, please try again later!");
			return model;
		}
		mav.addObject("additionalQuestionsRecordList", recordList);
		mav.addObject("studentName", studentName);
		mav.setViewName("additionalquestionsrecord");
		return mav;
	}
}
