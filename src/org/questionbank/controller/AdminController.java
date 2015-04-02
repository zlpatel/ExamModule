package org.questionbank.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.questionbank.form.StudentsRecordFormBean;
import org.questionbank.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	@RequestMapping(value = "/studentsRecord", method = RequestMethod.GET)
	public ModelAndView getStudentsRecord() {
		logger.debug("Received request to all student record");
		List<StudentsRecordFormBean> recordList= adminService.getStudentsRecord(); 
		ModelAndView mav=new ModelAndView();
		mav.setViewName("studentsrecord");
		mav.addObject("recordList",recordList);
		return mav;
	}
	@RequestMapping(value = "/categoricalRecords/{user}", method = RequestMethod.POST)
	public ModelAndView getSingleStudentCategoricalDetails(@PathVariable String user) {
		logger.debug("Received request to single student record based on category");
		//TODO: 
		ModelAndView mav=new ModelAndView();
		return mav;
	}
	@RequestMapping(value = "/regularQuestionsRecords/{user}", method = RequestMethod.POST)
	public ModelAndView getSingleStudentRegularQuestionsDetails(@PathVariable String user) {
		logger.debug("Received request to single student record for Regular Questions");
		//TODO:
		ModelAndView mav=new ModelAndView();
		return mav;
	}
	@RequestMapping(value = "/additionalQuestionsRecords/{user}", method = RequestMethod.POST)
	public ModelAndView getSingleStudentAdditionalQuestionsDetails(@PathVariable String user) {
		logger.debug("Received request to single student record for Additional Questions");
		//TODO:
		ModelAndView mav=new ModelAndView();
		return mav;
	}
}
