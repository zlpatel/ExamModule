package org.exammodule.serviceImpl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.exammodule.dao.QuestionDAO;
import org.exammodule.dao.UserDAO;
import org.exammodule.dto.AttemptsDTO;
import org.exammodule.dto.UserDTO;
import org.exammodule.exception.StudentNotFoundException;
import org.exammodule.form.AddStudentFormBean;
import org.exammodule.form.AdditionalQuestionsRecordFormBean;
import org.exammodule.form.RegularQuestionsRecordFormBean;
import org.exammodule.form.StudentsRecordFormBean;
import org.exammodule.handler.HashCode;
import org.exammodule.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

	protected static Logger logger = Logger.getLogger("admin service");
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private QuestionDAO questionDAO;
	
	@Transactional
	@Override
	public boolean addStudent(AddStudentFormBean student) throws Exception{
		UserDTO studentDTO=new UserDTO();
		studentDTO.setName(student.getFullName());
		studentDTO.setUserName(student.getUserName());
		studentDTO.setAccountNotBlocked(true);
		studentDTO.setPassword(HashCode.getHashPassword(student.getPassWord()));
		studentDTO.setAccess(student.getSelectedAccess());
		
		userDAO.addStudent(studentDTO);
		return true;
	} 
	
	@Transactional
	@Override
	public List<StudentsRecordFormBean> getStudentsRecord() throws Exception{
		logger.debug("Received request get all student records in admin service");
		long rightAttemptCount;
		long wrongAttemptCount;
		List<StudentsRecordFormBean> studentsRecord=new ArrayList<StudentsRecordFormBean>();
		List<UserDTO> studentList=userDAO.getAllStudents();
		for(UserDTO student: studentList)
		{
			rightAttemptCount=questionDAO.getRightAttemptCount(student.getUserName());
			wrongAttemptCount=questionDAO.getWrongAttemptCount(student.getUserName());
			StudentsRecordFormBean studentRecord=new StudentsRecordFormBean();
			studentRecord.setRightAttemptCount(rightAttemptCount);
			studentRecord.setStudentName(student.getName());
			studentRecord.setUserName(student.getUserName());
			studentRecord.setWrongAttemptCount(wrongAttemptCount);
			studentsRecord.add(studentRecord);
		}
		return studentsRecord;
	}
	
	@Transactional
	@Override
	public String getStudentName(String userName) throws StudentNotFoundException, Exception {
		logger.debug("Received request get a given student record in admin service");
		UserDTO user=userDAO.getThisStudent(userName);
		return user.getName();
	}
	
	@Transactional
	@Override
	public void resetUserAccount(String userName,String fullName) throws StudentNotFoundException, Exception{
		userDAO.resetBlockedAccount(userName, fullName, true);
	}
	
	
	@Transactional
	@Override
	public List<RegularQuestionsRecordFormBean> getRegularQuestionsRecord(
			String userName) throws Exception{
		logger.debug("Received request get regular questions records in admin service");
		List<RegularQuestionsRecordFormBean> regularQuestionsRecordList=new ArrayList<RegularQuestionsRecordFormBean>();
		List<AttemptsDTO> regularQuestionsListForRightAttempts=questionDAO.getQuestionsListForRightAttempts(userName,"a");
		List<AttemptsDTO> regularQuestionsListForWrongAttempts=questionDAO.getQuestionsListForWrongAttempts(userName,"a");
		for(AttemptsDTO question : regularQuestionsListForRightAttempts)
		{
			RegularQuestionsRecordFormBean regularQuestionsRecord=new RegularQuestionsRecordFormBean();
			regularQuestionsRecord.setQuestionName("$ \\documentclass[14pt]{report} $ $\\begin{document}"+question.getQuestion().getStatement()+" \\end{document} $");
			regularQuestionsRecord.setMarkedAnswer("$ \\documentclass[14pt]{report} $ $\\begin{document}"+question.getSelectedAnswer()+" \\end{document} $");
			regularQuestionsRecord.setStartTime(question.getStartTime());
			regularQuestionsRecord.setAttemptTime(question.getAttemptTime());
			regularQuestionsRecord.setResult(true);
			regularQuestionsRecordList.add(regularQuestionsRecord);
			
		}
		
		for(AttemptsDTO question : regularQuestionsListForWrongAttempts)
		{
			RegularQuestionsRecordFormBean regularQuestionsRecord=new RegularQuestionsRecordFormBean();
			
			regularQuestionsRecord.setQuestionName("$ \\documentclass[14pt]{report} $ $\\begin{document} "+question.getQuestion().getStatement()+"  \\end{document} $");
			regularQuestionsRecord.setMarkedAnswer("$ \\documentclass[14pt]{report} $ $\\begin{document} "+question.getSelectedAnswer()+"  \\end{document} $");
			regularQuestionsRecord.setStartTime(question.getStartTime());
			regularQuestionsRecord.setAttemptTime(question.getAttemptTime());
			regularQuestionsRecord.setResult(false);
			regularQuestionsRecordList.add(regularQuestionsRecord);
			
		}
		Collections.sort(regularQuestionsRecordList);
		return regularQuestionsRecordList;
	}

	@Transactional
	@Override
	public List<AdditionalQuestionsRecordFormBean> getAdditionalQuestionsRecord(
			String userName) throws Exception{
		logger.debug("Received request get additional questions records in admin service");
		List<AdditionalQuestionsRecordFormBean> additionalQuestionsRecordList=new ArrayList<AdditionalQuestionsRecordFormBean>();
		List<AttemptsDTO> additionalQuestionsListForRightAttempts=questionDAO.getQuestionsListForRightAttempts(userName,"b");
		List<AttemptsDTO> additionalQuestionsListForWrongAttempts=questionDAO.getQuestionsListForWrongAttempts(userName,"b");
		for(AttemptsDTO question : additionalQuestionsListForRightAttempts)
		{
			AdditionalQuestionsRecordFormBean additionalQuestionsRecord=new AdditionalQuestionsRecordFormBean();
			additionalQuestionsRecord.setQuestionName("$ \\documentclass[14pt]{report} $ $\\begin{document} "+question.getQuestion().getStatement()+" \\end{document} $");
			additionalQuestionsRecord.setMarkedAnswer("$ \\documentclass[14pt]{report} $ $\\begin{document} "+question.getSelectedAnswer()+" \\end{document} $");
			additionalQuestionsRecord.setStartTime(question.getStartTime());
			additionalQuestionsRecord.setAttemptTime(question.getAttemptTime());
			additionalQuestionsRecord.setResult(true);
			additionalQuestionsRecordList.add(additionalQuestionsRecord);
			
		}
		
		for(AttemptsDTO question : additionalQuestionsListForWrongAttempts)
		{
			AdditionalQuestionsRecordFormBean additionalQuestionsRecord=new AdditionalQuestionsRecordFormBean();
			
			additionalQuestionsRecord.setQuestionName("$ \\documentclass[14pt]{report} $ $\\begin{document} "+question.getQuestion().getStatement()+" \\end{document} $");
			additionalQuestionsRecord.setMarkedAnswer("$ \\documentclass[14pt]{report} $ $\\begin{document} "+question.getSelectedAnswer()+" \\end{document} $");
			additionalQuestionsRecord.setStartTime(question.getStartTime());
			additionalQuestionsRecord.setAttemptTime(question.getAttemptTime());
			additionalQuestionsRecord.setResult(false);
			additionalQuestionsRecordList.add(additionalQuestionsRecord);
			
		}
		Collections.sort(additionalQuestionsRecordList);
		return additionalQuestionsRecordList;
	}

	@Override
	public String getAdminName(String userName) throws Exception {
		logger.debug("Received request get admin name in admin service");
		UserDTO user=userDAO.fetchUserByUserName(userName);
		return user.getName();
	}

}
